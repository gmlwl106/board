package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
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

	
	//게시판 리스트 폼 (+검색)
	@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
	public String listForm(Model model,
			@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage,
			@RequestParam(value="kwdOpt", required = false, defaultValue = "") String kwdOpt,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword) {
		Map<String, Object> boardMap = boardService.getPostList(crtPage, kwdOpt, keyword);
		model.addAttribute("boardMap", boardMap);
		return "board/list";
	}
	
	
	//게시글 폼
	@RequestMapping(value="read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String readForm(Model model, @PathVariable int no,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage,
			@RequestParam(value="kwdOpt", required = false, defaultValue = "") String kwdOpt,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword) {
		
		
		if(session.getAttribute("authUser") != null) {
			//쿠키로 조회수 중복 방지
			Cookie oldCookie = null;
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("postView")) {
						oldCookie = cookie;
					}
				}
			}
			
			if(oldCookie != null) {
				if(!oldCookie.getValue().contains("[" + no + "]")) {
					//해당 글을 조회하지 않았을때
					//조회수 증가
					boardService.viewHitUp(no);
					oldCookie.setValue(oldCookie.getValue()+"[" + no + "]");
					oldCookie.setPath("/");
					oldCookie.setMaxAge(60);
					response.addCookie(oldCookie);
				}
			} else {
				//생성된 쿠키가 없을때
				//조회수 증가
				boardService.viewHitUp(no);
				Cookie newCookie = new Cookie("postView","[" + no + "]");
				newCookie.setPath("/");
				newCookie.setMaxAge(60);
				response.addCookie(newCookie);
			}
		
		
			
			//게시글 가져오기
			Map<String, Object> pMap = boardService.getPost(no);
			model.addAttribute("pMap", pMap);
			model.addAttribute("crtPage", crtPage);
			model.addAttribute("kwdOpt", kwdOpt);
			model.addAttribute("keyword", keyword);
			return "board/read";
		}
		
		return "redirect:/user/loginForm";
	}
	
	
	//글쓰기 폼
	@RequestMapping(value="writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(Model model, HttpSession session) {
		if(session.getAttribute("authUser") != null) {
			//카테고리 리스트 가져오기
			List<CategoryVo> cateList = boardService.getCategory();
			model.addAttribute("cateList", cateList);
			return "board/writeForm";
		}
		return "redirect:/user/loginForm";
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
		System.out.println("delete");
		return boardService.delete(no);
	}
	
	
	//통계 게시판
	@RequestMapping(value="stat", method = {RequestMethod.GET, RequestMethod.POST})
	public String stat() {
		return "board/statistics";
	}
	
	//통계 게시판2
	@RequestMapping(value="stat2", method = {RequestMethod.GET, RequestMethod.POST})
	public String stat2(Model model, @RequestParam(value="month", required = false, defaultValue = "all") String month) {
		JSONArray arr = boardService.getData(month);
		model.addAttribute("arr", arr);
		return "board/statistics2";
	}
	
	//통계 게시판3
	@RequestMapping(value="stat3", method = {RequestMethod.GET, RequestMethod.POST})
	public String stat3(Model model, @RequestParam(value="month", required = false, defaultValue = "all") String month) {
		//System.out.println("stat3");
		//JSONArray arr = boardService.getData2(month);
		//model.addAttribute("arr", arr);
		return "board/statistics3";
	}
	
}
