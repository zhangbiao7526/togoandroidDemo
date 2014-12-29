package com.example.togodemo.business;

import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalBitmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.togodemo.R;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;

	/**
	 * 自定义adapter (HomeFragment.java)
	 * @author Administrator
	 *
	 */
	public class NewAdapter_Buss extends BaseAdapter{
		private List<HashMap<String, ShopInfo>> data;
		 private Context hc;
		 private FinalBitmap fm;
		 private String string;
		
		 public NewAdapter_Buss(Context h,List<HashMap<String, ShopInfo>> data,FinalBitmap fm, String string) {
			this.data=data;
			this.hc=h;
			this.fm=fm;
			this.string=string;
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
				ShopInfo s=data.get(position).get(string);
			//缓存convertView
			if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(hc);
			//R.layout.simpleitem 布局文件
			convertView=inflater.inflate(R.layout.listview_item, null);
			}
			//要从convertView 找对象，否则回报空指针
			final TextView iv_shop=(TextView) convertView.findViewById(R.id.textView1);
			final TextView tv_shopname=(TextView) convertView.findViewById(R.id.textView2);
			TextView tv_shopsome=(TextView) convertView.findViewById(R.id.textView3);
			ImageView iv_shopimage=(ImageView) convertView.findViewById(R.id.imageView1);
			//获取图片要注意，不要写成setId
			//setText不能添加纯数字，要在数字前面加字符串，比如加个数量
			iv_shop.setText(s.getF_c_Sname());
			tv_shopname.setText("$"+s.getF_d_Ssprice());
			tv_shopsome.setText(s.getF_c_Stype());
			fm.display(iv_shopimage, VARIABLE.IMAGE_URL+s.getF_c_Simagpath());
			
			tv_shopname.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Toast.makeText(hc, tv_shopname.getText().toString(), Toast.LENGTH_SHORT).show();	
				}
			});
			
			return convertView;
		}
	}

