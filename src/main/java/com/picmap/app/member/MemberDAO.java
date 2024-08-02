package com.picmap.app.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
private SqlSession sqlSession;
private final String NAMESPACE="com.picmap.app.member.MemberDAO.";
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"login",memberDTO);
	}	
	
	
	
	
	
	
	
	
	
	
	
}
