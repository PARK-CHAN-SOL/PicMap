package com.picmap.app.ping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ping/")
public class PingController {
	@Autowired
	private PingService pingService;
	
	@GetMapping("map")
	public void map() throws Exception {
		
	}
}
