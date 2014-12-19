package com.example.togodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/*
 * 杨鸿谋
 */


public class StartPage extends Activity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.startactivitypg);

	

new Handler().postDelayed(new Runnable(){
	@Override
	public void run(){
		Intent intent = new Intent (StartPage.this,MainActivity.class);			
		startActivity(intent);			
		StartPage.this.finish();
		
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}
}, 3000);

	}

	
}
