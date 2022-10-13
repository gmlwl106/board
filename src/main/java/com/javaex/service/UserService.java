package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	//아이디 중복체크
	public String idCheck(UserVo userVo) {
		int cnt = userDao.idCheck(userVo);
		
		if(cnt > 0) {
			return "fail";
		} else {
			return "success";
		}
	}

	//회원 등록
	public String join(UserVo userVo) {
		int cnt = userDao.insertUser(userVo);
		
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	//로그인
	public UserVo login(UserVo userVo) {
		return userDao.selectID(userVo);
	}

}
