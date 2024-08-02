package com.picmap.app.ping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingService {
	@Autowired
	private PingDAO pingDAO;
	
	public int addPing(PingDTO pingDTO) throws Exception {
		return pingDAO.addPing(pingDTO);
	}
}
