package com.picmap.app.reply;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {

	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "com.picmap.app.comment.CommentMapper.";

	// 답글 추가
	public int insertReply(ReplyDTO replyDTO) {
		return sqlSession.insert(NAMESPACE + "insertReply", replyDTO);
	}

	// 답글 조회
	public List<ReplyDTO> findRepliesByCommentNum(Long commentNum) {
		return sqlSession.selectList(NAMESPACE + "findRepliesByCommentNum", commentNum);
	}

	// 답글 수정
	public int updateReply(ReplyDTO replyDTO) {
		return sqlSession.update(NAMESPACE + "updateReply", replyDTO);
	}

	// 답글 삭제
	public int deleteReply(ReplyDTO replyDTO) {
		return sqlSession.delete(NAMESPACE + "deleteReply", replyDTO);
	}
}
