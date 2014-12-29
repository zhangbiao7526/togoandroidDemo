package com.example.togodemo.ishopcar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.R;
import com.example.togodemo.ShopcarFragment;
import com.example.togodemo.myApplication;
import com.example.togodemo.mode.UserShopCar;
import com.example.togodemo.variable.VARIABLE;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class DemoAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context context = null;
	
	/**
	 * 数据集合
	 */
	private List<UserShopCar> datas ;
	
	/**
	 *图片展示 
	 */
	private FinalBitmap bm;
	private myApplication my;
	/**
	 * CheckBox 是否选择的存储集合,key 是 position , value 是该position是否选中
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	private Map<Integer, Integer> shopid = new HashMap<Integer, Integer>();

	public DemoAdapter(Context context, List<UserShopCar> result, FinalBitmap bm, myApplication my) {
		this.datas = result;
		this.context = context;
		this.bm=bm;
		this.my=my;
		// 初始化,默认都没有选中
		configCheckMap(false);
	}
	/**
	 * 首先,默认情况下,所有项目都是没有选中的.这里进行初始化
	 */
	public void configCheckMap(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			isCheckMap.put(i, bool);
		}

	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewGroup layout = null;

		/**
		 * 进行ListView 的优化
		 */
		if (convertView == null) {
			layout = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.shopcat_item, parent, false);
		} else {
			layout = (ViewGroup) convertView;
		}

		final UserShopCar bean = datas.get(position);

		/*
		 * 获得该item 是否允许删除
		 */
		boolean canRemove = bean.isCanRemove();

		/*
		 * 设置每一个item的文本
		 */
		
		TextView tvTitle = (TextView) layout.findViewById(R.id.tv_car_num);
		tvTitle.setText(""+bean.getF_i_Caddnum());
		TextView tvPrice = (TextView) layout.findViewById(R.id.tv_car_price);
		tvPrice.setText(""+bean.getShopInfo().getF_d_Ssprice());
		TextView tv_shopname = (TextView) layout.findViewById(R.id.tv_shopname);
		tv_shopname.setText(""+bean.getShopInfo().getF_c_Sname());
		ImageView ivShop = (ImageView) layout.findViewById(R.id.iv_item_shopcarimg);
		bm.display(ivShop,VARIABLE.IMAGE_URL+bean.getShopInfo().getF_c_Simagpath());

		/*
		 * 获得单选按钮
		 */
		CheckBox cbCheck = (CheckBox) layout.findViewById(R.id.cbCheckBox);

		/*
		 * 设置单选按钮的选中
		 */
		cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				/*
				 * 将选择项加载到map里面寄存
				 */
				isCheckMap.put(position, isChecked);
				shopid.put(position	, bean.getShopInfo().getF_i_Sid());
				
//				Toast.makeText(context, ""+shopid.get(position), Toast.LENGTH_SHORT).show();
				getMoney();
				
			}
		});

		if (!canRemove) {
			// 隐藏单选按钮,因为是不可删除的
			cbCheck.setVisibility(View.GONE);
			cbCheck.setChecked(false);	
		} else {
			cbCheck.setVisibility(View.VISIBLE);

			if (isCheckMap.get(position) == null) {
				isCheckMap.put(position, false);
			}

			cbCheck.setChecked(isCheckMap.get(position));

			ViewHolder holder = new ViewHolder();

			holder.cbCheck = cbCheck;

			holder.tvTitle = tvTitle;

			/**
			 * 将数据保存到tag
			 */
			layout.setTag(holder);
		}

		return layout;
	}

	/**
	 * 增加一项的时候
	 */
	public void add(UserShopCar bean) {
		this.datas.add(0, bean);

		// 让所有项目都为不选择
		configCheckMap(false);
	}

	// 移除一个项目的时候
	public void remove(int position) {
		this.datas.remove(position);
	}

	public Map<Integer, Boolean> getCheckMap() {
		return this.isCheckMap;
	}
	public Map<Integer, Integer> getShopid() {
		return this.shopid;
	}

	public static class ViewHolder {

		public TextView tvTitle = null;

		public CheckBox cbCheck = null;
		public Object data = null;

	}

	public List<UserShopCar> getDatas() {
		return datas;
	}
	
	public int getMoney(){
			/*
			 * 删除算法最复杂,拿到checkBox选择寄存map
			 */
			Map<Integer, Boolean> map = this.getCheckMap();

			// 获取当前的数据数量
			int count = this.getCount();
			int num = 0;
			int total = 0;
			// 进行遍历
			for (int i = 0; i < count; i++) {

				// 因为List的特性,删除了2个item,则3变成2,所以这里要进行这样的换算,才能拿到删除后真正的position
				int position = i - (count - this.getCount());

				if (map.get(i) != null && map.get(i)) {

					UserShopCar bean = (UserShopCar) this
							.getItem(position);

					if (bean.isCanRemove()) {
						
						// 进行遍历
						UserShopCar shopcar = (UserShopCar) this.getItem(i);

							num = num + shopcar.getF_i_Caddnum();
							int nums = shopcar.getF_i_Caddnum();
							total = total
									+ (int) (nums * shopcar.getShopInfo()
											.getF_d_Ssprice());
							
					} 

				}
			}
			
			return total;
		}
	
	
}
