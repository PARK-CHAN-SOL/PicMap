package com.picmap.app.heartComments;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeartCommentsDAO {
	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "com.picmap.app.heartComments.HeartCommentsDAO.";

	// 댓글 좋아요 확인
	public Long heartCommentsCheck(HeartCommentsDTO heartCommentsDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "heartCommentsCheck", heartCommentsDTO);
	}

	// 댓글 좋아요 추가
	public int heartCommentsUp(HeartCommentsDTO heartCommentsDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "heartCommentsUp", heartCommentsDTO);
	}

	// 댓글 좋아요 삭제
	public int heartCommentsDown(HeartCommentsDTO heartCommentsDTO) throws Exception {
		return sqlSession.delete(NAMESPACE + "heartCommentsDown", heartCommentsDTO);
	}

	// 댓글 좋아요 수 카운트
	public Long heartCommentsCount(Long commentNum) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "heartCommentsCount", commentNum);
	}
}
