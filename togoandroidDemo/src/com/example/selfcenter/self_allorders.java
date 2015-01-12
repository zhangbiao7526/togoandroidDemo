package com.example.selfcenter;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.business.business_one;
import com.example.togodemo.mode.Indent;
import com.example.togodemo.user.NetUtil;

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

public class self_allorders extends Activity implements android.widget.AdapterView.OnItemClickListener{
	
	private ListView listview;
	private FinalBitmap fm;
	private ImageView intent_none;
	private TextView tv_title;
	private ImageView img_back,img_search_visible;
	private String image_path,shop_type,shop_price,shop_name,shop_num,shop_shouldPay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_shopshow);
		
		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.layout_title_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(R.string.tv_allorders);
		img_search_visible = (ImageView) findViewById(R.id.img_search_visible);
		img_search_visible.setVisibility(View.GONE);
		img_back = (ImageView) findViewById(R.id.img_back);
		img_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				self_allorders.this.finish();
			}
		});
		myApplication my2 = (myApplication) this.getApplication();
		fm = FinalBitmap.create(this);
		
		listview=(ListView) findViewById(R.id.indent_listview);
		intent_none= (ImageView) findViewById(R.id.intent_none);
		
		listview.setOnItemClickListener(this);	
		NetUtil.select_Indent(this,my2.getUser_name(),listview,fm,intent_none);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		Toast.makeText(this, ""+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//			
//		Indent indent = (Indent) parent.getItemAtPosition(position);
//		 image_path = indent.getShopInfo().getF_c_Simagpath();
//		shop_type = indent.getShopInfo().getF_c_Stype();
//		shop_price = String.valueOf(indent.getShopInfo().getF_d_Ssprice());
//		shop_name = indent.getF_c_Ishopname();
//		shop_num = String.valueOf(indent.getF_i_Inum());
//		shop_shouldPay = String.valueOf(indent.getF_c_IshouldPay());
//		
//		ArrayList<String> array_indent= new ArrayList<String>();
//		array_indent.add(image_path);
//		array_indent.add(shop_type);
//		array_indent.add(shop_price);
//		array_indent.add(shop_name);
//		array_indent.add(shop_num);
//		array_indent.add(shop_shouldPay);
//		
//		Intent intent = new Intent(this, business_one.class);
//		intent.putExtra("shop_indent", array_indent);
//		self_allorders.this.startActivity(intent);
//		
	}
}
