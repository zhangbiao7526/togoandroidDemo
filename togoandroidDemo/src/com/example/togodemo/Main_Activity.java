package com.example.togodemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.example.togodemo.ui.abstOnTouchListener;
import com.example.togodemo.user.NetUtil;
import com.example.togodemo.user.User_Login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
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
public class Main_Activity extends abstOnTouchListener {
	
	TextView tv_geren,tv_shoucang,tv_allorders,
			 tv_addressadministration,tv_callcenter,btn_close;
	
	private Uri imageUri;
	public static final int SELECTPIC=11;
	
	private static final int STROKE_WIDTH = 4;
	ImageView self_headimg;
	File f;
	Uri imageuri;
	int uid=2;
	static Properties prop;
	InputStream  is=null;
	String path="";
	Intent intent;
	private int sys_brightness;
	public static final int SLE_PH=10;
	static{
		InputStream is=Main_Activity.class.getResourceAsStream("/project.properties");
		prop=new Properties();
		try {
			prop.load(is);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static final String WEBURL=prop.getProperty("weburl")+"Sendheadimg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.layout_title);
		// 找到个人中心textview
		//12/25
		 tv_geren=(TextView) findViewById(R.id.textView1);
		 tv_shoucang = (TextView) findViewById(R.id.tv_baobeishoucang);
		 tv_allorders = (TextView) findViewById(R.id.tv_allorders);
		 tv_addressadministration = (TextView) findViewById(R.id.tv_addressadministration);
		 tv_callcenter = (TextView) findViewById(R.id.tv_callcenter);
		 self_headimg=(ImageView) findViewById(R.id.self_headimg);
		 TextView btn_close=(TextView) findViewById(R.id.tv_close);
		// 单击事件
		tv_shoucang.setClickable(true);
		tv_allorders.setClickable(true);
		tv_addressadministration.setClickable(true);
		tv_callcenter.setClickable(true);
		// 获取焦点事件
		tv_shoucang.setFocusable(true);
		tv_allorders.setFocusable(true);
		tv_addressadministration.setFocusable(true);
		tv_callcenter.setFocusable(true);

		tv_shoucang.setOnClickListener(this);
		tv_allorders.setOnClickListener(this);
		tv_addressadministration.setOnClickListener(this);
		tv_callcenter.setOnClickListener(this);
		btn_close.setOnClickListener(this);

		// 初始化布局元素
		initViews();
		// 获得fragment管理器
		fragmentManager = getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);

		initValues(); // 初始化 关键属性

		content.setOnTouchListener(this);// 创建content的事件监听

		// imageButton1.setOnTouchListener(this);
		myLinearLayout = (LinearLayout) this.findViewById(R.id.menu);
		right = (TextView) findViewById(R.id.tv_title);
		right_geren = (TextView) findViewById(R.id.tv_title_geren);
		
		f=new File(Environment.getExternalStorageDirectory(),"head.png");
		
		imageuri=Uri.fromFile(f);

		if(imageuri!=null){
			try {
				imageuri=Uri.fromFile(f);
				is= getContentResolver().openInputStream(imageuri);
				Bitmap bm=BitmapFactory.decodeStream(is);
				self_headimg.setImageBitmap(bm);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		self_headimg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent i=new Intent();
				i.setAction(Intent.ACTION_PICK);
				i.setType("image/*");
				i.putExtra("crop", "true");
				i.putExtra("aspectX", 1);
				i.putExtra("aspectY", 1);
				i.putExtra("outputX",200);
				i.putExtra("outputY",200);
				i.putExtra("scale",true);
				i.putExtra("return-data",false);
				i.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
				i.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
				i.putExtra("noFaceDetection",true);
				myApplication my = (myApplication) Main_Activity.this.getApplication();
				my.setUSER_LOGIN(true);
				startActivityForResult(i,SLE_PH);

			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode!=RESULT_OK){
			System.out.println("error");
		}if(requestCode==SLE_PH){
			if(imageuri!=null){
				try {
					is= getContentResolver().openInputStream(imageuri);
					Bitmap bm=BitmapFactory.decodeStream(is);
					Bitmap bm2= toRoundBitmap(bm);
					self_headimg.setImageBitmap(bm2);
					saveMyBitmap(bm2);
					path=Environment.getExternalStorageDirectory()+"/head.png";
					myApplication my=(myApplication) Main_Activity.this.getApplication();
//					Toast.makeText(Main_Activity.this, ""+my.getUser_name(), Toast.LENGTH_SHORT).show();
					NetUtil.uploadDataByHttpClientFile(Main_Activity.this,f);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		/**清除缓存文件*/  
        if(f!=null){
        	f.delete();   
        }
		super.onDestroy();
	}
	
	public void saveMyBitmap(Bitmap mBitmap)  {
		if (f.exists()) { 
			f.delete(); 
			} 
        FileOutputStream fOut = null;
        try {
                fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
                fOut.flush();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
                fOut.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
}
	public Bitmap toRoundBitmap(Bitmap bitmap) {  
        int width = bitmap.getWidth();  
        int height = bitmap.getHeight();  
        float roundPx;  
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;  
        if (width <= height) {  
            roundPx = width / 2;  
            left = 0;  
            top = 0;  
            right = width;  
            bottom = width;  
            height = width;  
            dst_left = 0;  
            dst_top = 0;  
            dst_right = width;  
            dst_bottom = width;  
        } else {  
            roundPx = height / 2;  
            float clip = (width - height) / 2;  
            left = clip;  
            right = width - clip;  
            top = 0;  
            bottom = height;  
            width = height;  
            dst_left = 0;  
            dst_top = 0;  
            dst_right = height;  
            dst_bottom = height;  
        }  
  
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
  
        final int color = 0xff424242;  
        final Paint paint = new Paint();  
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);  
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);  
        final RectF rectF = new RectF(dst);  
  
        paint.setAntiAlias(true);// 设置画笔无锯齿  
  
        canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas  
        paint.setColor(color);  
  
        // 以下有两种方法画圆,drawRounRect和drawCircle  
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。  
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);  
  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452  
        canvas.drawBitmap(bitmap, src, dst, paint); //以Mode.SRC_IN模式合并bitmap和已经draw了的Circle  
        
        
	    //画白色圆圈
	    paint.reset();
	    paint.setColor(Color.WHITE);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setStrokeWidth(STROKE_WIDTH);
	    paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, width / 2, width / 2 - STROKE_WIDTH / 2, paint);
     
        return output;  
    }  

	/**
	 * 重写手机自带退出按钮
	 * 
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i("MainActivity", "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			System.out.println(pageID);
			if (pageID == 0) {
				scrollToContent();
				pageID = -1;
			} else {
				exit();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if (!wantToClose) {
			wantToClose = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
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
	//个人中心单击事件
	public void click(View v) {
		myApplication my = (myApplication) this.getApplication();
		if (flag == 0 && my.isUSER_LOGIN()) {
			pageID = 0;
			scrollToMenu();
			//12/25
//			right.setText(R.string.title_myhome);
			right_geren.setVisibility(View.VISIBLE);
			right.setVisibility(View.GONE);
			tv_geren.setText("账号："+my.getUser_name());
		} else if (!my.isUSER_LOGIN()) {
			Intent in = new Intent(Main_Activity.this, User_Login.class);
			startActivity(in);
		} else if (flag == 1) {
			pageID = -1;
			scrollToContent();
//			right.setText(R.string.title_home);
			right_geren.setVisibility(View.GONE);
			right.setVisibility(View.VISIBLE);
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
