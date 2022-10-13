package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping(value="board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

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
	
	
	//글쓰기 폼
	@RequestMapping(value="writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(Model model) {
		//카테고리 리스트 가져오기
		List<CategoryVo> cateList = boardService.getCategory();
		model.addAttribute("cateList", cateList);
		return "board/writeForm";
	}
	
}
