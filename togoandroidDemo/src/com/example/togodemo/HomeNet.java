package com.example.togodemo;

import java.lang.reflect.Type;
import java.util.List;

import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class HomeNet {

	public static void Buy_Moreshop(final FragmentActivity activity,
			final View[] buy_moreshop) {
		FinalHttp fh=new FinalHttp();
		AjaxParams params=new AjaxParams();
		params.put("method", "find_buymoreshop");
		
		fh.post(VARIABLE.BUYMORE_SHOP, params, new AjaxCallBack<Object>() {
			
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				Gson g=new Gson();
				//将集合字符串转换成集合
				Type typeOfT=new TypeToken<List<ShopInfo>>(){}.getType();
				myApplication my=(myApplication) activity.getApplication();
				List<ShopInfo> list=my.list_buymoreshop;
				list=g.fromJson((String) t, typeOfT);
				my.setList_buymoreshop(list);
				for(ShopInfo s:list){
				System.out.println("HomeNet:"+s.getF_c_Simagpath()+","+s.getF_c_Sname());
				}
			}
		});
	}
	
}
