package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileDao {

	@Autowired
	private SqlSession sqlSession;

	//파일 추가
	public int insertFile(FileVo fileVo) {
		return sqlSession.insert("files.insert", fileVo);
	}

	//파일 가져오기
	public List<FileVo> getFile(int no) {
		return sqlSession.selectList("files.getFile", no);
	}

	//파일 경로 가져오기
	public String getFilePath(int no) {
		return sqlSession.selectOne("files.getFilePath", no);
	}

	//파일 이름 가져오기
	public String getName(int no) {
		return sqlSession.selectOne("files.getName", no);
	}
}
