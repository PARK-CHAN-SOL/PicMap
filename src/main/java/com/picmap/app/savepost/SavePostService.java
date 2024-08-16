package com.picmap.app.savepost;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.member.MemberDTO;

@Service
public class SavePostService {
	
	@Autowired
	private SavePostDAO savePostDAO;
	
	//게시물 저장 클릭
	public Integer savePostClick(SavePostDTO savePostDTO, HttpSession session) throws Exception {
		Integer checker = savePostCheck(savePostDTO, session);
		if (checker == -1000) { //로그인 안하고 눌렀으면 -1000 리턴
			return -1000;
		} else if (checker == 0) { //게시글을 이미 저장했으면 삭제, 저장안했으면 저장
			return ~savePostDAO.delSavedPost(savePostDTO) + 1; // -(0+1) 리턴
		} else {//좋아요 킹애 1 리턴
			return savePostDAO.savePost(savePostDTO);
		}
	}
	
	//저장한 게시물인지 아닌지 판별
	public Integer savePostCheck(SavePostDTO savePostDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			savePostDTO.setMemberNum(memberDTO.getMemberNum());
			savePostDTO.setSavePostNum(savePostDAO.savePostCheck(savePostDTO));
			if (savePostDTO.getSavePostNum() != null) return 0; //이미 저장한 게시글이면 0 리턴
			else
				return 1; //아니면 1리턴
		} else {
			return -1000; //session에 담긴 member키의 밸류값이 비어있으면(로그인 안했음)
		}
	}

}
