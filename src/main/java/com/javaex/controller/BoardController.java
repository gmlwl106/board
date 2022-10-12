package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="board")
public class BoardController {

	//게시판 리스트 폼
	@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
	public String listForm() {
		
		return "board/list";
	}
	
	
	//게시글 폼
	@RequestMapping(value="read", method= {RequestMethod.GET, RequestMethod.POST})
	public String readForm() {
		
		return "board/read";
	}
	
}
