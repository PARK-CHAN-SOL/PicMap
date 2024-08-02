package com.picmap.app.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.member.MemberDTO;

public interface BoardService {
	
	public List<BoardDTO> getList() throws Exception;
	
	public int add(BoardDTO boardDTO, MultipartFile[] files, HttpSession session) throws Exception;
	
	public int update() throws Exception;

}
