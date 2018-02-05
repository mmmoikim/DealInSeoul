package com.dis.dis.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.dis.dis.vo.PlayerVO;

public interface PlayerInfoService {
	
	void signup(PlayerVO player);
	Map<Object,Object> login(PlayerVO player) throws ParseException;
	int changePw(String currentpw, String newpw);
	int changeEmail(PlayerVO player);
	void deleteplayer(String id);
	public int getmoney(String email);
	public int getmoneywithid(String id);
	public Map<Object,Object> searchStation(Double lat, Double lon);
	public List<Map<Object, Object>>getPropertyList(String station);
	public void checkUpdateDay() throws ParseException;
	Integer emailcheck(String email);
	Integer idcheck(String id);
	String searchpassword(String email);
	
	

}
