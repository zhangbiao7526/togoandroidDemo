package com.example.togodemo;

import java.util.ArrayList;
import java.util.List;

import com.example.togodemo.ztest.shop;


import android.app.Application;
/**
 * 全局变量。待开发中，application比整个工程的生命周期都长
 * @author Administrator
 *
 */
public class myApplication extends Application {
	//判断用户是否是已登录状态
	private boolean USER_LOGIN;
	//缓存记录用户购买的商品
	public List<shop> list_shop=new ArrayList<shop>();
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

	public boolean isUSER_LOGIN() {
		return USER_LOGIN;
	}

	public void setUSER_LOGIN(boolean uSER_LOGIN) {
		USER_LOGIN = uSER_LOGIN;
	}
	
	public List<shop> getList_shop() {
		return list_shop;
	}

	public void setList_shop(List<shop> list_shop) {
		this.list_shop = list_shop;
	}

}
