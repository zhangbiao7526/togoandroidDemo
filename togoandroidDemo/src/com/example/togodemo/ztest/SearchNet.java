package com.example.togodemo.ztest;

import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.togodemo.myApplication;
import com.example.togodemo.classifyLayout.ClassifySearch;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchNet {
	public static String SEARCH_URL=VARIABLE.HOMEVIPAGER_URI8;
	public static void getDataBySearch(final FragmentActivity fragmentActivity, String string){
		AjaxParams params = new AjaxParams();
		params.put("search_info", string);
		FinalHttp fh=new FinalHttp();
		fh.post(SEARCH_URL, params, new AjaxCallBack<Object>() {
			
			@Override
			public void onSuccess(Object t) {
				super.onSuccess(t);
				Gson g=new Gson();
				List<ShopInfo> list_shops=g.fromJson((String) t, new TypeToken<List<ShopInfo>>(){}.getType());
				myApplication ma=(myApplication) fragmentActivity.getApplication();
				ma.setList_goodshop(list_shops);
				Toast.makeText(fragmentActivity, list_shops.toString(), 1000).show();
				//在线程跳转，在线程外跳转易出现延迟造成空指针
				Intent intent=new Intent(fragmentActivity,ClassifySearch.class);
				fragmentActivity.startActivity(intent);
			}
		});
	}

}
