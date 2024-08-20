package com.picmap.app.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.member.MemberDTO;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "Notice";
	}
	
	
	
	@GetMapping("list")
	public String getList(Model model) throws Exception {
		
		List<BoardDTO> list = noticeService.getList();
				
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	
	
	@GetMapping("add")
	public String add() throws Exception {
		
		return "board/write";
		
	}
	@PostMapping("add")
	public String add(NoticeDTO noticeDTO, MultipartFile[] files, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		noticeDTO.setMemberNum(memberDTO.getMemberNum());
		
		int result = noticeService.add(noticeDTO, files, session);
		
		return "redirect:./list";
	}
	
	
	
	@GetMapping("update")
	public String update() throws Exception {
		
		return "board/write";
	}
	@PostMapping("update")
	public void update(HttpSession session) throws Exception {
		
	}
	
	@GetMapping("detail")
	public void getDetail(NoticeDTO noticeDTO, Model model) throws Exception {
		noticeDTO = noticeService.getDetail(noticeDTO);
		model.addAttribute("dto", noticeDTO);
	}
	
	
	

}
