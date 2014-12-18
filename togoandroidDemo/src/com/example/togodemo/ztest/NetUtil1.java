package com.example.togodemo.ztest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.togodemo.ShopcarFragment.NewAdapter;
import com.example.togodemo.myApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

public class NetUtil1 {
	public static final String Path="http://192.168.173.1:8080/togoProjectDemo/servlet/and_Login";
//	public static final String Path="http://10.30.6.92:8080/togoProjectDemo/servlet/and_Login";
	public static void getDataFromServer( final FragmentActivity fragmentActivity, final ListView listview,final NewAdapter newadapter) {
		
		AsyncTask<Void, Void, List<shop>> at=new AsyncTask<Void, Void, List<shop>>(){

			@Override
			protected List<shop> doInBackground(Void... params) {

				try {
					HttpClient client=new DefaultHttpClient();
					HttpGet get=new HttpGet(Path);
					HttpResponse response= client.execute(get);
					if(response.getStatusLine().getStatusCode()==200){
						InputStream in=response.getEntity().getContent();
						byte[] data=convertInputStreamByteArray(in);
						String json=new String(data);//????json??????
						Gson g=new Gson();
						List<shop> list_json=g.fromJson(json, new TypeToken<List<shop>>(){}.getType());
						return list_json;
					}
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(List<shop> result) {
				if(result !=null){
					newadapter.setData(result);
					myApplication M=(myApplication) fragmentActivity.getApplication();
					M.setList_shop(result);
					listview.setAdapter(newadapter);
				}
			}
			};
			
			at.execute();	
	}
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
}
