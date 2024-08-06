package com.picmap.app.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.member.MemberDTO;
import com.picmap.app.travel.TravelDTO;

public interface BoardService {
	
	public List<BoardDTO> getList() throws Exception;
	
	public int add(HttpSession session, MultipartFile files, BoardDTO boardDTO) throws Exception;
	
	public int update() throws Exception;

}
