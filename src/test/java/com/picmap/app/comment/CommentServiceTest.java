package com.picmap.app.comment;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.picmap.app.comments.CommentsDTO;

public class CommentServiceTest extends SampleTest {
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private CommentDAO commentDAO;
    
    private CommentsDTO comment;
    
    @Before
    public void setUp() throws Exception {
        // 테스트에 사용할 CommentsDTO 객체를 초기화합니다.
        comment = new CommentsDTO();
        comment.setBoardNum(1L); // 예시 게시글 번호 설정
        comment.setMemberNum(1L); // 예시 회원 번호 설정
        comment.setContent("Test comment"); // 댓글 내용 설정
    }
    
    @Test
    public void testAddComment() throws Exception {
        // 새로운 댓글을 추가하는 테스트 메서드
        int result = commentDAO.addComment(comment);
        assertEquals(1, result); // 댓글 추가가 성공하면 result는 1이어야 합니다.
    }
    
    @Test
    public void testUpdateComment() throws Exception {
        // 댓글을 수정하는 테스트 메서드
        comment.setCommentNum(6L); // 수정할 댓글 번호 설정
        comment.setContent("Updated content"); // 수정할 댓글 내용 설정
        int result = commentDAO.updateComment(comment);
        assertEquals(1, result); // 댓글 수정이 성공하면 result는 1이어야 합니다.
    }
    
    @Test
    public void testDeleteComment() throws Exception {
        // 댓글을 삭제하는 테스트 메서드
        comment.setCommentNum(6L); // 삭제할 댓글 번호 설정
        int result = commentDAO.deleteComment(comment);
        assertEquals(1, result); // 댓글 삭제가 성공하면 result는 1이어야 합니다.
    }
}
