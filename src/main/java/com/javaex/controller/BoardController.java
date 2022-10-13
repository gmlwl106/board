package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

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
	@RequestMapping(value="read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String readForm(Model model, @PathVariable int no) {
		//게시글 가져오기
		Map<String, Object> pMap = boardService.getPost(no);
		model.addAttribute("pMap", pMap);
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
	
	//게시글 등록
	@RequestMapping(value="write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PostVo postVo) {
		String result = boardService.write(postVo);
		
		if(result == "success") {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/write?result=fail";
		}
	}
	
}
