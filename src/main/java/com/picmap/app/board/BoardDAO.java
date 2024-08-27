package com.picmap.app.board;

import java.util.List;

import com.picmap.app.member.MemberDTO;
import com.picmap.app.util.Pager;

public interface BoardDAO{
	
	public List<BoardDTO> getList(Pager pager) throws Exception;
	
	public Long getTotalCount(Pager pager) throws Exception;
	
	public int add(BoardDTO boardDTO) throws Exception;
	
	public int addFile(BoardFileDTO boardFileDTO) throws Exception;
	
	public int update(BoardDTO boardDTO) throws Exception;
	
	
}
