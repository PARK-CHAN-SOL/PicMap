package com.picmap.app.travel;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.picmap.app.board.BoardDTO;
import com.picmap.app.board.BoardFileDTO;
import com.picmap.app.board.BoardService;
import com.picmap.app.file.FileManager;

@Service
public class TravelService implements BoardService{
	
	@Autowired
	private TravelDAO travelDAO;
	
	@Autowired
	private FileManager fileManager;
	
	
	
	//게시판(게시글 리스트)
	@Override
	public List<BoardDTO> getList() throws Exception {
		// TODO Auto-generated method stub
		return travelDAO.getList();
	}


	//게시글 작성
	@Override
	public int add(BoardDTO boardDTO, MultipartFile[] files, HttpSession session) throws Exception {
		
		int result = travelDAO.add(boardDTO);
		
		//저장 경로 설정
		String path = session.getServletContext().getRealPath("resources/upload/travel");
		
		if (files == null) { //첨부한 파일이 없으면 걍 없이 리턴
			return result;
		}
		
		for (MultipartFile f : files) {
			if(f.isEmpty()) {	
				continue;
			}
			
			//FileManger 클래스의 fileSave메서드를 이용해 실제 하드에 파일을 저장하고, 리턴 받은 파일 이름 값을 DB에 담을 것임
			String fileName = fileManager.fileSave(path, f);
			
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setOriName(f.getOriginalFilename());
			
			result = travelDAO.addFile(boardFileDTO);
		
		}
		return result;

	}


	//게시글 수정
	@Override
	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
}
