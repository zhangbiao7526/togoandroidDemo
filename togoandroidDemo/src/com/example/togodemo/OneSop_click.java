package com.example.togodemo;

import java.lang.reflect.Field;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
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

import com.example.togodemo.R;

public class OneSop_click extends ActionBarActivity {
	private FinalBitmap fm;
	private TextView tv_oneshop_shopname, tv_oneshop_money, tv_oneshop_type,
			tv_oneshop_address, tv_oneshop_description;
	private ImageView iv_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oneshop_layout);
		final Intent in = super.getIntent();
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
		showOverflowMenu();
				
		iv_ = (ImageView) findViewById(R.id.iv_oneshop);

		tv_oneshop_shopname = (TextView) findViewById(R.id.tv_oneshop_shopname);
		tv_oneshop_money = (TextView) findViewById(R.id.tv_oneshop_money);
		tv_oneshop_type = (TextView) findViewById(R.id.tv_oneshop_type);
		tv_oneshop_address = (TextView) findViewById(R.id.tv_oneshop_address);
		tv_oneshop_description = (TextView) findViewById(R.id.tv_oneshop_description);

		// 接收从首页传过来的点击事件的值
		Bundle bundle=getIntent().getExtras();
		String method=in.getStringExtra("method");
		if("home_buymore".equals(method)){
		HomeFragment(bundle);
		}else if("home_viewpg".equals(method)){
			// 将控件
		shop_NetUtil.getHome_ViewPage(this, in, fm, tv_oneshop_shopname,
					tv_oneshop_money, tv_oneshop_type, tv_oneshop_address,
					tv_oneshop_description, iv_);	
		}
		
		

	}

	private void HomeFragment(Bundle bundle) {
		
		ShopInfo shop=bundle.getParcelable("buy_moreshop");	
		String img_uri=VARIABLE.IMAGE_URL+ shop.getF_c_Simagpath();
		tv_oneshop_shopname.setText(shop.getF_c_Sname());
		tv_oneshop_money.setText("$" + shop.getF_d_Ssprice());
		fm.display(iv_, img_uri);
		tv_oneshop_type.setText(shop.getF_c_Stype());
		tv_oneshop_address.setText(shop.getF_c_Saddress());
		tv_oneshop_description.setText(shop.getF_c_Sdescription());
	}

	/**
	 * 显示溢出区按钮 若没有此方法，则需要单击menu菜单才会显示actionBar的菜单
	 */
	private void showOverflowMenu() {
		ViewConfiguration config = ViewConfiguration.get(this);
		try {
			// sHasPermanentMenuKey 有没有物理菜单键
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {

				menuKeyField.setAccessible(true);

				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		// 监听到返回键
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

		return true;
	}
}
