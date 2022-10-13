package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	//게시글 등록
	public String write(PostVo postVo) {
		postVo.setContent(postVo.getContent().replace("\r\n", "<br>")); //줄바꿈
		int cnt = postDao.insertPost(postVo);
		
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	//게시글 가져오기
	public Map<String, Object> getPost(int no) {
		//조회수 올리기
		postDao.updateHit(no);
		
		//게시글 상세내용 가져오기
		Map<String, Object> post = postDao.getPost(no);
		
		//map에 담기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("post", post);
		//댓글
		
		return map;
	}

	//게시글 리스트 가져오기
	public List<Map<String, Object>> getPostList() {
		return postDao.getPostList();
	}

	

}
