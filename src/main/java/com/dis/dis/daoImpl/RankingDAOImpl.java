package com.dis.dis.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dis.dis.dao.RankingDAO;

@Repository
public class RankingDAOImpl implements RankingDAO {
	@Autowired
	SqlSession sqlSession;

	private String checkUpdateDay = "2015/02/02 00:00:00";
	
	@Override
	public List<Map<Object, Object>> getRanking(int asset) {
		System.out.println("Ranking RankingDAOImpl");
		int playerRank = (int) (sqlSession.selectList("dis.query.mapper.getplayerrank", asset).get(0));
		int count = (int) (sqlSession.selectList("dis.query.mapper.getplayercount").get(0));
		List<Map<Object,Object>> result = new ArrayList();
		List<Map<Object,Object>> list = sqlSession.selectList("dis.query.mapper.getrankinglist");
		for(int i=0; i<list.size(); i++){
			if(playerRank-10<= i && playerRank+10>= i){
				Map<Object,Object> map = new HashMap<Object,Object>();
				map.put("RANK", list.get(i).get("RANK"));//이름
				map.put("PLAYER_ID", list.get(i).get("PLAYER_ID"));//플레이어_ID
				map.put("TITLE_ID", list.get(i).get("TITLE_ID"));//타이틀
				map.put("ASSET", list.get(i).get("ASSET"));//총 자산
				map.put("PERCENT", ((double)(playerRank))/((double)(count)));
				result.add(map);
			}
		}
		
		return result;
	}
	
	@Override
	public void checkUpdateDay() throws ParseException{
		System.out.println("4");
		int flag = 0;
		String latestStr = checkUpdateDay;//로긴 날짜 업데이트 하기전
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date latestDate = transFormat.parse(latestStr);//최근접속일
		Date todayDate = Calendar.getInstance().getTime();//오늘
		
		//시간 0시로 셋팅
		todayDate.setHours(0);
		todayDate.setMinutes(0);
		todayDate.setSeconds(0);
		
		while (true) {
			if(latestDate.before(todayDate)){
				System.out.println(checkUpdateDay);
				Calendar cal = Calendar.getInstance();
		        cal.setTime(latestDate);
		        cal.add(Calendar.DATE, 7);
		        latestDate = cal.getTime();
		        flag = 1;
		        System.out.println("flag = "+ flag);
				System.out.println("check = " + latestDate);
			}
			else{
				break;
			}
		}
		if (flag == 1){
			checkUpdateDay = transFormat.format(latestDate);
			System.out.println(checkUpdateDay);
			flag = 0;
			setRankinglineup();
		}
	}
	
	@Override
	public void setRankinglineup(){
		System.out.println("Rankinglineup RankingDAOImpl");
		double percent = 0;
		int count = (int) (sqlSession.selectList("dis.query.mapper.getplayercount").get(0));
		List<Map<Object,Object>> list = sqlSession.selectList("dis.query.mapper.getrankinglist");
		
		Map<Object,Object> updatetitle = new HashMap<Object, Object>();
		for(int i=0; i<list.size(); i++){
			percent = 100-Math.floor(((double)(i+1))/((double)(count))*100);
			
			updatetitle.put("titleId", getTitle(percent));
			updatetitle.put("money", (int)getMoney(percent));
			updatetitle.put("asset", (int)(getMoney(percent))/2);
			updatetitle.put("playerId", list.get(i).get("PLAYER_ID"));
			sqlSession.update("dis.query.mapper.updatetitle",updatetitle);
			// sqlSession.delete("dis.query.mapper.deleteplayergameD"); // 게임 건물 정보 초기화 Test를 위해 주석처리
		}
	}//setRankinglineup
	
	public String getTitle(double percent){
		String titleId = null;
		if (percent >= 0 && percent < 20){
			titleId = "6";
		}
		else if(percent >= 20 && percent < 50){
			titleId = "5";
		}
		else if(percent >= 50 && percent < 70){
			titleId = "4";
		}
		else if(percent >= 70 && percent < 90){
			titleId = "3";
		}
		else if(percent >= 90 && percent < 97){
			titleId = "2";
		}
		else if(percent >= 97 && percent <= 100){
			titleId = "1";
		}
		return titleId;
	}
	public int getMoney(double percent){
		int money = 0;
		if (percent >= 0 && percent < 20){
			money = 500;
		}
		else if(percent >= 20 && percent < 50){
			money = 700;
		}
		else if(percent >= 50 && percent < 70){
			money = 1000;
		}
		else if(percent >= 70 && percent < 90){
			money = 1300;
		}
		else if(percent >= 90 && percent < 97){
			money = 1800;
		}
		else if(percent >= 97 && percent <= 100){
			money = 2500;
		}
		return money;
	}

}
