package com.example.togodemo.user;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.selfcenter.NewAdapter_dingdan;
import com.example.selfcenter.self_allorders;
import com.example.togodemo.Main_Activity;
import com.example.togodemo.Shop_goods;
import com.example.togodemo.ShopcarFragment;
import com.example.togodemo.Shopgoods_successful;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.Indent;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.mode.User;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NetUtil {

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

	public static void uploadDataByGetLogin(final User_Login context,
			final HashMap<String, String> map, final String edt_login_name) {

		// System.out.println(edt_name);
		AsyncTask<Void, Void, String> at = new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				// 1.客户端，相当于一个浏览器，可以设置请求头
				HttpClient hc = new DefaultHttpClient();
				// 3.说明请求类型
				HttpPost post = new HttpPost(VARIABLE.LOGIN);

				try {
					// 需要一个list集合
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();
					String edt_name = map.get("username");
					String edt_password = map.get("userpassword");

					// 6.设置发送的数据
					parameters.add(new BasicNameValuePair("UserName",
							URLEncoder.encode(edt_name, "utf-8")));
					parameters.add(new BasicNameValuePair("UserPassword",
							URLEncoder.encode(edt_password, "utf-8")));
					parameters.add(new BasicNameValuePair("method",
							"USER_LOGIN"));
					// 5.表单提交，存放一个集合parameters
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
							parameters);

					// 4.设置发送实体数据
					post.setEntity(entity);
					HttpResponse res;
					res = hc.execute(post);// 2.发送请求
					if (res.getStatusLine().getStatusCode() == 200) {
						// 获得一个输入流
						InputStream is = res.getEntity().getContent();
						byte[] data = convertInputStreamByteArray(is);
						String back = new String(data);
						return back;
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// res.getStatusLine();//获得状态行

				return null;
				// AsyncTask<Void, Void, String> at=new AsyncTask<Void, Void,
				// String>(){
				//
				// @Override
				// protected String doInBackground(Void... params) {
				// // TODO Auto-generated method stub
				// InputStream is=null;
				// try {
				// String edt_name=map.get("username");
				// String edt_password=map.get("userpassword");
				// System.out.println("edt_name="+edt_name+"edt_password="+edt_password);
				// //数据的传输是从地址栏过去的,传到get请求
				// URL url=new
				// URL(VARIABLE.LOGIN+"?UserName="+edt_name+"&UserPassword="+edt_password+"&method=USER_LOGIN");
				// System.out.println(url.toString());
				// HttpURLConnection conn=(HttpURLConnection)
				// url.openConnection();
				// conn.setRequestMethod("GET");
				// conn.setConnectTimeout(5000);//请求超时，5秒
				//
				// if(conn.getResponseCode()==200){
				// is=conn.getInputStream();
				// //在上面已经做了一个封装
				// // byte[] buffer=new byte[1024];
				// // int len;
				// // ByteArrayOutputStream bos=new ByteArrayOutputStream();
				// // while((len=is.read(buffer))>0){
				// // bos.write(buffer, 0, len);
				// // }
				// byte[]data=convertInputStreamByteArray(is);
				// String back=new String(data);
				// return back;
				// }
				// } catch (MalformedURLException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// return null;
			}

			/**
			 * 不要用返回值，若需要在控件显示，则将控件传递过来
			 * 
			 * @return
			 */
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if (result == null) {
					Toast.makeText(context, "网络超时", Toast.LENGTH_SHORT).show();
				}
				if ("true".equals(result)) {
					Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
					// Intent intent=new Intent(context,aaa.class);
					// panduan(0);
					// context.startActivity(intent);
					
					//12/26,用户登录后才去查找用户的地址与用户名
					NetUtil.select_Useraddress(context,map.get("username"));
					myApplication my = (myApplication) context.getApplication();
					my.setUSER_LOGIN(true);
					my.setUser_name(edt_login_name);
					context.finish(0);
					System.out.println("2," + my.isUSER_LOGIN());
				} else if ("false".equals(result)) {
					Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT)
							.show();

				}else if("alreadylogin".equals(result)){
					Toast.makeText(context, "该用户已在其他地方登陆！", Toast.LENGTH_SHORT).show();
				}
			}
		};
		at.execute();
	}

	//12/25添加用户地址
	public static void addUserAddress(final Context shop_goods, 
			final String username,final String f_c_useraddress,final String f_c_userphone) {
		FinalHttp fh = new FinalHttp();

		AjaxParams param = new AjaxParams();
		param.put("method", "userAddress");
		try {
			param.put("UserName",
					URLEncoder.encode(username, "utf-8"));
			param.put("f_c_useraddress",
					URLEncoder.encode(f_c_useraddress, "utf-8"));
			param.put("f_c_userphone",
					URLEncoder.encode(f_c_userphone, "utf-8"));
			}catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {
				int i;
				@Override
				public void onSuccess(Object t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);
						Gson g = new Gson();
						// 将集合字符串转换成集合
						Type typeOfT = new TypeToken<List<ShopInfo>>() {
						}.getType();
//						myApplication my = (myApplication) activity.getApplication();
//						List<ShopInfo> list = my.list_buymoreshop;
//						list = g.fromJson((String) t, typeOfT);
					
					}
			});
		}
	//12/25添加用户地址FragmentCar
	public static void addUserAddress_car(final ShopcarFragment shopcarFragment, 
			final String username,final String f_c_useraddress,final String f_c_userphone) {
		FinalHttp fh = new FinalHttp();
		
		AjaxParams param = new AjaxParams();
		param.put("method", "userAddress");
		try {
			param.put("UserName",
					URLEncoder.encode(username, "utf-8"));
			param.put("f_c_useraddress",
					URLEncoder.encode(f_c_useraddress, "utf-8"));
			param.put("f_c_userphone",
					URLEncoder.encode(f_c_userphone, "utf-8"));
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {
			int i;
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				Gson g = new Gson();
				// 将集合字符串转换成集合
				Type typeOfT = new TypeToken<List<ShopInfo>>() {
				}.getType();
//						myApplication my = (myApplication) activity.getApplication();
//						List<ShopInfo> list = my.list_buymoreshop;
//						list = g.fromJson((String) t, typeOfT);
				
			}
		});
	}
	//12/25//订单的添加
		public static void addUserIndent(final Shop_goods shop_goods, String username,String shopid,
				String user_address, String shop_name, String shop_num,
				String shop_price, String arrive_phone, String peisong, final String allmoney) {
			FinalHttp fh = new FinalHttp();
			myApplication my=(myApplication) shop_goods.getApplication();
			AjaxParams param = new AjaxParams();
			param.put("method", "addUserIndent");
			try {
				param.put("username",
						URLEncoder.encode(username, "utf-8"));
				param.put("shopid",
						URLEncoder.encode(shopid, "utf-8"));
				param.put("user_address",
						URLEncoder.encode(user_address, "utf-8"));
				param.put("shop_name",
						URLEncoder.encode(shop_name, "utf-8"));
				param.put("shop_num",
						URLEncoder.encode(shop_num, "utf-8"));
				param.put("shop_price",
						URLEncoder.encode(shop_price, "utf-8"));
				param.put("arrive_phone",
						URLEncoder.encode(arrive_phone, "utf-8"));
				param.put("peisong",
						URLEncoder.encode(peisong, "utf-8"));
				
				NetUtil.select_Useraddress(shop_goods,my.getUser_name());
				}catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {

					@Override
					public void onSuccess(Object t) {
						// TODO Auto-generated method stub
						super.onSuccess(t);
//						Intent intent_s=new Intent(shop_goods,Shopgoods_successful.class);
						
						Intent intent_s=new Intent();
						intent_s.putExtra("tv_shopgoods1_price", allmoney);
						intent_s.setClass(shop_goods,Shopgoods_successful.class);
//						startActivity(intent_s);
						shop_goods.startActivity(intent_s);
//						Toast.makeText(shop_goods, ""+intent_s.toString(), Toast.LENGTH_LONG).show();
						}
				});
			}
		//12/25//Fragment订单的添加
//		public static void addUserIndent_car(ShopcarFragment shopcarFragment, String username,String shopid,
//				String user_address, String shop_name, String shop_num,
//				String shop_price) {
		public static void addUserIndent_car(ShopcarFragment shopcarFragment,
				String username, String shopid, String shop_name,
				String shop_num, String shop_price, String peisong) {
			FinalHttp fh = new FinalHttp();
			AjaxParams param = new AjaxParams();
			param.put("method", "shopcar_addshop");
			try {
				param.put("username",
						URLEncoder.encode(username, "utf-8"));
				param.put("shopid",
						URLEncoder.encode(shopid, "utf-8"));
				param.put("shop_name",
						URLEncoder.encode(shop_name, "utf-8"));
				param.put("shop_num",
						URLEncoder.encode(shop_num, "utf-8"));
				param.put("shop_price",
						URLEncoder.encode(shop_price, "utf-8"));
				param.put("peisong",
						URLEncoder.encode(peisong, "utf-8"));
			}catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {
				
				@Override
				public void onSuccess(Object t) {
					super.onSuccess(t);
				}
			});
		}
		//12/26查看用户订单
		public static void select_Indent(final self_allorders self_allorders, 
				final String username, final ListView listview, final FinalBitmap fm, final ImageView intent_none) {
			FinalHttp fh = new FinalHttp();

			AjaxParams param = new AjaxParams();
			param.put("method", "select_indent");
			try {
				param.put("username",
						URLEncoder.encode(username, "utf-8"));
				}catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {

					@Override
					public void onSuccess(Object t) {
						super.onSuccess(t);
						if(t!=null){
							Gson g = new Gson();
							// 将集合字符串转换成集合
							Type typeOfT = new TypeToken<ArrayList<Indent>>() {
							}.getType();
							List<Indent> list_indent=new ArrayList<Indent>();
							list_indent=g.fromJson((String) t, typeOfT);
							
//							System.out.println("图片"+list_indent.get(0).getShopInfo().getF_c_Simagpath());
//							Toast.makeText(self_allorders, "数量："+list_indent.get(0).getF_i_Inum()
//									+",商品名："+list_indent.get(0).getF_c_Ishopname()+",单价："+list_indent.get(0).getShopInfo().getF_d_Ssprice()
//									+","+",应付款"+list_indent.get(0).getF_c_IshouldPay(), Toast.LENGTH_SHORT).show();
							if(list_indent.size()==0){
							//没有合适的图片
								//	intent_none.setVisibility(View.VISIBLE);
							}
//							intent_none.setVisibility(View.GONE);
							NewAdapter_dingdan	adapters=new NewAdapter_dingdan(self_allorders, list_indent, fm);
							listview.setAdapter(adapters);
						}
					}
				});
			}
		//12/26查看用户地址
		public static void select_Useraddress(final Activity user_Login,final String username) {
			FinalHttp fh = new FinalHttp();

			AjaxParams param = new AjaxParams();
			param.put("method", "select_Address");
			try {
				param.put("username",
						URLEncoder.encode(username, "utf-8"));
				}catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {

					@Override
					public void onSuccess(Object t) {
						super.onSuccess(t);
						if(t!=null){
							Gson g = new Gson();
							// 将集合字符串转换成集合
							Type typeOfT = new TypeToken<User>() {
							}.getType();
							User user=new User();
							user=g.fromJson((String) t, typeOfT);
							myApplication my=(myApplication) user_Login.getApplication();
							my.setUser_address(""+user.getF_c_useraddress());
							my.setUser_phone(""+user.getF_c_userphone());
//							Toast.makeText(user_Login, my.getUser_name()+","+my.getUser_address(), Toast.LENGTH_SHORT).show();					
						}
						}
				});
				
			}

		public static void uploadDataByHttpClientFile(
				final Main_Activity main_Activity, File file) {
			FinalHttp fh = new FinalHttp();
			AjaxParams param = new AjaxParams();
			try {
				param.put("method", "addfile");
				param.put("filename", file);
				myApplication my= (myApplication) main_Activity.getApplication();
//				String username=my.getUser_name();
//				param.put("username",URLEncoder.encode(username, "utf-8"));
				}
			catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {

					@Override
					public void onSuccess(Object t) {
						// TODO Auto-generated method stub
						super.onSuccess(t);
						if(t!=null){
							Gson g = new Gson();
							// 将集合字符串转换成集合
//							Toast.makeText(main_Activity, "上传成功", Toast.LENGTH_SHORT).show();					
						}
						}
				});
				
			}

}