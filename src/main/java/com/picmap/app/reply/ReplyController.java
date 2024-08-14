package com.picmap.app.reply;

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

import com.picmap.app.follow.FollowDTO;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.member.MemberService;
import com.picmap.app.util.Scroller;

@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@Autowired
	private MemberService memberService; // MemberService 주입

	@PostMapping("/list")
	public String getReplies(@RequestParam Long commentNum, @RequestParam int startRow, Model model) {
		Scroller scroller = new Scroller();
		scroller.setStartRow((long) startRow);
		scroller.setEndRow(scroller.getStartRow() + 9);

		List<ReplyDTO> replies = replyService.getRepliesByCommentNum(commentNum, scroller);
		model.addAttribute("replies", replies);
		model.addAttribute("commentNum", commentNum);
		model.addAttribute("nextStartRow", scroller.getEndRow() + 1);

		return "replies/list";
	}

	@PostMapping("/add")
	public String addReply(@RequestParam Long commentNum, @RequestParam String content, HttpSession session,
			Model model) throws Exception {
		// 세션에서 로그인한 사용자의 정보를 가져옵니다.
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		Long memberNum = memberDTO.getMemberNum();

		// ReplyDTO 객체를 생성하고 데이터를 설정합니다.
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setCommentNum(commentNum);
		replyDTO.setMemberNum(memberNum);
		replyDTO.setContent(content);

		int result = replyService.addReply(replyDTO);
		if (result <= 0) {
			model.addAttribute("msg", "fail");
		} else {
			model.addAttribute("msg", "success");
		}

		return "commons/result";
	}

	@PostMapping("/update")
	public String updateReply(@RequestParam Long replyNum, @RequestParam String content, HttpSession session,
			Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		Long memberNum = memberDTO.getMemberNum();

		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setReplyNum(replyNum);
		replyDTO.setMemberNum(memberNum);
		replyDTO.setContent(content);

		int result = replyService.updateReply(replyDTO);
		if (result <= 0) {
			model.addAttribute("msg", "fail");
		} else {
			model.addAttribute("msg", "success");
		}

		return "commons/result";
	}

	@PostMapping("/delete")
	public String deleteReply(@RequestParam Long replyNum, HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		Long memberNum = memberDTO.getMemberNum();

		int result = replyService.deleteReply(replyNum, memberNum);
		if (result <= 0) {
			model.addAttribute("msg", "fail");
		} else {
			model.addAttribute("msg", "success");
		}

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
