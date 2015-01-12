package com.example.selfcenter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.home.NewAdapter;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.ztest.CollectNet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class self_shoucang extends Activity {

	private ListView lv_shoucang;
	private FinalBitmap fb;
	private NewAdapter adapter;
	private String username;
	List<ShopInfo> list_shops;
	private Intent intent;
	private TextView tv_title;
	private ImageView img_back,img_search_visible;
	
		public NewAdapter getAdapter() {
			return adapter;
		}

		public void setAdapter(NewAdapter adapter) {
			this.adapter = adapter;
		}
		
		public List<ShopInfo> getList_shops() {
			return list_shops;
		}

		public void setList_shops(List<ShopInfo> list_shops) {
			this.list_shops = list_shops;
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
						R.layout.layout_title_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(R.string.tv_baobeishoucang);
		img_search_visible = (ImageView) findViewById(R.id.img_search_visible);
		img_search_visible.setVisibility(View.GONE);
		img_back = (ImageView) findViewById(R.id.img_back);
		img_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				self_shoucang.this.finish();
			}
		});
		
		lv_shoucang = (ListView) findViewById(R.id.shop_listview);
		fb = FinalBitmap.create(this);
		myApplication my=(myApplication) this.getApplication();
		username=my.getUser_name();
		CollectNet.getDataByCollect(this, lv_shoucang, fb,username);

		lv_shoucang.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Toast.makeText(self_shoucang.this, "ccc", Toast.LENGTH_SHORT)
//						.show();
				ShopInfo shop = (ShopInfo) lv_shoucang.getItemAtPosition(arg2);
				// Toast.makeText(ClassifySearch.this,shop.toString(),
				// 1000).show();
				Intent intent = new Intent(self_shoucang.this,
						OneSop_click.class);
				// 如果全局缓存不为空
				if (shop != null) {
					intent.putExtra("method", "home_buymore");
					Bundle bundle = new Bundle();
					bundle.putParcelable("buy_moreshop", shop);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
		});

	}
	
	 public void showInfo(final int position) {  
//	        new AlertDialog.Builder(this).setTitle("我的提示").setMessage("确定要删除吗？")                  
//	                .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
//	                    @Override  
//	                    public void onClick(DialogInterface dialog, int which) { 
//	                    	ShopInfo shop = (ShopInfo) lv_shoucang.getItemAtPosition(position);
//	                    	int shopid=shop.getF_i_Sid();
//	                    	CollectNet.delete(username,shopid);
//	                    	
//	                    	list_shops.remove(position); 
//	                        // 通过程序我们知道删除了，但是怎么刷新ListView呢？  
//	                        // 只需要重新设置一下adapter  
//	                    	lv_shoucang.setAdapter(adapter); 
//	                    	
//	                    }  
//	                }).show();
		 
		 intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			new AlertDialog.Builder(this)
			.setTitle("提示")
			.setIcon(R.drawable.logo_togo)
			.setMessage("确定要删除吗？")
			.setPositiveButton("是", new android.content.DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialoginterface, int i)
			    	 {
				ShopInfo shop = (ShopInfo) lv_shoucang.getItemAtPosition(position);
            	int shopid=shop.getF_i_Sid();
            	CollectNet.delete(username,shopid);
            	
            	list_shops.remove(position); 
                // 通过程序我们知道删除了，但是怎么刷新ListView呢？  
                // 只需要重新设置一下adapter  
            	lv_shoucang.setAdapter(adapter); 
			    	 }
			})
			.setNegativeButton("否", new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
			}).show();
			
	    }  
}
