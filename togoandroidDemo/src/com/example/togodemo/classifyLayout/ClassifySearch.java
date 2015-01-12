package com.example.togodemo.classifyLayout;

import java.util.List;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.home.NewAdapter_Seach;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.ztest.SearchNet;

public class ClassifySearch extends Activity {

	private ListView listview_search;
	private FinalBitmap fm;
	private List<ShopInfo> list_shopinfos;
	private NewAdapter_Seach adapter;
	private TextView tv_title;
	private EditText edt_search;
	private RelativeLayout layout_search;
	private ImageView img_back, img_search_visible, img_search_gone;
	 InputMethodManager manager ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 自定义标题栏
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);

		// 自定义的标题栏xml布局
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.layout_title_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(R.string.app_name);
		
		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  //单击空白处取消输入框
		
		img_back = (ImageView) findViewById(R.id.img_back);
		img_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ClassifySearch.this.finish();
			}
		});
		
		img_search_visible = (ImageView) findViewById(R.id.img_search_visible);
		layout_search = (RelativeLayout) findViewById(R.id.layout_search);
		img_search_visible.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				img_search_visible.setVisibility(View.GONE);
				layout_search.setVisibility(View.VISIBLE);
			}
		});
		
		edt_search=(EditText) findViewById(R.id.edt_search);
		img_search_gone = (ImageView) findViewById(R.id.img_search_gone);
		img_search_gone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String edittext=edt_search.getText().toString().trim();
				if("".equals(edittext)){
					Toast.makeText(ClassifySearch.this, "请输入商品名称或类型", Toast.LENGTH_SHORT).show();
				}else{
				SearchNet.getDataBySearch_child(ClassifySearch.this,edittext,listview_search,adapter,fm);
				}}
		});
		
		listview_search = (ListView) findViewById(R.id.shop_listview);
		fm = FinalBitmap.create(this);
		myApplication ma = (myApplication) this.getApplication();

		if (ma.getList_goodshop() != null) {
			list_shopinfos = ma.getList_goodshop();
			adapter = new NewAdapter_Seach(this, list_shopinfos, fm);

			listview_search.setAdapter(adapter);

			listview_search.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					ShopInfo shop = (ShopInfo) listview_search
							.getItemAtPosition(arg2);
					// Toast.makeText(ClassifySearch.this,shop.toString(),
					// 1000).show();
					Intent intent = new Intent(ClassifySearch.this,
							OneSop_click.class);
					// 如果全局缓存不为空
					if (shop != null) {
						intent.putExtra("method", "home_buymore");
						Bundle bundle = new Bundle();
						bundle.putParcelable("buy_moreshop", shop);
						intent.putExtras(bundle);
						
						edt_search.setText("");
						
						startActivity(intent);
						// getActivity().startActivity(intent);
					} else {
						Toast.makeText(ClassifySearch.this, "未联入后台",
								Toast.LENGTH_LONG).show();
						intent.setClass(ClassifySearch.this, OneSop_click.class);
						startActivity(intent);
					}
				}
			});
		} else {
			Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
		}
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
	
	// 12/26
	// 删除listview中item
	// public void showInfo(final int position) {
	// new AlertDialog.Builder(this).setTitle("我的提示").setMessage("确定要删除吗？")
	// .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// list_shopinfos.remove(position);
	// // 通过程序我们知道删除了，但是怎么刷新ListView呢？
	// // 只需要重新设置一下adapter
	// listview_search.setAdapter(adapter);
	// }
	// }).show();
	// }
	//
}
