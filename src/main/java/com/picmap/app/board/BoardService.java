package com.picmap.app.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.member.MemberDTO;
import com.picmap.app.travel.TravelDTO;
import com.picmap.app.util.Pager;

public interface BoardService {
	
	public List<BoardDTO> getList(Pager pager) throws Exception;
	
	public int add(BoardDTO boardDTO, MultipartFile[] files, HttpSession session) throws Exception;
	
}
