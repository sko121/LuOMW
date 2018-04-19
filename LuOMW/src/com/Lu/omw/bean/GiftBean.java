package com.Lu.omw.bean;

public class GiftBean {

	private int id;
	private int picName;
	private String giftName;
	
	public GiftBean() {
	}
	
	public GiftBean(int picName, String giftName) {
		this.picName = picName;
		this.giftName = giftName;
	}
	
	public GiftBean(int picName, String giftName, int id) {
		this.picName = picName;
		this.giftName = giftName;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int getPicName() {
		return picName;
	}
	public void setPicName(int picName) {
		this.picName = picName;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
	@Override
	public String toString() {
		return "GiftBean [id=" + id + ", picName=" + picName + ", giftName="
				+ giftName + "]";
	}
}
