package com.picmap.app.travel;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.files.FileManager;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.util.Scroller;

@Service
public class TravelService {
	
	//다른 부분이 너무 많아서 travel은 전부 board 상속 안하고 따로 씀
	
	@Autowired
	private TravelDAO travelDAO;
	@Autowired
	private FileManager fileManager;
	
	private String name = "travels";
	
	public Long getTotalCount() throws Exception {
		return travelDAO.getTotalCount();
	}
	
	//베스트 게시글
	public List<TravelDTO> bestList() throws Exception {
		return travelDAO.bestList();
	}
	
	
	//게시판(게시글 리스트)
	public List<BoardDTO> getList(Scroller scroller) throws Exception {
		return travelDAO.getList(scroller);
	}

	
	//게시글 번호 boardNum 매기기 (부모글, 최상위 부모글 만들기용)
	public Long makeBoardNum() throws Exception {
		return travelDAO.makeBoardNum();
	}


	//게시글 작성(최상위 부모글)
	public int add(TravelDTO travelDTO, MultipartFile[] files, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		travelDTO.setMemberNum(memberDTO.getMemberNum());
		ServletContext servletContext =	session.getServletContext();
		//1. 어디에 저장? (운영체제가 알고 있는 경로에 써줘야한다)
		String path = servletContext.getRealPath("resources/upload/" + name);
		System.out.println(path);

		if(null==files){
			travelDTO.setFileName("default");
		}else {
			for (MultipartFile f : files) {
				if (f.isEmpty()) {
					continue;
				}
				travelDTO.setFileName(fileManager.fileSave(f, path));
			}
		}		
		int result = travelDAO.add(travelDTO);
		return result;
	}
	
	
	//부모글, 자식글 설정
	public int addPlus(TravelDTO travelDTO) throws Exception {
		return travelDAO.addPlus(travelDTO);
	}
	
	
	//게시글 수정
	public int update() throws Exception {
		return 0;
	}
	
	
	//게시글 디테일
	public TravelDTO detail(TravelDTO travelDTO) throws Exception {
		return travelDAO.detail(travelDTO);
	}
	
	//자식글 삭제
	public int delete(TravelDTO travelDTO) throws Exception {
		return travelDAO.delete(travelDTO);
	}
	
	//최상위 부모글 삭제(자식글 모두 삭제)
	public int deleteAll(TravelDTO travelDTO) throws Exception {
		return travelDAO.deleteAll(travelDTO);
	}
	
	//조회수
	public int hit(TravelDTO travelDTO) throws Exception {
		return travelDAO.hit(travelDTO);
	}
	
	
}
