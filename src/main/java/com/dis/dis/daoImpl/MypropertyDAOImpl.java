package com.dis.dis.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dis.dis.dao.MypropertyDAO;
@Repository
public class MypropertyDAOImpl implements MypropertyDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Map<Object, Object> getmyproperty(String playerId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		//총자산
		result.put("asset", sqlSession.selectOne("dis.query.mapper.getasset",playerId));
		
		List<Map<Object,Object>> propertylistid = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> propertylistname = new ArrayList<Map<Object,Object>>();
		//건물 리스트
		propertylistid = sqlSession.selectList("dis.query.mapper.getmypropertylist", playerId);
		
		for(int i=0; i<propertylistid.size(); i++){
			Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("station",
					sqlSession.selectOne("dis.query.mapper.selectstationname",
							propertylistid.get(i).get("STATION_ID")));
			map.put("property",
					sqlSession.selectOne("dis.query.mapper.selectpropertyname",
			propertylistid.get(i).get("PROPERTY_ID")));
			
			map.put("saleprice", propertylistid.get(i).get("SALE_PRICE"));
			propertylistname.add(map);
		}
		result.put("propertyList", propertylistname);
		System.out.println(result);
		
		return result;
	}

}
