package com.example.togodemo.ztest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.togodemo.BusinessFragment;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessNet {

	static String uri = VARIABLE.HOMEVIPAGER_URI7;

	public static void getDataFromSQL(final BusinessFragment businessFragment,
			final List<TextView> tv_list, final List<TextView> tv_desclist) {
		AsyncTask<Void, Void, List<ShopInfo>> ab = new AsyncTask<Void, Void, List<ShopInfo>>() {

			@Override
			protected List<ShopInfo> doInBackground(Void... params) {
				InputStream is = null;
				try {
					HttpClient client = new DefaultHttpClient();
					// HttpGet get=new HttpGet(uri);
					HttpPost post = new HttpPost(uri);
					
					//需要一个list集合
					ArrayList<NameValuePair> params1=new ArrayList<NameValuePair>();
					params1.add(new BasicNameValuePair("method", "find_busness"));
					UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params1);
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					
					if (response.getStatusLine().getStatusCode() == 200) {
						is = response.getEntity().getContent();
						int len = 0;
						byte[] b = new byte[1024];
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						while ((len = is.read(b)) != -1) {
							bos.write(b, 0, len);

							String data = new String(bos.toByteArray());
							// data=URLDecoder.decode(data,"UTF-8");
							System.out.println("jsondata:" + data);

							Gson g = new Gson();
							List<ShopInfo> list_shopinfos = g.fromJson(data,
									new TypeToken<List<ShopInfo>>() {
									}.getType());
							return list_shopinfos;
						}
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
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
				return null;
			}

			@Override
			protected void onPostExecute(List<ShopInfo> result) {
				super.onPostExecute(result);
				myApplication ma;
				if (result != null) {
					System.out.println("result" + result.toString());
					ma = (myApplication) businessFragment.getActivity()
							.getApplication();
					ma.setList_shopinfos(result);
					for (int i = 0; i < 2; i++) {
						ShopInfo S = result.get(i);
						tv_list.get(i).setText("$" + S.getF_d_Ssprice());
						tv_desclist.get(i).setText("" + S.getF_c_Sname());
					}
					Toast.makeText(businessFragment.getActivity(), "aaa",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(businessFragment.getActivity(), "aa",
							Toast.LENGTH_LONG).show();
				}
			}
		};

		ab.execute();
	}

}
