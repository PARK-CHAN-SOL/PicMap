package com.picmap.app.follow;

public class FollowDTO {
	private Long followNum;
	private Long fromFollow;
	private Long toFollow;
	public Long getFollowNum() {
		return followNum;
	}
	public void setFollowNum(Long followNum) {
		this.followNum = followNum;
	}
	public Long getFromFollow() {
		return fromFollow;
	}
	public void setFromFollow(Long fromFollow) {
		this.fromFollow = fromFollow;
	}
	public Long getToFollow() {
		return toFollow;
	}
	public void setToFollow(Long toFollow) {
		this.toFollow = toFollow;
	}
}
