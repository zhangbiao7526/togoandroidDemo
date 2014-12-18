package com.example.togodemo;

import com.example.togodemo.ui.abstOnTouchListener;
import com.example.togodemo.user.User_Login;
import com.example.togodemo.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。<br>
 * 要想能够管理fragment那么就要继承FragmentActivity
 * 
 * @author guolin
 */
public class MainActivity extends abstOnTouchListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		 //自定义标题栏
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		
		//自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_title);
		// 初始化布局元素
		initViews();
		//获得fragment管理器
		fragmentManager = getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
		
		  initValues();  //初始化 关键属性
	      
		  content.setOnTouchListener(this);//创建content的事件监听  
		  
	      //imageButton1.setOnTouchListener(this);
	      myLinearLayout=(LinearLayout)this.findViewById(R.id.menu);
	      right=(TextView) findViewById(R.id.tv_title);
	       
	       
	}
	/**
	 * 重写手机自带退出按钮
	 * 
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i("MainActivity", "onKeyDown");
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0){
			System.out.println(pageID);
			if(pageID==0){
				scrollToContent(); 
				pageID=-1;
			}else{
				exit();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	public void exit(){
		if(!wantToClose){
		wantToClose=true;
		Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();  
		    mHandler.sendEmptyMessageDelayed(0, 2000); 
        } else {  
				 Intent intent = new Intent(Intent.ACTION_MAIN);  

		            intent.addCategory(Intent.CATEGORY_HOME);  

		            startActivity(intent);  
		            try {
						Thread.sleep(1000);
						 System.exit(0);  
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           
        } 
	}
	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {  

        @Override  

        public void handleMessage(Message msg) {  

            // TODO Auto-generated method stub   

            super.handleMessage(msg);  

            wantToClose = false;  

        }  

  

    }; 
	
	
	public void click(View v){
		myApplication my=(myApplication) this.getApplication();
		if(flag==0&&my.isUSER_LOGIN()){
			pageID=0;
			scrollToMenu();
			}else if(!my.isUSER_LOGIN()){
				Intent in=new Intent(MainActivity.this,User_Login.class);
				startActivity(in);
			}else if(flag==1){
				pageID=-1;
				scrollToContent();
			}
	}


	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		homeLayout = findViewById(R.id.message_layout);
		classifyLayout = findViewById(R.id.contacts_layout);
		businessLayout = findViewById(R.id.news_layout);
		shopcarLayout = findViewById(R.id.setting_layout);
		messageImage = (ImageView) findViewById(R.id.message_image);
		contactsImage = (ImageView) findViewById(R.id.contacts_image);
		newsImage = (ImageView) findViewById(R.id.news_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		messageText = (TextView) findViewById(R.id.message_text);
		contactsText = (TextView) findViewById(R.id.contacts_text);
		newsText = (TextView) findViewById(R.id.news_text);
		settingText = (TextView) findViewById(R.id.setting_text);
		homeLayout.setOnClickListener(this);
		classifyLayout.setOnClickListener(this);
		businessLayout.setOnClickListener(this);
		shopcarLayout.setOnClickListener(this);
	}
	 
}
