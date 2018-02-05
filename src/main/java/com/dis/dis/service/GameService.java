package com.dis.dis.service;

import java.util.List;
import java.util.Map;

public interface GameService {
	void firstpurchase(Map<Object,Object> info);
	void purchase(Map<Object,Object> info);
	void sale(Map<Object,Object> info);
	void invest(Map<Object,Object> info);
	public List<Map<Object, Object>> getPropertyList(String station);
	public int getmoney(String email);
}
