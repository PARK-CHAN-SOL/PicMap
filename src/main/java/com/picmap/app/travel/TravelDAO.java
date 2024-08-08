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
	
	
	//게시글 번호 매기기
	public Long makeBoardNum() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"makeBoardNum");
	}
	
	
	//게시글 작성(최상위 부모글 작성)
	public int add(TravelDTO travelDTO) throws Exception {
		return  sqlSession.insert(NAMESPACE+"add", travelDTO);
	}

	//게시글 작성(자식글, 부모글 값 설정)
	public int addPlus(TravelDTO travelDTO) throws Exception {
		int result=0;
		result += sqlSession.update(NAMESPACE+"addPlus1", travelDTO);
		result += sqlSession.update(NAMESPACE+"addPlus2", travelDTO);
		
		return result;
	}

	
	//최상위 부모글 설정
	public TravelDTO setRoot(TravelDTO travelDTO) throws Exception {	
		
		TravelDTO root = new TravelDTO();
		//부모글이 존재하지 않는 게시글까지 부모글 거슬러 올라가기(부모글이 없으면 최상위부모글)
		while(root.getParentBoard() != null) {			
			root = sqlSession.selectOne(NAMESPACE+"getRoot", travelDTO);
			travelDTO.setBoardNum(root.getParentBoard()); 
		}
		
		TravelDTO child = new TravelDTO();
		child = sqlSession.selectOne(NAMESPACE+"getRoot", root);
		//루트부터 자식글이 존재하지 않는 게시글까지 다시 내려가면서 최상위부모글 칼럼에 root 값 넣기
		while(child.getChildBoard() != null) {
			child.setBoardNum(child.getChildBoard());
			sqlSession.update(NAMESPACE+"setRoot", child);
			child = sqlSession.selectOne(NAMESPACE+"getRoot", child);
		}
		
		
		
		return root;
	}
	
	
	//게시글 수정
	public int update(TravelDTO travelDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//게시글 디테일
	public TravelDTO detail(TravelDTO travelDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"detail", travelDTO);
	}
	
	
}
