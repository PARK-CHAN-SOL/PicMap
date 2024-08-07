package com.picmap.app.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.follow.FollowDTO;




@Repository
public class MemberDAO {

	@Autowired
private SqlSession sqlSession;
private final String NAMESPACE="com.picmap.app.member.MemberDAO.";


public int idCheck(MemberDTO memberDTO) throws Exception {
	return sqlSession.selectOne(NAMESPACE + "idCheck", memberDTO);
}
public int memberNickName(MemberDTO memberDTO) throws Exception {
	return sqlSession.selectOne(NAMESPACE + "memberNickName", memberDTO);
}
	//로그인 
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"login",memberDTO);
	}	
	//회원가입
	public int join(MemberDTO memberDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "join", memberDTO);
	}
	//로그아웃
	public MemberDTO logout(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "logout", memberDTO);
	}
//마이페이지
	public MemberDTO mypage(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "mypage", memberDTO);
	}
	public MemberDTO detail(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "detail", memberDTO);
	}	
	public int update(MemberDTO memberDTO) throws Exception {
		return sqlSession.update(NAMESPACE + "update", memberDTO);
	}

	public int delete(MemberDTO memberDTO) throws Exception {
		return sqlSession.delete(NAMESPACE + "delete", memberDTO);
	}
	
	public int follow(FollowDTO followDTO)throws Exception {
		return sqlSession.insert(NAMESPACE + "follow", followDTO);
	}	
	public Long fromFollow(MemberDTO memberDTO)throws Exception {
		return sqlSession.selectOne(NAMESPACE + "fromFollow", memberDTO);
	}	
	public Long toFollow(MemberDTO memberDTO)throws Exception {
		return sqlSession.selectOne(NAMESPACE + "toFollow", memberDTO);
	}	
	public Integer followCheck(FollowDTO followDTO)throws Exception {
		return sqlSession.selectOne(NAMESPACE + "followCheck", followDTO);
	}	
	public int followDelete(FollowDTO followDTO) throws Exception {
		return sqlSession.delete(NAMESPACE + "followDelete", followDTO);
	}
	public List<MemberDTO> fromFollowList(FollowDTO followDTO)throws Exception {
		return sqlSession.selectList(NAMESPACE + "fromFollowList", followDTO);
	}	
	public List<MemberDTO> toFollowList(FollowDTO followDTO)throws Exception {
		return sqlSession.selectList(NAMESPACE + "toFollowList", followDTO);
	}	
}
