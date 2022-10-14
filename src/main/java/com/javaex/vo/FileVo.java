package com.javaex.vo;

public class FileVo {

	private int fileNo; //파일 번호
	private int postNo; //게시글 번호
	private String saveName; //저장명
	private String filePath; //파일경로
	
	
	public FileVo() {
	}
	public FileVo(int postNo, String saveName, String filePath) {
		this.postNo = postNo;
		this.saveName = saveName;
		this.filePath = filePath;
	}
	public FileVo(int fileNo, int postNo, String saveName, String filePath) {
		this.fileNo = fileNo;
		this.postNo = postNo;
		this.saveName = saveName;
		this.filePath = filePath;
	}
	
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	@Override
	public String toString() {
		return "FileVo [fileNo=" + fileNo + ", postNo=" + postNo + ", saveName=" + saveName + ", filePath=" + filePath
				+ "]";
	}
	
	
}
