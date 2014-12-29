package com.example.togodemo.ztest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.togodemo.myApplication;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessNet {
	/**
	 * 该改
	 */
	static String uri = VARIABLE.HOMEVIPAGER_URI7;

	public static void getDataFromSQL_zuidi(final FragmentActivity businessFragment,
			final List<TextView> tv_list, final List<TextView> tv_desclist,final FinalBitmap fb,
			final ImageView[] imageview) {

		FinalHttp fh = new FinalHttp();
		AjaxParams params = new AjaxParams();
		
		final myApplication my=(myApplication) businessFragment.getApplication();
		final List<HashMap<String,ShopInfo>> list_hasp_zuidi=new ArrayList<HashMap<String,ShopInfo>>();
		params.put("method", "find_busness_zuidi");
			fh.post(VARIABLE.BUYMORE_SHOP, params, new AjaxCallBack<Object>() {

				@Override
				public void onSuccess(Object t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);
					
						Gson g = new Gson();
						// 将集合字符串转换成集合
						Type typeOfT = new TypeToken<List<ShopInfo>>() {
						}.getType();
						
//						List<ShopInfo> lists = my.list_buymoreshop;
						List<ShopInfo> list = g.fromJson((String) t, typeOfT);
						for(int i=0;i<3;i++){
							fb.display(imageview[i], VARIABLE.IMAGE_URL+list.get(i).getF_c_Simagpath());
							tv_list.get(i).setText("单价："+list.get(i).getF_d_Ssprice());
							tv_desclist.get(i).setText("活动结束日期："+list.get(i).getBusiness().getF_d_Bend());
						}
						for(int i=0;i<list.size();i++){
							HashMap<String,ShopInfo> hasp=new HashMap<String, ShopInfo>();
							hasp.put("haoping", list.get(i));
							list_hasp_zuidi.add(hasp);
							my.setList_shopinfos(list_hasp_zuidi);
							//Toast.makeText(businessFragment,"单个hasp"+list_hasp_zuidi.get(i).get("haoping").toString() , Toast.LENGTH_SHORT).show();
						}
						
//						Toast.makeText(businessFragment,"单个hasp1"+my.getList_shopinfos().get(0).get("haoping").toString()+"集合长度"+list_hasp.size() , Toast.LENGTH_SHORT).show();
//						Toast.makeText(businessFragment,"单个hasp2"+list_hasp.get(1).get("haoping").toString() , Toast.LENGTH_SHORT).show();
						//Toast.makeText(businessFragment, list.get(0).toString(), Toast.LENGTH_SHORT).show();
				}
				});
		
	}

	public static void getDataFromSQL_baoyou(final FragmentActivity businessFragment,
			final List<TextView> tv_list, final List<TextView> tv_desclist,final FinalBitmap fb,
			final ImageView[] imageview) {

		FinalHttp fh = new FinalHttp();
		AjaxParams params = new AjaxParams();
		
		final myApplication my=(myApplication) businessFragment.getApplication();
		final List<HashMap<String,ShopInfo>> list_hasp_baoyou=new ArrayList<HashMap<String,ShopInfo>>();
		params.put("method", "find_busness_baoyou");
			fh.post(VARIABLE.BUYMORE_SHOP, params, new AjaxCallBack<Object>() {

				@Override
				public void onSuccess(Object t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);
					
						Gson g = new Gson();
						// 将集合字符串转换成集合
						Type typeOfT = new TypeToken<List<ShopInfo>>() {
						}.getType();
						//List<ShopInfo> list = my.list_buymoreshop;
						List<ShopInfo> list = g.fromJson((String) t, typeOfT);
						for(int i=0;i<3;i++){
							fb.display(imageview[i], VARIABLE.IMAGE_URL+list.get(i).getF_c_Simagpath());
							tv_list.get(i+3).setText("单价："+list.get(i).getF_d_Ssprice());
							tv_desclist.get(i+3).setText("活动结束日期："+list.get(i).getBusiness().getF_d_Bend());
						}
						for(int i=0;i<list.size();i++){
							HashMap<String,ShopInfo> hasp=new HashMap<String, ShopInfo>();
							hasp.put("baoyou", list.get(i));
							list_hasp_baoyou.add(hasp);
							my.setList_shopinfos_baoyou(list_hasp_baoyou);
//							Toast.makeText(businessFragment,"包邮单个hasp"+list_hasp_baoyou.get(i).get("baoyou").toString() , Toast.LENGTH_SHORT).show();
						}
						
//						Toast.makeText(businessFragment, list.get(0).toString(), Toast.LENGTH_SHORT).show();
				}
				});
	}
	public static void getDataFromSQL_cuxiao(final FragmentActivity businessFragment,
			final List<TextView> tv_list, final List<TextView> tv_desclist,final FinalBitmap fb,
			final ImageView[] imageview) {

		FinalHttp fh = new FinalHttp();
		AjaxParams params = new AjaxParams();
		final myApplication my=(myApplication) businessFragment.getApplication();
		final List<HashMap<String,ShopInfo>> list_hasp_cuxiao=new ArrayList<HashMap<String,ShopInfo>>();
		params.put("method", "find_busness_cuxiao");
			fh.post(VARIABLE.BUYMORE_SHOP, params, new AjaxCallBack<Object>() {

				@Override
				public void onSuccess(Object t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);
					
						Gson g = new Gson();
						// 将集合字符串转换成集合
						Type typeOfT = new TypeToken<List<ShopInfo>>() {
						}.getType();
						//List<ShopInfo> list = my.list_buymoreshop;
						List<ShopInfo> list = g.fromJson((String) t, typeOfT);
						for(int i=0;i<3;i++){
							fb.display(imageview[i], VARIABLE.IMAGE_URL+list.get(i).getF_c_Simagpath());
							tv_list.get(i+6).setText("单价："+list.get(i).getF_d_Ssprice());
							tv_desclist.get(i+6).setText("活动结束日期："+list.get(i).getBusiness().getF_d_Bend());
						}
						for(int i=0;i<list.size();i++){
							HashMap<String,ShopInfo> hasp=new HashMap<String, ShopInfo>();
							hasp.put("cuxiao", list.get(i));
							list_hasp_cuxiao.add(hasp);
							my.setList_shopinfos_cuxiao(list_hasp_cuxiao);
//							Toast.makeText(businessFragment,"包邮单个hasp"+list_hasp_cuxiao.get(i).get("cuxiao").toString() , Toast.LENGTH_SHORT).show();
						}
						
//						Toast.makeText(businessFragment, list.get(0).toString(), Toast.LENGTH_SHORT).show();
				}
				});
	}
}
