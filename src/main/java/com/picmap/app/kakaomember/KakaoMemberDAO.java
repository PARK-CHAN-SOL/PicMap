package com.picmap.app.kakaomember;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.member.MemberDTO;

@Repository
public class KakaoMemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.member.MemberDAO."; 
	
	public MemberDTO kakaoMemberJoinCheck(String memberId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "kakaoMemberJoinCheck", memberId);
	}
	
	public Integer kakaoMemberJoin(MemberDTO memberDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "kakaoMemberJoin", memberDTO);
	}


}
