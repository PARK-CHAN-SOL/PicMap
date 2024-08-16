package com.picmap.app.travel;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.util.Scroller;

@Repository
public class TravelDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.travel.TravelDAO.";
	
	
	
	//게시판(게시글 리스트)
	public List<BoardDTO> getList(Scroller scroller) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+"getList", scroller);
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

		return 0;
	}
	
	//자식글 삭제
	public int delete(TravelDTO travelDTO) throws Exception {
		int result = 0;
		if(travelDTO.getChildBoard() != null) { //자식글이 존재한다면 자식글의 부모글을 자신의 부모글로
			result += sqlSession.delete(NAMESPACE+"adoption1", travelDTO);// 부모글의 자식글을 자신의 자식글로
			result += sqlSession.delete(NAMESPACE+"adoption2", travelDTO);
		}else {//최하위 자식글이면 부모글의 자식글을 null처리
			result += sqlSession.delete(NAMESPACE+"adoption3", travelDTO);
		}
		
		result += sqlSession.delete(NAMESPACE+"delete", travelDTO);
		
		return result;
	}
	
	//최상위부모글 삭제(자식글 모두 삭제)
	public int deleteAll(TravelDTO travelDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteAll", travelDTO);
	}
	
	//조회수
	public int hit(TravelDTO travelDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"hit", travelDTO);
	}

	
	
}
