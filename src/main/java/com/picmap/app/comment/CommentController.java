package com.picmap.app.comment;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.picmap.app.comments.CommentsDTO;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.reply.ReplyService;

@Controller
@RequestMapping("/comments") //컨트롤러 기본 URL 경로 설정	
public class CommentController {
	@Autowired
	private CommentService commentService;
	
		
	// 특정 게시글의 댓글 목록을 조회합니다.
	@GetMapping("/list")
	public String getCommentsByBoard(@RequestParam Long boardNum, Model model, HttpSession session) throws Exception {
	    List<CommentDTO> comments = commentService.getCommentsByBoard(boardNum); // 서비스 계층을 호출하여 댓글 목록을 조회합니다.
	    model.addAttribute("comments", comments); // 조회된 댓글 목록을 모델에 추가합니다.
	    model.addAttribute("boardNum", boardNum); // 게시글 번호를 모델에 추가합니다.
	    MemberDTO member = (MemberDTO) session.getAttribute("member"); // 세션에서 로그인한 사용자 정보를 가져옵니다.
	    model.addAttribute("member", member); // 로그인한 사용자 정보를 모델에 추가합니다.
	    return "comments/list"; // 댓글 목록 페이지로 이동합니다.
  }
	// 댓글을 추가하는 메서드입니다 (로그인한 사용자만 가능).
	@PostMapping("/add")
	public ResponseEntity<String> addComment(@RequestParam Long boardNum, @RequestParam String content, HttpSession session, Model model) throws Exception {
        // 세션에서 로그인한 사용자의 정보를 가져옵니다.
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if (memberDTO == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        Long memberNum = memberDTO.getMemberNum();
        if (memberNum == null) {
            return ResponseEntity.status(400).body("회원 정보가 잘못되었습니다.");
        }

        // CommentsDTO 객체를 생성하고 데이터를 설정합니다.
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setBoardNum(boardNum);
        commentsDTO.setMemberNum(memberNum);
        commentsDTO.setContent(content);

        int result = commentService.addComment(commentsDTO);
        if (result <= 0) {
            throw new Exception("댓글 추가 실패");
        }

        return ResponseEntity.ok("success");
    }

	
	// 댓글을 수정하는 메서드입니다 (자신의 댓글만 수정 가능).
	@PostMapping("/update")
	public ResponseEntity<String> updateComment(@RequestParam Long commentNum, @RequestParam Long boardNum, @RequestParam String content, HttpSession session) throws Exception {
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
	    if (memberDTO == null) {
	        return ResponseEntity.status(401).body("로그인이 필요합니다.");
	    }
	    Long memberNum = memberDTO.getMemberNum();
	    if (memberNum == null) {
	        return ResponseEntity.status(400).body("회원 정보가 잘못되었습니다.");
	    }

	    // 서비스 계층을 호출하여 댓글을 수정합니다.
	    CommentDTO updatedComment = commentService.updateComment(commentNum, memberNum, content);
	    if (updatedComment == null) {
	        return ResponseEntity.status(500).body("댓글 수정 실패");
	    }

	    return ResponseEntity.ok("success");
	}
    
    // 댓글을 삭제하는 메서드입니다 (자신의 댓글만 삭제 가능).
	@PostMapping("/delete")
	public ResponseEntity<String> deleteComment(@RequestParam Long commentNum, HttpSession session) throws Exception {
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
	    if (memberDTO == null) {
	        return ResponseEntity.status(401).body("로그인이 필요합니다.");
	    }
	    Long memberNum = memberDTO.getMemberNum();
	    if (memberNum == null) {
	        return ResponseEntity.status(400).body("회원 정보가 잘못되었습니다.");
	    }

	    int result = commentService.deleteComment(commentNum, memberNum);
	    if (result <= 0) {
	        throw new Exception("댓글 삭제 실패");
	    }

	    return ResponseEntity.ok("success");
	}

}
