package com.picmap.app.savepost;

public class SavePostDTO {
	private Long savePostNum;
	private Long memberNum;
	private Long boardNum;
	public Long getSavePostNum() {
		return savePostNum;
	}
	public void setSavePostNum(Long savePostNum) {
		this.savePostNum = savePostNum;
	}
	public Long getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}
	public Long getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(Long boardNum) {
		this.boardNum = boardNum;
	}
}
