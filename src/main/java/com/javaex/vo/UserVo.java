package com.javaex.vo;

public class UserVo {

	private int userNo; //회원 번호
	private String name; //회원 이름
	private String id; //회원 아이디
	private String password; //회원 비밀번호
	private String gender; //회원 성별
	
	
	public UserVo() {
	}
	public UserVo(int userNo, String name, String id, String password, String gender) {
		this.userNo = userNo;
		this.name = name;
		this.id = id;
		this.password = password;
		this.gender = gender;
	}
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	@Override
	public String toString() {
		return "UsersVo [userNo=" + userNo + ", name=" + name + ", id=" + id + ", password=" + password + ", gender="
				+ gender + "]";
	}
	
}
