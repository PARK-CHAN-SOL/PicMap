package com.picmap.app.travel;

import com.picmap.app.board.BoardDTO;

public class TravelDTO extends BoardDTO{
	private Long heartCount;
	private Long rootBoard;
	private Long parentBoard;
	private Long childBoard;
	private Long pingNum;
	private String writeDate; //createDate에 년,월,일을 붙인 값
	
	public Long getHeartCount() {
		return heartCount;
	}
	public void setHeartCount(Long heartCount) {
		this.heartCount = heartCount;
	}
	public Long getRootBoard() {
		return rootBoard;
	}
	public void setRootBoard(Long rootBoard) {
		this.rootBoard = rootBoard;
	}
	public Long getParentBoard() {
		return parentBoard;
	}
	public void setParentBoard(Long parentBoard) {
		this.parentBoard = parentBoard;
	}
	public Long getChildBoard() {
		return childBoard;
	}
	public void setChildBoard(Long childBoard) {
		this.childBoard = childBoard;
	}
	public Long getPingNum() {
		return pingNum;
	}
	public void setPingNum(Long pingNum) {
		this.pingNum = pingNum;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
}
