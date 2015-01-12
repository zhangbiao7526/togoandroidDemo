package com.example.selfcenter;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.variable.VARIABLE;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class self_callcenter extends Activity {

	private EditText feedback_et;
	private Button btn_commit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);

		feedback_et = (EditText) findViewById(R.id.feedback_et);
		btn_commit = (Button) findViewById(R.id.btn_commit);

		btn_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String COLLECT_URL = VARIABLE.HOMEVIPAGER_URI9;
				myApplication my = (myApplication) self_callcenter.this
						.getApplication();
				String username = my.getUser_name();
				if ("".equals(feedback_et.getText().toString().trim())) {
					Toast.makeText(self_callcenter.this, "意见不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					AjaxParams params = new AjaxParams();
					params.put("method", "useridea");
					params.put("username", username);
					params.put("idea", feedback_et.getText().toString().trim());
					FinalHttp fh = new FinalHttp();
					fh.post(COLLECT_URL, params, new AjaxCallBack<Object>() {

						@Override
						public void onSuccess(Object t) {
							super.onSuccess(t);
						}
					});

					Toast.makeText(self_callcenter.this, "意见已提交",
							Toast.LENGTH_SHORT).show();
					feedback_et.setText("");
				}
			}
		});

	}

}
