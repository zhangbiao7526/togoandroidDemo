package com.example.togodemo.home;

import java.util.ArrayList;
import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.ShopInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Home_moreshop_click extends Activity implements android.widget.AdapterView.OnItemClickListener{

	private ShopInfo shop;
	private ArrayList<ShopInfo> list_goodmore;
	private ListView listview;
	private FinalBitmap fm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);                         
		setContentView(R.layout.listview_layout);
		fm = FinalBitmap.create(this);
		
		myApplication my=(myApplication) this.getApplication();
		Intent in=super.getIntent();
		listview=(ListView) findViewById(R.id.shop_listview);
		
		listview.setOnItemClickListener(this);	
		
		if(my.getList_buymoreshop()!=null&&"buymore_shop".equals(in.getStringExtra("tv_HOMEFragment_buymore_click1"))){
			ArrayList<ShopInfo> list_buymore=(ArrayList<ShopInfo>) my.getList_buymoreshop();
//			NewAdapter newadapter=new NewAdapter(Home_moreshop_click.this,list_buymore);
			//封装好的listviewAdapter
			NewAdapter newadapter=new NewAdapter(Home_moreshop_click.this,list_buymore,fm);
			listview.setAdapter(newadapter);
//			Toast.makeText(this, list_buymore.get(0).toString(), Toast.LENGTH_SHORT).show();	
		}
		else if(my.getList_goodshop()!=null&&"goodmore_shop".equals(in.getStringExtra("tv_HOMEFragment_buymore_click2"))){
			ArrayList<ShopInfo> list_goodmore=(ArrayList<ShopInfo>) my.getList_goodshop();
//			NewAdapter newadapter=new NewAdapter(list_goodmore);
			NewAdapter newadapter=new NewAdapter(Home_moreshop_click.this,list_goodmore,fm);
			listview.setAdapter(newadapter);
//			Toast.makeText(this, list_goodmore.get(0).toString(), Toast.LENGTH_SHORT).show();	
		}else if(my.getList_shopinfos()!=null&&"zuidi".equals(in.getStringExtra("zuidi"))){
			
		}
		
		else{
			Toast.makeText(this, "未加载完成，请返回", Toast.LENGTH_SHORT).show();
		}
		//list_buymore=(ArrayList<ShopInfo>) my.getList_goodshop();//数据源
		
	}
	


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ShopInfo shop=(ShopInfo) parent.getItemAtPosition(position);
		// TODO Auto-generated method stub
		Intent intent=new Intent(Home_moreshop_click.this,OneSop_click.class);
		//如果全局缓存不为空
		if(shop!=null){
		intent.putExtra("method", "home_buymore");
		Bundle bundle = new Bundle();
		bundle.putParcelable("buy_moreshop", shop);
		intent.putExtras(bundle);
		startActivity(intent);
		//getActivity().startActivity(intent);
		}else{
			Toast.makeText(Home_moreshop_click.this, "未联入后台", Toast.LENGTH_LONG).show();
			intent.setClass(Home_moreshop_click.this, OneSop_click.class);
			startActivity(intent);
		}
	}
		
}
