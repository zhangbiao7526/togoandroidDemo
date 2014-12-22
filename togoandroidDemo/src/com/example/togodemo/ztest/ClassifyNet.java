package com.example.togodemo.ztest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.togodemo.myApplication;
import com.example.togodemo.classifyLayout.Classify_Activity_children.NewAdapter;
import com.example.togodemo.classifyLayout.Classify_Activity_children;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;


public class ClassifyNet {

	public static void Find_classify(
			final Classify_Activity_children classify_Activity_children,
			final String fenlei,final ListView find_class_list,final NewAdapter newadapter) {
		
		AsyncTask<Void, Void, List<ShopInfo>> at=new AsyncTask<Void, Void, List<ShopInfo>>(){

			@Override
			protected List<ShopInfo> doInBackground(Void... params) {
				
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet get = new HttpGet(VARIABLE.BUYMORE_SHOP+"?method=fenlei&type="+URLEncoder.encode(fenlei,"utf-8"));
					
					HttpResponse response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == 200) {
						InputStream in = response.getEntity().getContent();
						byte[] data = convertInputStreamByteArray(in);
						String json = new String(data);// ????json??????
						Gson g = new Gson();
						List<ShopInfo> list_json = g.fromJson(json,
								new TypeToken<List<ShopInfo>>() {
								}.getType());
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
			protected void onPostExecute(List<ShopInfo> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(result==null){
					Toast.makeText(classify_Activity_children, "网络超时", Toast.LENGTH_SHORT).show();
				}else{
					myApplication my = (myApplication) classify_Activity_children.getApplication();
					newadapter.setData(result);
					find_class_list.setAdapter(newadapter);
					for (ShopInfo s : result) {
						System.out.println("HomeNet:" + s.getF_c_Simagpath() + ","
								+ s.getF_c_Sname());
					}
				}
			}
			};
			
			at.execute();
	}

	protected static byte[] convertInputStreamByteArray(InputStream is) {
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


	
}
