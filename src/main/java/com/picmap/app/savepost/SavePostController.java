package com.picmap.app.savepost;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.picmap.app.member.MemberDTO;
import com.picmap.app.travel.TravelDTO;
import com.picmap.app.util.Scroller;

@Controller
@RequestMapping("/savepost/")
public class SavePostController {
	@Autowired
	private SavePostService savePostService;

	@GetMapping("click")
	@ResponseBody
	public Integer savePostClick(SavePostDTO savePostDTO, HttpSession session) throws Exception {
		return savePostService.savePostClick(savePostDTO, session);
	}

	@GetMapping("check")
	@ResponseBody
	public Integer savePostCheck(SavePostDTO savePostDTO, HttpSession session) throws Exception {
		return savePostService.savePostCheck(savePostDTO, session);
	}
	@PostMapping("list")
	@ResponseBody
	public List<TravelDTO> savePostList(SavePostDTO savePostDTO, Scroller scroller) throws Exception {
		return savePostService.savePostList(savePostDTO, scroller);
	}
}
