package com.picmap.app.kakaomember;

public class KakaoMemberDTO {
	private Long kakaoMemberNum;
	private String kakaoMemberToken;
	private Long memberNum;
	public Long getKakaoMemberNum() {
		return kakaoMemberNum;
	}
	public void setKakaoMemberNum(Long kakaoMemberNum) {
		this.kakaoMemberNum = kakaoMemberNum;
	}
	public String getKakaoMemberToken() {
		return kakaoMemberToken;
	}
	public void setKakaoMemberToken(String kakaoMemberToken) {
		this.kakaoMemberToken = kakaoMemberToken;
	}
	public Long getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}
}
