package com.picmap.app.heart;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeartDAO {
	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "com.picmap.app.heart.HeartDAO.";

	public Integer heartUp(HeartDTO heartDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "heartUp", heartDTO);
	}

	public Integer heartDown(HeartDTO heartDTO) throws Exception {
		return sqlSession.delete(NAMESPACE + "heartDown", heartDTO);
	}

	public Long heartCheck(HeartDTO heartDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "heartCheck", heartDTO);
	}

	public Long heartCount(HeartDTO heartDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "heartCount", heartDTO);
	}
}
