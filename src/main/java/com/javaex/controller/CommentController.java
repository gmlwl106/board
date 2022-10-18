package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;

@Controller
@RequestMapping(value="comment")
public class CommentController {
	
	@Autowired
	CommentService cmtService;
	
	//댓글 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="getCmtList", method = {RequestMethod.GET, RequestMethod.POST})
	public List<Map<String, Object>> getCmtList(@RequestParam(value="postNo") int postNo) {
		return cmtService.getCmtList(postNo);
	}
	
	//댓글 가져오기
	@ResponseBody
	@RequestMapping(value="getCmt", method = {RequestMethod.GET, RequestMethod.POST})
	public CommentVo getCmt(@RequestParam(value="cmtNo") int cmtNo) {
		return cmtService.getCmt(cmtNo);
	}

	//댓글 등록
	@ResponseBody
	@RequestMapping(value="write", method= {RequestMethod.GET, RequestMethod.POST})
	public CommentVo write(@ModelAttribute(value="cmtVo") CommentVo cmtVo) {
		System.out.println(cmtVo);
		return cmtService.write(cmtVo);
	}
	
	//댓글 수정
	@ResponseBody
	@RequestMapping(value="modifyCmt", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyCmt(@ModelAttribute(value="cmtVo") CommentVo cmtVo) {
		System.out.println(cmtVo);
		return cmtService.modifyCmt(cmtVo);
	}
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value="deleteCmt", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteCmt(@RequestParam(value="cmtNo") int cmtNo) {
		return cmtService.deleteCmt(cmtNo);
	}
}
