package com.picmap.app.savepost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picmap.app.travel.TravelDTO;

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
	public List<TravelDTO> savePostList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+"savePostList",map);
	}
}
