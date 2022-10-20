package com.javaex.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.DownloadService;

@Controller
@RequestMapping(value="download")
public class DownloadController {
	
	@Autowired
	private DownloadService downService;
	
	//파일 다운로드
	@RequestMapping(value="file/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public void fileDownload(HttpServletResponse response,
				@PathVariable(value="no") int no) throws Exception {
		System.out.println("fileDownloadController->download("+no+")");
		downService.fileDownload(response, no);
	}
	
	
	//게시글 다운로드
	@RequestMapping(value="post/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public void postDownload(HttpServletResponse response, @PathVariable(value="no") int no) throws Exception {
		System.out.println("postDownloadController->download("+no+")");
		downService.postDownload(no, response);
	}
}
