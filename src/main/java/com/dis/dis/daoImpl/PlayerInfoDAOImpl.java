package com.dis.dis.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dis.dis.dao.PlayerInfoDAO;
import com.dis.dis.vo.PlayerVO;

import domain.Lottery;

@Repository
public class PlayerInfoDAOImpl implements PlayerInfoDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void signup(PlayerVO player) {
		System.out.println("PlayerInfoDAO - signup");
		System.out.println(player);
		player.setTitle("6");
		sqlSession.insert("dis.query.mapper.insertplayer", player);
	}
	@Override
	public Map<Object,Object> login(PlayerVO player) throws ParseException {
		System.out.println("PlayerInfoDAO - login : " + player);
		
		Map<Object,Object> map = sqlSession.selectOne("dis.query.mapper.login", player);
		int lottery = 0;
		System.out.println(map);
		if(map != null)
		{
		if(checkaccess(map))
		{
			lottery = new Lottery().issueLottery();
			player.addMoney(lottery);
			Map<Object, Object> setassetqeury = new HashMap<Object,Object>();
			setassetqeury.put("lottery", lottery);
			setassetqeury.put("email", player.getEmail());
			sqlSession.update("dis.query.mapper.setassetbymoney",setassetqeury);
		}
		
		player.updateAccessDate();//vo에 accessdate 업데이트
		System.out.println("바뀐 player" + player);
		if(lottery != 0)
		sqlSession.update("dis.query.mapper.setmoney", player);//db에 money 갱신
		
		sqlSession.update("dis.query.mapper.access", player);//db에 accessdata 갱신
	
		map = sqlSession.selectOne("dis.query.mapper.login", player);
		map.put("lottery", String.valueOf(lottery));
		String id = (String) map.get("PLAYER_ID");
		String title = sqlSession.selectOne("dis.query.mapper.gettitle",id);
		map.put("title",title);
		}
		return map;
	}
	@Override
	public int changePw(String currentpw, String newpw) {
		System.out.println("PlayerInfoDAO - changePw");
		Map<String, String> m = new HashMap<String,String>();
		m.put("currentpw", currentpw);
		m.put("newpw", newpw);
		
		return sqlSession.update("dis.query.mapper.changepw",m);
		
	}
	@Override
	public int changeEmail(PlayerVO player) {
		System.out.println("PlayerInfoDAO - changeEmail");
		return sqlSession.update("dis.query.mapper.changeemail", player);
		
	}
	
	public boolean checkaccess(Map map) throws ParseException{
		
		String latestStr = (String) map.get("ACCESSDATE");//로긴 날짜 업데이트 하기전
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date latestDate = transFormat.parse(latestStr);//최근접속일
			Date todayDate = Calendar.getInstance().getTime();//오늘
			
			//시간 0시로 셋팅
			todayDate.setHours(0);
			todayDate.setMinutes(0);
			todayDate.setSeconds(0);
			
			if(latestDate.before(todayDate)){
				return true;
			}
		return false;
	}
	@Override
	public void deleteplayer(String id) {
		int check=sqlSession.selectOne("dis.query.mapper.deletecheck",id);
		if(check<1){
			return;
		}
		sqlSession.update("dis.query.mapper.deleteplayergame",id);
		sqlSession.update("dis.query.mapper.deleteplayer",id);
		
	}
	public Integer emailcheck(String email){
		return sqlSession.selectOne("dis.query.mapper.emailC",email);
	}
	public Integer idcheck(String id){
		return sqlSession.selectOne("dis.query.mapper.idC",id);
	}
	
	@Override
	public String searchpassword(String email){
		return sqlSession.selectOne("dis.query.mapper.searchpassword",email);
	}

}
