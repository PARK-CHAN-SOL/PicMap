package com.picmap.app.comment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.picmap.app.comments.CommentsDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentDAOTest extends SampleTest {

    @Autowired
    private CommentDAO commentDAO;

    @Test
    public void testAddComment() throws Exception {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setBoardNum(1L);
        commentsDTO.setMemberNum(1L);
        commentsDTO.setContent("This is a test comment");

        int result = commentDAO.addComment(commentsDTO);
        assertEquals(1, result);
    }

    @Test
    public void testGetCommentsByBoard() throws Exception {
        List<CommentDTO> comments = commentDAO.getCommentsByBoard(1L);
        assertNotNull(comments);
        assertEquals(comments.size() >= 0, true);
    }

    @Test
    public void testUpdateComment() throws Exception {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentNum(4L);
        commentDTO.setMemberNum(1L);
        commentDTO.setContent("Updated content");

        int result = commentDAO.updateComment(commentDTO);
        assertEquals(1, result);
    }

    @Test
    public void testDeleteComment() throws Exception {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentNum(2L);         //존재하는id로
        commentDTO.setMemberNum(1L);

        int result = commentDAO.deleteComment(commentDTO);
        assertEquals(1, result);
    }

    @Test
    public void testGetCommentById() throws Exception {
        CommentDTO comment = commentDAO.getCommentById(4L);
        assertNotNull(comment);
    }
}
