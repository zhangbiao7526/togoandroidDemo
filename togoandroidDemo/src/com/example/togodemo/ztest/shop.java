package com.example.togodemo.ztest;

public class shop {

	private  String shopname;//??????
	private  String shopsomething;//????????
	private String shoppic;//????????
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getShopsomething() {
		return shopsomething;
	}
	public void setShopsomething(String shopsomething) {
		this.shopsomething = shopsomething;
	}
	public String getShoppic() {
		return shoppic;
	}
	public void setShoppic(String shoppic) {
		this.shoppic = shoppic;
	}
	public shop(String shopname, String shopsomething, String shoppic) {
		super();
		this.shopname = shopname;
		this.shopsomething = shopsomething;
		this.shoppic = shoppic;
	}
	public shop() {
		super();
	}
	
	
}
