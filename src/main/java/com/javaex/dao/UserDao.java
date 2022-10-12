package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	//id 중복체크
	public int selectId(UserVo userVo) {
		System.out.println(userVo);
		return sqlSession.selectOne("users.selectId", userVo);
	}
}
