package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	@Autowired
	private UserService userSevice;

	//회원가입 폼
	@RequestMapping(value="joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value="idCheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String idCheck(@ModelAttribute UserVo userVo) {
		return userSevice.idCheck(userVo);
	}
	
	//로그인 폼
	@RequestMapping(value="loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		
		return "user/loginForm";
	}
}
