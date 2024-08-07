package com.picmap.app.board;

import java.util.List;

import com.picmap.app.member.MemberDTO;

public interface BoardDAO{
	
	public List<BoardDTO> getList() throws Exception;
	
	public int add(BoardDTO boardDTO) throws Exception;
	
	public int addFile(BoardFileDTO boardFileDTO) throws Exception;
	
	
}
