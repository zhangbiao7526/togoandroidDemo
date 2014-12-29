package com.example.togodemo.home;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.togodemo.R;
import com.example.togodemo.classifyLayout.ClassifySearch;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;

	/**
	 * 自定义adapter (HomeFragment.java)
	 * @author Administrator
	 *
	 */
	public class NewAdapter_Seach extends BaseAdapter{
		private List<ShopInfo> data;
		 private Context hc;
		 private FinalBitmap fm;
		
		 public NewAdapter_Seach(Context h,List<ShopInfo> data,FinalBitmap fm) {
			this.data=data;
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			ShopInfo s = null;
			//创建对象
			if(data!=null){
			s=data.get(position);}
			//缓存convertView
			if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(hc);
			//R.layout.simpleitem 布局文件
			convertView=inflater.inflate(R.layout.listview_item_delete, null);
			}
			//要从convertView 找对象，否则回报空指针
			final TextView iv_shop=(TextView) convertView.findViewById(R.id.textView1);
			final TextView tv_shopname=(TextView) convertView.findViewById(R.id.textView2);
			TextView tv_shopsome=(TextView) convertView.findViewById(R.id.textView3);
			ImageView iv_shopimage=(ImageView) convertView.findViewById(R.id.imageView1);
			Button btn_delete=(Button) convertView.findViewById(R.id.btn_delete);
			//获取图片要注意，不要写成setId
			//setText不能添加纯数字，要在数字前面加字符串，比如加个数量
			iv_shop.setText(s.getF_c_Sname());
			tv_shopname.setText("$"+s.getF_d_Ssprice());
			tv_shopsome.setText(s.getF_c_Stype());
			fm.display(iv_shopimage, VARIABLE.IMAGE_URL+s.getF_c_Simagpath());
			//12/26   删除单击操作
			btn_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Toast.makeText(hc, tv_shopname.getText().toString(), Toast.LENGTH_SHORT).show();	
					((ClassifySearch) hc).showInfo(position);
				}
			});
			
			return convertView;
		}
	}

