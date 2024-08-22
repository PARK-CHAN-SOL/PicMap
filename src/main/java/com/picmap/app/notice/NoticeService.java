package com.picmap.app.notice;

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
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileManager fileManager;
	
	private String name = "notices";
	
	
	
	//게시판(게시글 리스트)
	@Override
	public List<BoardDTO> getList() throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getList();
	}
	

	//게시글 작성
	@Override
	public int add(BoardDTO boardDTO, MultipartFile[] files, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		boardDTO.setMemberNum(memberDTO.getMemberNum());
		ServletContext servletContext =	session.getServletContext();
		//1. 어디에 저장
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
		int result = noticeDAO.add(boardDTO);
		return result;
	}

	public NoticeDTO getDetail(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.getDetail(noticeDTO);
	}

	//게시글 수정
	public int update(NoticeDTO noticeDTO, MultipartFile[] files, HttpSession session) throws Exception {
		ServletContext servletContext =	session.getServletContext();
		//파일 수정시 저장경로
		String path = servletContext.getRealPath("resources/upload/" + name);
		System.out.println(path);

		if(files != null) {//files==null이면 수정하지 않았다는 것이므로 저장하지 않음
			for (MultipartFile f : files) {
				if (f.isEmpty()) {
					continue;
				}
				noticeDTO.setFileName(fileManager.fileSave(f, path));
			}
		}
		
		int result = noticeDAO.update(noticeDTO); 
		
		return result; 
	}
	
	public Integer delete(NoticeDTO noticeDTO) throws Exception {
//		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		return noticeDAO.delete(noticeDTO);
	}
	
	

}
