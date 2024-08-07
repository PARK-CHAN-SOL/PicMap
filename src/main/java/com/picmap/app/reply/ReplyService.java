package com.picmap.app.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyDAO replyDAO;
	
	public int addReply(ReplyDTO replyDTO) {
		return replyDAO.insertReply(replyDTO);
	}
	
	public List<ReplyDTO> getRepliesByCommentNum(Long commentNum) {
        return replyDAO.findRepliesByCommentNum(commentNum);
    }

}
