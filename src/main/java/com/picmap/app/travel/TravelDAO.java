package com.picmap.app.travel;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.comments.CommentsDTO;
import com.picmap.app.util.Scroller;

@Repository
public class TravelDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.travel.TravelDAO.";
	
	public Long getTotalCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE + "getTotalCount");
	}
	

	//베스트 게시글
	public List<TravelDTO> bestList() throws Exception {
		return sqlSession.selectList(NAMESPACE+"bestList");
	}
	
	
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
		if(travelDTO.getChildBoard() != null) { //자식글이 존재한다면 
			result += sqlSession.delete(NAMESPACE+"adoption1", travelDTO); //자식글의 부모글을 자신의 부모글로
			result += sqlSession.delete(NAMESPACE+"adoption2", travelDTO); // 부모글의 자식글을 자신의 자식글로
		}else {//최하위 자식글이면 부모글의 자식글을 null처리
			result += sqlSession.delete(NAMESPACE+"adoption3", travelDTO);
		}
		
		List<CommentsDTO> comment = sqlSession.selectList(NAMESPACE+"getCommentNum", travelDTO); //댓글 번호 따놓기
        if (comment != null && !comment.isEmpty()) {//댓글이 존재하면
			result += sqlSession.delete(NAMESPACE+"deleteHeartComments", comment); //댓글 좋아요 다 지우고
			result += sqlSession.delete(NAMESPACE+"deleteReply", comment); //대댓글 다 지우고
			result += sqlSession.delete(NAMESPACE+"deleteComments", comment); //댓글 다 지우고
        }
		
		result += sqlSession.delete(NAMESPACE+"delete", travelDTO);//글 지우고
		result += sqlSession.delete(NAMESPACE+"deletePing", travelDTO); //핑 지우고
		
		return result;
	}
	
	//최상위부모글 삭제(자식글 모두 삭제)
	public int deleteAll(TravelDTO travelDTO) throws Exception {
		int result = 0;
		result += sqlSession.delete(NAMESPACE+"deleteHeart", travelDTO); //좋아요 다 지우고
		List<TravelDTO> child = sqlSession.selectList(NAMESPACE+"getChildInfo", travelDTO); //자식글들의 핑,게시글 번호 따놓기
		
		result += sqlSession.delete(NAMESPACE+"deleteSavePost", travelDTO); //저장한 게시글에서 지우고
		
		List<CommentsDTO> comments = sqlSession.selectList(NAMESPACE+"getCommentNums", child); //자식글 포함 댓글 번호 따놓기
		
        if (comments != null && !comments.isEmpty()) {//댓글이 존재하면
			result += sqlSession.delete(NAMESPACE+"deleteHeartComments", comments); //댓글 좋아요 다 지우고
			result += sqlSession.delete(NAMESPACE+"deleteReply", comments); //대댓글 다 지우고
			result += sqlSession.delete(NAMESPACE+"deleteComments", comments); //댓글 다 지우고
		}
		result += sqlSession.delete(NAMESPACE+"deleteAll", travelDTO); //자식글 포함 게시글 다 지우고
		result += sqlSession.delete(NAMESPACE+"deleteAllPing", child); //자식글 포함 핑 다 지우고
		return result;
	}
	
	//조회수
	public int hit(TravelDTO travelDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"hit", travelDTO);
	}

	
	
}
