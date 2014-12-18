package com.example.togodemo.home;



import com.example.togodemo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeF_vpage_click extends Activity{
	
	private TextView tv_;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_title);
		
		tv_=(TextView) findViewById(R.id.tv_title);
		final Intent in=super.getIntent();
//		tv_.setText("="+in.getStringExtra("img_name"));
//		shop_NetUtil.getHome_ViewPage(this,in,tv_);
	}
}
