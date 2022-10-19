package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao cmtDao;
	
	//댓글 리스트 가져오기
	public List<Map<String, Object>> getCmtList(int postNo) {
		return cmtDao.getCmtList(postNo);
	}
	
	//댓글 가져오기
	public Map<String, Object> getCmt(int cmtNo) {
		return cmtDao.getCmt(cmtNo);
	}

	//댓글 등록
	public Map<String, Object> write(CommentVo cmtVo) {

		//댓글 등록
		int cnt = cmtDao.insertCmt(cmtVo);
		
		if(cnt > 0) {
			//등록한 댓글 가져오기
			return cmtDao.getCmt(cmtVo.getCmtNo());
		}
		
		return null;
	}

	//댓글 수정
	public String modifyCmt(CommentVo cmtVo) {
		int cnt = cmtDao.updateCmt(cmtVo);
		
		if(cnt > 0) {
			return "success";
		}
		
		return "fail";
	}

	//댓글 삭제
	public String deleteCmt(int cmtNo) {
		
		//댓글인지 답글인지 확인
		int depth = cmtDao.getDepth(cmtNo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cmtNo", cmtNo);
		map.put("depth", depth);
		
		int cnt = cmtDao.deleteCmt(map);
		
		if(cnt > 0) {
			return "success";
		}
		return "fail";
	}

	
	//답글 등록
	public Map<String, Object> writeReply(CommentVo cmtVo) {
		int cnt = cmtDao.insertReply(cmtVo);
		
		if(cnt > 0) {
			return cmtDao.getCmt(cmtVo.getCmtNo());
		}
		
		return null;
	}

	

	

	
}
