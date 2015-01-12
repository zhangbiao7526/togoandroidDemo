package com.example.togodemo.user;

import com.example.togodemo.Main_Activity;
import com.example.togodemo.R;
import java.util.HashMap;

import net.tsz.afinal.FinalActivity;

import com.example.togodemo.myApplication;
import com.example.togodemo.mode.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class User_Login extends FinalActivity implements OnClickListener {

	private Button btn_login, btn_calloff;
	private EditText edt_login_name, edt_login_password;
	private TextView tv_login_register, tv_login_forget,tv_title;
	public User user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.layout_title);
		ImageButton iv_myhome = (ImageButton) findViewById(R.id.iv_myhome);
		iv_myhome.setVisibility(View.INVISIBLE);
		
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_calloff = (Button) findViewById(R.id.btn_calloff);

		edt_login_name = (EditText) findViewById(R.id.edt_login_name);
		edt_login_password = (EditText) findViewById(R.id.edt_login_password);

		tv_login_register = (TextView) findViewById(R.id.tv_login_register);
		tv_login_forget = (TextView) findViewById(R.id.tv_login_forget);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("登录");
		
		btn_login.setOnClickListener(this);
		btn_calloff.setOnClickListener(this);
		tv_login_register.setOnClickListener(this);
		tv_login_forget.setOnClickListener(this);

		myApplication my = (myApplication) User_Login.this.getApplication();
	}
	
	@Override
	public void onClick(View v) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", edt_login_name.getText().toString().trim());
		map.put("userpassword", edt_login_password.getText().toString().trim());
		int id = v.getId();
		switch (id) {
		case R.id.btn_login:
			if ("".equals(edt_login_name.getText().toString().trim())
					|| edt_login_name.getText().toString() == null) {
				Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			} else if ("".equals(edt_login_password.getText().toString().trim())
					|| edt_login_password.getText().toString() == null) {
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			}
//			myApplication my=(myApplication) this.getApplication();
//			my.setUser_name(edt_login_name.getText().toString().trim());
//			Toast.makeText(this, my.getUser_name(), Toast.LENGTH_SHORT).show();

			else{
			//判断用户是否存在
			NetUtil.uploadDataByGetLogin(User_Login.this, map,edt_login_name.getText().toString().trim());
			}
			//12/25 用户登录就去后台查找有没有设置地址
//			NetUtil.select_Useraddress(User_Login.this,edt_login_name.getText().toString().trim());
			break;
		case R.id.tv_login_forget:
			Intent in1 = new Intent(this, ForgotPassWord.class);
			startActivity(in1);
			break;
		case R.id.btn_calloff:
			Intent ins=super.getIntent();
			if("user_close".equals(ins.getStringExtra("user_close"))){
			Intent intent=new Intent(User_Login.this, Main_Activity.class);
			startActivity(intent);
			}else{
			User_Login.this.finish();
			}
			break;

		case R.id.tv_login_register:
			Intent in = new Intent(this, UserRegister.class);
			startActivity(in);
			break;
			
		default:
			break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Intent ins=super.getIntent();
		Log.i("MainActivity", "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0&&"user_close".equals(ins.getStringExtra("user_close"))) {
			Intent intent=new Intent(User_Login.this, Main_Activity.class);
			startActivity(intent);
			}else if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
				User_Login.this.finish();
				}
		return false;
		}
	
	public void finish(int id) {
		if (id == 0) {
			User_Login.this.finish();

		}
	}
}
