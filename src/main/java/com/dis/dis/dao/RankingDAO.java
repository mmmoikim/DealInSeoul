package com.dis.dis.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface RankingDAO {

	List<Map<Object, Object>> getRanking(int asset);
	void setRankinglineup();
	void checkUpdateDay() throws ParseException;
}
