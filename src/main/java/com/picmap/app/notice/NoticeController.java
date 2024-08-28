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
import com.picmap.app.heart.HeartDTO;
import com.picmap.app.heart.HeartService;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.util.Pager;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private HeartService heartService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "Notice";
	}
	
	
	
	@GetMapping("list")
	public String getList(Model model, Pager pager) throws Exception {
		
		List<BoardDTO> list = noticeService.getList(pager);
		
		for (BoardDTO boardDTO : list) {
		    System.out.println(boardDTO);
		}
				
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
		
		String boardTitleTmp = noticeDTO.getBoardTitle();
		boardTitleTmp = boardTitleTmp.replaceAll("<", "&lt");
		boardTitleTmp = boardTitleTmp.replaceAll(">", "&gt");
		noticeDTO.setBoardTitle(boardTitleTmp);
		
		int result = noticeService.add(noticeDTO, files, session);
		
		return "redirect:./list";
	}
	
	
	
	@GetMapping("update")
	public String update(Model model, NoticeDTO noticeDTO) throws Exception {
		noticeDTO = noticeService.getDetail(noticeDTO);
		model.addAttribute("dto", noticeDTO);
		return "board/write";
	}
	@PostMapping("update")
	public String update(NoticeDTO noticeDTO, MultipartFile[] files, HttpSession session) throws Exception {
		String boardTitleTmp = noticeDTO.getBoardTitle();
		boardTitleTmp = boardTitleTmp.replaceAll("<", "&lt");
		boardTitleTmp = boardTitleTmp.replaceAll(">", "&gt");
		noticeDTO.setBoardTitle(boardTitleTmp);
		
		noticeService.update(noticeDTO, files, session);
		
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(NoticeDTO noticeDTO, Model model, HttpSession session) throws Exception {
		
		//조회수
		noticeService.hit(noticeDTO);
		
		
		noticeDTO = noticeService.getDetail(noticeDTO);
		model.addAttribute("dto", noticeDTO);
		//좋아요
		HeartDTO heartDTO = new HeartDTO();
		heartDTO.setBoardNum(noticeDTO.getBoardNum());
		Long heartCount = heartService.noticeHeartCount(heartDTO);
		model.addAttribute("heart", heartCount);
		
		//게시글 수정 및 삭제, 자식글 작성할 때 작성자의 계정과 일치하는 계정인지 판별하는 필터를 걸어두기 위해
		//작성자 memberNum을 세션에 올림
		session.setAttribute("writer", noticeDTO.getMemberNum());
		
		return "/board/detail";
	}
	
	@PostMapping("delete")
	public String delete(NoticeDTO noticeDTO) throws Exception {
		Integer result = noticeService.delete(noticeDTO);
		return "redirect:./list";
	}
	
	
	

}
