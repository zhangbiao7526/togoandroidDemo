package com.example.togodemo.user;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.togodemo.myApplication;
import com.example.togodemo.udao.inter.IUserService;
import com.example.togodemo.variable.VARIABLE;


public class NetUtil {
	public IUserService iu;
	/**
	 * 将输入流转化成byte流，可封装
	 * @param is
	 * @return
	 */
	public static byte[] convertInputStreamByteArray(InputStream is){
		   byte[] buffer=new byte[1024];
		   int len;
		   ByteArrayOutputStream bos=new ByteArrayOutputStream();
		   try {
			while((len=is.read(buffer))>0){
				 bos.write(buffer, 0, len);  
			   }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is!=null){
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
			final HashMap<String, String> map) {
		
		//System.out.println(edt_name);
AsyncTask<Void, Void, String> at= new AsyncTask<Void, Void, String>(){
			
			@Override
			protected String doInBackground(Void... params) {
				//1.客户端，相当于一个浏览器，可以设置请求头
				HttpClient hc=new DefaultHttpClient();
				//3.说明请求类型
				HttpPost post=new HttpPost(VARIABLE.LOGIN);
				
				try {
					//需要一个list集合
					List<NameValuePair> parameters= new ArrayList<NameValuePair>();
					String edt_name=map.get("username");
					String edt_password=map.get("userpassword");
					
					//6.设置发送的数据
					parameters.add(new BasicNameValuePair("UserName",URLEncoder.encode(edt_name,"utf-8")));
					parameters.add(new BasicNameValuePair("UserPassword",URLEncoder.encode(edt_password,"utf-8")));
					parameters.add(new BasicNameValuePair("method","USER_LOGIN"));
					//5.表单提交，存放一个集合parameters 
					UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
					
					//4.设置发送实体数据
					post.setEntity(entity);
					HttpResponse res;
					res = hc.execute(post);//2.发送请求
					if(res.getStatusLine().getStatusCode()==200){
						//获得一个输入流
						InputStream is=res.getEntity().getContent();
						byte[]data=convertInputStreamByteArray(is);	
						   String back=new String(data);
						   return back;
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				res.getStatusLine();//获得状态行
				
				return null;
//		AsyncTask<Void, Void, String> at=new AsyncTask<Void, Void, String>(){
//
//			@Override
//			protected String doInBackground(Void... params) {
//				// TODO Auto-generated method stub
//				InputStream is=null;
//				try {
//					String edt_name=map.get("username");
//					String edt_password=map.get("userpassword");
//					System.out.println("edt_name="+edt_name+"edt_password="+edt_password);
//					//数据的传输是从地址栏过去的,传到get请求
//					URL url=new URL(VARIABLE.LOGIN+"?UserName="+edt_name+"&UserPassword="+edt_password+"&method=USER_LOGIN");
//					System.out.println(url.toString());
//					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
//					conn.setRequestMethod("GET");
//					conn.setConnectTimeout(5000);//请求超时，5秒
//					
//					if(conn.getResponseCode()==200){
//						is=conn.getInputStream();
//						//在上面已经做了一个封装
////						   byte[] buffer=new byte[1024];
////						   int len;
////						   ByteArrayOutputStream bos=new ByteArrayOutputStream();
////						   while((len=is.read(buffer))>0){
////							 bos.write(buffer, 0, len);  
////						   }
//						byte[]data=convertInputStreamByteArray(is);	
//						   String back=new String(data);
//						   return back;
//					}
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
			}
			/**
			 * 不要用返回值，若需要在控件显示，则将控件传递过来
			 * @return 
			 */
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if(result==null){
					Toast.makeText(context, "网络超时", Toast.LENGTH_SHORT).show();
				}
				if("true".equals(result)){
					Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
//					Intent intent=new Intent(context,aaa.class);
//					panduan(0);
//					context.startActivity(intent);
					myApplication my=(myApplication) context.getApplication();
					my.setUSER_LOGIN(true);
					context.finish(0);
					System.out.println("2,"+my.isUSER_LOGIN());
 				}else if("false".equals(result)){
					Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
					
				}
			}
		};
		at.execute();
  }
}