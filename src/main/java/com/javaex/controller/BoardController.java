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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String listForm(Model model) {
		List<Map<String, Object>> pList = boardService.getPostList();
		model.addAttribute("pList", pList);
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
	@ResponseBody
	@RequestMapping(value="write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute(value="postVo") PostVo postVo,
			@RequestPart(value = "file", required = false) List<MultipartFile> fileList) {
		String result = boardService.write(postVo, fileList);
		System.out.println(result);
		return result;
	}
	
	
	//게시글 수정 폼
	@RequestMapping(value="modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @PathVariable int no) {
		//게시글 가져오기
		Map<String, Object> pMap = boardService.modifyForm(no);
		model.addAttribute("pMap", pMap);
		return "board/modifyForm";
	}
	
	
	//게시글 수정
	@RequestMapping(value="modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PostVo postVo) {
		boardService.modify(postVo);
		return "redirect:/board/read/"+postVo.getPostNo();
	}
	
	
	//게시글 삭제
	@ResponseBody
	@RequestMapping(value="delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="postNo") int no) {
		return boardService.delete(no);
	}
	
}
