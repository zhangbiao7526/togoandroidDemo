package com.example.togodemo.ztest;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.widget.Toast;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.variable.VARIABLE;

public class AddFavoriteNet {

	static String AddFavorite_URL=VARIABLE.HOMEVIPAGER_URI10;
	public static void addFavoriteData(final OneSop_click oneSop_click, Integer f_i_Sid,String f_c_username){
		AjaxParams params = new AjaxParams();
		params.put("f_i_Sid",f_i_Sid+"");
		params.put("f_c_username", f_c_username);
		
		FinalHttp fh=new FinalHttp();
		fh.post(AddFavorite_URL, params, new AjaxCallBack<Object>() {
			
			@Override
			public void onSuccess(Object t) {
				super.onSuccess(t);
				
				String bool=(String) t;
				if(bool.trim().equals("false")){
					Toast.makeText(oneSop_click, "已收藏", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(oneSop_click, "收藏成功", Toast.LENGTH_SHORT).show();
				}
  		    }
		});
	
	}
}
