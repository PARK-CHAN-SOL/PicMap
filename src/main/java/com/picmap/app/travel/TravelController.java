package com.picmap.app.travel;

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

@Controller
@RequestMapping("/travel/*")
public class TravelController {
	
	@Autowired
	private TravelService travelService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "Travel";
	}
	
	
	@GetMapping("list")
	public String getList(Model model) throws Exception {
		
		List<BoardDTO> list = travelService.getList();
				
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	
	
	@GetMapping("add")
	public String add() throws Exception {
		
		return "board/write";
	}
	@PostMapping("add")
	public void add(HttpSession session, MultipartFile files, TravelDTO travelDTO) throws Exception {
		
	}
	
	
	
	@GetMapping("update")
	public String update() throws Exception {
		
		return "board/write";
	}
	@PostMapping("update")
	public void update(HttpSession session) throws Exception {
		
	}
	
	
	
	
	
	
}
