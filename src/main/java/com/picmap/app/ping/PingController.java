package com.picmap.app.ping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		int result = pingService.addPing(pingDTO);
		String s = "성공";
		if(result == 0) s = "실패";
		System.out.println(s);
		return "/ping/map";
	}
}
