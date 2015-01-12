package com.example.togodemo;

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
import com.example.togodemo.user.User_Login;
import com.example.togodemo.variable.VARIABLE;
import com.example.togodemo.ztest.AddFavoriteNet;
import com.example.togodemo.ztest.ShopCarNet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.togodemo.R;

public class OneSop_click extends ActionBarActivity implements OnClickListener{
	private FinalBitmap fm;
	private TextView tv_oneshop_shopname, tv_oneshop_money, tv_oneshop_type,
			tv_oneshop_address, tv_oneshop_description,tv_oneshop_shopactivity;
	private ImageView iv_;
	String img_uri;
	private Button  whitegrid_bt,btn_addshopcar;
	private YtTemplate temp;
	private final int GET_POINT = 0;
	private final int GIVE_POINT = 1;
	private final int REDUCE_POINT = 2;
	private final int ADD_POINT = 3;
	private myApplication my;
	ShopInfo shop;
	private Handler uiHandler8= new Handler(){
		@Override
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
		my=(myApplication) this.getApplication();
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
		tv_oneshop_shopactivity=(TextView) findViewById(R.id.tv_oneshop_shopactivity);

		//12/28
		btn_addshopcar =(Button) findViewById(R.id.btn_addshopcar);
		//12/28
		btn_addshopcar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(!my.isUSER_LOGIN()){
					Intent in = new Intent(OneSop_click.this, User_Login.class);
					startActivity(in);
				}else{
				AddShopcar(shop);
				}
			}

			

		});
		  /*
         * @杨鸿谋    加入立即购买btn,然后跳转页面
         */
		Button btn_immediately_buy=(Button) findViewById(R.id.btn_immediately_buy);
		btn_immediately_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View va) {
				// TODO Auto-generated method stub
				if(!my.isUSER_LOGIN()){
//				Toast.makeText(OneSop_click.this, shop.toString(), 1000).show();
				Intent in = new Intent(OneSop_click.this, User_Login.class);
				//12/25
				startActivity(in);
				}else{
//				Toast.makeText(OneSop_click.this, "ca"+shop.getF_i_Sid(), 1000).show();
				Intent intent_im=new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("img_uri", img_uri);  
				bundle.putString("tv_oneshop_shopname", tv_oneshop_shopname.getText().toString());  
				bundle.putString("tv_oneshop_money", tv_oneshop_money.getText().toString()); 
				//12/25
				bundle.putString("img_uri",img_uri); 
				intent_im.putExtra("user_address", my.getUser_address());
				bundle.putString("img_uri", img_uri); 
				intent_im.putExtras(bundle);
				intent_im.putExtra("shop_id", ""+shop.getF_i_Sid());
				intent_im.putExtra("from_different","from_OneSop_click");
				
//				intent_im.putExtra("tv_oneshop_shopname", tv_oneshop_shopname.getText());
//				intent_im.putExtra("tv_oneshop_money", tv_oneshop_money.getText());
//				intent_im.putExtra("tv_oneshop_type", tv_oneshop_type.getText());
//				intent_im.putExtra("tv_oneshop_address", tv_oneshop_address.getText());
				
				intent_im.setClass(OneSop_click.this, Shop_goods.class);
				startActivity(intent_im);
				}
			}
		});

		// 接收从首页传过来的点击事件的值
		Bundle bundle=getIntent().getExtras();
		String method=in.getStringExtra("method");
		String shop_activity=in.getStringExtra("activity");
		if("home_buymore".equals(method)){
			HomeFragment(bundle,shop_activity);
		}
		//12/25
//		else if("home_viewpg".equals(method)){
//			// 将控件
//		shop_NetUtil.getHome_ViewPage(this, in, fm, tv_oneshop_shopname,
//					tv_oneshop_money, tv_oneshop_type, tv_oneshop_address,
//					tv_oneshop_description, iv_);	
//		}
		
		

	}
	void initView() {
		// 白色网格样式
		whitegrid_bt = (Button) findViewById(R.id.action_2);
	//	whitegrid_bt.setOnClickListener(this);
		
	}
	//所有商品的汇总
	private void HomeFragment(Bundle bundle, String str) {
		
		shop=bundle.getParcelable("buy_moreshop");	
//		Toast.makeText(this, shop.toString(), 1000).show();
		img_uri=VARIABLE.IMAGE_URL+ shop.getF_c_Simagpath();
		tv_oneshop_shopname.setText(shop.getF_c_Sname());
		tv_oneshop_money.setText(""+shop.getF_d_Ssprice());
		fm.display(iv_, img_uri);
		tv_oneshop_type.setText(shop.getF_c_Stype());
		tv_oneshop_address.setText(shop.getF_c_Saddress());
		tv_oneshop_description.setText(shop.getF_c_Sdescription());
		
		tv_oneshop_shopactivity.setText(str);

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
			if(!my.isUSER_LOGIN()){
				
			Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();		
			Intent in = new Intent(OneSop_click.this, User_Login.class);
			startActivity(in);
			
			}else{
				
				String username=my.getUser_name();
				Bundle bundle=getIntent().getExtras();
				ShopInfo shopinfo=bundle.getParcelable("buy_moreshop");
				AddFavoriteNet.addFavoriteData(OneSop_click.this,shopinfo.getF_i_Sid(), username);
				
//				Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
				
			}
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
//			whiteviewShareData.setTarget_url("http://youtui.mobi/");
			whiteviewShareData.setTarget_url("http://www.baidu.com");
			whiteviewShareData.setImageUrl(VARIABLE.IMAGE_URL+ shop.getF_c_Simagpath());
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
	@Override
	public void onClick(View v) {
		int id=v.getId();
		
		switch (id) {
//		case R.id.btn_addshopcar:
//			
//			ShopCarNet.addShopCar();
//			break;

		default:
			break;
		}
		
	}
	private void AddShopcar(ShopInfo shop) {
		
		int shopid=shop.getF_i_Sid();
		String username=my.getUser_name();
		int shopnum=1;
		ShopCarNet.addShopCar(OneSop_click.this,shopid,username,shopnum);
	}
}
