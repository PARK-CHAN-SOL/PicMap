package com.picmap.app.ping;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PingDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public int addPing(PingDTO pingDTO) throws Exception {
		
		return 0;
	}
}
