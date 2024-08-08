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

import com.picmap.app.member.MemberDTO;

@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@GetMapping("/list")
	public String getReplies(@RequestParam Long commentNum, Model model, HttpSession session) {
		List<ReplyDTO> replies = replyService.getRepliesByCommentNum(commentNum);
		model.addAttribute("replies", replies);
		model.addAttribute("commentNum", commentNum);
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		model.addAttribute("member", member);
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

}
