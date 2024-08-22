package com.picmap.app.travel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.heart.HeartDTO;
import com.picmap.app.heart.HeartService;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.member.MemberService;
import com.picmap.app.ping.PingDTO;
import com.picmap.app.ping.PingService;
import com.picmap.app.util.Scroller;

@Controller
@RequestMapping("/travel/*")
public class TravelController {
	
	@Autowired
	private TravelService travelService;
	@Autowired
	private PingService pingService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private HeartService heartService;
	
	
	@ModelAttribute("board")
	public String getBoard() {
		return "Travel";
	}
	
	@GetMapping("travelTotalCount")
	@ResponseBody
	public Long getTotalCount() throws Exception {
		return travelService.getTotalCount();
	}
	
	@GetMapping("list")
	public String getList(@RequestParam(required = false) String search,
	                      @RequestParam(required = false, defaultValue = "1") int startRow,
	                      @RequestParam(required = false, defaultValue = "18") int endRow,
	                      Model model) throws Exception {
	    // 검색 조건과 시작 및 종료 행 번호를 모델에 추가
	    model.addAttribute("search", search);
	    model.addAttribute("startRow", startRow);
	    model.addAttribute("endRow", endRow);
	    return "board/travel/list";
	}
	
	@PostMapping("list")
	@ResponseBody
	public List<BoardDTO> getList(Model model, Scroller scroller) throws Exception {

		return travelService.getList(scroller);
	}

	@PostMapping("listSortByLikes")
	@ResponseBody
	public List<BoardDTO> getListSortByLikes(Model model, Scroller scroller) throws Exception {
		return travelService.getListSortByLikes(scroller);
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
		
		String boardTitleTmp = travelDTO.getBoardTitle();
		boardTitleTmp = boardTitleTmp.replaceAll("<", "&lt");
		boardTitleTmp = boardTitleTmp.replaceAll(">", "&gt");
		travelDTO.setBoardTitle(boardTitleTmp);
		
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
	
	
	//게시글 수정
	@GetMapping("update")
	public String update(Model model, TravelDTO travelDTO) throws Exception {
		TravelDTO travelDetail = travelService.detail(travelDTO);
		model.addAttribute("dto", travelDetail);
		
		PingDTO pingDTO = new PingDTO();
		pingDTO.setPingNum(travelDetail.getPingNum());
		pingDTO = pingService.getDetail(pingDTO);
		model.addAttribute("pingDTO", pingDTO);
		
		return "board/travel/write";
	}
	@PostMapping("update")
	public String update(TravelDTO travelDTO, MultipartFile[] files, HttpSession session, PingDTO pingDTO) throws Exception {
		
		pingService.updatePing(pingDTO);
		
		String boardTitleTmp = travelDTO.getBoardTitle();
		boardTitleTmp = boardTitleTmp.replaceAll("<", "&lt");
		boardTitleTmp = boardTitleTmp.replaceAll(">", "&gt");
		travelDTO.setBoardTitle(boardTitleTmp);
		
		travelService.update(travelDTO, files, session);
		
		return "redirect:./list";
		
	}
	
	
	//게시글 삭제
	@PostMapping("delete")
	public String delete(TravelDTO travelDTO) throws Exception {
		TravelDTO travelDetail = travelService.detail(travelDTO);
		
		if(!travelDetail.getBoardNum().equals(travelDetail.getRootBoard()) ) { //자식글이라면 그 글만 삭제		
			int result = travelService.delete(travelDetail);
		}else { //최상위 부모글이라면 그 아래로 싹 다 삭제
			int result = travelService.deleteAll(travelDetail);
		}

		
		return "redirect:./list";
	}
	
	//게시글 디테일
	@GetMapping
	public String detail(HeartDTO heartDTO, TravelDTO travelDTO, Model model,
			HttpSession session, HttpServletRequest request) throws Exception {
		MemberDTO memberDTO= (MemberDTO)session.getAttribute("member");
		model.addAttribute("login", memberDTO);
		//조건문을 위해 일단 디테일을 먼저 땡겨오고
		TravelDTO travelDetail = travelService.detail(travelDTO);
		model.addAttribute("dto", travelDetail);
		
		//조회수(최상위 부모글 기준)
		if(travelDetail.getBoardNum().equals(travelDetail.getRootBoard()) ) {
			travelService.hit(travelDTO);
		}else { //자식글은 최상위부모글 조회수를 그대로 가져오고, 조회수를 올리지 않는다
			travelDTO.setBoardNum(travelDetail.getRootBoard());
		}
		TravelDTO h = travelService.detail(travelDTO);
		model.addAttribute("hit", h.getHit());
		
		
		//작성자 프로필 사진
		MemberDTO boardWriter = new MemberDTO();
		boardWriter.setMemberNum(travelDetail.getMemberNum());
		boardWriter = memberService.detail(boardWriter);
		model.addAttribute("member", boardWriter);
		
		//좋아요
		heartDTO.setBoardNum(travelDetail.getBoardNum());
		Long heartCount = heartService.heartCount(heartDTO);
		model.addAttribute("heart", heartCount);
		
		
		
		//게시글 수정 및 삭제, 자식글 작성할 때 작성자의 계정과 일치하는 계정인지 판별하는 필터를 걸어두기 위해
		//작성자 memberNum을 세션에 올림
		session.setAttribute("writer", travelDetail.getMemberNum());
		
		
		return "board/travel/detail";
	}
	
	
	
}
