package com.dis.dis.service;

import java.util.List;
import java.util.Map;

import com.dis.dis.vo.PlayerVO;
import com.dis.dis.vo.PropertyVO;

public interface RankingService {
	
	public List<Map<Object, Object>>getRanking(int asset);
	public void setRankinglineup();
}
