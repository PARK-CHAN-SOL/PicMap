package com.picmap.app.member;

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
import org.springframework.web.multipart.MultipartFile;



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
public String login(Model model, MemberDTO memberDTO, String remember, HttpServletResponse response,HttpSession session) throws Exception {
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


	//회원가입

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
	//로그아웃
@RequestMapping(value = "logout", method = RequestMethod.GET)
public String logout(HttpSession session) {
	session.invalidate(); 
	return "redirect:/";
}
	
	
	
	
	
}
