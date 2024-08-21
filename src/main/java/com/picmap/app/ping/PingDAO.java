package com.picmap.app.ping;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.travel.TravelDTO;

@Repository
public class PingDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.ping.PingDAO.";
	
	public int addPing(PingDTO pingDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "addPing", pingDTO);
	}
	
	public int updatePing(PingDTO pingDTO) throws Exception {
		return sqlSession.update(NAMESPACE + "updatePing", pingDTO);
	}
	
	public Long savePingNum() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"savePingNum");
	}
	
	public List<PingDTO> getPingList(PingDTO pingDTO) throws Exception {
		return sqlSession.selectList(NAMESPACE + "getPingList", pingDTO);
	}
	
	public List<PingDTO> getMyPingList(List<TravelDTO> travelList) throws Exception {
		return sqlSession.selectList(NAMESPACE + "getMyPingList", travelList);
	}
	
	public List<TravelDTO> getTravelList (List<PingDTO> list) throws Exception {
		return sqlSession.selectList(NAMESPACE + "getTravelList", list);
	}
	
	public List<TravelDTO> getRecommendList(PingDTO pingDTO) throws Exception {
		return sqlSession.selectList(NAMESPACE + "getRecommendList", pingDTO);
	}
	
	public PingDTO getDetail(PingDTO pingDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "getDetail", pingDTO);
	}
}
