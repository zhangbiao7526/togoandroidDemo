package com.example.togodemo.user;

import com.example.togodemo.R;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.togodemo.variable.VARIABLE;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassWord extends Activity {
	EditText e1, e2, e3, e4;
	private TextView tv_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.layout_title);
		Button b1 = (Button) findViewById(R.id.wanchengxiugai);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("密码找回");
		ImageButton iv_myhome = (ImageButton) findViewById(R.id.iv_myhome);
		iv_myhome.setVisibility(View.INVISIBLE);
		
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				e1 = (EditText) findViewById(R.id.putyonghuming);
				e2 = (EditText) findViewById(R.id.putmimawenti);
				e3 = (EditText) findViewById(R.id.putmimadaan);
				e4 = (EditText) findViewById(R.id.putnewmima);

				String putyonghuming = e1.getText().toString();
				String putmimawenti = e2.getText().toString();
				String putmimadaan = e3.getText().toString();
				String putnewmima = e4.getText().toString();

				if ("".equals(putyonghuming) || putyonghuming == null) {
					Toast.makeText(ForgotPassWord.this, "用户名为空",
							Toast.LENGTH_LONG).show();

				} else if ("".equals(putmimawenti) || putmimawenti == null) {
					Toast.makeText(ForgotPassWord.this, "密码找回问题为空",
							Toast.LENGTH_LONG).show();
				} else if ("".equals(putmimadaan) || putmimadaan == null) {
					Toast.makeText(ForgotPassWord.this, "密码答案问题为空",
							Toast.LENGTH_LONG).show();
				} else if ("".equals(putnewmima) || putnewmima == null) {
					Toast.makeText(ForgotPassWord.this, "新密码为空",
							Toast.LENGTH_LONG).show();
				} else {
					FinalHttp fh = new FinalHttp();

					AjaxParams param = new AjaxParams();
					try {
						param.put("method", "findNewpassword");
						param.put("newuser",
								URLEncoder.encode(putyonghuming, "utf-8"));
						param.put("newpassword",
								URLEncoder.encode(putnewmima, "utf-8"));
						param.put("newquestion",
								URLEncoder.encode(putmimawenti, "utf-8"));
						param.put("newresult",
								URLEncoder.encode(putmimadaan, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					fh.post(VARIABLE.LOGIN, param, new AjaxCallBack<Object>() {
						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							super.onStart();
							System.out.println("开始请求FinalServlet");
						}

						// 成功返回
						@Override
						public void onSuccess(Object t) {
							// TODO Auto-generated method stub
							super.onSuccess(t);
							// System.out.println("请求FinalServlet成功"+t);
							// tv.setText(t.toString());//接收反馈回来的数据
//							Toast.makeText(ForgotPassWord.this, t.toString(),
//									Toast.LENGTH_SHORT).show();
							String t_ = (String) t;
							if ("true".equals(t_)) {
								Toast.makeText(ForgotPassWord.this,
										"密码修改成功，请重新登录", Toast.LENGTH_LONG)
										.show();
//								ForgotPassWord.this.startActivity(new Intent(ForgotPassWord.this,User_Login.class));
								ForgotPassWord.this.finish();
							} else {
								Toast.makeText(ForgotPassWord.this,
										"账号或问题答案错误", Toast.LENGTH_LONG).show();
							}
							// Intent in=new Intent(Register.this,Login.class);
							// startActivity(in);
						}

						@Override
						@SuppressWarnings("unused")
						public void onFailure(Throwable t, int errorNo,
								String strMsg) {
							// TODO Auto-generated method stub
							super.onFailure(t, errorNo, strMsg);
							Toast.makeText(ForgotPassWord.this, strMsg,
									Toast.LENGTH_SHORT).show();
							// System.out.println("请求失败"+strMsg);
							// tv.setText(strMsg );//接收反馈回来的数据
						}

						@Override
						public void onLoading(long count, long current) {
							// TODO Auto-generated method stub
							super.onLoading(count, current);
							System.out.println("1秒钟被回调一次");
						}
					});

				}

			}
		});
	}
}
