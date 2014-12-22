package com.example.togodemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	public static byte[] convertInputStreamByteArray(InputStream is) {
		byte[] buffer = new byte[1024];
		int len;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			while ((len = is.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bos.toByteArray();

	}

	// 判断用户是否是已登录状态
	private boolean USER_LOGIN;
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
	// 获取从数据库返回的集合对象
	private List<ShopInfo> list_shopinfos;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

	}

	public boolean isUSER_LOGIN() {
		return USER_LOGIN;
	}

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

	public void setUSER_LOGIN(boolean uSER_LOGIN) {
		USER_LOGIN = uSER_LOGIN;
	}

	public List<shop> getList_shop() {
		return list_shop;
	}

	public void setList_shop(List<shop> list_shop) {
		this.list_shop = list_shop;
	}

	public List<ShopInfo> getList_shopinfos() {
		return list_shopinfos;
	}

	public void setList_shopinfos(List<ShopInfo> list_shopinfos) {
		this.list_shopinfos = list_shopinfos;
	}

}
