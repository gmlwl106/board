package com.javaex.vo;

public class PostVo {
	private int postNo; //글번호
	private int cateNo; //카테고리 번호
	private String title; //글 제목
	private int userNo; //회원 번호
	private String regDate; //작성일
	private int hit; //조회수
	private String content; //글 내용
	
	
	public PostVo() {
	}
	public PostVo(int postNo, int cateNo, String title, int userNo, String regDate, int hit, String content) {
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.title = title;
		this.userNo = userNo;
		this.regDate = regDate;
		this.hit = hit;
		this.content = content;
	}
	
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", cateNo=" + cateNo + ", title=" + title + ", userNo=" + userNo
				+ ", regDate=" + regDate + ", hit=" + hit + ", content=" + content + "]";
	}
	
	
	
}
