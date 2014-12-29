package com.example.togodemo.classifyLayout;

import java.util.List;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.home.NewAdapter_Seach;
import com.example.togodemo.mode.ShopInfo;

public class ClassifySearch extends Activity {

	private ListView listview_search;
	private FinalBitmap fm;
	List<ShopInfo> list_shopinfos;
	NewAdapter_Seach adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		listview_search=(ListView)findViewById(R.id.shop_listview);
		fm=FinalBitmap.create(this);
		myApplication ma=(myApplication)this.getApplication();
		
		if(ma.getList_goodshop()!=null){
		 list_shopinfos=ma.getList_goodshop();
		adapter=new NewAdapter_Seach(this,list_shopinfos,fm);
		listview_search.setAdapter(adapter);
		listview_search.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ShopInfo shop=(ShopInfo) listview_search.getItemAtPosition(arg2);
//				Toast.makeText(ClassifySearch.this,shop.toString(), 1000).show();
				Intent intent=new Intent(ClassifySearch.this,OneSop_click.class);
				//如果全局缓存不为空
				if(shop!=null){
				intent.putExtra("method", "home_buymore");
				Bundle bundle = new Bundle();
				bundle.putParcelable("buy_moreshop", shop);
				intent.putExtras(bundle);
				startActivity(intent);
				//getActivity().startActivity(intent);
				}else{
					Toast.makeText(ClassifySearch.this, "未联入后台", Toast.LENGTH_LONG).show();
					intent.setClass(ClassifySearch.this, OneSop_click.class);
					startActivity(intent);
			}
			}
		});
		}else{
			Toast.makeText(this, "b", 1000).show();
		}
	}
	//12/26
	//删除listview中item
	 public void showInfo(final int position) {  
	        new AlertDialog.Builder(this).setTitle("我的提示").setMessage("确定要删除吗？")                  
	                .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	                    @Override  
	                    public void onClick(DialogInterface dialog, int which) {  
	                    	list_shopinfos.remove(position);  
	                        // 通过程序我们知道删除了，但是怎么刷新ListView呢？  
	                        // 只需要重新设置一下adapter  
	                    	listview_search.setAdapter(adapter); 
	                    }  
	                }).show();  
	    }  
	
}
