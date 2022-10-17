package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao cmtDao;

	//댓글 등록
	public CommentVo write(CommentVo cmtVo) {

		//댓글 등록
		int cnt = cmtDao.insertCmt(cmtVo);
		
		if(cnt > 0) {
			System.out.println(cmtVo.getCmtNo());
			//등록한 댓글 가져오기
			return cmtDao.getCmt(cmtVo.getCmtNo());
		}
		
		return null;
	}

	
}
