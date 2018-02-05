package com.dis.dis.dao;

import java.text.ParseException;
import java.util.Map;

import com.dis.dis.vo.PlayerVO;

public interface PlayerInfoDAO {
	void signup(PlayerVO player);
	Map<Object,Object> login(PlayerVO player) throws ParseException;
	int changePw(String currentpw, String newpw);
	int changeEmail(PlayerVO player);
	void deleteplayer(String id);	
	Integer emailcheck(String email);
	Integer idcheck(String id);
	String searchpassword(String email);
}
