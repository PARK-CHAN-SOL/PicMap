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
import org.springframework.web.bind.annotation.ResponseBody;

import com.picmap.app.comments.CommentsDTO;
import com.picmap.app.follow.FollowDTO;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.member.MemberService;
import com.picmap.app.util.Scroller;

@Controller
@RequestMapping("/comments") // 컨트롤러의 기본 URL 경로 설정
public class CommentController {
	@Autowired
	private CommentService commentService; // CommentService 클래스의 객체를 주입받음

	@Autowired
	private MemberService memberService; // MemberService 주입

	// 특정 게시글의 댓글 목록을 조회하는 메서드
	@PostMapping("/list")
	@ResponseBody // 이 메서드는 JSON 또는 XML 형식으로 데이터를 반환함
	public List<CommentDTO> getCommentsByBoard(CommentsDTO commentsDTO, Model model, Scroller scroller,
			HttpSession session) throws Exception {
		// 서비스 계층을 호출하여 댓글 목록을 조회함
		List<CommentDTO> comments = commentService.getCommentsByBoard(commentsDTO, scroller);
		return comments; // 조회된 댓글 목록을 반환함
	}

	// 댓글 페이지를 반환하는 메서드 (뷰 페이지로 이동)
	@GetMapping("/list")
	public void getCommentsPage(Model model, HttpSession session) throws Exception {
		// 세션에서 로그인한 사용자 정보를 가져옴
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		model.addAttribute("member", member); // 로그인한 사용자 정보를 모델에 추가함
	}

	// 특정 게시글의 전체 댓글 수를 조회하는 메서드
	@GetMapping("getTotalCount")
	@ResponseBody // 이 메서드는 JSON 또는 XML 형식으로 데이터를 반환함
	public Long getTotalCount(CommentsDTO commentsDTO) throws Exception {
		// 서비스 계층을 호출하여 특정 게시글의 댓글 수를 반환함
		return commentService.getTotalCount(commentsDTO);
	}

	// 댓글을 추가하는 메서드 (로그인한 사용자만 가능)
	@PostMapping("/add")
	public String addComment(@RequestParam Long boardNum, @RequestParam String content, HttpSession session,
			Model model) throws Exception {
		// 세션에서 로그인한 사용자 정보를 가져옴
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO == null) {
			return "로그인이 필요합니다."; // 로그인하지 않은 경우
		}
		Long memberNum = memberDTO.getMemberNum();
		if (memberNum == null) {
			return "회원 정보가 잘못되었습니다."; // 회원 정보가 없는 경우
		}

		// 새로운 댓글 DTO 객체 생성 및 데이터 설정
		CommentsDTO commentsDTO = new CommentsDTO();
		commentsDTO.setBoardNum(boardNum);
		commentsDTO.setMemberNum(memberNum);
		commentsDTO.setContent(content);

		// 서비스 계층을 호출하여 댓글을 추가함
		int result = commentService.addComment(commentsDTO);
		if (result <= 0) {
			model.addAttribute("msg", "fail"); // 댓글 추가 실패
		} else {
			model.addAttribute("msg", "success"); // 댓글 추가 성공
		}

		// 결과를 반환할 페이지로 이동
		return "commons/result";
	}

	// 댓글을 수정하는 메서드 (자신의 댓글만 수정 가능)
	@PostMapping("/update")
	public String updateComment(@RequestParam Long commentNum, @RequestParam String content, HttpSession session,
			Model model) throws Exception {
		// 세션에서 로그인한 사용자 정보를 가져옴
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO == null) {
			return "로그인이 필요합니다."; // 로그인하지 않은 경우
		}
		Long memberNum = memberDTO.getMemberNum();
		if (memberNum == null) {
			return "회원 정보가 잘못되었습니다."; // 회원 정보가 없는 경우
		}

		// 서비스 계층을 호출하여 댓글을 수정함
		CommentDTO updatedComment = commentService.updateComment(commentNum, memberNum, content);
		if (updatedComment == null) {
			model.addAttribute("msg", "fail"); // 댓글 수정 실패
		} else {
			model.addAttribute("msg", "success"); // 댓글 수정 성공
		}
		// 결과를 반환할 페이지로 이동
		return "commons/result";
	}

	// 댓글을 삭제하는 메서드 (자신의 댓글만 삭제 가능)
	@PostMapping("/delete")
	public String deleteComment(@RequestParam Long commentNum, HttpSession session, Model model) throws Exception {
		// 세션에서 로그인한 사용자 정보를 가져옴
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO == null) {
			return "로그인이 필요합니다."; // 로그인하지 않은 경우
		}
		Long memberNum = memberDTO.getMemberNum();
		if (memberNum == null) {
			return "회원 정보가 잘못되었습니다."; // 회원 정보가 없는 경우
		}

		// 서비스 계층을 호출하여 댓글을 삭제함
		int result = commentService.deleteComment(commentNum, memberNum);
		if (result <= 0) {
			model.addAttribute("msg", "fail"); // 댓글 삭제 실패
		} else {
			model.addAttribute("msg", "success"); // 댓글 삭제 성공
		}

		// 결과를 반환할 페이지로 이동
		return "commons/result";
	}

	// 팔로우 기능 가져오기
	@GetMapping("/followCheck")
	@ResponseBody
	public Integer followCheck(@RequestParam("toFollow") Long toFollow, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			FollowDTO followDTO = new FollowDTO();
			followDTO.setFromFollow(memberDTO.getMemberNum());
			followDTO.setToFollow(toFollow);
			return memberService.followCheck(followDTO);
		} else {
			return 0;
		}
	}

}
