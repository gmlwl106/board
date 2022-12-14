package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	
	//게시글 등록
	public int insertPost(PostVo postVo) {
		System.out.println("postDao->"+postVo);
		return sqlSession.insert("post.insert", postVo);
	}


	//게시글 가져오기
	public Map<String, Object> getPost(int no) {
		return sqlSession.selectOne("post.getPost", no);
	}


	//조회수 올리기
	public int updateHit(int no) {
		return sqlSession.update("post.updateHit", no);
	}


	//게시글 리스트 가져오기
	public List<Map<String, Object>> getPostList(Map<String, Object> map) {
		return sqlSession.selectList("post.getPostList", map);
	}


	//게시글 수정
	public int updatePost(PostVo postVo) {
		return sqlSession.update("post.updatePost", postVo);
	}


	//게시글 삭제
	public int deletePost(int no) {
		System.out.println("deletePost");
		return sqlSession.delete("post.delete", no);
	}


	//전체글 갯수
	public int getTotalCnt(Map<String, Object> map) {
		return sqlSession.selectOne("post.getTotalCnt", map);
	}
}
