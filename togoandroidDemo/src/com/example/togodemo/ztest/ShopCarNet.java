package com.example.togodemo.ztest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.myApplication;
import com.example.togodemo.ishopcar.DemoAdapter;
import com.example.togodemo.mode.UserShopCar;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ShopCarNet {
	
	public static void selectShopcar(final FragmentActivity activity,
			String username,final ListView lvListView,final DemoAdapter adpAdapter, final FinalBitmap bm, final myApplication my) {
		// TODO Auto-generated method stub
		
		try {
			AjaxParams params = new AjaxParams();
			//params.put("search_info", string);
			FinalHttp fh=new FinalHttp();
			params.put("method", "selectShopcar");
			params.put("newuser", URLEncoder.encode(username,"utf-8"));
			fh.post(VARIABLE.SELECT_ONESHOP, params, new AjaxCallBack<Object>() {
				
				@Override
				public void onSuccess(Object t) {
					super.onSuccess(t);
					Gson g=new Gson();
					List<UserShopCar> list_shops=g.fromJson((String) t, new TypeToken<List<UserShopCar>>(){}.getType());
				
					//在线程跳转，在线程外跳转易出现延迟造成空指针
					if(list_shops!=null){
//						Toast.makeText(activity, list_shops.toString(), 1000).show();
						DemoAdapter adapter=new DemoAdapter(activity, list_shops,bm,my);
						
						myApplication my=(myApplication) activity.getApplication();
						my.setDemoadapter(adapter);
						lvListView.setAdapter(adapter);
					}else{
						Toast.makeText(activity, "空值", 1000).show();
					}
					
				}
			});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void addShopCar(final OneSop_click oneSop_click, int shopid, String username, int shopnum) {
		try {
			AjaxParams params = new AjaxParams();
			//params.put("search_info", string);
			FinalHttp fh=new FinalHttp();
			params.put("method", "addShopcar");
			params.put("newuser", URLEncoder.encode(username,"utf-8"));
			params.put("shopid", String.valueOf(shopid));
			params.put("shopnum", String.valueOf(shopnum));
			fh.post(VARIABLE.SELECT_ONESHOP, params, new AjaxCallBack<Object>() {
				
				@Override
				public void onSuccess(Object t) {
					super.onSuccess(t);
					if(t==null){
						Toast.makeText(oneSop_click, "连接超时", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(oneSop_click, "加入购物车成功", Toast.LENGTH_SHORT).show();
					}
//					if("true".equals(t)){
//						Toast.makeText(oneSop_click, "加入购物车成功", Toast.LENGTH_SHORT).show();
//					}else{
//						Toast.makeText(oneSop_click, "加入购物车失败", Toast.LENGTH_SHORT).show();
//					}
					
				}
			});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	public static void deleteShopcar(final FragmentActivity activity, int shopid, String username) {
		try {
		AjaxParams params = new AjaxParams();
		//params.put("search_info", string);
		FinalHttp fh=new FinalHttp();
		params.put("method", "delShopCar");
		params.put("deleteid", String.valueOf(shopid));
		params.put("newuser", URLEncoder.encode(username,"utf-8"));
		fh.post(VARIABLE.SELECT_ONESHOP, params, new AjaxCallBack<Object>() {
			
			@Override
			public void onSuccess(Object t) {
				super.onSuccess(t);
				if(t==null){
					Toast.makeText(activity, "连接超时", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(activity, "删除成功", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}
