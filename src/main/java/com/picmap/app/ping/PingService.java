package com.picmap.app.ping;

import java.util.ArrayList;
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
	
	public int updatePing(PingDTO pingDTO) throws Exception {
		return pingDAO.updatePing(pingDTO);
	}

	public Long savePingNum() throws Exception {
		return pingDAO.savePingNum();
	}

	// 지번으로 핑 검색
	public Map<String, Object> getPingList(PingDTO pingDTO) throws Exception {
		// pingDTO에 address에 검색할 주소를 담아서 전달
		List<PingDTO> pingList = pingDAO.getPingList(pingDTO);
		List<TravelDTO> travelList = pingDAO.getTravelList(pingList);

		return setMap(pingList, travelList);
	}

	// 마이페이지에 띄울 맵,
	// memberNum으로 게시글 검색, 게시글로 핑 검색
	public Map<String, Object> getMyPingList(List<TravelDTO> travelList) throws Exception {
		List<PingDTO> pingList = pingDAO.getMyPingList(travelList);
		
		return setMap(pingList, travelList);
	}

	// 추천 게시글 리스트 검색
	public Map<String, Object> getRecommendList(PingDTO pingDTO) throws Exception {
		pingDTO = pingDAO.getDetail(pingDTO);
//		List<TravelDTO> list = pingDAO.getRecommendList(pingDTO);
		TravelDTO travelDTO = pingDAO.getTravelDetail(pingDTO);
		
		List<TravelDTO> travelList = pingDAO.getRecommendList(pingDTO);
		List<PingDTO> pingList = new ArrayList<PingDTO>();
		if(travelList.size() != 0) {
			pingList = pingDAO.getMyPingList(travelList);
		}
		
		pingList.add(pingDTO);
		travelList.add(travelDTO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pingList", pingList);
		map.put("travelList", travelList);
		
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("");
//		// 최대 4개 까지 추천 게시글 이미지(<img>) 출력,
//		// a태그로 각 img태그를 묶어서 /travel/detail ... 으로 링크
//		if (list.size() != 0) {
//			for (TravelDTO dto : list) {
//				sb.append("<a href=\"/travel/detail?boardNum=").append(dto.getBoardNum())
//						.append("\"><img src=\"/resources/upload/travels/")
//						.append(dto.getFileName() == null ? "default.png" : dto.getFileName()).append("\" class=\"rounded m-4\" style=\"width:150px; height:150px;\" /></a>");
//			}
//		}
//
//		return sb.toString();
		
		return map;
	}
	
	public Map<String, Object> setMap(List<PingDTO> pingList, List<TravelDTO> travelList) throws Exception {

		// 리턴 값을 담을 맵 선언
		Map<String, Object> map = new HashMap<String, Object>();
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

		map.put("lat", lat);
		map.put("lon", lon);

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
		else if (length < 400) level = 3;

		int lengthTmp = 1000;
		while (lengthTmp <= length) {
			lengthTmp <<= 1;
		}

		switch (lengthTmp) {
		case 1000:
			level = 4;
			break;
		case 2000:
			level = 5;
			break;
		case 4000:
			level = 6;
			break;
		case 8000:
			level = 7;
			break;
		case 16000:
			level = 8;
			break;
		case 32000:
			level = 9;
			break;
		case 64000:
			level = 10;
			break;
		case 128000:
			level = 11;
			break;
		case 256000:
			level = 12;
			break;
		case 512000:
			level = 13;
			break;
		default:
			level = 14;
			break;
		}

		// 지도정보를 맵에 담아서 리턴
		map.put("level", level);
		map.put("pingMap", pingMap);
		map.put("travelList", travelList);

		return map;

	}

}
