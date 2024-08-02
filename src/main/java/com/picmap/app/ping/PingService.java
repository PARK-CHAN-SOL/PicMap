package com.picmap.app.ping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingService {
	@Autowired
	private PingDAO pingDAO;

	public int addPing(PingDTO pingDTO) throws Exception {
		return pingDAO.addPing(pingDTO);
	}

	public PingDTO searchPing(PingDTO pingDTO) throws Exception {
		List<PingDTO> list = pingDAO.searchPing(pingDTO);
		Double lat = 0.0;
		Double lon = 0.0;
		
		System.out.println(list);
		
		if(list.size() != 0) {
			for(PingDTO dto : list) {
				lat += dto.getLatitude();
				lon += dto.getLongitude();
			}
			
			lat = lat / list.size();
			lon = lon / list.size();
			
		}
		
		pingDTO.setLatitude(lat);
		pingDTO.setLongitude(lon);
		
		return pingDTO;
	}
}
