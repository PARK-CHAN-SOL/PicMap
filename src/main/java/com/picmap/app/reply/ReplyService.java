package com.picmap.app.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.util.Scroller;

@Service
public class ReplyService {

	@Autowired
	private ReplyDAO replyDAO;

	public int addReply(ReplyDTO replyDTO) {
		return replyDAO.insertReply(replyDTO);
	}

	public List<ReplyDTO> getRepliesByCommentNum(Long commentNum, Scroller scroller) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentNum", commentNum);
		map.put("scroller", scroller);

		return replyDAO.findRepliesByCommentNum(map);
	}

	public int updateReply(ReplyDTO replyDTO) {
		return replyDAO.updateReply(replyDTO);
	}

	public int deleteReply(Long replyNum, Long memberNum) {
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setReplyNum(replyNum);
		replyDTO.setMemberNum(memberNum);

		return replyDAO.deleteReply(replyDTO);
	}

}
