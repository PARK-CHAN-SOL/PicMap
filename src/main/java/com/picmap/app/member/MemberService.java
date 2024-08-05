package com.picmap.app.member;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.files.FileManager;




@Service
public class MemberService {
@Autowired
private MemberDAO memberDAO;
@Autowired
private FileManager fileManager;

private String name = "members";

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
		ServletContext servletContext =	session.getServletContext();
		//1. 어디에 저장? (운영체제가 알고 있는 경로에 써줘야한다)
		String path = servletContext.getRealPath("resources/upload/" + name);
		System.out.println(path);
		FileManager fm = new FileManager();

		if(null==files){
			memberDTO.setProfilePath("default");
		}else {
		fm.fileSave(files, path, memberDTO);
		}
		int result = memberDAO.join(memberDTO);
		return result;
		
		
	}
	//로그아웃
	public MemberDTO logout(MemberDTO memberDTO) throws Exception {
		return memberDAO.logout(memberDTO);
	}

	//마이페이지
	public MemberDTO mypage(MemberDTO memberDTO) throws Exception {
		return memberDTO;
	}
	public MemberDTO detail(MemberDTO memberDTO) throws Exception {
		return memberDAO.detail(memberDTO);
	}

	public int update(MemberDTO memberDTO, MultipartFile files, HttpSession session) throws Exception {
		ServletContext servletContext =	session.getServletContext();
		//1. 어디에 저장? (운영체제가 알고 있는 경로에 써줘야한다)
		String path = servletContext.getRealPath("resources/upload/" + name);
		System.out.println(path);
		FileManager fm = new FileManager();

		if(null==files){
			memberDTO.setProfilePath("default");
		}else {
		fm.fileSave(files, path, memberDTO);
		}
		int result = memberDAO.update(memberDTO);
		return result;
		
	}

	public int delete(MemberDTO memberDTO) throws Exception {
		return memberDAO.delete(memberDTO);
	}
	
	
	
	
}
