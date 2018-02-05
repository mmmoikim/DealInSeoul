package com.dis.dis.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dis.dis.dao.RankingDAO;
import com.dis.dis.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService{
	@Autowired
	private RankingDAO rankingDAO;

	@Override
	public List<Map<Object, Object>> getRanking(int asset) {
		System.out.println("Ranking ServiceImpl");
	return rankingDAO.getRanking(asset);
	}
	@Override
	public void setRankinglineup() {
		System.out.println("Rankinglineup ServiceImpl");
		rankingDAO.setRankinglineup();
	}
}
