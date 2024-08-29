package com.picmap.app.kakaomember;

import java.sql.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picmap.app.member.MemberDTO;
import com.picmap.app.member.MemberService;

@Controller
public class KakaoMemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private KakaoMemberService kakaoMemberService;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code, HttpSession session) throws Exception {
        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();
        
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        // 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String> ();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "580555887802ff728f2d9f964d6ad050");
        params.add("redirect_uri", "http://43.203.172.227/auth/kakao/callback");
        params.add("code", code);
        
        // HttpEntity 생성 (헤더와 본문 포함)
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<MultiValueMap<String, String>> (params, headers);
        
        // 카카오 토큰 요청
        String url = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, kakaoTokenRequest, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
        	oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(oauthToken.getAccess_token());
        
        // 프로필 요청을 위한 헤더 설정
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        // HttpEntity 생성 (헤더만 포함, 본문은 null)
        HttpEntity<String>kakaoProfileRequest = new HttpEntity<String>(headers2);
        
        // 카카오 프로필 요청
        String url2 = "https://kapi.kakao.com/v2/user/me";
        ResponseEntity<String> response2 = restTemplate.exchange(url2, HttpMethod.POST, kakaoProfileRequest, String.class);
        System.out.println(response2.getBody());
        
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        MemberDTO memberDTO = kakaoMemberService.kakaoMemberJoinCheck(kakaoProfile.getId());
        
        if (memberDTO == null) {
            memberDTO = new MemberDTO();
            System.out.println("카카오 아이디 (번호:)" + kakaoProfile.getId());
            System.out.println("닉네임:" + kakaoProfile.getProperties().getNickname());
            System.out.println("프로필:" + kakaoProfile.getProperties().getProfile_image());
            System.out.println("이메일" + kakaoProfile.getKakao_account().getEmail());
            UUID garbagePassword = UUID.randomUUID();
            String garbagePasswordString = garbagePassword.toString();
            System.out.println("패스워드" + garbagePasswordString);
            
            memberDTO.setMemberId(kakaoProfile.getId());
            memberDTO.setMemberPassword(garbagePasswordString);
            memberDTO.setMemberEmail(kakaoProfile.getKakao_account().getEmail());
            memberDTO.setMemberName(kakaoProfile.getProperties().getNickname());
            memberDTO.setMemberNickName(kakaoProfile.getProperties().getNickname());
            Date sqlDate = Date.valueOf("1900-01-01");
            memberDTO.setMemberBirth(sqlDate);
            memberDTO.setMemberPhone(garbagePasswordString);
            memberDTO.setProfilePath(kakaoProfile.getProperties().getProfile_image());
            memberService.join(memberDTO);
            memberDTO = memberService.login(memberDTO, session);
            kakaoMemberService.kakaoMemberJoin(memberDTO);
        } else {
            memberDTO = memberService.detail(memberDTO);
            System.out.println(memberDTO.getMemberId());
            memberService.login(memberDTO, session);
        }

        return "redirect:/";
    }



    }