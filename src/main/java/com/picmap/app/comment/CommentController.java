package com.picmap.app.comment;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.picmap.app.comments.CommentsDTO;
import com.picmap.app.member.MemberDTO;

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
	// 댓글을 추가하는 메서드입니다 (로그인한 사용자만 가능).
	@PostMapping("/add")
	public String addComment(@RequestParam Long boardNum, @RequestParam String content, HttpSession session, Model model) throws Exception {
	    // 세션에서 로그인한 사용자의 정보를 가져옵니다.
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
	    // 사용자의 회원 번호를 가져옵니다.
	    Long memberNum = memberDTO.getMemberNum();

	    // CommentsDTO 객체를 생성하고 데이터를 설정합니다.
	    CommentsDTO commentsDTO = new CommentsDTO();
	    commentsDTO.setBoardNum(boardNum);
	    commentsDTO.setMemberNum(memberNum);
	    commentsDTO.setContent(content);

	    // 서비스 계층을 호출하여 댓글을 추가합니다.
	    commentService.addComment(commentsDTO);

	    // 댓글 목록 페이지로 리다이렉트합니다.
	    return "redirect:/comments/list?boardNum=" + boardNum;
	}
	
	// 댓글을 수정하는 메서드입니다 (자신의 댓글만 수정 가능).
    @PostMapping("/update")
    public String updateComment(@RequestParam Long commentNum, @RequestParam Long boardNum, @RequestParam String content, HttpSession session, Model model) throws Exception {
        // 세션에서 로그인한 사용자의 정보를 가져옵니다.
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        // 사용자의 회원 번호를 가져옵니다.
        Long memberNum = memberDTO.getMemberNum();

        // 서비스 계층을 호출하여 댓글을 수정합니다.
        CommentDTO updatedComment = commentService.updateComment(commentNum, memberNum, content);
        // 수정된 댓글을 모델에 추가하여 뷰로 전달합니다.
        model.addAttribute("comment", updatedComment);

        // 결과 페이지로 이동합니다.
        return "redirect:/comments/list?boardNum=" + boardNum;
    }
    
    // 댓글을 삭제하는 메서드입니다 (자신의 댓글만 삭제 가능).
    @PostMapping("/delete")
    public String deleteComment(@RequestParam Long commentNum, @RequestParam Long boardNum, HttpSession session, Model model) throws Exception {
        // 세션에서 로그인한 사용자의 정보를 가져옵니다.
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        // 사용자의 회원 번호를 가져옵니다.
        Long memberNum = memberDTO.getMemberNum();

        // 서비스 계층을 호출하여 댓글을 삭제합니다.
        commentService.deleteComment(commentNum, memberNum);
        // 삭제 결과 메시지를 모델에 추가하여 뷰로 전달합니다.
        model.addAttribute("msg", "댓글이 삭제되었습니다.");

        // 결과 페이지로 이동합니다.
        return "redirect:/comments/list?boardNum=" + boardNum;
    }

}
