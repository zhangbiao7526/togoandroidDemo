package com.example.selfcenter;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.user.NetUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class self_allorders extends Activity implements android.widget.AdapterView.OnItemClickListener{
	
	private ListView listview;
	private FinalBitmap fm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_shopshow);
		myApplication my2 = (myApplication) this.getApplication();
		fm = FinalBitmap.create(this);
		
		listview=(ListView) findViewById(R.id.indent_listview);
		
		listview.setOnItemClickListener(this);	
		NetUtil.select_Indent(this,my2.getUser_name(),listview,fm);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
