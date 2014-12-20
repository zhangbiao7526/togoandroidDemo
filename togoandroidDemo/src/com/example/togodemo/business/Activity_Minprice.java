package com.example.togodemo.business;

import com.example.togodemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.togodemo.mode.ShopInfo;

public class Activity_Minprice extends Activity {
	private TextView tv_price;
	private TextView tv_shopname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_minprice);
		tv_price = (TextView) findViewById(R.id.tv_price);
		tv_shopname = (TextView) findViewById(R.id.tv_shopname);

		Bundle bundle = getIntent().getExtras();
		ShopInfo shopinfo = bundle.getParcelable("parcel");
		tv_price.setText("$" + shopinfo.getF_d_Ssprice());
		tv_shopname.setText(shopinfo.getF_c_Sname());
	}

}
