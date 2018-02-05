package com.dis.dis.dao;

import java.util.Map;

public interface GameDAO {
	void firstpurchase(Map<Object,Object> info);
	void purchase(Map<Object,Object> info);
	void sale(Map<Object,Object> info);
	void invest(Map<Object,Object> info);
}
