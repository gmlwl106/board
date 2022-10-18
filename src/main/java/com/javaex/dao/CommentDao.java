package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	private SqlSession sqlSession;
	
	//댓글 리스트 가져오기
	public List<Map<String, Object>> getCmtList(int postNo) {
		return sqlSession.selectList("comments.getCmtList", postNo);
	}

	//댓글 등록
	public int insertCmt(CommentVo cmtVo) {
		return sqlSession.insert("comments.insertCmt", cmtVo);
	}

	//댓글 가져오기
	public CommentVo getCmt(int cmtNo) {
		return sqlSession.selectOne("comments.getCmt", cmtNo);
	}

	//댓글 수정
	public int updateCmt(CommentVo cmtVo) {
		return sqlSession.update("comments.updateCmt", cmtVo);
	}

	
}
