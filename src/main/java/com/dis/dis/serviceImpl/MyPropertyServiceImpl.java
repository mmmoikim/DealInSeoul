package com.dis.dis.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dis.dis.dao.MypropertyDAO;
import com.dis.dis.service.MyPropertyService;
@Service
public class MyPropertyServiceImpl implements MyPropertyService {
	@Autowired
	private MypropertyDAO myPropertyDAO;
	@Override
	public Map<Object, Object> getmyproperty(String playerId) {
		// TODO Auto-generated method stub
		return myPropertyDAO.getmyproperty(playerId);
	}

}
