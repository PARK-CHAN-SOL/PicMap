package com.picmap.app.ping;

public class PingDTO {
	private Long pingNum;
	private Double latitude;
	private Double longitude;
	private String address;
	public Long getPingNum() {
		return pingNum;
	}
	public void setPingNum(Long pingNum) {
		this.pingNum = pingNum;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


}
