package com.javaex.vo;

public class CommentVo {

	private int cmtNo; //댓글 번호
	private int postNo; //게시글 번호
	private int userNo; //회원 번호
	private String content; //댓글 내용
	private String regDate; //날짜
	private int groupNo; //댓글 그룹 번호
	
	
	public CommentVo() {
	}
	public CommentVo(int cmtNo, int postNo, int userNo, String content, String regDate, int groupNo) {
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.content = content;
		this.regDate = regDate;
		this.groupNo = groupNo;
	}
	
	
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	
	
	@Override
	public String toString() {
		return "CommentVo [cmtNo=" + cmtNo + ", postNo=" + postNo + ", userNo=" + userNo + ", content=" + content
				+ ", regDate=" + regDate + ", groupNo=" + groupNo + "]";
	}
	
	
}
