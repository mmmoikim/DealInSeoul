package com.dis.dis.vo;

public class PropertyVO {
	private String property;//건물이름
	private String station;//정류장
	private int salePrice;//매매가
	private int investCount;//투자횟수
	private int investPrice;//투자가격
	private String owner;//소유주
	private String state;//상태
	
	public PropertyVO(){}
	
	public PropertyVO(String property, String station, int salePrice){
		this(property, station, salePrice, 0, 0, "", "");
	}
	
	public PropertyVO(String property, String station, int salePrice,
			int investCount, int investPrice, String owner, String state) {
		setProperty(property);
		setStation(station);
		setSalePrice(salePrice);
		setInvestCount(investCount);
		setInvestPrice(investPrice);
		setOwner(owner);
		setState(state);
		
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getInvestCount() {
		return investCount;
	}

	public void setInvestCount(int investCount) {
		this.investCount = investCount;
	}

	public int getInvestPrice() {
		return investPrice;
	}

	public void setInvestPrice(int investPrice) {
		this.investPrice = investPrice;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + investCount;
		result = prime * result + investPrice;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		result = prime * result + salePrice;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyVO other = (PropertyVO) obj;
		if (investCount != other.investCount)
			return false;
		if (investPrice != other.investPrice)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (salePrice != other.salePrice)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PropertyVO [property=" + property + ", station=" + station
				+ ", salePrice=" + salePrice + ", investCount=" + investCount
				+ ", investPrice=" + investPrice + ", owner=" + owner
				+ ", state=" + state + "]";
	}
	
}
