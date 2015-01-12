package com.example.selfcenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.togodemo.R;
import com.example.togodemo.Shop_goods;
import com.example.togodemo.myApplication;
import com.example.togodemo.user.NetUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class self_addressadministration extends Activity {
	private EditText edt_user_address,edt_user_phone;
	private Button btn_yes,btn_no;
	private myApplication my;
	String from_shop_good=null;
	//1/5
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_address_layout);
		
		my=(myApplication) this.getApplication();
		edt_user_address = (EditText) findViewById(R.id.edt_user_address);
		edt_user_phone = (EditText) findViewById(R.id.edt_user_phone);
		
		btn_yes = (Button) findViewById(R.id.btn_yes);
		btn_no = (Button) findViewById(R.id.btn_no);
		
		String from_string=this.getIntent().getStringExtra("from_butong");
		if(from_string.equals("from_shop_goods")){
			
			final String img_uri=this.getIntent().getStringExtra("img_uri");
			final String tv_oneshop_shopname=this.getIntent().getStringExtra("tv_oneshop_shopname");
			final String tv_oneshop_money=this.getIntent().getStringExtra("tv_oneshop_money");
			final String shop_id=this.getIntent().getStringExtra("shop_id");
			final String username=my.getUser_name();
			//1/6
			final String edt_shopgoods_buyquantity=this.getIntent().
					getStringExtra("edt_shopgoods_buyquantity");
			final String et_leave_message=this.getIntent().
					getStringExtra("et_leave_message");
			final String tv_shopgoods_price=this.getIntent().
					getStringExtra("tv_shopgoods_price");
//			Toast.makeText(this, tv_shopgoods_price, 1000).show();
			btn_yes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String useraddress=edt_user_address.getText().toString().trim();
					String userphone=edt_user_phone.getText().toString().trim();
					Pattern pattern=Pattern.compile("[1][3458]\\d{9}");
					Matcher matcher=pattern.matcher(userphone);
					if(matcher.matches()){
						my.setUser_address(useraddress);
						my.setUser_phone(userphone);
						
						NetUtil.addUserAddress(self_addressadministration.this, username, useraddress, userphone);
					    Intent intent=new Intent(self_addressadministration.this,Shop_goods.class);
					    intent.putExtra("from_different", "from_self_address");
					    intent.putExtra("useraddress", useraddress);
					    intent.putExtra("userphone", userphone);
					    intent.putExtra("img_uri", img_uri);
					    intent.putExtra("tv_oneshop_shopname", tv_oneshop_shopname);
					    intent.putExtra("tv_oneshop_money", tv_oneshop_money);
					    intent.putExtra("shop_id", shop_id);
					 //1/6
					    intent.putExtra("edt_shopgoods_buyquantity", edt_shopgoods_buyquantity);
					    intent.putExtra("et_leave_message", et_leave_message);
					    intent.putExtra("tv_shopgoods_price", tv_shopgoods_price);
					    self_addressadministration.this.startActivity(intent);
					}else{
						Toast.makeText(self_addressadministration.this,"手机号码输入有误", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
		}else if(from_string.equals("from_tv_addressadministration")){
//			Intent in=super.getIntent();
//			from_shop_good=in.getStringExtra("from_shop_good");
			if(my.getUser_address()!=null){
				edt_user_address.setText(""+my.getUser_address());
				edt_user_phone.setText(""+my.getUser_phone());
			}
			btn_yes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					String username=my.getUser_name();
					String useraddress=edt_user_address.getText().toString().trim();
					String userphone=edt_user_phone.getText().toString().trim();
//					Toast.makeText(self_addressadministration.this, my.getUser_phone(), Toast.LENGTH_SHORT).show();
					//手机号码验证     1/4
					Pattern pattern=Pattern.compile("[1][3458]\\d{9}");
					Matcher matcher=pattern.matcher(userphone);
					
					if(useraddress==null||"".equals(useraddress)){
						Toast.makeText(self_addressadministration.this, "地址不能为空", Toast.LENGTH_SHORT).show();
					}else if(edt_user_phone.getText().toString().trim()==""){
						Toast.makeText(self_addressadministration.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
					}else if(matcher.matches()){
							my.setUser_address(useraddress);
							my.setUser_phone(userphone);
							
							NetUtil.addUserAddress(self_addressadministration.this, username, useraddress, userphone);
							self_addressadministration.this.finish();
						}else{
							Toast.makeText(self_addressadministration.this,"手机号码输入有误",  Toast.LENGTH_SHORT).show();
					}
				}
			});
	
		}
		
		btn_no.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(from_shop_good!=null){
					
				}else{
				self_addressadministration.this.finish();
				}
			}
		});
	}
}
