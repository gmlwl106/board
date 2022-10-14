package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.FileDao;

@Service
public class DownloadService {

	@Autowired
	private FileDao fileDao;

	//파일 경로 가져오기
	public String getFilePath(int no) {
		return fileDao.getFilePath(no);
	}
	
	
}
