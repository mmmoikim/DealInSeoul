package com.dis.dis.serviceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dis.dis.dao.HomeDAO;
import com.dis.dis.dao.RankingDAO;
import com.dis.dis.service.HomeService;
import com.dis.dis.vo.PropertyVO;
@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private HomeDAO homeDAO;
	@Autowired
	private RankingDAO rankingDAO;

	@Override
	public int getmoney(String email) {
		return homeDAO.getmoney(email);
	}
	public int getmoneywithid(String id) {
		return homeDAO.getmoneywithid(id);
	}

	@Override
	public Map<Object,Object> searchStation(Double lat, Double lon) {
		return homeDAO.searchStation(lat, lon);
	}

	@Override
	public List<Map<Object, Object>> getPropertyList(String station) {
		return homeDAO.getPropertyList(station);
	}
	
	@Override
	public void checkUpdateDay() throws ParseException{
		rankingDAO.checkUpdateDay();
	}

}
