package com.dis.dis.daoImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.dis.dis.dao.HomeDAO;
import com.dis.dis.vo.PropertyVO;
import com.dis.dis.vo.StationVO;

import domain.StationHandler;
@Repository
public class HomeDAOImpl implements HomeDAO {
	@Autowired
	SqlSession sqlSession;
	
	private int houseInitValue  = 1000;
	private int apartmentInitValue  = 3000;
	private int buildingInitValue = 15000;
	
	@Override
	public int getmoney(String email) {
		return sqlSession.selectOne("dis.query.mapper.getmoney", email);
	}

	@Override
	public Map<Object,Object> searchStation(Double lat, Double lon) {
		Map<Object,Object> result = new HashMap<Object, Object>();
		try {
			URL url = new URL("http://ws.bus.go.kr/api/rest/stationinfo/getStationByPos?ServiceKey=yfn7jTXhZKKlowkPlzVXqOww4YUBAm7y9xeQuJhDXOSyNsnw8F2yvy93ovVCmnKOO8nvZ6YgAhz0%2FXp8GSdCSg%3D%3D&tmX="+lon+"&tmY="+lat+"&radius=300");
			
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			XMLReader xr = sp.getXMLReader();

			StationHandler handler =new StationHandler();
			xr.setContentHandler(handler);
			xr.parse(new InputSource(url.openStream()));
			
			StationVO vo = new StationVO();
			if(handler.getParsedData() != null){
			vo = handler.getParsedData();
			 result.put("stationName", vo.getStationNm());
			 result.put("stationId", vo.getArsId());
			 return result;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<Object, Object>> getPropertyList(String station) {
		List<Map<Object,Object>> result = new ArrayList();
		List<Map<Object,Object>> list = sqlSession.selectList("dis.query.mapper.getpropertylist", station);
		System.out.println(list);
		if(!list.isEmpty())
		for(int i=0; i<list.size(); i++){
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("PROPERTY_NAME", list.get(i).get("PROPERTY_NAME"));//이름
			map.put("PLAYER_ID", list.get(i).get("PLAYER_ID"));//소유주
			map.put("INVEST_COUNT", list.get(i).get("INVEST_COUNT"));//투자횟수
			map.put("STATION_NAME", list.get(i).get("STATION_NAME"));//정류장
			
			String propertyName = (String) list.get(i).get("PROPERTY_NAME");
			int inverstCount = Integer.parseInt(String.valueOf(list.get(i).get("INVEST_COUNT")));
			int investCost = 0;
			switch (propertyName) {
			case "주택":
				investCost = (int) (inverstCount * (houseInitValue*0.1));
				map.put("INVEST_COST", investCost);//투자 비용
				map.put("SALE_PRICE", (Math.round((investCost + houseInitValue)/100.0)*100));//매매가
				break;
			case "아파트":
				investCost = (int) (inverstCount * (apartmentInitValue*0.1));
				map.put("INVEST_COST", investCost);//투자 비용
				map.put("SALE_PRICE", (Math.round((investCost + apartmentInitValue)/100.0)*100));//매매가
				break;
			case "빌딩":
				investCost = (int) (inverstCount * (buildingInitValue*0.1));
				map.put("INVEST_COST", investCost);//투자 비용
				map.put("SALE_PRICE", (Math.round((investCost + buildingInitValue)/100.0)*100));//매매가
				break;
			}
			result.add(map);
		}
		return result;
	}

	@Override
	public int getmoneywithid(String id) {
		return sqlSession.selectOne("dis.query.mapper.getmoneywithid", id);
	}
	

}
