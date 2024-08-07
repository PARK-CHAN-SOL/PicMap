package com.picmap.app.travel;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.board.BoardService;
import com.picmap.app.files.FileManager;
import com.picmap.app.member.MemberDTO;

@Service
public class TravelService implements BoardService{
	
	@Autowired
	private TravelDAO travelDAO;
	@Autowired
	private FileManager fileManager;
	
	private String name = "travels";
	
	//게시판(게시글 리스트)
	@Override
	public List<BoardDTO> getList() throws Exception {
		// TODO Auto-generated method stub
		return travelDAO.getList();
	}


	//게시글 작성
	@Override
	public int add(BoardDTO boardDTO, MultipartFile[] files, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		boardDTO.setMemberNum(memberDTO.getMemberNum());
		ServletContext servletContext =	session.getServletContext();
		//1. 어디에 저장? (운영체제가 알고 있는 경로에 써줘야한다)
		String path = servletContext.getRealPath("resources/upload/" + name);
		System.out.println(path);

		if(null==files){
			boardDTO.setFileName("default");
		}else {
			for (MultipartFile f : files) {
				if (f.isEmpty()) {
					continue;
				}
				boardDTO.setFileName(fileManager.fileSave(f, path));
			}
		}		
		int result = travelDAO.add(boardDTO);
		return result;
	}


	//게시글 수정
	@Override
	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
}
