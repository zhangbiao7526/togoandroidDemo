package com.example.togodemo.ztest;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.togodemo.home.OneSop_click;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;

public class shop_NetUtil {
	public static void getHome_ViewPage(final OneSop_click homeF_vpage_click,final Intent in,
			final FinalBitmap fm, final TextView tv_oneshop_shopname, final TextView tv_oneshop_money, 
			final TextView tv_oneshop_type,final TextView tv_oneshop_address, 
			final TextView tv_oneshop_description, final ImageView iv_){
		FinalHttp fh=new FinalHttp();
		AjaxParams params=new AjaxParams();
		params.put("method", "find_Homeimg");
		params.put("Homeimg", in.getStringExtra("img_name"));
		//post 有3个参数
		fh.post(VARIABLE.SELECT_ONESHOP,params, new AjaxCallBack<Object>(){
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				System.out.println("开始请求FinalServlet");
			}
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				//System.out.println("请求FinalServlet成功"+t);
				//tv.setText(t.toString());//接收反馈回来的数据
				//Toast.makeText(homeF_vpage_click, t.toString(), Toast.LENGTH_SHORT).show();
				
				Gson g=new Gson();
				ShopInfo shop=g.fromJson((String) t, ShopInfo.class);
				String img_uri=VARIABLE.HOME_URI+shop.getF_c_Simagpath();
				fm.display(iv_, img_uri);
				tv_oneshop_shopname.setText(shop.getF_c_Sname());
				tv_oneshop_money.setText("$"+shop.getF_d_Ssprice());
				tv_oneshop_type.setText(shop.getF_c_Stype());
				tv_oneshop_address.setText(shop.getF_c_Saddress());
				tv_oneshop_description.setText(shop.getF_c_Sdescription());
//				Intent in=new Intent(Register.this,Login.class);
//				startActivity(in);
			}
		});
	}

}
