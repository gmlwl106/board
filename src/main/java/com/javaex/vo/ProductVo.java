package com.javaex.vo;

public class ProductVo {

	private int proNum;
	private int proType1;
	private int proType2;
	private String userId;
	private int proPrice;
	private String proTitle;
	private String proWrite;
	private String proPhoto;
	private String proPic;
	private String proDate;
	private int amount;
	private int sellAmount;
	private int proStatus;
	
	
	public ProductVo() {
	}
	public ProductVo(int proNum, int proType1, int proType2, String userId, int proPrice, String proTitle,
			String proWrite, String proPhoto, String proPic, String proDate, int amount, int sellAmount,
			int proStatus) {
		this.proNum = proNum;
		this.proType1 = proType1;
		this.proType2 = proType2;
		this.userId = userId;
		this.proPrice = proPrice;
		this.proTitle = proTitle;
		this.proWrite = proWrite;
		this.proPhoto = proPhoto;
		this.proPic = proPic;
		this.proDate = proDate;
		this.amount = amount;
		this.sellAmount = sellAmount;
		this.proStatus = proStatus;
	}
	
	
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public int getProType1() {
		return proType1;
	}
	public void setProType1(int proType1) {
		this.proType1 = proType1;
	}
	public int getProType2() {
		return proType2;
	}
	public void setProType2(int proType2) {
		this.proType2 = proType2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProPrice() {
		return proPrice;
	}
	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}
	public String getProTitle() {
		return proTitle;
	}
	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}
	public String getProWrite() {
		return proWrite;
	}
	public void setProWrite(String proWrite) {
		this.proWrite = proWrite;
	}
	public String getProPhoto() {
		return proPhoto;
	}
	public void setProPhoto(String proPhoto) {
		this.proPhoto = proPhoto;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public String getProDate() {
		return proDate;
	}
	public void setProDate(String proDate) {
		this.proDate = proDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSellAmount() {
		return sellAmount;
	}
	public void setSellAmount(int sellAmount) {
		this.sellAmount = sellAmount;
	}
	public int getProStatus() {
		return proStatus;
	}
	public void setProStatus(int proStatus) {
		this.proStatus = proStatus;
	}
	
	
	@Override
	public String toString() {
		return "ProductVo [proNum=" + proNum + ", proType1=" + proType1 + ", proType2=" + proType2 + ", userId="
				+ userId + ", proPrice=" + proPrice + ", proTitle=" + proTitle + ", proWrite=" + proWrite
				+ ", proPhoto=" + proPhoto + ", proPic=" + proPic + ", proDate=" + proDate + ", amount=" + amount
				+ ", sellAmount=" + sellAmount + ", proStatus=" + proStatus + "]";
	}
	
	
}
