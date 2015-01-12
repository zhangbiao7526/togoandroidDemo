package com.example.togodemo.business;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.variable.VARIABLE;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class business_one extends ActionBarActivity{

	private FinalBitmap fm;
	private TextView tv_oneshop_shopname, tv_oneshop_money, tv_oneshop_type,
			tv_oneshop_address, tv_oneshop_description,tv_oneshop_shopactivity;
	private ImageView iv_;
	String img_uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oneshop_layout);
		
		final Intent in = super.getIntent();
		ArrayList<String> shop_indent=in.getStringArrayListExtra("shop_indent");

		fm = FinalBitmap.create(this);
		
		// v4的包getActionBar()
				// v7的包getSupportActionBar(),让标题左边多一个返回键、
				// 要在配置文件配置
		android.support.v7.app.ActionBar action = getSupportActionBar();
		action.setDisplayHomeAsUpEnabled(true);
		action.setHomeButtonEnabled(true);
		action.setDisplayShowHomeEnabled(true);
		action.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.danfense));
				
		iv_ = (ImageView) findViewById(R.id.iv_oneshop);

		tv_oneshop_shopname = (TextView) findViewById(R.id.tv_oneshop_shopname);
		tv_oneshop_money = (TextView) findViewById(R.id.tv_oneshop_money);
		tv_oneshop_type = (TextView) findViewById(R.id.tv_oneshop_type);
		tv_oneshop_address = (TextView) findViewById(R.id.tv_oneshop_address);
		tv_oneshop_description = (TextView) findViewById(R.id.tv_oneshop_description);
		tv_oneshop_shopactivity=(TextView) findViewById(R.id.tv_oneshop_shopactivity);
		
		img_uri=VARIABLE.IMAGE_URL+ shop_indent.get(0);
		fm.display(iv_, img_uri);
		tv_oneshop_type.setText(shop_indent.get(1));
		tv_oneshop_money.setText(shop_indent.get(2));
		tv_oneshop_shopname.setText(shop_indent.get(3));
		tv_oneshop_description.setText(shop_indent.get(4));
		tv_oneshop_shopactivity.setText(shop_indent.get(5));
	}

	}
