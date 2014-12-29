package com.example.togodemo.classifyLayout;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.OneSop_click;
import com.example.togodemo.R;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.example.togodemo.ztest.ClassifyNet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 后台限定死了类型，未更改
 * @author Administrator
 *
 */
public class Classify_Activity_children extends Activity implements android.widget.AdapterView.OnItemClickListener{
	
	private NewAdapter newadapter;
	private FinalBitmap fm;
	private ListView find_class_list;
	private List<ShopInfo> data;
	public myApplication my;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		find_class_list=(ListView) findViewById(R.id.shop_listview);
		find_class_list.setOnItemClickListener(this);
		fm = FinalBitmap.create(this);
		my=(myApplication) this.getApplication();
		
		Intent in=super.getIntent();
		String fenlei=in.getStringExtra("fenlei");
		newadapter = new NewAdapter();
		ClassifyNet.Find_classify(this, fenlei, find_class_list, newadapter);
		
//		Toast.makeText(this, "子listView"+fenlei, Toast.LENGTH_SHORT).show();
		
	}
	
	public class NewAdapter extends BaseAdapter{
		private List<ShopInfo> data;
		//绑定数据
		public List<ShopInfo> getData() {
			return data;
		}
		public void setData(List<ShopInfo> data) {
			this.data = data;
		}
		public NewAdapter(){
			super();
		}	
		public NewAdapter(List<ShopInfo> data) {
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
		public View getView(int position, View convertView, ViewGroup parent) {
			//创建对象
			ShopInfo s=data.get(position);
			//缓存convertView
			if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(Classify_Activity_children.this);
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
					Toast.makeText(Classify_Activity_children.this, tv_shopname.getText().toString(), Toast.LENGTH_SHORT).show();	
				}
			});
			
			return convertView;
		}
		
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Classify_Activity_children.this,OneSop_click.class);
				//如果全局缓存不为空
				if(my.getList_buymoreshop()!=null){
				intent.putExtra("method", "home_buymore");
				Bundle bundle = new Bundle();
				ShopInfo shop=(ShopInfo) parent.getItemAtPosition(position);
				bundle.putParcelable("buy_moreshop", shop);
				//Toast.makeText(this, shop.toString(), Toast.LENGTH_SHORT).show();
//				intent.setClass(Classify_Activity_children.this, OneSop_click.class);
				intent.putExtras(bundle);
				startActivity(intent);
				//getActivity().startActivity(intent);
				}else{
					Toast.makeText(Classify_Activity_children.this, "未联入后台", Toast.LENGTH_LONG).show();
					intent.setClass(Classify_Activity_children.this, OneSop_click.class);
					startActivity(intent);
				}
		}

}
