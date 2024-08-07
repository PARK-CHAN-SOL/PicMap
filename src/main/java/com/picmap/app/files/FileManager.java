package com.picmap.app.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SessionAttributeMethodArgumentResolver;

import com.picmap.app.member.MemberDTO;
@Component
public class FileManager {
//HDD에 파일 저장
	//fileSave
	//1. 저장할 폴더 지정

public String fileSave (MultipartFile mf, String path)throws Exception {
		
		File file = new File(path);
		
		//1. 파일 디렉토리 확인
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//2. 파일명 지정
		String fileName = UUID.randomUUID().toString();
		
		String oriName = mf.getOriginalFilename();
		fileName = fileName + "_^@@^_" + oriName; 
		
		//3. HDD에 파일 저장
		File f = new File(file, fileName);
		mf.transferTo(f);
		
		return fileName;
		
}
}
