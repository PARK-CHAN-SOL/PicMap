package com.picmap.app.travel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.board.BoardService;

@Service
public class TravelService implements BoardService{
	
	@Autowired
	private TravelDAO travelDAO;
	
	
	
	//게시판(게시글 리스트)
	@Override
	public List<BoardDTO> getList() throws Exception {
		// TODO Auto-generated method stub
		return travelDAO.getList();
	}


	//게시글 작성
	@Override
	public int add() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	//게시글 수정
	@Override
	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
}
