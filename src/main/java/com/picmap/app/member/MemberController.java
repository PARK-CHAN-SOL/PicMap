package com.picmap.app.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member/*")
public class MemberController {
@Autowired
	private MemberService memberService;
	
@RequestMapping(value = "Modal", method = RequestMethod.GET)
public void Modal() throws Exception {

}
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
	if (memberDTO != null) {
		session.setAttribute("member", memberDTO);
	} 
	return "redirect:/";
}
	
	
	
	
	
	
	
	
	
}
