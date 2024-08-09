package com.picmap.app.kakaomember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class KakaoMemberService {
	@Autowired
	private KakaoMemberDAO kakaoMemberDAO;
	
	public MemberDTO kakaoMemberJoinCheck(String token) throws Exception {
		return kakaoMemberDAO.kakaoMemberJoinCheck(token);
	}
}
