package com.picmap.app.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.comments.CommentsDTO;
import com.picmap.app.heartComments.HeartCommentsDAO;
import com.picmap.app.util.Scroller;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAO; // CommentDAO 객체를 주입받습니다.
	
	@Autowired
	private HeartCommentsDAO heartCommentDAO;

	// 특정 게시글의 전체 댓글 수를 조회하는 메서드
	public Long getTotalCount(CommentsDTO commentsDTO) throws Exception {
		// DAO를 호출하여 댓글 수를 조회하고 반환합니다.
		return commentDAO.getTotalCount(commentsDTO);
	}

	// 특정 게시글의 댓글 목록을 조회하는 메서드
	public List<CommentDTO> getCommentsByBoard(CommentsDTO commentsDTO, Scroller scroller) throws Exception {
		// 댓글 목록을 조회하기 위한 파라미터를 담을 맵 객체를 생성합니다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentsDTO", commentsDTO); // commentsDTO 객체를 맵에 추가합니다.
		map.put("scroller", scroller); // Scroller 객체를 맵에 추가합니다.

		// DAO를 호출하여 댓글 목록을 조회하고 반환합니다.
		return commentDAO.getCommentsByBoard(map);
	}

	// 새로운 댓글을 추가하는 메서드
	public int addComment(CommentsDTO commentsDTO) throws Exception {
		// DAO를 호출하여 새로운 댓글을 추가합니다.
		return commentDAO.addComment(commentsDTO);
	}

	// 댓글을 수정하는 메서드 (자신의 댓글만 수정 가능)
	public CommentDTO updateComment(Long commentNum, Long memberNum, String content) throws Exception {
		// 수정할 댓글 정보를 담을 CommentDTO 객체를 생성합니다.
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setCommentNum(commentNum); // 댓글 ID 설정
		commentDTO.setMemberNum(memberNum); // 댓글 작성자 ID 설정
		commentDTO.setContent(content); // 새로운 댓글 내용 설정

		// DAO를 호출하여 댓글을 수정합니다.
		commentDAO.updateComment(commentDTO);

		// 수정된 댓글 객체를 반환합니다.
		return commentDAO.getCommentById(commentNum);
	}

	// 댓글을 삭제하는 메서드 (자신의 댓글만 삭제 가능)
	public int deleteComment(Long commentNum, Long memberNum) throws Exception {
		// 삭제할 댓글 정보를 담을 CommentDTO 객체를 생성합니다.
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setCommentNum(commentNum); // 댓글 ID 설정
		commentDTO.setMemberNum(memberNum); // 댓글 작성자 ID 설정
		
		Long replyCount = commentDAO.countReply(commentDTO);
		if(replyCount != 0L) {
			return 0;
		}
		
		heartCommentDAO.heartCommentsDelete(commentDTO.getCommentNum());

		// DAO를 호출하여 댓글을 삭제합니다.
		return commentDAO.deleteComment(commentDTO);
	}

}
