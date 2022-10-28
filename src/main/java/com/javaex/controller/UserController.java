package com.javaex.controller;

import javax.servlet.http.HttpSession;

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
	private UserService userService;

	//회원가입 폼
	@RequestMapping(value="joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		//userService.getUser();
		return "user/joinForm";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value="idCheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String idCheck(@ModelAttribute UserVo userVo) {
		return userService.idCheck(userVo);
	}
	
	//회원 등록
	@RequestMapping(value="join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinOk";
	}
	
	//회원가입 성공 폼
	@RequestMapping(value="joinOk", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinOk() {
		return "user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value="loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(HttpSession session, @ModelAttribute UserVo userVo) {
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) {
			System.out.println("<<로그인 성공>>");
			
			//세션에 저장
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
		
		System.out.println("<<로그인 실패>>");
		return "redirect:/user/loginForm?result=fail";
	}
	
	//로그아웃
	@RequestMapping(value="logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		//세션 삭제
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
}
