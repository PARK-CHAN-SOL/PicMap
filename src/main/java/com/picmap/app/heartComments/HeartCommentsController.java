package com.picmap.app.heartComments;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/heartComments/")
public class HeartCommentsController {
	@Autowired
	private HeartCommentsService heartCommentsService;

	// 좋아요 클릭 처리
	@GetMapping("click")
	@ResponseBody
	public Integer heartCommentsClick(HeartCommentsDTO heartCommentsDTO, HttpSession session) throws Exception {
		return heartCommentsService.heartCommentsClick(heartCommentsDTO, session);
	}

	// 좋아요 수 카운트
	@GetMapping("count")
	@ResponseBody
	public Long heartCommentsCount(Long commentNum) throws Exception {
		return heartCommentsService.heartCommentsCount(commentNum);
	}

	// 좋아요 상태 체크
	@GetMapping("check")
	@ResponseBody
	public Integer heartCommentsCheck(HeartCommentsDTO heartCommentsDTO, HttpSession session) throws Exception {
		return heartCommentsService.heartCommentsCheck(heartCommentsDTO, session);
	}
}
