package com.picmap.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.files.FileManager;
import com.picmap.app.follow.FollowDTO;
import com.picmap.app.travel.TravelDTO;
import com.picmap.app.util.Scroller;


@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileManager fileManager;

	private String name = "members/";

	public int idCheck(MemberDTO memberDTO) throws Exception {
		return memberDAO.idCheck(memberDTO);
	}

	public int memberNickName(MemberDTO memberDTO) throws Exception {
		return memberDAO.memberNickName(memberDTO);
	}

	// 로그인
	public MemberDTO login(MemberDTO memberDTO, HttpSession session) throws Exception {
		MemberDTO result = memberDAO.login(memberDTO);
		if (result != null) {
			if (result.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				// 로그인 성공
				session.setAttribute("member", result);
				return result;
			} else {
				return null;
			}
		}
		return result;
	}

//회원가입
	public int join(MemberDTO memberDTO, MultipartFile files, HttpSession session) throws Exception {
		ServletContext servletContext = session.getServletContext();
		// 1. 어디에 저장? (운영체제가 알고 있는 경로에 써줘야한다)
		String path = servletContext.getRealPath("/resources/upload/" + name);
		System.out.println(path);
		FileManager fm = new FileManager();

		if (null == files.getOriginalFilename() ||  files.getOriginalFilename().equals("")) {
			memberDTO.setProfilePath("default");
		} else {
			memberDTO.setProfilePath("/resources/upload/" + name + fm.fileSave(files, path));
		}
		int result = memberDAO.join(memberDTO);
		return result;

	}
	// 카카오 회원가입
	public int join(MemberDTO memberDTO) throws Exception {
		int result = memberDAO.join(memberDTO);
		return result;
	}

	// 로그아웃
	public MemberDTO logout(MemberDTO memberDTO) throws Exception {
		return memberDAO.logout(memberDTO);
	}

	// 마이페이지
	public MemberDTO mypage(MemberDTO memberDTO) throws Exception {

		return memberDTO;
	}

	public MemberDTO detail(MemberDTO memberDTO) throws Exception {
		return memberDAO.detail(memberDTO);
	}

	public int update(MemberDTO memberDTO, MultipartFile files, HttpSession session) throws Exception {
		ServletContext servletContext = session.getServletContext();
		// 1. 어디에 저장? (운영체제가 알고 있는 경로에 써줘야한다)
		String path = servletContext.getRealPath("resources/upload/" + name);
		System.out.println(path);
		FileManager fm = new FileManager();

		if (null == files.getOriginalFilename() ||  files.getOriginalFilename().equals("")) {
			memberDTO.setProfilePath("default");
		} else {
			memberDTO.setProfilePath("/resources/upload/" + name + fm.fileSave(files, path));
		}
		int result = memberDAO.update(memberDTO);
		return result;

	}

	public int delete(MemberDTO memberDTO) throws Exception {
		return memberDAO.delete(memberDTO);
	}

	public int follow(FollowDTO followDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		followDTO.setFromFollow(memberDTO.getMemberNum());
		int result = followCheck(followDTO);
		if (result == 0) {
			return 0;
		}
		if (result == 1) {
			return memberDAO.follow(followDTO);
		} else {
			return memberDAO.followDelete(followDTO);
		}
	}

	public Long countFromFollow(MemberDTO memberDTO) throws Exception {
		return memberDAO.countFromFollow(memberDTO);
	}

	public Long countToFollow(MemberDTO memberDTO) throws Exception {
		return memberDAO.countToFollow(memberDTO);
	}

	public Integer followCheck(FollowDTO followDTO) throws Exception {
		if (followDTO.getToFollow().equals(followDTO.getFromFollow())) {
			return 0;
		}
		int result = memberDAO.followCheck(followDTO);
		if (result == 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public List<MemberDTO> fromFollowList(FollowDTO followDTO, Scroller scroller) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("followDTO", followDTO);
		map.put("scroller", scroller);
		return memberDAO.fromFollowList(map);
	}
	public List<MemberDTO> toFollowList(FollowDTO followDTO, Scroller scroller) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("followDTO", followDTO);
		map.put("scroller", scroller);
		return memberDAO.toFollowList(map);
	}
	//게시판(게시글 리스트)
	public List<TravelDTO> getList(MemberDTO memberDTO) throws Exception {
		return memberDAO.getList(memberDTO);
	}
	
}