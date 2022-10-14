package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileDao {

	@Autowired
	private SqlSession sqlSession;

	//파일 저장
	public int insertFile(FileVo fileVo) {
		return sqlSession.insert("files.insert", fileVo);
	}
}
