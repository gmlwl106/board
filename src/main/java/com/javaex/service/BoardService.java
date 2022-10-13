package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BoardService {
	
	@Autowired
	private CategoryDao cateDao;
	@Autowired
	private PostDao postDao;

	//카테고리 가져오기
	public List<CategoryVo> getCategory() {
		return cateDao.getCategory();
	}

	//글 등록
	public String write(PostVo postVo) {
		int cnt = postDao.insertPost(postVo);
		
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	

}
