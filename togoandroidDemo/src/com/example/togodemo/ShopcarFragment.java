package com.example.togodemo;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.ztest.NetUtil1;
import com.example.togodemo.ztest.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopcarFragment extends Fragment implements android.widget.AdapterView.OnItemClickListener{
	
	private NewAdapter newadapter;
	private FinalBitmap bm;
	private LayoutInflater shopcar_inflater;
	TextView tv_shopname;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);
		//要创建Final对象  
		bm=FinalBitmap.create(getActivity());
		//创建Fragment上下文
		shopcar_inflater=getActivity().getLayoutInflater();
		ListView listview=(ListView)settingLayout.findViewById(R.id.lv_shopcar);
		//新建一个类，用于异步处理,将listview传过去
		newadapter=new NewAdapter();
		NetUtil1.getDataFromServer(getActivity(),listview,newadapter);
		listview.setOnItemClickListener(this);
		
		return settingLayout;
	}
	public class NewAdapter extends BaseAdapter{
		private List<shop> data;
		//绑定数据
		public List<shop> getData() {
			return data;
		}
		public void setData(List<shop> data) {
			this.data = data;
		}
		public NewAdapter(){
			super();
		}	
		public NewAdapter(List<shop> data) {
			this.data=data;
		}
		/**
		 * 统计item数量,说明listview有多少个条目
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}
		/**
		 * 说明position指定的条目关联的数据对象
		 * position条目id是固定的。getItem无法绑定条目到指定的数据对象
		 */
		@Override
		public Object getItem(int position) {
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			//创建对象
			shop s=data.get(position);
			//缓存convertView
			if(convertView==null){
			//R.layout.simpleitem 布局文件
			convertView=shopcar_inflater.inflate(R.layout.shopcat_item, null);
			}
			//要从convertView 找对象，否则回报空指针
			ImageView iv_shop=(ImageView) convertView.findViewById(R.id.iv_item_shopcarimg);
			 tv_shopname=(TextView) convertView.findViewById(R.id.textView2);
			TextView tv_shopsome=(TextView) convertView.findViewById(R.id.textView3);
			
			//获取图片要注意，不要写成setId
			//setText不能添加纯数字，要在数字前面加字符串，比如加个数量
			bm.display(iv_shop, s.getShoppic());
//			iv_shop.setImageResource(s.getShoppic());
			tv_shopname.setText(s.getShopname());
			tv_shopsome.setText(s.getShopsomething());
			tv_shopname.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "ff"+position, Toast.LENGTH_SHORT).show();
				}
			});
			return convertView;
		}
		
		
		}
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position,
				long id) {
			shop s=new shop();
			myApplication M=(myApplication) getActivity().getApplication();
			List<shop> list=M.getList_shop();
			s=list.get(position);
			String a=s.getShopname();
			String b=s.getShoppic();
			String c=s.getShopsomething();
			Toast.makeText(getActivity(), "a="+a+"b="+b+"c="+c, Toast.LENGTH_SHORT).show();
			
		}

}
