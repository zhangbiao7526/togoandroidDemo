package com.example.togodemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.AsyncTask;
import android.os.DeadObjectException;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessNet {
	
    static String uri=VARIABLE.HOMEVIPAGER_URI7;
	public static void getDataFromSQL(final BusinessFragment businessFragment,
			final List<TextView> tv_list,final List<TextView> tv_desclist) {
		AsyncTask<Void, Void, List<ShopInfo>> ab=new AsyncTask<Void, Void, List<ShopInfo>>() {

			@Override
			protected List<ShopInfo> doInBackground(Void... params) {
				InputStream is=null;
				try {
					HttpClient client=new DefaultHttpClient();
					//HttpGet get=new HttpGet(uri);
					HttpPost post=new HttpPost(uri);
					HttpResponse response=client.execute(post);
					
					if(response.getStatusLine().getStatusCode()==200){
						 is=response.getEntity().getContent();
						int len=0;
						byte []b=new byte[1024];
						ByteArrayOutputStream bos=new ByteArrayOutputStream();
						while((len=is.read(b))!=-1){
							bos.write(b, 0, len);
						
						String data=new String(bos.toByteArray());
						//data=URLDecoder.decode(data,"UTF-8");
						System.out.println("jsondata:"+data);
					
						Gson g=new Gson();
						List<ShopInfo> list_shopinfos=g.fromJson(data, new TypeToken<List<ShopInfo>>(){}.getType());
						return list_shopinfos;
						}
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
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
				return null;
			}
			@Override
			protected void onPostExecute(List<ShopInfo> result) {
				super.onPostExecute(result);
				System.out.println("result"+result.toString());
				if(result!=null){
				for(int i=0;i<3;i++){
					ShopInfo S=(ShopInfo)result.get(i);
//					ShopInfo S1=(ShopInfo)result.get(0);
					tv_list.get(i).setText("$"+S.getF_d_Ssprice());
					tv_desclist.get(i).setText(""+S.getF_c_Sname());
					//tv_list.setText("$"+S.getF_d_Ssprice());
					System.out.println("for"+S.toString());
				}
//				ShopInfo S1=(ShopInfo)result.get(0);
//				tv_list.setText("$"+S1.getF_d_Ssprice());
				Toast.makeText(businessFragment.getActivity(), "aaa", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(businessFragment.getActivity(), "aa", Toast.LENGTH_LONG).show();
			}
				}
		};
		
		ab.execute();
	}

}
