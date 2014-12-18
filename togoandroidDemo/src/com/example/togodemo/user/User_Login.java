package com.example.togodemo.user;

import java.util.HashMap;

import net.tsz.afinal.FinalActivity;

import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.User;
import com.example.togodemo.udao.biz.UserManagers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class User_Login extends FinalActivity implements OnClickListener{
	
	private myApplication My_Application=(myApplication) this.getApplication();
	private Button btn_login,btn_calloff;
	private EditText edt_login_name,edt_login_password;
	private TextView tv_login_register,tv_login_forget;
	public User user=null;
	public UserManagers userManagers=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		btn_login=(Button) findViewById(R.id.btn_login);
		btn_calloff=(Button) findViewById(R.id.btn_calloff);
		
		edt_login_name=(EditText) findViewById(R.id.edt_login_name);
		edt_login_password=(EditText) findViewById(R.id.edt_login_password);
		
		tv_login_register=(TextView) findViewById(R.id.tv_login_register);
		tv_login_forget=(TextView) findViewById(R.id.tv_login_forget);
		
		btn_login.setOnClickListener(this);
		btn_calloff.setOnClickListener(this);
		tv_login_register.setOnClickListener(this);
		tv_login_forget.setOnClickListener(this);
		
		myApplication my=(myApplication) User_Login.this.getApplication();
	}
	@Override
	public void onClick(View v) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("username", edt_login_name.getText().toString());
		map.put("userpassword", edt_login_password.getText().toString());
		int id=v.getId();
		switch (id) {
		case R.id.btn_login:
			if("".equals(edt_login_name.getText().toString())||edt_login_name.getText().toString()==null){
				Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			}else if("".equals(edt_login_password.getText().toString())||edt_login_password.getText().toString()==null){
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			}
			NetUtil.uploadDataByGetLogin(User_Login.this,map);
			break;
		case R.id.tv_login_forget:
			Intent in1=new Intent(this,ForgotPassWord.class);
			startActivity(in1);
			break;
		case R.id.btn_calloff:
			User_Login.this.finish();
			break;
			
		case R.id.tv_login_register:
			Intent in=new Intent(this,UserRegister.class);
			startActivity(in);
		default:
			break;
		}
	}
	public void finish(int id){
		if(id==0){
			User_Login.this.finish();
//			Intent in=new Intent(User_Login.this,aaa.class);
//			startActivity(in);
			
		}
	}
}
