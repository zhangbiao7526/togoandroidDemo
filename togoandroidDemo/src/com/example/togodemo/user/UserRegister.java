package com.example.togodemo.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.example.togodemo.MainActivity;
import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.variable.VARIABLE;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

//import eyt.AjaxCallBack;
//import eyt.AjaxParams;
//import eyt.FinalHttp;






import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegister extends Activity {
	
	EditText e1,e2,e3,e4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeruser);
	 Button b1= (Button) findViewById(R.id.sign);
	 b1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	 
	 
	   Button b2=(Button) findViewById(R.id.gobacklogin);
	   b2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		     e1=(EditText) findViewById(R.id.fname);
		     e2=(EditText) findViewById(R.id.Last);
		     e3 =(EditText) findViewById(R.id.pass01);
		     e4=(EditText) findViewById(R.id.Addre);
		     
		     final String username=e1.getText().toString();
		     final String password=e2.getText().toString();
		     String uesrquestion=e3.getText().toString();
		     String useranswer=e4.getText().toString();
		     
		     if("".equals(username)||username==null){
					Toast.makeText(UserRegister.this, "用户名不能为空", Toast.LENGTH_LONG).show();
					
				}else if("".equals(password)||password==null){
					Toast.makeText(UserRegister.this, "密码不能为空", Toast.LENGTH_LONG).show();
				}else if("".equals(uesrquestion)||uesrquestion==null){  
					Toast.makeText(UserRegister.this, "找回问题不能为空", Toast.LENGTH_LONG).show();
				}else if("".equals(useranswer)||useranswer==null){  
					Toast.makeText(UserRegister.this, "问题答案不能为空", Toast.LENGTH_LONG).show();
				}else{
					FinalHttp fh=new FinalHttp();
					
					AjaxParams param=new AjaxParams();
						param.put("method", "userRegister");
						try {
							param.put("newuser", URLEncoder.encode(username, "utf-8"));
							param.put("newpassword", URLEncoder.encode(password, "utf-8"));
							param.put("newquestion", URLEncoder.encode(uesrquestion, "utf-8"));
							param.put("newresult", URLEncoder.encode(useranswer, "utf-8"));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// TODO Auto-generated catch block
					
					fh.post(VARIABLE.LOGIN,param, new AjaxCallBack<Object>(){
						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							super.onStart();
							System.out.println("开始FinalServlet");
						}
			           //成功返回值
						@Override
						public void onSuccess(Object t) {
							// TODO Auto-generated method stub
							super.onSuccess(t);
							String t_=(String) t;
							if("true".equals(t_)){
								Toast.makeText(UserRegister.this, "注册成功，账号："+t.toString()+","+username+"密码："+password, Toast.LENGTH_LONG).show();
								myApplication my=(myApplication) UserRegister.this.getApplication();
								my.setUSER_LOGIN(true);
								Intent in=new Intent(UserRegister.this,MainActivity.class);
								startActivity(in);
								
							}else {
								Toast.makeText(UserRegister.this, t.toString(), Toast.LENGTH_SHORT).show();	
							}
						}
					});
					
				}
		}});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
