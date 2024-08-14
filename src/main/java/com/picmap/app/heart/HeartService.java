package com.picmap.app.heart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class HeartService {
	@Autowired
	private HeartDAO heartDAO;
	
	//좋아요 클릭
	public Integer heartClick(HeartDTO heartDTO, HttpSession session) throws Exception {
		Integer checker = heartCheck(heartDTO, session);
		System.out.println(checker);
		if (checker == 2) { //로그인 안하고 눌렀으면 -1000 리턴
			return 2;
		} else if (checker == 0) { //좋아요를 이미 누른 상태에서 좋아요를 누르면 좋아요 수를 내림. 1 리턴
			return ~heartDAO.heartDown(heartDTO) + 1;
		} else {//좋아요 킹애 1 리턴
			return heartDAO.heartUp(heartDTO);
		}
	}
	
	//좋아요를 클릭했을 때 좋아요를 이미 눌렀었는지 아닌지 판별
	public Integer heartCheck(HeartDTO heartDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			heartDTO.setMemberNum(memberDTO.getMemberNum());
			heartDTO.setHeartNum(heartDAO.heartCheck(heartDTO));
			if (heartDTO.getHeartNum() != null) return 0; //좋아요를 이미 눌렀으면 0 리턴
			else
				return 1; //좋아요를 안눌렀으면 1 리턴
		} else {
			System.out.println("2.m,n.,mniu");
			return 2; //session에 담긴 member키의 밸류값이 비어있으면(로그인 안했음)
		}
	}
	
	//게시글 좋아요 수 카운트
	public Long heartCount(HeartDTO heartDTO) throws Exception {
		return heartDAO.heartCount(heartDTO);
	}

}
