package com.example.test;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class User_Login extends Activity implements OnClickListener{
	
	private Button btn_login,btn_calloff;
	private EditText edt_login_name,edt_login_password;
	private TextView tv_login_register,tv_login_forget;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layoutx);
		
		btn_login=(Button) findViewById(R.id.btn_login);
		btn_calloff=(Button) findViewById(R.id.btn_calloff);
		
		edt_login_name=(EditText) findViewById(R.id.edt_login_name);
		edt_login_password=(EditText) findViewById(R.id.edt_login_password);
		
		tv_login_register=(TextView) findViewById(R.id.tv_login_register);
		tv_login_forget=(TextView) findViewById(R.id.tv_login_forget);
		
		
	}
	@Override
	public void onClick(View v) {
		
		int id=v.getId();
		switch (id) {
		case R.id.btn_login:
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("username", edt_login_name.getText().toString());
			map.put("userpassword", edt_login_password.getText().toString());
			NetUtil.uploadDataByGetLogin(User_Login.this,map);
			 
			break;

		default:
			break;
		}
	}
}
