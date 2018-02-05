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
		//������ id ��ȸ
		String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		//�ǹ� id ��ȸ
		String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
		
		
		if(stationId == null){
			Map<Object, Object> insertstation = new HashMap<Object, Object>();
			insertstation.put("station_id", info.get("STATION_ID"));
			insertstation.put("station_name", info.get("STATION_NAME"));
			//������ ���� ������ insert
			sqlSession.insert("dis.query.mapper.insertstation",insertstation);
			//�ٽ� ��ȸ
			stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		}
		
		Map<Object, Object> playergame = new HashMap<Object, Object>();
		playergame.put("salePrice", info.get("SALE_PRICE"));
		playergame.put("playerId",info.get("PLAYER_ID"));
		playergame.put("stationId",stationId);
		playergame.put("propertyID",propertyId);
		//������ �߰�
		sqlSession.insert("dis.query.mapper.insertplayergame",playergame);
		//�� ����
		sqlSession.update("dis.query.mapper.subtractmoney",playergame);
		
		//������ �ִ� �ǹ� �ŸŰ� ���ϱ�
		int sum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
		playergame.replace("SALE_PRICE", sum);
		//�ڻ� �ٲٱ�
		sqlSession.update("dis.query.mapper.calcasset",playergame);
	}
	
	@Override
	public void purchase(Map<Object, Object> info) {
		System.out.println("sssss" + info);
		//������ id ��ȸ
		String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		//�ǹ� id ��ȸ
		String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
		
		Map<Object, Object> playergame = new HashMap<Object, Object>();
		playergame.put("salePrice", info.get("SALE_PRICE"));
		playergame.put("playerId",info.get("PLAYER_ID"));
		playergame.put("stationId",stationId);
		playergame.put("propertyID",propertyId);
		//������ ����
		sqlSession.update("dis.query.mapper.changeowner", playergame);
		//�� ����
		sqlSession.update("dis.query.mapper.subtractmoney",playergame);
		//������ �ִ� �ǹ� �ŸŰ� ���ϱ�
		int sum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
		playergame.replace("SALE_PRICE", sum);
		//�� ���ڻ� �ٲٱ�
		sqlSession.update("dis.query.mapper.calcasset",playergame);
		
		
		
		playergame.replace("playerId", info.get("OWNER"));
		//������ ���ֱ�
		sqlSession.update("dis.query.mapper.refund",playergame);
	
		sum = 0;
		//�P���� �� �ŸŰ� ���ϱ�
		Object ownersum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("OWNER"));
		if(ownersum != null)
			sum = (int) ownersum;

		playergame.replace("SALE_PRICE", sum);
		//������ ���ڻ� �ٲٱ�
		sqlSession.update("dis.query.mapper.calcasset",playergame);

	}

	@Override
	public void sale(Map<Object, Object> info) {
		//������ id ��ȸ
		String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
		//�ǹ� id ��ȸ
		String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
		//property �����ϱ�
		sqlSession.selectOne("dis.query.mapper.deleteproperty",info.get("PLAYER_ID"));
		
		Map<Object, Object> playergame = new HashMap<Object, Object>();
		playergame.put("playerId", info.get("PLAYER_ID"));
		playergame.put("salePrice", info.get("SALE_PRICE"));
		
		//�� ȯ��
		sqlSession.selectOne("dis.query.mapper.refundbysale",playergame);
		
		int sum = 0;
		//������ �ִ� �ǹ� �ŸŰ� ���ϱ�
		Object obsum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
	
		if(obsum != null)
			sum = (int) obsum;
		
		playergame.replace("salePrice", sum);
		
		
		//�� �ڻ� ����
		sqlSession.update("dis.query.mapper.calcasset",playergame);
	}

	@Override
	public void invest(Map<Object, Object> info) {
				//������ id ��ȸ
				String stationId = sqlSession.selectOne("dis.query.mapper.selectstationid",info.get("STATION_NAME"));
				//�ǹ� id ��ȸ
				String propertyId = sqlSession.selectOne("dis.query.mapper.selectpropertyid",info.get("PROPERTY_NAME"));
				//�ǹ� �̸����� ���ں�� �˻�
				int investcost = sqlSession.selectOne("dis.query.mapper.selectinvestcost",propertyId);
				
				
				Map<Object, Object> playergame = new HashMap<Object, Object>();
				playergame.put("investCost", investcost);
				playergame.put("stationId", stationId);
				playergame.put("propertyId", propertyId);
				//���� Ƚ��, �� �ŸŰ� ����
				sqlSession.selectOne("dis.query.mapper.invest",playergame);
				
				playergame.put("salePrice", investcost);
				playergame.put("playerId", info.get("PLAYER_ID"));
				
				//���ӸӴ� ����
				sqlSession.update("dis.query.mapper.subtractmoney",playergame);
				
				//�� �ڻ� ����
				int sum = 0;
				//������ �ִ� �ǹ� �ŸŰ� ���ϱ�
				Object obsum = sqlSession.selectOne("dis.query.mapper.sumsaleprice",info.get("PLAYER_ID"));
				
				if(obsum != null)
					sum = (int) obsum;
				
				playergame.replace("salePrice", sum);
				//�� �ڻ� ����
				sqlSession.update("dis.query.mapper.calcasset",playergame);
	}



}
