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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home_moreshop_click extends Activity implements android.widget.AdapterView.OnItemClickListener{

	private ShopInfo shop;
	private ArrayList<ShopInfo> list_goodmore;
	private ListView listview;
	private FinalBitmap fm;
	private TextView tv_title;
	private ImageView img_back,img_search_visible;
	
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
		tv_title.setText(R.string.app_name);
		img_back = (ImageView) findViewById(R.id.img_back);
		img_search_visible = (ImageView) findViewById(R.id.img_search_visible);
		img_search_visible.setVisibility(View.GONE);
		img_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Home_moreshop_click.this.finish();
			}
		});
		fm = FinalBitmap.create(this);
		
		myApplication my=(myApplication) this.getApplication();
		Intent in=super.getIntent();
		listview=(ListView) findViewById(R.id.shop_listview);
		
		listview.setOnItemClickListener(this);	
		
		if(my.getList_buymoreshop()!=null&&"buymore_shop".equals(in.getStringExtra("tv_HOMEFragment_buymore_click1"))){
			ArrayList<ShopInfo> list_buymore=(ArrayList<ShopInfo>) my.getList_buymoreshop();
//			NewAdapter newadapter=new NewAdapter(Home_moreshop_click.this,list_buymore);
			//封装好的listviewAdapter
			NewAdapter_Seach newadapter=new NewAdapter_Seach(Home_moreshop_click.this,list_buymore,fm);
			listview.setAdapter(newadapter);
//			Toast.makeText(this, list_buymore.get(0).toString(), Toast.LENGTH_SHORT).show();	
		}
		else if(my.getList_goodshop()!=null&&"goodmore_shop".equals(in.getStringExtra("tv_HOMEFragment_buymore_click2"))){
			ArrayList<ShopInfo> list_goodmore=(ArrayList<ShopInfo>) my.getList_goodshop();
//			NewAdapter newadapter=new NewAdapter(list_goodmore);
			NewAdapter_Seach newadapter=new NewAdapter_Seach(Home_moreshop_click.this,list_goodmore,fm);
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
