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
	public int idCheck(UserVo userVo) {
		return sqlSession.selectOne("users.idCheck", userVo);
	}

	//회원 추가
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao->"+userVo);
		return sqlSession.insert("users.insert", userVo);
	}
	
	//로그인(회원 확인)
	public UserVo selectID(UserVo userVo) {
		return sqlSession.selectOne("users.selectID", userVo);
	}
}
