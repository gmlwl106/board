package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	private SqlSession sqlSession;

	//댓글 등록
	public int insertCmt(CommentVo cmtVo) {
		return sqlSession.insert("comments.insertCmt", cmtVo);
	}
}
