package com.picmap.app.board;

import com.picmap.app.files.FileDTO;

public class BoardFileDTO extends FileDTO {

	private Long boardNum;

	public Long getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(Long boardNum) {
		this.boardNum = boardNum;
	}
}