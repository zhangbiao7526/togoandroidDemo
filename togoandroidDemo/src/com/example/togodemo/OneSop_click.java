package com.example.togodemo;

import java.io.File;
import java.lang.reflect.Field;

import net.tsz.afinal.FinalBitmap;

import cn.bidaround.point.YtLog;
import cn.bidaround.youtui_template.YouTuiViewType;
import cn.bidaround.youtui_template.YtTemplate;
import cn.bidaround.youtui_template.YtToast;
import cn.bidaround.ytcore.ErrorInfo;
import cn.bidaround.ytcore.YtShareListener;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;

import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.example.togodemo.ztest.shop;
import com.example.togodemo.ztest.shop_NetUtil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.togodemo.R;

public class OneSop_click extends ActionBarActivity {
	private FinalBitmap fm;
	private TextView tv_oneshop_shopname, tv_oneshop_money, tv_oneshop_type,
			tv_oneshop_address, tv_oneshop_description;
	private ImageView iv_;
	String img_uri;
	private Button  whitegrid_bt;
	private YtTemplate temp;
	private final int GET_POINT = 0;
	private final int GIVE_POINT = 1;
	private final int REDUCE_POINT = 2;
	private final int ADD_POINT = 3;
	private Handler uiHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GET_POINT:
				YtToast.showS(OneSop_click.this, (String)msg.obj);
				break;
			case GIVE_POINT:
				YtToast.showS(OneSop_click.this, (String)msg.obj);
				break;
			case REDUCE_POINT:
				YtToast.showS(OneSop_click.this, (String)msg.obj);
				break;
			case ADD_POINT:
				YtToast.showS(OneSop_click.this, (String)msg.obj);
				break;
			default:
				break;
			}
		};
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oneshop_layout);
		final Intent in = super.getIntent();
		fm = FinalBitmap.create(this);
	
			
			YtTemplate.init(this,"测试账号");
			initView();
		
		// v4的包getActionBar()
				// v7的包getSupportActionBar(),让标题左边多一个返回键、
				// 要在配置文件配置
		android.support.v7.app.ActionBar action = getSupportActionBar();
		action.setDisplayHomeAsUpEnabled(true);
		action.setHomeButtonEnabled(true);
		action.setDisplayShowHomeEnabled(true);
		action.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.danfense));
		showOverflowMenu();
				
		iv_ = (ImageView) findViewById(R.id.iv_oneshop);

		tv_oneshop_shopname = (TextView) findViewById(R.id.tv_oneshop_shopname);
		tv_oneshop_money = (TextView) findViewById(R.id.tv_oneshop_money);
		tv_oneshop_type = (TextView) findViewById(R.id.tv_oneshop_type);
		tv_oneshop_address = (TextView) findViewById(R.id.tv_oneshop_address);
		tv_oneshop_description = (TextView) findViewById(R.id.tv_oneshop_description);

		// 接收从首页传过来的点击事件的值
		Bundle bundle=getIntent().getExtras();
		String method=in.getStringExtra("method");
		if("home_buymore".equals(method)){
		HomeFragment(bundle);
		}else if("home_viewpg".equals(method)){
			// 将控件
		shop_NetUtil.getHome_ViewPage(this, in, fm, tv_oneshop_shopname,
					tv_oneshop_money, tv_oneshop_type, tv_oneshop_address,
					tv_oneshop_description, iv_);	
		}
		
		

	}
	void initView() {
		// 白色网格样式
		whitegrid_bt = (Button) findViewById(R.id.action_2);
	//	whitegrid_bt.setOnClickListener(this);
		
	}
	private void HomeFragment(Bundle bundle) {
		
		ShopInfo shop=bundle.getParcelable("buy_moreshop");	
		img_uri=VARIABLE.IMAGE_URL+ shop.getF_c_Simagpath();
		tv_oneshop_shopname.setText(shop.getF_c_Sname());
		tv_oneshop_money.setText("$" + shop.getF_d_Ssprice());
		fm.display(iv_, img_uri);
		tv_oneshop_type.setText(shop.getF_c_Stype());
		tv_oneshop_address.setText(shop.getF_c_Saddress());
		tv_oneshop_description.setText(shop.getF_c_Sdescription());
	}

	/**
	 * 显示溢出区按钮 若没有此方法，则需要单击menu菜单才会显示actionBar的菜单
	 */
	private void showOverflowMenu() {
		ViewConfiguration config = ViewConfiguration.get(this);
		try {
			// sHasPermanentMenuKey 有没有物理菜单键
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {

				menuKeyField.setAccessible(true);

				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		// 监听到返回键
		case android.R.id.home:
			finish();
			break;
		case R.id.action_1:
			Toast.makeText(this, "单击了笑脸", Toast.LENGTH_SHORT).show();

			break;
		case R.id.action_2:
	//		Toast.makeText(this, "单击了分享", Toast.LENGTH_SHORT).show();
//			  Intent intent=new Intent(Intent.ACTION_SEND);    
//              intent.setType("image/*");    
//              intent.putExtra(Intent.EXTRA_SUBJECT, "来自togo(兔购)");    
//              intent.putExtra(Intent.EXTRA_TEXT,tv_oneshop_shopname.getText());    
//              intent.putExtra(Intent.EXTRA_STREAM,);
//              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
//              startActivity(Intent.createChooser(intent, getTitle()));    
			ShareData whiteviewShareData = new ShareData();
			whiteviewShareData.isAppShare = false;
			whiteviewShareData.setDescription("兔购分享");
			whiteviewShareData.setTitle("兔购分享");
			whiteviewShareData.setText("通过兔购，你可以拥有更多，幸福，源于生活 ");
			whiteviewShareData.setTarget_url("http://youtui.mobi/");
			whiteviewShareData.setImageUrl(VARIABLE.IMAGE_URL+"togo.jpg");
			// shareData.setImagePath(Environment.getExternalStorageDirectory()+YoutuiConstants.FILE_SAVE_PATH+"demo.png");
			YtTemplate whiteGridTemplate = new YtTemplate(this, YouTuiViewType.WHITE_GRID, true);
			whiteGridTemplate.setShareData(whiteviewShareData);
			YtShareListener whiteViewListener = new YtShareListener() {
				@Override
				public void onSuccess(ErrorInfo error) {
					YtLog.w("----", error.getErrorMessage());
				}
				@Override
				public void onPreShare() {
					YtLog.w("----", "onPreShare");
				}
				@Override
				public void onError(ErrorInfo error) {
					YtLog.w("----", error.getErrorMessage());
				}
				@Override
				public void onCancel() {
					YtLog.w("----", "onCancel");
				}
			};
			/** 添加分享结果监听,如果开发者不需要处理回调事件则不必设置 */
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_QQ, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_QZONE, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_RENN, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_SINAWEIBO, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_TENCENTWEIBO, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_WECHAT, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_WECHATMOMENTS, whiteViewListener);
			/**
			 * 为每个平台添加分享数据,如果不单独添加,分享的为whiteViewTemplate.setShareData(
			 * whiteviewShareData)设置的分享数据
			 */
			whiteGridTemplate.addData(YtPlatform.PLATFORM_QQ, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_QZONE, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_RENN, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_SINAWEIBO, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_TENCENTWEIBO, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_WECHAT, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_WECHATMOMENTS, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_MESSAGE, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_EMAIL, whiteviewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_MORE_SHARE, whiteviewShareData);

			whiteGridTemplate.showScreenCapShare();
			temp = whiteGridTemplate;

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}
}
