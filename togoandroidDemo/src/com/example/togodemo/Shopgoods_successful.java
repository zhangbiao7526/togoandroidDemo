package com.example.togodemo;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


//@杨鸿谋 2014/12/26 dialog 页面    只显示了dialog页面   
public class Shopgoods_successful extends Activity implements OnClickListener {

	private Button btn_return_home;
	private TextView tv_mul;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopgoods_successful);
		
		
		tv_mul=(TextView) findViewById(R.id.tv_mul);
		btn_return_home=(Button) findViewById(R.id.btn_return_home);
		btn_return_home.setOnClickListener(this);
	
		intent=super.getIntent();
		tv_mul.setText(intent.getStringExtra("tv_shopgoods1_price").toString());
	}
	

	@Override
	public void onClick(View v) {
		
		Intent inreturn=new Intent();
		inreturn.setClass(Shopgoods_successful.this, Main_Activity.class);
		startActivity(inreturn);
		Shopgoods_successful.this.finish();
		
	}	
	
}
