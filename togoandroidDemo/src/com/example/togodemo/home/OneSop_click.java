package com.example.togodemo.home;

import java.lang.reflect.Field;

import net.tsz.afinal.FinalBitmap;


import com.example.togodemo.R;
import com.example.togodemo.ztest.shop_NetUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class OneSop_click extends ActionBarActivity {
	private FinalBitmap fm;
	private TextView tv_oneshop_shopname,tv_oneshop_money,tv_oneshop_type,
					 tv_oneshop_address,tv_oneshop_description;
	private ImageView iv_;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oneshop_layout);
		final Intent in=super.getIntent();
		fm=FinalBitmap.create(this);
		
		iv_=(ImageView) findViewById(R.id.iv_oneshop);
		
		tv_oneshop_shopname=(TextView) findViewById(R.id.tv_oneshop_shopname);
		tv_oneshop_money=(TextView) findViewById(R.id.tv_oneshop_money);
		tv_oneshop_type=(TextView) findViewById(R.id.tv_oneshop_type);
		tv_oneshop_address=(TextView) findViewById(R.id.tv_oneshop_address);
		tv_oneshop_description=(TextView) findViewById(R.id.tv_oneshop_description);
		
		//接收从首页传过来的点击事件的值
		
		//将控件
		shop_NetUtil.getHome_ViewPage(this, in,fm, tv_oneshop_shopname,
				tv_oneshop_money,tv_oneshop_type,tv_oneshop_address,
				tv_oneshop_description,iv_);
		//v4的包getActionBar()
		//v7的包getSupportActionBar(),让标题左边多一个返回键、
		//要在配置文件配置
		android.support.v7.app.ActionBar action=getSupportActionBar();
		action.setDisplayHomeAsUpEnabled(true);
		action.setHomeButtonEnabled(true);
		action.setDisplayShowHomeEnabled(true);
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.danfense));
		showOverflowMenu();
		
		
	}
	
	/**
	 * 显示溢出区按钮
	 * 若没有此方法，则需要单击menu菜单才会显示actionBar的菜单
	 */
	private void showOverflowMenu(){
		ViewConfiguration config=ViewConfiguration.get(this);
		try {
			//sHasPermanentMenuKey 有没有物理菜单键
			Field menuKeyField =ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			if(menuKeyField!=null){
				
				menuKeyField.setAccessible(true);
				
				menuKeyField.setBoolean(config,false);
			}
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		//监听到返回键
		case android.R.id.home:
			finish();
			break;
		case R.id.action_1:
			Toast.makeText(this, "单击了笑脸", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.action_2:
			Toast.makeText(this, "单击了分享", Toast.LENGTH_SHORT).show();
			
			break;	
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		/**点击menu，弹出菜单*/
		
		/*
		 * add()方法的四个参数，依次是：
		*
		* 1、组别，如果不分组的话就写Menu.NONE,
		*
		* 2、Id，这个很重要，<a href="http://www.it165.net/pro/ydad/" target="_blank" class="keylink">Android</a>根据这个Id来确定不同的菜单
		*
		* 3、顺序，那个菜单现在在前面由这个参数的大小决定
		*
		* 4、文本，菜单的显示文本
		*/
		 
//		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "aaa").setIcon(
//		
//		R.drawable.ic_launcher);
		
		return true;
	}
}
