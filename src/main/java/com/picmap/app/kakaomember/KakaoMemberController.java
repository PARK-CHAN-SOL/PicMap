package com.picmap.app.kakaomember;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class KakaoMemberController {

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code){
        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();
        
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "580555887802ff728f2d9f964d6ad050");
        params.add("redirect_uri", "http://localhost/auth/kakao/callback");
        params.add("code", code);
        
        // HttpEntity 생성 (헤더와 본문 포함)
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        
        // 카카오 토큰 요청
        String url = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, kakaoTokenRequest, String.class);
        ObjectMapper objectMapper=new ObjectMapper();
        OAuthToken oaythToken = null;
		try {
			oaythToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonMappingException e) {
		
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
        System.out.println(oaythToken.getAccess_token());   //W5zTaf3JA4mQHQ4iF-C30RBgwHX-HazUAAAAAQoqJREAAAGRMLESrrG7d-HwzTGR
       // RestTemplate 생성
       RestTemplate restTemplate2 = new RestTemplate();
       
       // 헤더 설정
       HttpHeaders headers2 = new HttpHeaders();
//       headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       headers2.add("Authorization", "Bearer "+oaythToken.getAccess_token());
       headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
     
       // HttpEntity 생성 (헤더와 본문 포함)
       HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
       
       // 카카오 토큰 요청
       String url2 = "https://kapi.kakao.com/v2/user/me";
       ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.POST, kakaoProfileRequest, String.class);
     System.out.println(response2.getBody());
        return "kakao:"+response2.getBody();

    }
   
    }


