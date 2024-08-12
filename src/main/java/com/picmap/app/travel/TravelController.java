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
import com.picmap.app.member.MemberDTO;
import com.picmap.app.member.MemberService;
import com.picmap.app.ping.PingDTO;
import com.picmap.app.ping.PingService;

@Controller
@RequestMapping("/travel/*")
public class TravelController {
	
	@Autowired
	private TravelService travelService;
	@Autowired
	private PingService pingService;
	@Autowired
	private MemberService memberService;
	
	
	@ModelAttribute("board")
	public String getBoard() {
		return "Travel";
	}
	
	
	@GetMapping("list")
	public String getList(Model model) throws Exception {
		
		List<BoardDTO> list = travelService.getList();
				
		model.addAttribute("list", list);
		
		return "board/travel/list";
	}
	
	
	//게시글 작성(최상위 부모글)
	@GetMapping("add")
	public String add() throws Exception {
		
		return "board/travel/write";
	}
	@PostMapping("add")
	public String add(TravelDTO travelDTO, MultipartFile[] files, HttpSession session, PingDTO pingDTO) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		travelDTO.setMemberNum(memberDTO.getMemberNum());
		
		// 1. 게시글번호를 매기고,
		Long boardNum = travelService.makeBoardNum();
		travelDTO.setBoardNum(boardNum);
		// 2. 최상위부모게시글을 자신으로 설정, 자식글,부모글은 일단 Null
		travelDTO.setRootBoard(boardNum);

		// 3. 위치정보 추가
		Long pingNum = pingService.savePingNum();
		travelDTO.setPingNum(pingNum);
		pingDTO.setPingNum(pingNum);
		pingService.addPing(pingDTO);
		
		//4. 게시글 추가
		int result = travelService.add(travelDTO, files, session);
		
		return "redirect:./list";
	}
	
	
	
	//자식글 작성
	@GetMapping("addPlus")
	public String addPlus(TravelDTO travelDTO, Model model) throws Exception {
		
		model.addAttribute("dto", travelDTO);
		
		return "board/travel/write";
	}
	@PostMapping("addPlus")
	public String addPlus(TravelDTO travelDTO, MultipartFile[] files, HttpSession session, PingDTO pingDTO) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		travelDTO.setMemberNum(memberDTO.getMemberNum());
		
		// add메서드와 거의 비슷하게 따라감
		// 1. 게시글번호를 매기고,
		Long boardNum = travelService.makeBoardNum();
		travelDTO.setBoardNum(boardNum);
		// 2. 최상위부모글과 부모글 값은 이미 넘겨받았음
		
		// 3. 위치정보 추가
		Long pingNum = pingService.savePingNum();
		travelDTO.setPingNum(pingNum);
		pingDTO.setPingNum(pingNum);
		pingService.addPing(pingDTO);
		
		// 4. 게시글 추가
		int result = travelService.add(travelDTO, files, session);
		
		
		// 5. 내 부모글의 자식글 값을 나로 변경
		travelService.addPlus(travelDTO);

		
		return "redirect:./list";
	}
	
	

	
	@GetMapping("update")
	public String update() throws Exception {
		
		return "board/travel/write";
	}
	@PostMapping("update")
	public void update(HttpSession session) throws Exception {
		
	}
	
	
	//게시글 디테일
	@GetMapping
	public String detail(TravelDTO travelDTO, Model model) throws Exception {
		
		TravelDTO travelDetail = travelService.detail(travelDTO);
		model.addAttribute("dto", travelDetail);
		System.out.println(travelDetail.getMemberNum());
		
		//작성자 프로필 사진 가져올거임
		MemberDTO boardWriter = new MemberDTO();
		boardWriter.setMemberNum(travelDetail.getMemberNum());
		boardWriter = memberService.detail(boardWriter);
		model.addAttribute("member", boardWriter);
		
		
		
		return "board/travel/detail";
	}
	
	
}
