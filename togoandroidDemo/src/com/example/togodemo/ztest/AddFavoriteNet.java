package com.example.togodemo.ztest;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.togodemo.variable.VARIABLE;

public class AddFavoriteNet {

	static String AddFavorite_URL=VARIABLE.HOMEVIPAGER_URI10;
	public static void addFavoriteData(Integer f_i_Sid,String f_c_username){
		AjaxParams params = new AjaxParams();
		params.put("f_i_Sid",f_i_Sid+"");
		params.put("f_c_username", f_c_username);
		
		FinalHttp fh=new FinalHttp();
		fh.post(AddFavorite_URL, params, new AjaxCallBack<Object>() {
			
			@Override
			public void onSuccess(Object t) {
				super.onSuccess(t);
//				Gson g=new Gson();
//				List<ShopInfo> list_shops=g.fromJson((String) t, new TypeToken<List<ShopInfo>>(){}.getType());
////				myApplication ma=(myApplication) activity.getApplication();
////				ma.setShopinfos(list_shops);
//				Toast.makeText(activity, list_shops.toString(), 1000).show();
//				//在线程跳转，在线程外跳转易出现延迟造成空指针
//				if(list_shops!=null){
//					NewAdapter adapter=new NewAdapter(activity, list_shops, fb);
//					listview.setAdapter(adapter);
//				}else{
//					Toast.makeText(activity, "空值", 1000).show();
//				}
//				
  		    }
		});
	
	}
}
