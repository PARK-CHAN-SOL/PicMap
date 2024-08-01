package com.picmap.app.reply;

import com.picmap.app.comment.CommentDTO;

public class ReplyDTO extends CommentDTO {
	private Long replyNum;

	public Long getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Long replyNum) {
		this.replyNum = replyNum;
	}
}
