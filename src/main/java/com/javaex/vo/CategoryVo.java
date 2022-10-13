package com.javaex.vo;

public class CategoryVo {

	private int cateNo; //카테고리 번호
	private String cateName; //카테고리 이름
	
	
	public CategoryVo() {
	}
	public CategoryVo(int cateNo, String cateName) {
		this.cateNo = cateNo;
		this.cateName = cateName;
	}
	
	
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	
	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", cateName=" + cateName + "]";
	}
	
	
}
