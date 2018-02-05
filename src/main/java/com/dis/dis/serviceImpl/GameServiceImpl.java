package com.dis.dis.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dis.dis.dao.GameDAO;
import com.dis.dis.dao.HomeDAO;
import com.dis.dis.service.GameService;
@Service
public class GameServiceImpl implements GameService {
	@Autowired
	private GameDAO gameDAO;
	@Autowired
	private HomeDAO homeDAO;
	
	@Override
	public void firstpurchase(Map<Object, Object> info) {
		gameDAO.firstpurchase(info);
		
	}
	
	@Override
	public void purchase(Map<Object, Object> info) {
		gameDAO.purchase(info);

	}

	@Override
	public void sale(Map<Object, Object> info) {
		gameDAO.sale(info);

	}

	@Override
	public void invest(Map<Object, Object> info) {
		gameDAO.invest(info);

	}
	
	@Override
	public List<Map<Object, Object>> getPropertyList(String station) {
		return homeDAO.getPropertyList(station);
	}
	
	@Override
	public int getmoney(String email) {
		return homeDAO.getmoney(email);
	}



}
