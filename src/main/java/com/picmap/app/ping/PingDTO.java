package com.picmap.app.ping;

public class PingDTO {
	private Long pingNum;
	private Long latitude;
	private Long longitude;
	private String address;
	public Long getPingNum() {
		return pingNum;
	}
	public void setPingNum(Long pingNum) {
		this.pingNum = pingNum;
	}
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	public Long getLongitude() {
		return longitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
