package com.dis.dis.dao;

import java.util.List;
import java.util.Map;

import com.dis.dis.vo.PropertyVO;

public interface HomeDAO {
	public int getmoney(String email);
	public int getmoneywithid(String id);
	public Map<Object,Object> searchStation(Double lat, Double lon);
	public List<Map<Object, Object>> getPropertyList(String station);

}
