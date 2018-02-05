package com.dis.dis.serviceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dis.dis.dao.HomeDAO;
import com.dis.dis.dao.PlayerInfoDAO;
import com.dis.dis.dao.RankingDAO;
import com.dis.dis.service.PlayerInfoService;
import com.dis.dis.vo.PlayerVO;

@Service
public class PlayerInfoServiceImpl implements PlayerInfoService {

	@Autowired
	private PlayerInfoDAO playerInfoDAO;
	@Autowired
	private HomeDAO homeDAO;
	@Autowired
	private RankingDAO rankingDAO;

	@Override
	public void signup(PlayerVO player) {
		System.out.println("PlayerInfoService - signup");
		playerInfoDAO.signup(player);
	}

	@Override
	public Map<Object,Object> login(PlayerVO player) throws ParseException {
		System.out.println("PlayerInfoService - login");
		return playerInfoDAO.login(player);
	}

	@Override
	public int changePw(String currentpw, String newpw) {
		System.out.println("PlayerInfoService - changePw");
		return playerInfoDAO.changePw(currentpw, newpw);
	}

	@Override
	public int changeEmail(PlayerVO player) {
		System.out.println("PlayerInfoService - changeEmail");
		return playerInfoDAO.changeEmail(player);
		
	}
	@Override
	public void deleteplayer(String id) {
		System.out.println("PlayerInfoService - deleteplayer");
		playerInfoDAO.deleteplayer(id);
	}
	
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
	@Override
	public Integer idcheck(String id){
		return playerInfoDAO.idcheck(id);
	}

	@Override
	public Integer emailcheck(String email) {
		return playerInfoDAO.emailcheck(email);
	}
	
	@Override
	public String searchpassword(String email){
		return playerInfoDAO.searchpassword(email);
	}

}
