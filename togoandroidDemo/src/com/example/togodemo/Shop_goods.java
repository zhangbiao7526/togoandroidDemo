package com.example.togodemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import com.example.selfcenter.self_addressadministration;
import com.example.togodemo.user.NetUtil;
import net.tsz.afinal.FinalBitmap;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * @杨鸿谋  购买页面
 */
public class Shop_goods extends ActionBarActivity implements OnClickListener{

	private FinalBitmap fm;
	private TextView tv_oneshop_shopname, tv_oneshop_money, 
	tv_shop_buyquantity,tv_shop1_buyquantity,tv_shopgoods_price,tv_shopgoods1_price ;
	private TextView edt_u_arrive_address,edt_u_arrive_phone,edt_u_arrive_peisong;
	private ImageView iv_;
	private Button btn_shopgoods_reduce,btn_shopgoods_add,btn_shopgoods_affirm;
	EditText edt_shopgoods_buyquantity,et_leave_message;
	String img_uri,user_peisong="圆通快递";
	int num ;
	//1/6
	String string_different;
	//设置数据
	FileInputStream file=null;
	Result_sum result_sum;
	myApplication my;
	Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_goods);
		my=(myApplication) this.getApplication();
		
		android.support.v7.app.ActionBar action = getSupportActionBar();
		action.setDisplayHomeAsUpEnabled(true);
		action.setHomeButtonEnabled(true);
		action.setDisplayShowHomeEnabled(true);
		action.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.danfense));
		  
		fm = FinalBitmap.create(this);
		
		iv_ = (ImageView) findViewById(R.id.iv_goods);
        edt_shopgoods_buyquantity =(EditText) findViewById(R.id.edt_shopgoods_buyquantity);
        btn_shopgoods_reduce =(Button) findViewById(R.id.btn_shopgoods_reduce);
        btn_shopgoods_add=(Button) findViewById(R.id.btn_shopgoods_add);
		tv_oneshop_shopname = (TextView) findViewById(R.id.tv_oneshop_shopname);
		tv_oneshop_money = (TextView) findViewById(R.id.tv_shopgood_money);
		tv_shop_buyquantity=(TextView) findViewById(R.id.tv_shop_buyquantity);
		tv_shop1_buyquantity=(TextView) findViewById(R.id.tv_shop1_buyquantity);
		tv_shopgoods_price=(TextView) findViewById(R.id.tv_shopgoods_price);
		tv_shopgoods1_price=(TextView) findViewById(R.id.tv_shopgoods1_price);
		btn_shopgoods_affirm=(Button) findViewById(R.id.btn_shopgoods_affirm);
		
		edt_u_arrive_address=(TextView) findViewById(R.id.edt_u_arrive_address);
		edt_u_arrive_phone=(TextView) findViewById(R.id.edt_u_arrive_phone);
		edt_u_arrive_peisong=(TextView) findViewById(R.id.edt_u_arrive_peisong);
		et_leave_message=(EditText) findViewById(R.id.et_leave_message);
		
		//1/5
		string_different=this.getIntent().getStringExtra("from_different");
		 if("from_self_address".equals(string_different)){
			    num=Integer.parseInt(this.getIntent().getStringExtra("edt_shopgoods_buyquantity"));
				String img_uri=this.getIntent().getStringExtra("img_uri");
				fm.display(iv_, img_uri);
				String tv_oneshop_shopname1=this.getIntent().getStringExtra("tv_oneshop_shopname");
				String tv_oneshop_money1=this.getIntent().getStringExtra("tv_oneshop_money");
				
				edt_u_arrive_address.setText(this.getIntent().getStringExtra("useraddress"));
				edt_u_arrive_phone.setText(this.getIntent().getStringExtra("userphone"));
				edt_u_arrive_peisong.setText(user_peisong);
				
				tv_oneshop_shopname.setText(tv_oneshop_shopname1);
				tv_oneshop_money.setText(tv_oneshop_money1);
				
				btn_shopgoods_reduce.setOnClickListener(this);
				btn_shopgoods_add.setOnClickListener(this);
				btn_shopgoods_affirm.setOnClickListener(this);
				//1/6
				//edt_shopgoods_buyquantity.setText("1");
				edt_shopgoods_buyquantity.setText(this.getIntent().
						getStringExtra("edt_shopgoods_buyquantity"));
				et_leave_message.setText(this.getIntent().getStringExtra("et_leave_message"));
				tv_shop_buyquantity.setText(this.getIntent().getStringExtra("edt_shopgoods_buyquantity"));
				tv_shop1_buyquantity.setText(this.getIntent().getStringExtra("edt_shopgoods_buyquantity"));
				
				tv_shopgoods_price.setText(this.getIntent().getStringExtra("tv_shopgoods_price"));
				tv_shopgoods1_price.setText(this.getIntent().getStringExtra("tv_shopgoods_price"));
				edt_shopgoods_buyquantity.setCursorVisible(false);
				
		 }else if("from_OneSop_click".equals(string_different)){
			//12/27
			 bundle = this.getIntent().getExtras();
			 if(my.getUser_address()!=null){
					edt_u_arrive_address.setText(my.getUser_address());
					edt_u_arrive_phone.setText(my.getUser_phone());
					edt_u_arrive_peisong.setText(user_peisong);
					}
					
					img_uri=bundle.getString("img_uri");
					fm.display(iv_, img_uri);
					tv_oneshop_shopname.setText(bundle.getString("tv_oneshop_shopname"));
					tv_oneshop_money.setText(bundle.getString("tv_oneshop_money"));
					
					btn_shopgoods_reduce.setOnClickListener(this);
					btn_shopgoods_add.setOnClickListener(this);
					btn_shopgoods_affirm.setOnClickListener(this);
					edt_shopgoods_buyquantity.setText("1");
					//Toast.makeText(this, bundle.getString("img_uri"), 3000).show();
					tv_shopgoods_price.setText(tv_oneshop_money.getText().toString().trim());
					tv_shopgoods1_price.setText(tv_oneshop_money.getText().toString().trim());
					edt_shopgoods_buyquantity.setCursorVisible(false);
		 }
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		// 监听到返回键
		case android.R.id.home:
			finish();
			break;
		
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View arg0) {
		int id=arg0.getId();
		
		switch (id) {
		case R.id.btn_shopgoods_add:
//			Toast.makeText(this, ""+(++num), 1000).show();
			edt_shopgoods_buyquantity.setText(""+(++num));
			tv_shop_buyquantity.setText("ｘ"+edt_shopgoods_buyquantity.getText().toString().trim());
			tv_shop1_buyquantity.setText(edt_shopgoods_buyquantity.getText().toString().trim());
			
			result_sum=new Result_sum(tv_oneshop_money.getText().toString(), num);
			
		//	Toast.makeText(this, result_sum.toString()+","+result_sum.mul(), Toast.LENGTH_SHORT).show();
			tv_shopgoods_price.setText(""+result_sum.mul());
			tv_shopgoods1_price.setText(""+result_sum.mul());
//			result_sum.set
//			result_sum.mul(tv_oneshop_money.getText().toString().trim(), num);
			break;
		case R.id.btn_shopgoods_reduce:
			if(num>1){
			edt_shopgoods_buyquantity.setText(""+(--num));
			tv_shop_buyquantity.setText("ｘ"+edt_shopgoods_buyquantity.getText().toString().trim());
			tv_shop1_buyquantity.setText(edt_shopgoods_buyquantity.getText().toString().trim());
			
			result_sum=new Result_sum(tv_oneshop_money.getText().toString(), num);
			
			//	Toast.makeText(this, result_sum.toString()+","+result_sum.mul(), Toast.LENGTH_SHORT).show();
				tv_shopgoods_price.setText(""+result_sum.mul());
				tv_shopgoods1_price.setText(""+result_sum.mul());
			}
			break;
		case R.id.btn_shopgoods_affirm:
			//12/25
			if("".equals(edt_u_arrive_address.getText().toString().trim())||"null".equals(edt_u_arrive_address.getText().toString().trim())){
				Toast.makeText(this, "收货地址不能为空", Toast.LENGTH_SHORT).show();
				
				Intent in=new Intent(Shop_goods.this,self_addressadministration.class);
				in.putExtra("from_butong","from_shop_goods");
				in.putExtra("img_uri", img_uri);
				//1/5
				in.putExtra("tv_oneshop_shopname",bundle.getString("tv_oneshop_shopname"));
				in.putExtra("tv_oneshop_money",bundle.getString("tv_oneshop_money"));
				in.putExtra("shop_id", this.getIntent().getStringExtra("shop_id"));
				//1/6 
				in.putExtra("edt_shopgoods_buyquantity", edt_shopgoods_buyquantity.getText().toString());
				if(et_leave_message.getText().toString().length()>0){
					in.putExtra("et_leave_message", et_leave_message.getText().toString());
				}
//				in.putExtra("tv_shopgoods_price", ""+result_sum.mul());
				in.putExtra("tv_shopgoods_price", ""+tv_shopgoods_price.getText().toString().trim());
				startActivity(in);
				finish();
				
			}else if("".equals(edt_u_arrive_phone.getText().toString().trim())||"null".equals(edt_u_arrive_phone.getText().toString().trim())){
				Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();		
			}else if("".equals(edt_u_arrive_peisong.getText().toString().trim())||"null".equals(edt_u_arrive_peisong.getText().toString().trim())){
				Toast.makeText(this, "送货方式不能为空", Toast.LENGTH_SHORT).show();
			}else {
				final Intent in=super.getIntent();
				add_users();
//				Toast.makeText(this, "用户名："+my.getUser_name()+",商品id:"+in.getStringExtra("shop_id")+",送货地址"+my.getUser_address()
//						+",商品名："+tv_oneshop_shopname.getText().toString()+",购买数量："+tv_shop_buyquantity.getText().toString()
//						+",商品单价："+tv_oneshop_money.getText().toString()+"配送方式："+edt_u_arrive_address.getText().toString(), 1000).show();
					
				String username=my.getUser_name(), shopid=in.getStringExtra("shop_id"),
						shop_name=tv_oneshop_shopname.getText().toString(),shop_num=edt_shopgoods_buyquantity.getText().toString()
						,shop_price=tv_oneshop_money.getText().toString(),peisong=edt_u_arrive_peisong.getText().toString().trim(),
						user_address=edt_u_arrive_address.getText().toString().trim(),
						arrive_phone=edt_u_arrive_phone.getText().toString().trim();
				String allmoney=tv_shopgoods1_price.getText().toString().trim();
				NetUtil.addUserAddress(this,username,edt_u_arrive_address.getText().toString().trim(),edt_u_arrive_phone.getText().toString().trim());
				NetUtil.addUserIndent(this,username,shopid,user_address,shop_name,shop_num,shop_price,arrive_phone,peisong,allmoney);
				
//				Intent intent_s=new Intent();
//				intent_s.putExtra("tv_shopgoods1_price", tv_shopgoods1_price.getText().toString());
//				intent_s.setClass(Shop_goods.this,Shopgoods_successful.class);
//				startActivity(intent_s);
//	            Toast.makeText(Shop_goods.this, ""+intent_s.toString(), Toast.LENGTH_LONG).show();

			}
			break;
			
		default:
			break;
		}
	}

	private void add_users() {
		
	}

	//相乘
	class Result_sum {
		private String money;
		private int num;
		
		 public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public Result_sum(String money, int num) {
			super();
			this.money = money;
			this.num = num;
		}

		public Result_sum() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Result_sum [money=" + money + ", num=" + num + "]";
		}

		public  double mul(){
				
//			    String money1=tv_oneshop_money.getText().toString().trim();
				//使用BigDecimal进行类型转换
				BigDecimal money2=new BigDecimal(money);
				//表示保留2位小数，默认用四舍五入方式 
				 money2=money2.setScale(2, BigDecimal.ROUND_HALF_UP);
				 
				 
		  //      BigDecimal b1 = new BigDecimal(Double.toString(money));
		        BigDecimal b2 = new BigDecimal(Double.toString(num));
		        double result = money2.multiply(b2).doubleValue();
		        return result ;
		}
		
	}
	public void addAddress(String address){
		FileOutputStream os_sdk=null;
		
		try {
		//Environment.MEDIA_MOUNTED,如果外存被挂载，那么sd卡就是可以使用的
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			//指定一个sdk路径file
			//Environment.getExternalStorageDirectory().getPath()获得外存的目录
			//String file=Environment.getExternalStorageDirectory().getPath()+"/u10.txt";
			File dir=Environment.getExternalStorageDirectory();
//			File file=new File(dir, "u10.txt");
			//第一个storage/sdcard0/代表sd卡
			//写入sd卡里的storage/sdcard0文件夹
			File file=new File("storage/sdcard0/storage/sdcard0", "useraddress.txt");
				os_sdk=new FileOutputStream(file);
				os_sdk.write("地址".getBytes());
//				Toast.makeText(this, "存入sdk,"+dir.toString(), Toast.LENGTH_SHORT).show();
			}
		}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				if(os_sdk!=null){
					try {
						os_sdk.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
	}
	public void readerAddress(){
		try {
		
		
		//读取sd卡数据,判断是否有sd卡
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
		  
		  File	fi=new File ("storage/sdcard0/storage/sdcard0","useraddress.txt");
		  file=new FileInputStream(fi);//读sd卡数据
			//用for循环读取数据。创个简单的缓存
			int len=0;
			byte[] buffer=new byte[1024];
			//将读出的数据存这,内存流
			ByteArrayOutputStream BAOS=new ByteArrayOutputStream();
			while((len=file.read(buffer))>0){
			//读一次写一次
			BAOS.write(buffer,0,len);	
			}
			//需要一个字符串放置while循环读出来的片段，读取图片也是一样，要一个数组存储读取到的片段，最后保存，然后输出
			String text=new String (BAOS.toByteArray());
		}
		//读文本对应id==R.id.btn_outPutStream
//		file=openFileInput("txta.txt");//读rom（永久性存在的数据，除非清除）
//		//file=new FileInputStream(file);//读sd卡数据
//		//用for循环读取数据。创个简单的缓存
//		int len=0;
//		byte[] buffer=new byte[1024];
//		//将读出的数据存这,内存流
//		ByteArrayOutputStream BAOS=new ByteArrayOutputStream();
//		while((len=file.read(buffer))>0){
//		//读一次写一次
//		BAOS.write(buffer,0,len);	
//		}
//		//需要一个字符串放置
//		String text=new String (BAOS.toByteArray());
//		tv_1.setText(text);
//		//读取图片
//		file=openFileInput("test.png");
//		//将图片包装成Bitmap
//		Bitmap bt=BitmapFactory.decodeStream(file);
//		iv_1.setImageBitmap(bt);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//tv_1.setText(text);
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(file!=null){
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}
}


