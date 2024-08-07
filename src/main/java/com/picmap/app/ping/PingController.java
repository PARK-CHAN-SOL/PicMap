package com.picmap.app.ping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ping/")
public class PingController {
	@Autowired
	private PingService pingService;

	@GetMapping("map")
	public void map() throws Exception {

	}

	@PostMapping("addPing")
	public String addPing(PingDTO pingDTO) throws Exception {
		Long pingNum = pingService.savePingNum();
		pingDTO.setPingNum(pingNum);
		
		int result = pingService.addPing(pingDTO);
		String s = "성공";
		if (result == 0) s = "실패";
		System.out.println(s);
		return "/ping/map";
	}

	// 검색한 지번과 유사한 지역의 지도를 띄움
	@PostMapping("getPingList")
	@ResponseBody
	public Map<String, Object> getPingList(PingDTO pingDTO) throws Exception {
		System.out.println(pingDTO.getAddress());

		return pingService.getPingList(pingDTO);
	}
	
	
	// 위도 latitude +- 0.0011 만큼 검색 (가로 세로 250m 범위)
	// 경도 longitude +- 0.0014
	// 리턴은 HTML 형식(String), 비동기 형식으로 호출됨
	@PostMapping("getRecommendList")
	@ResponseBody
	public String getRecommendList(PingDTO pingDTO) throws Exception {
		return pingService.getRecommendList(pingDTO);
	}
	
}
