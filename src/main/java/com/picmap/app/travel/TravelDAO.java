package com.picmap.app.travel;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.board.BoardDTO;

@Repository
public class TravelDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.travel.TravelDAO.";
	
	
	
	//게시판(게시글 리스트)
	public List<BoardDTO> getList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+"getList");
	}
	
	
	
	//게시글 디테일
	public TravelDTO detail(TravelDTO travelDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"detail", travelDTO);
	}
	
	
	//게시글 번호 매기기
	public Long makeBoardNum() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"makeBoardNum");
	}
	//게시글 작성(최상위 부모글 작성)
	public int add(TravelDTO travelDTO) throws Exception {
		return  sqlSession.insert(NAMESPACE+"add", travelDTO);
	}
	//자식글, 부모글 값 설정
	public int addPlus(TravelDTO travelDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"addPlus", travelDTO);
	}

	
	
	//게시글 수정 (미완)
	public int update(TravelDTO travelDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	
	
}
