package com.picmap.app.ping;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PingDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.picmap.app.ping.PingDAO.";
	
	public int addPing(PingDTO pingDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "addPing", pingDTO);
	}
	
	public List<PingDTO> searchPing(PingDTO pingDTO) throws Exception {
		return sqlSession.selectList(NAMESPACE + "searchPing", pingDTO);
	}
}
