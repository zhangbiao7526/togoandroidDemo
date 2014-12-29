package com.example.togodemo.ztest;

import java.util.List;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.togodemo.home.NewAdapter;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CollectNet {
		
		static String COLLECT_URL=VARIABLE.HOMEVIPAGER_URI9;
		public static void getDataByCollect(final Activity activity,
				final ListView listview,final FinalBitmap fb){
			AjaxParams params = new AjaxParams();
			//params.put("search_info", string);
			FinalHttp fh=new FinalHttp();
			fh.post(COLLECT_URL, params, new AjaxCallBack<Object>() {
				
				@Override
				public void onSuccess(Object t) {
					super.onSuccess(t);
					Gson g=new Gson();
					List<ShopInfo> list_shops=g.fromJson((String) t, new TypeToken<List<ShopInfo>>(){}.getType());
//					myApplication ma=(myApplication) activity.getApplication();
//					ma.setShopinfos(list_shops);
//					Toast.makeText(activity, list_shops.toString(), 1000).show();
					//在线程跳转，在线程外跳转易出现延迟造成空指针
					if(list_shops!=null){
						NewAdapter adapter=new NewAdapter(activity, list_shops, fb);
						listview.setAdapter(adapter);
					}else{
						Toast.makeText(activity, "空值", 1000).show();
					}
					
				}
			});
		}
}
