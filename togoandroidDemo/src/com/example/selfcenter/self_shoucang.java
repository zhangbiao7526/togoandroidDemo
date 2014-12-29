package com.example.selfcenter;


import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.ztest.CollectNet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class self_shoucang extends Activity {
	
	private ListView lv_shoucang;
	private FinalBitmap fb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		lv_shoucang=(ListView)findViewById(R.id.shop_listview);
		fb=FinalBitmap.create(this);
		
		CollectNet.getDataByCollect(this,lv_shoucang,fb);
	
	}
}
