package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ProductVo;

@Repository
public class ProductDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	//제품 추가
	public int insertProduct(ProductVo proVo) {
		return sqlSession.insert("product.insert", proVo);
	}
	
	

}
