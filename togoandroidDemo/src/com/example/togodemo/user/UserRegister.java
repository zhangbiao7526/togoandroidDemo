package com.example.togodemo.user;

import com.example.togodemo.R;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.example.togodemo.Main_Activity;
import com.example.togodemo.myApplication;
import com.example.togodemo.variable.VARIABLE;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegister extends Activity {
	InputMethodManager manager ;
	EditText e1, e2, e3, e4;
	private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeruser);
		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
						R.layout.layout_title);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("注册");
		ImageButton iv_myhome = (ImageButton) findViewById(R.id.iv_myhome);
		iv_myhome.setVisibility(View.VISIBLE);
		
		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  //单击空白处取消输入框
		Button b1 = (Button) findViewById(R.id.sign);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		Button b2 = (Button) findViewById(R.id.gobacklogin);
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				e1 = (EditText) findViewById(R.id.fname);
				e2 = (EditText) findViewById(R.id.Last);
				e3 = (EditText) findViewById(R.id.pass01);
				e4 = (EditText) findViewById(R.id.Addre);

				final String username = e1.getText().toString().trim();
				final String password = e2.getText().toString().trim();
				String uesrquestion = e3.getText().toString().trim();
				String useranswer = e4.getText().toString().trim();

				if ("".equals(username) || username == null) {
					Toast.makeText(UserRegister.this, "用户名不能为空",
							Toast.LENGTH_LONG).show();

				} else if ("".equals(password) || password == null) {
					Toast.makeText(UserRegister.this, "密码不能为空",
							Toast.LENGTH_LONG).show();
				} else if ("".equals(uesrquestion) || uesrquestion == null) {
					Toast.makeText(UserRegister.this, "找回问题不能为空",
							Toast.LENGTH_LONG).show();
				} else if ("".equals(useranswer) || useranswer == null) {
					Toast.makeText(UserRegister.this, "问题答案不能为空",
							Toast.LENGTH_LONG).show();
				} else {
					FinalHttp fh = new FinalHttp();

					AjaxParams param = new AjaxParams();
					param.put("method", "userRegister");
					try {
						param.put("newuser",
								URLEncoder.encode(username, "utf-8"));
						param.put("newpassword",
								URLEncoder.encode(password, "utf-8"));
						param.put("newquestion",
								URLEncoder.encode(uesrquestion, "utf-8"));
						param.put("newresult",
								URLEncoder.encode(useranswer, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// TODO Auto-generated catch block

					fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {
						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							super.onStart();
							System.out.println("开始FinalServlet");
						}

						// 成功返回值
						@Override
						public void onSuccess(Object t) {
							// TODO Auto-generated method stub
							super.onSuccess(t);
							String t_ = (String) t;
							if ("true".equals(t_)) {
								Toast.makeText(
										UserRegister.this,
										"注册成功",
										Toast.LENGTH_LONG).show();
								myApplication my = (myApplication) UserRegister.this
										.getApplication();
								my.setUSER_LOGIN(true);
								my.setUser_name(username);
								Intent in = new Intent(UserRegister.this,
										Main_Activity.class);
								startActivity(in);

							} else {
								Toast.makeText(UserRegister.this, "账号已存在",
										Toast.LENGTH_SHORT).show();
							}
						}
					});

				}
			}
		});
	}
	/**
	 * 单击空白处隐藏keyboard
	 */
	
	@Override  
	 public boolean onTouchEvent(MotionEvent event) {  
	  // TODO Auto-generated method stub  
	  if(event.getAction() == MotionEvent.ACTION_DOWN){  
	     if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){  
	       manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
	     }  
	  }  
	  return super.onTouchEvent(event);  
	 }  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
