package com.dis.dis.vo;

import org.springframework.stereotype.Component;
@Component
public class StationVO {
	String arsId;
	String stationNm;
	public String getArsId() {
		return arsId;
	}
	public void setArsId(String arsId) {
		this.arsId = arsId;
	}
	public String getStationNm() {
		return stationNm;
	}
	public void setStationNm(String stationNm) {
		this.stationNm = stationNm;
	}
	@Override
	public String toString() {
		return "StationVO [arsId=" + arsId + ", stationNm=" + stationNm + "]";
	}
	
	
}
