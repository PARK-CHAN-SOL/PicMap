package com.picmap.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.files.FileManager;
import com.picmap.app.follow.FollowDTO;
import com.picmap.app.kakaomember.KakaoMemberService;
import com.picmap.app.travel.TravelDTO;
import com.picmap.app.util.Scroller;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileManager fileManager;

	@Autowired
	private KakaoMemberService kakaoMemberService;
	private String name = "members/";

	public int idCheck(MemberDTO memberDTO) throws Exception {
		return memberDAO.idCheck(memberDTO);
	}
	public int phoneCheck(MemberDTO memberDTO) throws Exception {
		return memberDAO.phoneCheck(memberDTO);
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

		if (null == files.getOriginalFilename() || files.getOriginalFilename().equals("")) {
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
	public void logout(HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO.getMemberId().matches("\\d+")) {
			// RestTemplate 생성
			RestTemplate restTemplate = new RestTemplate();

			// 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			headers.add("Authorization", " KakaoAK d693b449c3106f1b661f277b3f220971");

			// 파라미터 설정
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("target_id_type", "user_id");
			params.add("target_id", memberDTO.getMemberId());

			// HttpEntity 생성 (헤더와 본문 포함)
			HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<MultiValueMap<String, String>>(
					params, headers);

			// 카카오 토큰 요청
			String url = "https://kapi.kakao.com/v1/user/logout";
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, kakaoTokenRequest,
					String.class);
			System.out.println(response);

			url = "https://kapi.kakao.com/v1/user/unlink";
			response = restTemplate.exchange(url, HttpMethod.POST, kakaoTokenRequest,
					String.class);
			
			System.out.println(response);
		}
		// 세션 무효화
		session.setAttribute("member", null);
		;
	}

	// 마이페이지
	public MemberDTO mypage(MemberDTO memberDTO) throws Exception {

		return memberDTO;
	}

	public MemberDTO detail(MemberDTO memberDTO) throws Exception {
		return memberDAO.detail(memberDTO);
	}

	public int update(MemberDTO memberDTO, HttpSession session) throws Exception {

		int result = memberDAO.update(memberDTO);
		return result;

	}

	public String deleteMember(MemberDTO memberDTO, HttpSession httpSession) throws Exception {
		// 카카오 회원일 경우 세션 초기화
		logout(httpSession); // 카카오 로그아웃 및 세션 초기화

		int num = delete(memberDTO);
		if (num > 0) {
			return "계정이 삭제되었습니다.";
		} else {
			return "계정 삭제 실패.";
		}
	}

	public int delete(MemberDTO memberDTO) throws Exception {
		// memberId가 모두 숫자로 이루어졌는지 확인
		if (memberDTO.getMemberId().matches("\\d+")) {
			// memberId가 숫자만으로 이루어진 경우 kakaoDelete 메서드 실행
			memberDAO.kakaoDelete(memberDTO);
		}

		// 랜덤 UUID 생성하여 멤버 정보 수정
		UUID random = UUID.randomUUID();
		String randomNum = random.toString();
		memberDTO.setMemberPassword(randomNum);
		memberDTO.setMemberId(randomNum);
		memberDTO.setMemberName(randomNum);
		
		memberDAO.memberFollowDelete(memberDTO);
		
		// 기본 delete 메서드 실행
		return memberDAO.delete(memberDTO);
	}

	public int follow(FollowDTO followDTO, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		MemberDTO memberDTO2 = new MemberDTO();
		memberDTO2.setMemberNum(followDTO.getToFollow());
		memberDTO2 = memberDAO.detail(memberDTO2);
		if (memberDTO2.getMemberEmail() == null) {
			return 0;
		}
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

	// 게시판(게시글 리스트)
	public List<TravelDTO> getList(MemberDTO memberDTO, Scroller scroller) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberDTO", memberDTO);
		map.put("scroller", scroller);
		return memberDAO.getList(map);
	}

	public Long getPostCountByMember(MemberDTO memberDTO) throws Exception {
		return memberDAO.getPostCountByMember(memberDTO);
	}

	public Long getSavePostCountByMember(MemberDTO memberDTO) throws Exception {
		return memberDAO.getSavePostCountByMember(memberDTO);
	}

	// 아이디 찾기 서비스
	public MemberDTO findID(MemberDTO memberDTO) throws Exception {
		return memberDAO.findID(memberDTO);
	}

	// 비번 찾기 서비스
	public MemberDTO findPassword(MemberDTO memberDTO) throws Exception {
		return memberDAO.findPassword(memberDTO);
	}

	public int proFileUpdate(MemberDTO memberDTO, MultipartFile files, HttpSession session) throws Exception {
		// 세션에서 MemberDTO 가져오기
		MemberDTO sessionMemberDTO = (MemberDTO) session.getAttribute("member");

		if (sessionMemberDTO != null) {
			// 세션에서 가져온 MemberDTO의 memberNum 설정
			memberDTO.setMemberNum(sessionMemberDTO.getMemberNum());
		} else {
			throw new Exception("세션에 회원 정보가 없습니다.");
		}
		System.out.println("세션에서 가져온 memberNum: " + sessionMemberDTO.getMemberNum());
		// 파일 저장 경로 설정
		ServletContext servletContext = session.getServletContext();
		String path = servletContext.getRealPath("resources/upload/" + name);

		FileManager fm = new FileManager();

		if (files == null || files.getOriginalFilename().isEmpty()) {
			// 파일이 업로드되지 않은 경우 기존 경로를 그대로 사용합니다.
			// 아무 것도 하지 않음으로써 기존 경로를 유지합니다.
		} else {
			// 파일이 업로드된 경우에만 새로운 파일을 저장하고 경로를 업데이트합니다.
			String fileName = fm.fileSave(files, path);
			memberDTO.setProfilePath("/resources/upload/members/" + fileName);
		}

		// DB 업데이트
		return memberDAO.proFileUpdate(memberDTO);

	}
}