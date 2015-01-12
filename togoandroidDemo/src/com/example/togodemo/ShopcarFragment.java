package com.example.togodemo;

import java.util.Map;

import net.tsz.afinal.FinalBitmap;

import com.example.selfcenter.self_addressadministration;
import com.example.togodemo.ishopcar.DemoAdapter;
import com.example.togodemo.mode.UserShopCar;
import com.example.togodemo.user.NetUtil;
import com.example.togodemo.ztest.ShopCarNet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopcarFragment extends Fragment implements OnClickListener,
		android.widget.AdapterView.OnItemClickListener {

	/**
	 * 确定按钮
	 */
	private ViewGroup btnAdd = null;
	/**
	 * 选择所有
	 */
	private Button btnSelectAll = null;

	/**
	 * 清除所有
	 */
	private Button btnDelete = null;

	/**
	 * ListView列表
	 */
	private ListView lvListView = null;

	/**
	 * 适配对象
	 */
	private DemoAdapter adpAdapter = null;

	private FinalBitmap bm;
	private TextView tv_num;
	private UserShopCar shopcar;
	private Intent intent;
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
		// 要创建Final对象
		bm = FinalBitmap.create(getActivity());

		// 初始化视图
		initView(settingLayout);

		// 初始化控件
		initData(settingLayout);
		
		return settingLayout;
	}
	
	//单击支付按钮
	public  void inittotal() {
		myApplication my = (myApplication) getActivity().getApplication();
		if (my.getDemoadapter() != null&&my.getUser_address()!=null) {
			adpAdapter=my.getDemoadapter();
			int i=adpAdapter.getMoney();
//			Toast.makeText(getActivity(), "价钱："+i, Toast.LENGTH_SHORT).show();
			tv_num.setText("支付成功,已花费"+i+"元");
			Map<Integer, Boolean> map = adpAdapter.getCheckMap();

		// 获取当前的数据数量
		int count = adpAdapter.getCount();
		// 进行遍历
		for (int ii = 0; ii < count; ii++) {

			// 因为List的特性,删除了2个item,则3变成2,所以这里要进行这样的换算,才能拿到删除后真正的position
			int position = ii - (count - adpAdapter.getCount());
			
			if (map.get(ii) != null && map.get(ii)) {

				UserShopCar bean = (UserShopCar) adpAdapter
						.getItem(position);

				if (bean.isCanRemove()) {
				String username=my.getUser_name(),
						shopid=String.valueOf(adpAdapter.getDatas().get(position).getShopInfo().getF_i_Sid()),
						shop_name=adpAdapter.getDatas().get(position).getShopInfo().getF_c_Sname(),
						shop_num=String.valueOf(adpAdapter.getDatas().get(position).getF_i_Caddnum()),
						shop_price=String.valueOf(adpAdapter.getDatas().get(position).getShopInfo().getF_d_Ssprice()),
						peisong="圆通快递";
//			
				NetUtil.addUserIndent_car(this,username,shopid,shop_name,shop_num,shop_price,peisong);
//				Toast.makeText(getActivity(), ""+adpAdapter.getDatas().get(position).getF_i_Caddnum(), Toast.LENGTH_SHORT).show();
		}
			}
		}
		// 所有项目全部不选中
		adpAdapter.configCheckMap(false);

		adpAdapter.notifyDataSetChanged();

		
		btnSelectAll.setText("全选");
		}
		}

	/**
	 * 初始化控件
	 * 
	 * @param settingLayout
	 */
	private void initView(View settingLayout) {

		// btnCancle = (ViewGroup) settingLayout.findViewById(R.id.btnCancle);
		// btnCancle.setOnClickListener(this);
		//
		btnAdd = (ViewGroup) settingLayout.findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnDelete = (Button) settingLayout.findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);

		btnSelectAll = (Button) settingLayout.findViewById(R.id.btnSelectAll);
		btnSelectAll.setOnClickListener(this);

		lvListView = (ListView) settingLayout.findViewById(R.id.lvListView);
		lvListView.setOnItemClickListener(this);

		tv_num = (TextView) settingLayout.findViewById(R.id.tv_num);

	}

	/**
	 * 初始化视图
	 */
	private void initData(View settingLayout) {

		myApplication my = (myApplication) getActivity().getApplication();
		String username = my.getUser_name();
		ShopCarNet.selectShopcar(getActivity(), username, lvListView,
				adpAdapter, bm,my);

	}

	/**
	 * 按钮点击事件
	 */
	@Override
	public void onClick(View v) {

		/*
		 * 当点击删除的时候
		 */
		if (v == btnDelete) {
			
			myApplication my = (myApplication) getActivity().getApplication();
			if (my.getDemoadapter().getCount() != 0) {
				adpAdapter=my.getDemoadapter();
				/*
				 * 删除算法最复杂,拿到checkBox选择寄存map
				 */
				Map<Integer, Boolean> map = adpAdapter.getCheckMap();

				// 获取当前的数据数量
				int count = adpAdapter.getCount();
				// 进行遍历
				for (int i = 0; i < count; i++) {

					// 因为List的特性,删除了2个item,则3变成2,所以这里要进行这样的换算,才能拿到删除后真正的position
					int position = i - (count - adpAdapter.getCount());
					int n=i;
					if(n==count-1){
						tv_num.setText("");
						btnSelectAll.setText("全选");	
					}//如果商品被选中
					if (map.get(i) != null && map.get(i)) {

						UserShopCar bean = (UserShopCar) adpAdapter
								.getItem(position);

						if (bean.isCanRemove()) {
							
							int shopid=adpAdapter.getShopid().get(i);
							
							adpAdapter.getCheckMap().remove(i);
							adpAdapter.remove(position);
							
							String username=my.getUser_name();
//							Toast.makeText(getActivity(), ""+shopid, Toast.LENGTH_SHORT	).show();
							
							ShopCarNet.deleteShopcar(getActivity(),shopid,username);
						} else {
								map.put(position, false);
						}

					}
				}
				adpAdapter.notifyDataSetChanged();
//				ShopCarNet.deleteShopCar();
			}else{
				tv_num.setText("");
				btnSelectAll.setText("全选");
				Toast.makeText(getActivity(), "没有商品可供操作", Toast.LENGTH_SHORT).show();
			}
		}

		/*
		 * 当点击全选的时候
		 */
		if (v == btnSelectAll) {
			myApplication my = (myApplication) getActivity()
					.getApplication();
			if (my.getDemoadapter().getCount() == 0) {
				tv_num.setText("");
				btnSelectAll.setText("全选");
				Toast.makeText(getActivity(), "没有商品可供操作", Toast.LENGTH_SHORT).show();
			}else if (btnSelectAll.getText().toString().trim().equals("全选")) {

				if (my.getDemoadapter() != null) {
					adpAdapter = my.getDemoadapter();

					// 所有项目全部选中
					adpAdapter.configCheckMap(true);

					adpAdapter.notifyDataSetChanged();

					// 获取当前的数据数量
					int count = adpAdapter.getCount();
					int num = 0;
					int total = 0;
					// 进行遍历
					for (int i = 0; i < count; i++) {
						shopcar = (UserShopCar) adpAdapter.getItem(i);

						num = num + shopcar.getF_i_Caddnum();
						int nums = shopcar.getF_i_Caddnum();
						total = total
								+ (int) (nums * shopcar.getShopInfo()
										.getF_d_Ssprice());
						tv_num.setText("商品总价：" + total + "元");
						// Toast.makeText(getActivity(),
						// shopcar.getShopInfo().toString(),
						// Toast.LENGTH_SHORT).show();
					}
					btnSelectAll.setText("全不选");
				}
			} else {

				// 所有项目全部不选中
				adpAdapter.configCheckMap(false);

				adpAdapter.notifyDataSetChanged();

				tv_num.setText("");

				btnSelectAll.setText("全选");
			}

		}
		if (v == btnAdd) {
			myApplication my=(myApplication) getActivity().getApplication();
			adpAdapter=my.getDemoadapter();
			int i=adpAdapter.getMoney();
			if(my.getDemoadapter().getCount() == 0){
				tv_num.setText("");
				btnSelectAll.setText("全选");
				Toast.makeText(getActivity(), "没有商品可供操作", Toast.LENGTH_SHORT).show();
			}else if("null".equals(my.getUser_address())||"null".equals(my.getUser_phone())){
//				Toast.makeText(getActivity(),"a"+my.getUser_address()+my.getDemoadapter().toString(), Toast.LENGTH_SHORT).show();
				Intent in=new Intent();
				in.setClass(getActivity(), self_addressadministration.class);
				startActivity(in);
			}else if(i!=0){
				intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				new AlertDialog.Builder(getActivity())
				.setTitle("提示")
				.setIcon(R.drawable.logo_togo)
				.setMessage("是否要支付?\n收获地址："+my.getUser_address()+"\n手机号码:"+my.getUser_phone()
						+"\n本次共需支付"+i+"元。")
				.setPositiveButton("是", new android.content.DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialoginterface, int i)
				    	 {
					inittotal();
				    	 }
				})
				.setNegativeButton("否", new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
				}).show();
				
			}else if(i==0){
				tv_num.setText("");
				btnSelectAll.setText("全选");
				Toast.makeText(getActivity(), "没有商品可供操作", Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View itemLayout,
			final int position, long id) {

	}

}
