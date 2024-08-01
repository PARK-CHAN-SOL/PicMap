package com.picmap.app.board;

import java.util.List;

import com.picmap.app.member.MemberDTO;

public interface BoardService {
	
	public List<BoardDTO> getList() throws Exception;
	
	public int add() throws Exception;
	
	public int update() throws Exception;

}
