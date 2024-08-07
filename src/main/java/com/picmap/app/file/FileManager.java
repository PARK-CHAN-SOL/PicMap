package com.picmap.app.file;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	//하드에 파일 저장
	public String fileSave(String path, MultipartFile multipartFile) throws Exception {
		
		//저장 경로 설정
		File file = new File(path);
		
		if (!file.exists()) { // service에서 path에 설정한 파일 저장 경로가 실존하지 않는다면 폴더를 만들기 
			file.mkdirs();
		}
		
		//저장
		String fileName = UUID.randomUUID().toString(); // UUID가 유니버스 모시꺵. 중복되지 않게 파일 이름 설정
		fileName = fileName + "_" + multipartFile.getOriginalFilename();
		
		file = new File(file, fileName); // 14줄에서 설정한 저장 경로와, 22줄에서 정한 파일 이름으로 새로운 파일 객체를 만들고 
		multipartFile.transferTo(file); // 고놈을 실제 저장
		
		return fileName;
		
	}

}
