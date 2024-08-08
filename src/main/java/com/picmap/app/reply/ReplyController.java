package com.picmap.app.reply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.picmap.app.member.MemberDTO;

@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@PostMapping("/list")
	public String getReplies(@RequestParam Long commentNum, Model model) {
		List<ReplyDTO> replies = replyService.getRepliesByCommentNum(commentNum);
		model.addAttribute("replies", replies);
		model.addAttribute("commentNum", commentNum);
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

}
