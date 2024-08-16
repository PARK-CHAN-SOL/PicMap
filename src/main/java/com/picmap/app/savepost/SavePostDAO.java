package com.picmap.app.savepost;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SavePostDAO {
	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "com.picmap.app.savepost.SavePostDAO.";

	public Integer savePost(SavePostDTO savePostDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "savePost", savePostDTO);
	}

	public Integer delSavedPost(SavePostDTO savePostDTO) throws Exception {
		return sqlSession.delete(NAMESPACE + "delSavedPost", savePostDTO);
	}

	public Long savePostCheck(SavePostDTO savePostDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "savePostCheck", savePostDTO);
	}
}
