package com.picmap.app.comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.comments.CommentsDTO;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSession sqlSession; // MyBatis의 SqlSession 객체를 주입받습니다.

	private final String NAMESPACE = "com.picmap.app.comment.CommentMapper."; // MyBatis 매퍼의 네임스페이스를 정의합니다.

	// 특정 게시글의 전체 댓글 수를 조회하는 메서드
	public Long getTotalCount(CommentsDTO commentsDTO) throws Exception {
		// MyBatis를 사용하여 데이터베이스에서 특정 게시글의 댓글 수를 조회함
		return sqlSession.selectOne(NAMESPACE + "getTotalCount", commentsDTO);
	}

	// 특정 게시글의 댓글 목록을 조회하는 메서드
	public List<CommentDTO> getCommentsByBoard(Map<String, Object> map) throws Exception {
		// MyBatis를 사용하여 데이터베이스에서 게시글 번호를 기준으로 댓글 목록을 조회함
		return sqlSession.selectList(NAMESPACE + "findByBoardNum", map);
	}

	// 특정 댓글을 ID로 조회하는 메서드
	public CommentDTO getCommentById(Long commentNum) throws Exception {
		// MyBatis를 사용하여 데이터베이스에서 댓글 ID를 기준으로 댓글을 조회함
		return sqlSession.selectOne(NAMESPACE + "findById", commentNum);
	}

	// 새로운 댓글을 추가하는 메서드
	public int addComment(CommentsDTO commentsDTO) throws Exception {
		// MyBatis를 사용하여 데이터베이스에 새로운 댓글을 삽입함
		return sqlSession.insert(NAMESPACE + "insertComment", commentsDTO);
	}

	// 댓글을 수정하는 메서드
	public int updateComment(CommentDTO commentDTO) throws Exception {
		// MyBatis를 사용하여 데이터베이스에서 기존 댓글을 수정함
		return sqlSession.update(NAMESPACE + "updateComment", commentDTO);
	}

	// 댓글을 삭제하는 메서드
	public int deleteComment(CommentDTO commentDTO) throws Exception {
		// MyBatis를 사용하여 데이터베이스에서 댓글을 삭제함
		return sqlSession.delete(NAMESPACE + "deleteComment", commentDTO);
	}
	
	public Long countReply(CommentDTO commentDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "countReply", commentDTO);
	}
}
