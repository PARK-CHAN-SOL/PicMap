package com.picmap.app.comment;

import com.picmap.app.comments.CommentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO; // CommentDAO 객체를 주입받습니다.

    // 특정 게시글의 댓글 목록을 조회합니다.
    public List<CommentDTO> getCommentsByBoard(Long boardNum) throws Exception {
        // DAO를 호출하여 댓글 목록을 조회하고 반환합니다.
        return commentDAO.getCommentsByBoard(boardNum);
    }

    // 새로운 댓글을 추가합니다.
    public int addComment(CommentsDTO commentsDTO) throws Exception {
        // DAO를 호출하여 댓글을 추가합니다.
        return commentDAO.addComment(commentsDTO);
    }


    // 댓글을 수정합니다 (자신의 댓글만 수정 가능).
    public CommentDTO updateComment(Long commentNum, Long memberNum, String content) throws Exception {
        // 수정할 댓글 객체를 생성하고 데이터를 설정합니다.
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentNum(commentNum);
        commentDTO.setMemberNum(memberNum);
        commentDTO.setContent(content);
        // DAO를 호출하여 댓글을 수정합니다.
        commentDAO.updateComment(commentDTO);
        // 수정된 댓글 객체를 반환합니다.
        return commentDAO.getCommentById(commentNum);
    }

    // 댓글을 삭제합니다 (자신의 댓글만 삭제 가능).
    public void deleteComment(Long commentNum, Long memberNum) throws Exception {
        // 삭제할 댓글 객체를 생성하고 데이터를 설정합니다.
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentNum(commentNum);
        commentDTO.setMemberNum(memberNum);
        // DAO를 호출하여 댓글을 삭제합니다.
        commentDAO.deleteComment(commentDTO);
    }
}
