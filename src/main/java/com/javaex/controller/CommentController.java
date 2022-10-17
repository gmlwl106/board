package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;

@Controller
@RequestMapping(value="comment")
public class CommentController {
	
	@Autowired
	CommentService cmtService;

	//댓글 등록
	@ResponseBody
	@RequestMapping(value="write", method= {RequestMethod.GET, RequestMethod.POST})
	public CommentVo write(@ModelAttribute(value="cmtVo") CommentVo cmtVo) {
		System.out.println(cmtVo);
		return cmtService.write(cmtVo);
	}
}
