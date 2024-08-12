package com.picmap.app.heart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/heart/")
public class HeartController {
	@Autowired
	private HeartService heartService;

	@GetMapping("click")
	@ResponseBody
	public Integer heartClick(HeartDTO heartDTO, HttpSession session) throws Exception {
		return heartService.heartClick(heartDTO, session);
	}

	@GetMapping("check")
	@ResponseBody
	public Integer heartCheck(HeartDTO heartDTO, HttpSession session) throws Exception {
		return heartService.heartCheck(heartDTO, session);
	}

	@GetMapping("count")
	@ResponseBody
	public Long heartCount(HeartDTO heartDTO) throws Exception {
		return heartService.heartCount(heartDTO);
	}
}
