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
	  public void kakaoLogout(HttpSession session) {
	        // 카카오 로그아웃 및 세션 초기화 로직 구현
	        // 예: 카카오 API 호출, 세션 데이터 삭제 등

	        // 카카오 세션 정보를 세션에서 제거
	        session.removeAttribute("kakaoAccessToken");
	        session.removeAttribute("kakaoUserInfo");
	    }
}
