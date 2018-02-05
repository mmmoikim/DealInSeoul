package com.dis.dis.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class PlayerVO {
	private String id;
	private String pw;
	private String email;
	private int asset;
	private int money;
	private String accessDate;
	private String title;
	
	public PlayerVO(){}
	
	
	public PlayerVO(String email, String pw){
		this("",  pw, email, 0, 0, "1970/01/01 09:00:00","");
	}
	
	public PlayerVO(String id, String pw, String email){
		this( id,  pw, email, 0, 0, "1970/01/01 09:00:00","");
	}
	public PlayerVO(String id,String email,int asset, int money,String accessDate){
		this( id, "", email, asset, money, accessDate, "");
	}
	
	public PlayerVO(String id, String pw, String email, int asset, int money,String accessDate, String title) {
		setId(id);
		setPw(pw);
		setEmail(email);
		setAsset(asset);
		setMoney(money);
		setAccessDate(accessDate);
		setTitle(title);
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAsset() {
		return asset;
	}
	public void setAsset(int asset) {
		this.asset = asset;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void addMoney(int money){
		setMoney(this.money + money);
	}
	public String getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}
	public void updateAccessDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		this.accessDate = sdf.format(Calendar.getInstance().getTime());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessDate == null) ? 0 : accessDate.hashCode());
		result = prime * result + asset;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + money;
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
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
		PlayerVO other = (PlayerVO) obj;
		if (accessDate == null) {
			if (other.accessDate != null)
				return false;
		} else if (!accessDate.equals(other.accessDate))
			return false;
		if (asset != other.asset)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (money != other.money)
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlayerVO [id=" + id + ", pw=" + pw + ", email=" + email
				+ ", asset=" + asset + ", money=" + money + ", accessDate="
				+ accessDate + "]";
	}
	

}
