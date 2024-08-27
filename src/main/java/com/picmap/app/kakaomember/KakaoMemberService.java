package com.picmap.app.kakaomember;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class KakaoMemberService {
	@Autowired
	private KakaoMemberDAO kakaoMemberDAO;
	
	public MemberDTO kakaoMemberJoinCheck(String memberId) throws Exception {
		return kakaoMemberDAO.kakaoMemberJoinCheck(memberId);
	}
	
	public Integer kakaoMemberJoin(MemberDTO memberDTO) throws Exception {
		return kakaoMemberDAO.kakaoMemberJoin(memberDTO);
	}

	public int kakaoLogout(HttpSession httpSession, MemberDTO memberDTO) {
		return kakaoMemberDAO.kakaoLogout(memberDTO);
		
	}



}
