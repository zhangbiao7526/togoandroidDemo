package com.example.selfcenter;

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
import com.example.togodemo.mode.Indent;
import com.example.togodemo.variable.VARIABLE;

	/**
	 * 自定义adapter (HomeFragment.java)
	 * @author Administrator
	 *
	 */
	public class NewAdapter_dingdan extends BaseAdapter{
		private List<Indent> data;
		 private Context hc;
		 private FinalBitmap fm;
		
		 public <T> NewAdapter_dingdan(Context h,List<Indent> list_indent,FinalBitmap fm) {
			this.data=list_indent;
			this.hc=h;
			this.fm=fm;
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
			Indent indent = null;
			//创建对象
			if(data!=null){
				indent=data.get(position);}
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
			iv_shop.setText(indent.getF_c_Ishopname());
			tv_shopname.setText("$"+indent.getShopInfo().getF_d_Ssprice());
			tv_shopsome.setText(""+indent.getF_i_Inum());
			fm.display(iv_shopimage, VARIABLE.IMAGE_URL+indent.getShopInfo().getF_c_Simagpath());
			
			tv_shopname.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Toast.makeText(hc, tv_shopname.getText().toString(), Toast.LENGTH_SHORT).show();	
				}
			});
			
			return convertView;
		}
	}

