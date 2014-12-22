package com.example.togodemo.classifyLayout;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.ztest.ClassifyNet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


public class Classify_Activity_children extends Activity{
	
	private FinalBitmap fm;
	private ListView find_class_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		Intent in=super.getIntent();
		String fenlei=in.getStringExtra("fenlei");
		Toast.makeText(this, "子listView"+fenlei, Toast.LENGTH_SHORT).show();
		
		ClassifyNet.Find_classify(this,fenlei,find_class_list);
	}

}
