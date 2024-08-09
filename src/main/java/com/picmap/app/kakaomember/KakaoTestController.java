package com.picmap.app.kakaomember;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/kakaoTest/*")
public class KakaoTestController {
	
	@PostMapping("img")
	@ResponseBody
	public String kakaoTest(String files)throws Exception {
		System.out.println(files);
		return "kakaoTest"; 
	}
	@GetMapping("img")
	public void kakaoTestPage()throws Exception{
		
	}
}


