package com.picmap.app.heartComments;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class HeartCommentsService {
	@Autowired
	private HeartCommentsDAO heartCommentsDAO;

	// 댓글 좋아요 클릭 처리
	public Integer heartCommentsClick(HeartCommentsDTO heartCommentsDTO, HttpSession session) throws Exception {
		// 로그인 여부 확인
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO == null) {
			return -1000; // 로그인하지 않은 경우
		}

		heartCommentsDTO.setMemberNum(memberDTO.getMemberNum());

		// 좋아요 확인
		Long heartCommentNum = heartCommentsDAO.heartCommentsCheck(heartCommentsDTO);
		if (heartCommentNum != null) {
			// 이미 좋아요를 눌렀다면 취소
			heartCommentsDTO.setHeartCommentNum(heartCommentNum);
			return ~heartCommentsDAO.heartCommentsDown(heartCommentsDTO) + 1;
		} else {
			// 좋아요 추가
			return heartCommentsDAO.heartCommentsUp(heartCommentsDTO);
		}
	}

	// 댓글 좋아요 수 카운트
	public Long heartCommentsCount(Long commentNum) throws Exception {
		return heartCommentsDAO.heartCommentsCount(commentNum);
	}

	// 좋아요 상태 체크
	public Integer heartCommentsCheck(HeartCommentsDTO heartCommentsDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			heartCommentsDTO.setMemberNum(memberDTO.getMemberNum());
			heartCommentsDTO.setHeartCommentNum(heartCommentsDAO.heartCommentsCheck(heartCommentsDTO));
			if (heartCommentsDTO.getHeartCommentNum() != null)
				return 0; // 좋아요를 이미 눌렀으면 0 리턴
			else
				return 1; // 좋아요를 안눌렀으면 1 리턴
		} else {
			return -1000; // session에 담긴 member키의 밸류값이 비어있으면(로그인 안했음)
		}
	}
}
