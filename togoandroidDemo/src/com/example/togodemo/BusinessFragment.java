package com.example.togodemo;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.business.Activity_Free;
import com.example.togodemo.business.Activity_Minprice;
import com.example.togodemo.business.Activity_SalesPromotion;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessFragment extends Fragment implements OnClickListener {

	Button btn_businessF_more1 = null;
	Button btn_businessF_more2 = null;
	Button btn_businessF_more3 = null;
	private FinalBitmap fb;
	private TextView news_minprice_price_tv1, news_minprice_price_tv2,
			news_minprice_price_tv3, news_minprice_desc_tv1,
			news_minprice_desc_tv2, news_minprice_desc_tv3;
	// 创建TextView集合对象
	private List<TextView> tv_list = new ArrayList<TextView>();
	private List<TextView> tv_desclist = new ArrayList<TextView>();
	private ImageView news_minprice_pic1;

	// 活动视图
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.news_layout, container,
				false);
		fb = FinalBitmap.create(getActivity());
		// 活动更多 按钮控件
		btn_businessF_more1 = (Button) newsLayout.findViewById(R.id.btn_more1);
		btn_businessF_more2 = (Button) newsLayout.findViewById(R.id.btn_more2);
		btn_businessF_more3 = (Button) newsLayout.findViewById(R.id.btn_more3);
		// 活动图片和文本的控件
		news_minprice_pic1 = (ImageView) newsLayout
				.findViewById(R.id.news_minprice_pic1);
		news_minprice_price_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_price_tv1);
		news_minprice_price_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_price_tv2);
		news_minprice_price_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_price_tv3);

		news_minprice_desc_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_desc_tv1);
		news_minprice_desc_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_desc_tv2);
		news_minprice_desc_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_desc_tv3);
		// 文本视图集合
		tv_list.add(news_minprice_price_tv1);
		tv_list.add(news_minprice_price_tv2);
		tv_list.add(news_minprice_price_tv3);

		tv_desclist.add(news_minprice_desc_tv1);
		tv_desclist.add(news_minprice_desc_tv2);
		tv_desclist.add(news_minprice_desc_tv3);
		// 单击事件
		btn_businessF_more1.setOnClickListener(this);
		btn_businessF_more2.setOnClickListener(this);
		btn_businessF_more3.setOnClickListener(this);
		news_minprice_pic1.setOnClickListener(this);
		// 访问服务器调取活动要显示的文本信息
		BusinessNet.getDataFromSQL(this, tv_list, tv_desclist);
		// 图片路径，并显示图片
		String uri = VARIABLE.HOMEVIPAGER_URI6;
		fb.display(news_minprice_pic1, uri);

		// news_minprice_tv1.setText(text);

		return newsLayout;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent();
		if (id == R.id.btn_more1) {
			intent.setClass(getActivity(), Activity_Minprice.class);
			getActivity().startActivity(intent);
		} else if (id == R.id.btn_more2) {
			intent.setClass(getActivity(), Activity_Free.class);
			startActivity(intent);
		} else if (id == R.id.btn_more3) {
			intent.setClass(getActivity(), Activity_SalesPromotion.class);
			startActivity(intent);
		} else if (id == R.id.news_minprice_pic1) {
			myApplication ma = (myApplication) this.getActivity()
					.getApplication();
			List<ShopInfo> list_shopinfos = ma.getList_shopinfos();
			for (int i = 0; i < 2; i++) {
				ShopInfo shopinfo = list_shopinfos.get(i);
			}
			ShopInfo shopinfo1 = list_shopinfos.get(0);
			Bundle bundle = new Bundle();
			bundle.putParcelable("parcel", shopinfo1);
			intent.setClass(this.getActivity(), Activity_Minprice.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}

	}

}
