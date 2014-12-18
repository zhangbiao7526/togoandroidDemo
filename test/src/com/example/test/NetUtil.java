package com.example.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class NetUtil {
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
	public static void uploadDataByGetLogin(final Context context,
			final HashMap<String, String> map) {
		//System.out.println(edt_name);
		AsyncTask<Void, Void, String> at=new AsyncTask<Void, Void, String>(){

			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				InputStream is=null;
				try {
					String edt_name=map.get("username");
					String edt_password=map.get("userpassword");
					System.out.println("edt_name="+edt_name+"edt_password="+edt_password);
					//数据的传输是从地址栏过去的,传到get请求
					URL url=new URL(VARIABLE.LOGIN+"?UserName="+edt_name+"&UserPassword="+edt_password+"&method=USER_LOGIN");
					System.out.println(url.toString());
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);//请求超时，5秒
					
					if(conn.getResponseCode()==200){
						is=conn.getInputStream();
						//在上面已经做了一个封装
//						   byte[] buffer=new byte[1024];
//						   int len;
//						   ByteArrayOutputStream bos=new ByteArrayOutputStream();
//						   while((len=is.read(buffer))>0){
//							 bos.write(buffer, 0, len);  
//						   }
						byte[]data=convertInputStreamByteArray(is);	
						   String back=new String(data);
						   return back;
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			/**
			 * 不要用返回值，若需要在控件显示，则将控件传递过来
			 */
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(result==null){
					Toast.makeText(context, "网络超时", Toast.LENGTH_SHORT).show();
				}
				if("true".equals(result)){
					Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
				}else if("false".equals(result)){
					Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();

				}
			}
		};
		at.execute();
}
}
