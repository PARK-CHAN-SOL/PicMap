package com.picmap.app.member;

import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.follow.FollowDTO;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("memberNickName")
	public String memberNickName(MemberDTO memberDTO, Model model) throws Exception {
		int result = memberService.memberNickName(memberDTO);
		model.addAttribute("msg", result);
		return "/commons/result";
	}

	@GetMapping("idCheck")
	public String idCheck(MemberDTO memberDTO, Model model) throws Exception {
		int result = memberService.idCheck(memberDTO);
		model.addAttribute("msg", result);
		return "/commons/result";
	}

//로그인
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(Model model, @CookieValue(name = "remember ", required = false, defaultValue = "") String value)
			throws Exception {
		model.addAttribute("id", value);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model, MemberDTO memberDTO, String remember, HttpServletResponse response,
			HttpSession session) throws Exception {
		if (remember != null) {
			Cookie cookie = new Cookie("remember", memberDTO.getMemberId());
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("remember", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		memberDTO = memberService.login(memberDTO);
		String url = "/commons/message";

		if (memberDTO != null) {

			session.setAttribute("member", memberDTO);
			model.addAttribute("result", "로그인 성공");
			model.addAttribute("url", "/");

		} else {
			model.addAttribute("result", "로그인 실패");
			model.addAttribute("url", "/");
		}

		return url;

	}

	// 회원가입

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void join() {
	}

	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(MemberDTO memberDTO, MultipartFile files, HttpSession session) throws Exception {

		int result = memberService.join(memberDTO, files, session);
		String url = "";
		if (result > 0) {
			url = "redirect:/";
		}
		return url;
	}

	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

//마이페이지
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public void mypage(MemberDTO memberDTO, Model model, HttpSession session) throws Exception {
		memberDTO = memberService.detail(memberDTO);

		Long following = memberService.fromFollow(memberDTO);
		Long follower = memberService.toFollow(memberDTO);
		model.addAttribute("member", memberDTO);
		model.addAttribute("follower", follower);
		model.addAttribute("following", following);
		MemberDTO myDTO = (MemberDTO) session.getAttribute("member");

		if (myDTO != null) {
			FollowDTO followDTO = new FollowDTO();
			followDTO.setFromFollow(myDTO.getMemberNum());
			followDTO.setToFollow(memberDTO.getMemberNum());
			int followCheck = memberService.followCheck(followDTO);
			System.out.println(followCheck);
			model.addAttribute("followCheck", followCheck);
		}

	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		memberDTO = memberService.detail(memberDTO);
		model.addAttribute("member", memberDTO);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(MemberDTO memberDTO, MultipartFile files, HttpSession session, Model model) throws Exception {
		MemberDTO dtoTmp = (MemberDTO) session.getAttribute("member");
		memberDTO.setMemberPassword(dtoTmp.getMemberPassword());
		memberDTO.setMemberId(dtoTmp.getMemberId());

		int num = memberService.update(memberDTO, files, session);

		return "redirect:/";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model, HttpSession httpSession) throws Exception {
		MemberDTO dto = (MemberDTO) httpSession.getAttribute("member");
		int num = memberService.delete(dto);
		if (num > 0) {
			model.addAttribute("result", "계정이 삭제되었습니다.");
			model.addAttribute("url", "/");
			httpSession.setAttribute("member", null);
		} else {
			model.addAttribute("result", "계정 삭제실패.");
			model.addAttribute("url", "/");
		}
		return "/commons/message";
	}

	@GetMapping("follow")
	public String follow(FollowDTO followDTO, HttpSession session, Model model) throws Exception {
		int result = memberService.follow(followDTO, session);
		model.addAttribute("msg", result);
		return "commons/result";
	}
	@GetMapping("fromFollowList")
	@ResponseBody
	public List<MemberDTO> fromFollowList(FollowDTO followDTO) throws Exception{
		List<MemberDTO>fromFollowList =memberService.fromFollowList(followDTO);
		return fromFollowList;
	}
	@GetMapping("toFollowList")
	@ResponseBody
	public List<MemberDTO> toFollowList(FollowDTO followDTO) throws Exception{
		List<MemberDTO>toFollowList =memberService.toFollowList(followDTO);
		return toFollowList;
	}
	@GetMapping("followCheck")
	@ResponseBody
	public Integer followCheck(FollowDTO followDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		if(memberDTO != null) {
			followDTO.setFromFollow(memberDTO.getMemberNum());			
			return memberService.followCheck(followDTO);
		} else {
			return 0;
		}
		
	}
}
