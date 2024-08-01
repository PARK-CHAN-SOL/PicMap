package com.picmap.app.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.comment.CommentMapper.";
	
	//특정 게시글 댓글 목록 조회
	public List<CommentDTO> getCommentsByBoard(Long boardNum) throws Exception {
	   
	    return sqlSession.selectList(NAMESPACE + "findByBoardNum", boardNum);
	}
	
	// 특정 댓글을 ID로 조회
	public CommentDTO getCommentById(Long commentNum) throws Exception {
	    
	    return sqlSession.selectOne(NAMESPACE + "findById", commentNum);
	}
	
	// 새로운 댓글을 추가합니다.
	public int addComment(CommentDTO commentDTO) throws Exception {
	    
	    return sqlSession.insert(NAMESPACE + "insertComment", commentDTO);
	}
	
	// 댓글을 수정합니다.
	public int updateComment(CommentDTO commentDTO) throws Exception {
	    
	    return sqlSession.update(NAMESPACE + "updateComment", commentDTO);
	}
	
	// 댓글을 삭제합니다.
	public int deleteComment(CommentDTO commentDTO) throws Exception {
	   
	    return sqlSession.delete(NAMESPACE + "deleteComment", commentDTO);
	}

}
