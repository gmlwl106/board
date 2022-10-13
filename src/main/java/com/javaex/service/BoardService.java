package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class BoardService {
	
	@Autowired
	private CategoryDao cateDao;

	//카테고리 가져오기
	public List<CategoryVo> getCategory() {
		return cateDao.getCategory();
	}

	

}
