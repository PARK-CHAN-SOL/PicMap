package com.picmap.app.ping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picmap.app.travel.TravelDTO;

@Service
public class PingService {
	@Autowired
	private PingDAO pingDAO;

	public int addPing(PingDTO pingDTO) throws Exception {
		return pingDAO.addPing(pingDTO);
	}
	
	public Long savePingNum() throws Exception {
		return pingDAO.savePingNum();
	}

	// 지번으로 핑 검색
	public Map<String, Object> getPingList(PingDTO pingDTO) throws Exception {
		// pingDTO에 address에 검색할 주소를 담아서 전달
		List<PingDTO> pingList = pingDAO.getPingList(pingDTO);
		List<TravelDTO> travelList = pingDAO.getTravelList(pingList);

		Map<Long, PingDTO> pingMap = new HashMap<Long, PingDTO>();

		Double lat = 0.0;
		Double lon = 0.0;
		Double minLat = 1000.0;
		Double maxLat = 0.0;
		Double minLon = 1000.0;
		Double maxLon = 0.0;

		// 검색 결과가 존재한다면 위도, 경도의 평균 값 계산
		if (pingList.size() != 0) {
			for (PingDTO dto : pingList) {
				pingMap.put(dto.getPingNum(), dto);
				Double latTmp = dto.getLatitude();
				Double lonTmp = dto.getLongitude();
				lat += latTmp;
				lon += lonTmp;
				if (minLat > latTmp) minLat = latTmp;
				if (maxLat < latTmp) maxLat = latTmp;
				if (minLon > latTmp) minLon = lonTmp;
				if (maxLon < latTmp) maxLon = lonTmp;
			}

			lat = lat / pingList.size();
			lon = lon / pingList.size();

		}

		pingDTO.setLatitude(lat);
		pingDTO.setLongitude(lon);

		// 가장 먼 핑들 간의 거리 계산 (lat, lon 변수 재활용)
		// 위도 37도에서 경도 1도 : 88.804km
		// 위도 1도 : 111.3194km
		lat = (maxLat - minLat) * 88804;
		lon = (maxLon - minLon) * 111319;
		Integer length = (int) Math.sqrt(Math.pow(lat, 2) + Math.pow(lon, 2));

		// 계산된 거리를 바탕으로 지도 크기(level) 설정
		Integer level;
		if (length < 80) level = 1;
		else if (length < 120) level = 2;
		else if (length < 200) level = 3;
		else if (length < 400) level = 4;

		int lengthTmp = 1000;
		while (lengthTmp <= length) {
			lengthTmp <<= 1;
		}

		switch (lengthTmp) {
		case 1000:
			level = 5;
			break;
		case 2000:
			level = 6;
			break;
		case 4000:
			level = 7;
			break;
		case 8000:
			level = 8;
			break;
		case 16000:
			level = 9;
			break;
		case 32000:
			level = 10;
			break;
		case 64000:
			level = 11;
			break;
		case 128000:
			level = 12;
			break;
		case 256000:
			level = 13;
			break;
		case 512000:
			level = 14;
			break;
		default:
			level = 14;
			break;
		}

		// 지도정보를 맵에 담아서 리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lat", pingDTO.getLatitude());
		map.put("lon", pingDTO.getLongitude());
		map.put("level", level);
		map.put("pingMap", pingMap);
		map.put("travelList", travelList);

		return map;
	}

	// 추천 게시글 리스트 검색
	public String getRecommendList(PingDTO pingDTO) throws Exception {
		pingDTO = getDetail(pingDTO);
		List<TravelDTO> list = pingDAO.getRecommendList(pingDTO);
		StringBuilder sb = new StringBuilder();

		sb.append("");
		// 최대 4개 까지 추천 게시글 이미지(<img>) 출력,
		// a태그로 각 img태그를 묶어서 /travel/detail ... 으로 링크
		if (list.size() != 0) {
			for (TravelDTO dto : list) {
				System.out.println(dto.getBoardNum());
				sb.append("<a href=\"/travel/detail?boardNum=").append(dto.getBoardNum())
						.append("\"><img src=\"/resources/assets/img/").append(dto.getFileName() == null ? "default1.png" : dto.getFileName()).append("\" /></a>");
			}
		}

		return sb.toString();
	}

	public PingDTO getDetail(PingDTO pingDTO) throws Exception {
		return pingDAO.getDetail(pingDTO);
	}
	
	
}
