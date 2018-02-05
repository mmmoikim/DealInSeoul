package com.dis.dis.daoImpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dis.dis.dao.GameDAO;
@Repository
public class GameDAOImpl implements GameDAO {
	@Autowired
	SqlSession sqlSession;
	@Override
	public void firstpurchase(Map<Object, Object> info) {
		System.out.println(info);
		//정류장 id 조회
		String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		//건물 id 조회
		String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
		
		
		if(stationId == null){
			Map<Object, Object> insertstation = new HashMap<Object, Object>();
			insertstation.put("station_id", info.get("STATION_ID"));
			insertstation.put("station_name", info.get("STATION_NAME"));
			//정류장 정보 없으면 insert
			sqlSession.insert("dis.query.mapper.insertstation",insertstation);
			//다시 조회
			stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		}
		
		Map<Object, Object> playergame = new HashMap<Object, Object>();
		playergame.put("salePrice", info.get("SALE_PRICE"));
		playergame.put("playerId",info.get("PLAYER_ID"));
		playergame.put("stationId",stationId);
		playergame.put("propertyID",propertyId);
		//소유자 추가
		sqlSession.insert("dis.query.mapper.insertplayergame",playergame);
		//돈 빼기
		sqlSession.update("dis.query.mapper.subtractmoney",playergame);
		
		//가지고 있는 건물 매매가 더하기
		int sum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
		playergame.replace("SALE_PRICE", sum);
		//자산 바꾸기
		sqlSession.update("dis.query.mapper.calcasset",playergame);
	}
	
	@Override
	public void purchase(Map<Object, Object> info) {
		System.out.println("sssss" + info);
		//정류장 id 조회
		String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		//건물 id 조회
		String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
		
		Map<Object, Object> playergame = new HashMap<Object, Object>();
		playergame.put("salePrice", info.get("SALE_PRICE"));
		playergame.put("playerId",info.get("PLAYER_ID"));
		playergame.put("stationId",stationId);
		playergame.put("propertyID",propertyId);
		//소유자 변경
		sqlSession.update("dis.query.mapper.changeowner", playergame);
		//돈 빼기
		sqlSession.update("dis.query.mapper.subtractmoney",playergame);
		//가지고 있는 건물 매매가 더하기
		int sum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
		playergame.replace("SALE_PRICE", sum);
		//내 총자산 바꾸기
		sqlSession.update("dis.query.mapper.calcasset",playergame);
		
		
		
		playergame.replace("playerId", info.get("OWNER"));
		//뺏긴사람 돈주기
		sqlSession.update("dis.query.mapper.refund",playergame);
	
		sum = 0;
		//뻇긴사람 총 매매가 더하기
		Object ownersum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("OWNER"));
		if(ownersum != null)
			sum = (int) ownersum;

		playergame.replace("SALE_PRICE", sum);
		//뺏긴사람 총자산 바꾸기
		sqlSession.update("dis.query.mapper.calcasset",playergame);

	}

	@Override
	public void sale(Map<Object, Object> info) {
		//정류장 id 조회
		String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		//건물 id 조회
		String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
		//property 삭제하기
		sqlSession.selectOne("dis.query.mapper.deleteproperty",info.get("PLAYER_ID"));
		
		Map<Object, Object> playergame = new HashMap<Object, Object>();
		playergame.put("playerId", info.get("PLAYER_ID"));
		playergame.put("salePrice", info.get("SALE_PRICE"));
		
		//돈 환불
		sqlSession.selectOne("dis.query.mapper.refundbysale",playergame);
		
		int sum = 0;
		//가지고 있는 건물 매매가 더하기
		Object obsum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
	
		if(obsum != null)
			sum = (int) obsum;
		
		playergame.replace("salePrice", sum);
		
		
		//총 자산 갱신
		sqlSession.update("dis.query.mapper.calcasset",playergame);
	}

	@Override
	public void invest(Map<Object, Object> info) {
				//정류장 id 조회
				String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
				//건물 id 조회
				String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
				//건물 이름으로 투자비용 검색
				int investcost = sqlSession.selectOne("dis.query.mapper.selectinvestcost",propertyId);
				
				
				Map<Object, Object> playergame = new HashMap<Object, Object>();
				playergame.put("investCost", investcost);
				playergame.put("stationId", stationId);
				playergame.put("propertyId", propertyId);
				//투자 횟수, 총 매매가 증가
				sqlSession.selectOne("dis.query.mapper.invest",playergame);
				
				playergame.put("salePrice", investcost);
				playergame.put("playerId", info.get("PLAYER_ID"));
				
				//게임머니 빼기
				sqlSession.update("dis.query.mapper.subtractmoney",playergame);
				
				//총 자산 갱신
				int sum = 0;
				//가지고 있는 건물 매매가 더하기
				Object obsum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
				
				if(obsum != null)
					sum = (int) obsum;
				
				playergame.replace("salePrice", sum);
				//총 자산 갱신
				sqlSession.update("dis.query.mapper.calcasset",playergame);
	}



}
