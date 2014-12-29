package com.example.togodemo.mode;

public class UserShopCar {

	int f_i_Sid;
	String f_c_username;
	int f_i_Caddnum;
	
	ShopInfo shopInfo;
	
	/**
	 * 标识是否可以删除,也是标识是否选中
	 */
	private boolean canRemove = true;


	public boolean isCanRemove() {
		return canRemove;
	}

	public void setCanRemove(boolean canRemove) {
		this.canRemove = canRemove;
	}

	public UserShopCar(int title, boolean canRemove) {
		this.f_i_Caddnum = title;
		this.canRemove = canRemove;
	}
	
	public int getF_i_Sid() {
		return f_i_Sid;
	}

	public void setF_i_Sid(int f_i_Sid) {
		this.f_i_Sid = f_i_Sid;
	}

	public String getF_c_username() {
		return f_c_username;
	}

	public void setF_c_username(String f_c_username) {
		this.f_c_username = f_c_username;
	}

	public int getF_i_Caddnum() {
		return f_i_Caddnum;
	}

	public void setF_i_Caddnum(int f_i_Caddnum) {
		this.f_i_Caddnum = f_i_Caddnum;
	}

	public ShopInfo getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(ShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}

	public UserShopCar(int f_i_Sid, String f_c_username, int f_i_Caddnum,
			ShopInfo shopInfo) {
		super();
		this.f_i_Sid = f_i_Sid;
		this.f_c_username = f_c_username;
		this.f_i_Caddnum = f_i_Caddnum;
		this.shopInfo = shopInfo;
	}

	public UserShopCar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
