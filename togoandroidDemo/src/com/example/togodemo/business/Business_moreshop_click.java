package com.example.togodemo.business;

import java.util.HashMap;
import java.util.List;

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

public class Business_moreshop_click extends Activity implements
		android.widget.AdapterView.OnItemClickListener {

	private ListView listview;
	private FinalBitmap fm;
	public myApplication my;
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
				Business_moreshop_click.this.finish();
			}
		});
		fm = FinalBitmap.create(this);
		my = (myApplication) this.getApplication();

		myApplication my = (myApplication) this.getApplication();
		Intent in = super.getIntent();
		listview = (ListView) findViewById(R.id.shop_listview);

		listview.setOnItemClickListener(this);

		if (my.getList_shopinfos() != null
				&& "BusinessF_zuidi".equals(in
						.getStringExtra("BusinessF_zuidi"))) {
			List<HashMap<String, ShopInfo>> list_zuidi = my.getList_shopinfos();
			// 封装好的listviewAdapter
			NewAdapter_Buss newadapter = new NewAdapter_Buss(
					Business_moreshop_click.this, list_zuidi, fm, "haoping");
			listview.setAdapter(newadapter);
			// 提取listview中的每个对象
			HashMap<String, ShopInfo> hasp = (HashMap<String, ShopInfo>) newadapter
					.getItem(0);
			// Toast.makeText(this,
			// "newadapter.getItem(0)"+hasp.get("haoping").toString(),
			// Toast.LENGTH_SHORT).show();
		}

		else if (my.getList_shopinfos_baoyou() != null
				&& "BusinessF_baoyou".equals(in
						.getStringExtra("BusinessF_baoyou"))) {
			List<HashMap<String, ShopInfo>> list_baoyou = my
					.getList_shopinfos_baoyou();
			NewAdapter_Buss newadapter = new NewAdapter_Buss(
					Business_moreshop_click.this, list_baoyou, fm, "baoyou");
			listview.setAdapter(newadapter);
			// Toast.makeText(this, list_goodmore.get(0).toString(),
			// Toast.LENGTH_SHORT).show();

		} else if (my.getList_shopinfos_cuxiao() != null
				&& "BusinessF_cuxiao".equals(in
						.getStringExtra("BusinessF_cuxiao"))) {
			List<HashMap<String, ShopInfo>> list_cuxiao = my
					.getList_shopinfos_cuxiao();
			NewAdapter_Buss newadapter = new NewAdapter_Buss(
					Business_moreshop_click.this, list_cuxiao, fm, "cuxiao");
			listview.setAdapter(newadapter);

		}

		else {
			Toast.makeText(this, "未加载完成，请返回", Toast.LENGTH_SHORT).show();
		}
		// list_buymore=(ArrayList<ShopInfo>) my.getList_goodshop();//数据源

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(Business_moreshop_click.this,
				OneSop_click.class);

		// 如果全局缓存不为空
		if (my.getList_buymoreshop() != null) {

			intent.putExtra("method", "home_buymore");
			Bundle bundle = new Bundle();
			// ShopInfo shop=(ShopInfo) parent.getItemAtPosition(position);//
			HashMap<String, ShopInfo> hasp = (HashMap<String, ShopInfo>) parent
					.getItemAtPosition(position);
			ShopInfo shop = null;
			
			if (hasp.get("haoping") != null) {
				shop = hasp.get("haoping");
				intent.putExtra("activity", "活动： 最低价");
				bundle.putParcelable("buy_moreshop", shop);
				
			} else if (hasp.get("baoyou") != null) {
				shop = hasp.get("baoyou");
				intent.putExtra("activity", "活动：包邮");
				bundle.putParcelable("buy_moreshop", shop);
				
			} else if (hasp.get("cuxiao") != null) {
				shop = hasp.get("cuxiao");
				intent.putExtra("activity", "活动：限时促销");
				bundle.putParcelable("buy_moreshop", shop);
			}

			intent.putExtras(bundle);
			startActivity(intent);
			// getActivity().startActivity(intent);
		} else {
			Toast.makeText(Business_moreshop_click.this, "未联入后台",
					Toast.LENGTH_LONG).show();
			intent.setClass(Business_moreshop_click.this, OneSop_click.class);
			startActivity(intent);
		}
	}

}
