package com.example.togodemo.home;

import java.util.ArrayList;
import java.util.List;

import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.ShopInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home_moreshop_click extends Activity {

	private ShopInfo shop;
	private ArrayList<ShopInfo> list_goodmore;
	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		myApplication my=(myApplication) this.getApplication();
		
		listview=(ListView) findViewById(R.id.shop_listview);
		if(my.getList_buymoreshop()!=null){
			ArrayList<ShopInfo> list_buymore=(ArrayList<ShopInfo>) my.getList_buymoreshop();
			NewAdapter newadapter=new NewAdapter(list_buymore);
			listview.setAdapter(newadapter);
			Toast.makeText(this, list_buymore.get(0).toString(), Toast.LENGTH_SHORT).show();	
		}else{
			Toast.makeText(this, "未加载完成，请返回", Toast.LENGTH_SHORT).show();
		}
		//list_buymore=(ArrayList<ShopInfo>) my.getList_goodshop();//数据源
		
	}
	
	/**
	 * 自定义adapter
	 * @author Administrator
	 *
	 */
	class NewAdapter extends BaseAdapter{
		private List<ShopInfo> data;
		
		public NewAdapter(List<ShopInfo> data) {
			this.data=data;
		}
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int  position) {
			//这里不能缺，否则会找不到对象
			return data.get(position);
		}

		/**
		 * 条目的id
		 */
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/**
		 * View convertView作为缓存的对象
		 * 说明每个条目的布局
		 * convertView 缓存的条目
		 * parent 就是listView
		 * 返回值作为listView的一个条目
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//创建对象
			ShopInfo s=data.get(position);
			//缓存convertView
			if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(Home_moreshop_click.this);
			//R.layout.simpleitem 布局文件
			convertView=inflater.inflate(R.layout.listview_item, null);
			}
			//要从convertView 找对象，否则回报空指针
			final TextView iv_shop=(TextView) convertView.findViewById(R.id.textView1);
			final TextView tv_shopname=(TextView) convertView.findViewById(R.id.textView2);
			TextView tv_shopsome=(TextView) convertView.findViewById(R.id.textView3);
			
			//获取图片要注意，不要写成setId
			//setText不能添加纯数字，要在数字前面加字符串，比如加个数量
			iv_shop.setText(s.getF_c_Sname());
			tv_shopname.setText("$"+s.getF_d_Ssprice());
			tv_shopsome.setText(s.getF_c_Stype());
			
			tv_shopname.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Toast.makeText(Home_moreshop_click.this, tv_shopname.getText().toString(), Toast.LENGTH_SHORT).show();	
				}
			});
			
			return convertView;
		}
	}
		
}
