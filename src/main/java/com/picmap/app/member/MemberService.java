package com.picmap.app.member;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class MemberService {
@Autowired
private MemberDAO memberDAO;


public int idCheck(MemberDTO memberDTO) throws Exception {
	return memberDAO.idCheck(memberDTO);
}
public int memberNickName(MemberDTO memberDTO) throws Exception {
	return memberDAO.memberNickName(memberDTO);
}

	//로그인
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
//회원가입
	public int join(MemberDTO memberDTO, MultipartFile files, HttpSession session) throws Exception {
		if(null==memberDTO.getProfilePath() || memberDTO.getProfilePath().equals("")){
		   memberDTO.setProfilePath("default");
		}
		
		return memberDAO.join(memberDTO);

	}
	//로그아웃
	public MemberDTO logout(MemberDTO memberDTO) throws Exception {
		return memberDAO.logout(memberDTO);
	}

	
	
	
	
	
	
	
	
}
