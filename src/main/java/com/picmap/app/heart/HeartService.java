package com.picmap.app.heart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class HeartService {
	@Autowired
	private HeartDAO heartDAO;

	public Integer heartClick(HeartDTO heartDTO, HttpSession session) throws Exception {
		Integer checker = heartCheck(heartDTO, session);
		if (checker == -1000) {
			return -1000;
		} else if (checker == 0) {
			return ~heartDAO.heartDown(heartDTO) + 1;
		} else {
			return heartDAO.heartUp(heartDTO);
		}
	}

	public Integer heartCheck(HeartDTO heartDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			heartDTO.setMemberNum(memberDTO.getMemberNum());
			heartDTO.setHeartNum(heartDAO.heartCheck(heartDTO));
			if (heartDTO.getHeartNum() != null) return 0;
			else
				return 1;
		} else {
			return -1000;
		}
	}

	public Long heartCount(HeartDTO heartDTO) throws Exception {
		return heartDAO.heartCount(heartDTO);
	}

}
