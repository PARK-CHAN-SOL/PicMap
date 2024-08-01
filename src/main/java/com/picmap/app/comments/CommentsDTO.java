package com.picmap.app.comments;

import com.picmap.app.comment.CommentDTO;

public class CommentsDTO extends CommentDTO{

	private Long boardNum;

	public Long getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(Long boardNum) {
		this.boardNum = boardNum;
	}

}
