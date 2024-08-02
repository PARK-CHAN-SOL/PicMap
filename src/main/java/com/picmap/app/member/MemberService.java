package com.picmap.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class MemberService {
@Autowired
private MemberDAO memberDAO;
	
	public MemberDTO login(MemberDTO memberDTO)throws Exception {
		MemberDTO result = memberDAO.login(memberDTO);
		if (result != null) {
			if (result.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				// 로그인 성공
				return result;
			} else {
				return null;
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
