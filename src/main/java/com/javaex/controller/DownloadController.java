package com.javaex.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.DownloadService;

@RestController
public class DownloadController {
	
	@Autowired
	private DownloadService downService;
	
	//파일 다운로드
	@RequestMapping(value="download/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public void download(HttpServletResponse response,
				@PathVariable(value="no") int no) throws Exception {
		System.out.println("DownloadController->download("+no+")");
		
		//파일 경로 가져오기
		String path = downService.getFilePath(no);
		
		try {
			
		File file = new File(path);
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		// 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
		
		
		FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기 
		OutputStream out = response.getOutputStream();
		
		int read = 0;
		byte[] buffer = new byte[1024];
		//1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
		while ((read = fileInputStream.read(buffer)) != -1) { 
			out.write(buffer, 0, read);
		}
		
		fileInputStream.close();
		        
		} catch (Exception e) {
			throw new Exception("download error");
		}
	}
}
