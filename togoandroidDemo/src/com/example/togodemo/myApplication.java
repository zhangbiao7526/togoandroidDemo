package com.example.togodemo;

import java.util.HashMap;
import java.util.List;

import com.example.togodemo.ishopcar.DemoAdapter;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.ztest.shop;

import android.app.Application;

/**
 * 全局变量。待开发中，application比整个工程的生命周期都长
 * 
 * @author Administrator
 * 
 */
public class myApplication extends Application {
	/**
	 * 将输入流转化成byte流，可封装
	 * 
	 * @param is
	 * @return
	 */
	// 判断用户是否是已登录状态
	private boolean USER_LOGIN;
	
	//
	public String user_name;
	public String user_address;
	public String user_phone;
	// 活动fragment
	// 缓存记录用户购买的商品
	public List<shop> list_shop = null;
	/**
	 * 销量最多商品：//取3个最多的展示于首页
	 */
	public List<ShopInfo> list_buymoreshop = null;
	/**
	 * 好评最多商品，//取3个最多展示于首页(8:30改)
	 */
	public List<ShopInfo> list_goodshop = null;
	// 获取从数据库返回的集合对象，活动界面，最低价
	public List<HashMap<String, ShopInfo>> list_shopinfos;
	// 获取从数据库返回的集合对象，活动界面，包邮
	public List<HashMap<String, ShopInfo>> list_shopinfos_baoyou;
	// 获取从数据库返回的集合对象，活动界面，cuxiao
	public List<HashMap<String, ShopInfo>> list_shopinfos_cuxiao;
	//首页轮播商品对象：//12/26
	public List<ShopInfo> homge_v_shop;
	
	public DemoAdapter demoadapter;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

	}

	
	

	public boolean isUSER_LOGIN() {
		return USER_LOGIN;
	}
	//首页销量最多，好评最多
	public List<ShopInfo> getList_buymoreshop() {
		return list_buymoreshop;
	}

	public void setList_buymoreshop(List<ShopInfo> list_buymoreshop) {
		this.list_buymoreshop = list_buymoreshop;
	}

	public List<ShopInfo> getList_goodshop() {
		return list_goodshop;
	}

	public void setList_goodshop(List<ShopInfo> list_goodshop) {
		this.list_goodshop = list_goodshop;
	}
	//判断用户是否已登录
	public void setUSER_LOGIN(boolean uSER_LOGIN) {
		USER_LOGIN = uSER_LOGIN;
	}

	public List<shop> getList_shop() {
		return list_shop;
	}

	public void setList_shop(List<shop> list_shop) {
		this.list_shop = list_shop;
	}
	//活动界面
	//最低价
	public List<HashMap<String, ShopInfo>> getList_shopinfos() {
		return list_shopinfos;
	}

	public void setList_shopinfos(List<HashMap<String, ShopInfo>> list_shopinfos) {
		this.list_shopinfos = list_shopinfos;
	}
	//包邮

	public List<HashMap<String, ShopInfo>> getList_shopinfos_baoyou() {
		return list_shopinfos_baoyou;
	}
	
	public void setList_shopinfos_baoyou(
			List<HashMap<String, ShopInfo>> list_shopinfos_baoyou) {
		this.list_shopinfos_baoyou = list_shopinfos_baoyou;
	}
	//促销
	public List<HashMap<String, ShopInfo>> getList_shopinfos_cuxiao() {
		return list_shopinfos_cuxiao;
	}
	//
	public void setList_shopinfos_cuxiao(
			List<HashMap<String, ShopInfo>> list_shopinfos_cuxiao) {
		this.list_shopinfos_cuxiao = list_shopinfos_cuxiao;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	//12/25
	public List<ShopInfo> getHomge_v_shop() {
		return homge_v_shop;
	}

	public void setHomge_v_shop(List<ShopInfo> homge_v_shop) {
		this.homge_v_shop = homge_v_shop;
	}

	public DemoAdapter getDemoadapter() {
		return demoadapter;
	}

	public void setDemoadapter(DemoAdapter demoadapter) {
		this.demoadapter = demoadapter;
	}

	
}
