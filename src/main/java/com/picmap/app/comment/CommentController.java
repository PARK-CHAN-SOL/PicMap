package com.picmap.app.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments") //컨트롤러 기본 URL 경로 설정	
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	// 특정 게시글의 댓글 목록을 조회합니다.
	@GetMapping("/list")
	public String getCommentsByBoard(@RequestParam Long boardNum, Model model) throws Exception {
	    List<CommentDTO> comments = commentService.getCommentsByBoard(boardNum); // 서비스 계층을 호출하여 댓글 목록을 조회합니다.
	    model.addAttribute("comments", comments); // 조회된 댓글 목록을 모델에 추가합니다.
	    return "comments/list"; // 댓글 목록 페이지로 이동합니다.
	}
	
	

}
