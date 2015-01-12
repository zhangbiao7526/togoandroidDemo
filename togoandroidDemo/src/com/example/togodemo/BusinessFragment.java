package com.example.togodemo;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.business.Business_moreshop_click;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.ztest.BusinessNet;

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
import android.widget.Toast;

public class BusinessFragment extends Fragment implements OnClickListener {

	Button btn_businessF_more1 = null;
	Button btn_businessF_more2 = null;
	Button btn_businessF_more3 = null;
	
	private FinalBitmap fb;
	private TextView news_minprice_price_tv1, news_minprice_price_tv2,news_minprice_price_tv3, 
		             news_minprice_desc_tv1, news_minprice_desc_tv2, news_minprice_desc_tv3,
		             
		             news_free_price_tv1, news_free_price_tv2,news_free_price_tv3,
		             news_free_price_tv1_1 ,news_free_price_tv1_2, news_free_price_tv1_3,
		             
					 news_promotion_price_tv1,news_promotion_price_tv2,news_promotion_price_tv3,
					 news_promotion_desc_tv1,news_promotion_desc_tv2,news_promotion_desc_tv3;
		             
	// 创建TextView集合对象
	private List<TextView> tv_list = new ArrayList<TextView>();
	private List<TextView> tv_desclist = new ArrayList<TextView>();
	private ImageView news_minprice_pic1,news_minprice_pic2,news_minprice_pic3,
					  iv_free_price_tv1,iv_free_price_tv2,iv_free_price_tv3,
					  iv_news_promotion1,iv_news_promotion2,iv_news_promotion3;
					  
	private ImageView[] minprice,free_price,promotion;
	private myApplication my;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fb = FinalBitmap.create(getActivity());
		my=(myApplication) getActivity().getApplication();
	}
	// 活动视图
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.news_layout, container,
				false);
		//fb = FinalBitmap.create(getActivity());
		// 活动更多 按钮控件
		btn_businessF_more1 = (Button) newsLayout.findViewById(R.id.btn_more1);
		btn_businessF_more2 = (Button) newsLayout.findViewById(R.id.btn_more2);
		btn_businessF_more3 = (Button) newsLayout.findViewById(R.id.btn_more3);
		// 活动图片和文本的控件
		news_minprice_pic1 = (ImageView) newsLayout
				.findViewById(R.id.news_minprice_pic1);
		news_minprice_pic2 = (ImageView) newsLayout
				.findViewById(R.id.news_minprice_pic2);
		news_minprice_pic3 = (ImageView) newsLayout
				.findViewById(R.id.news_minprice_pic3);
		
		iv_free_price_tv1=(ImageView) newsLayout
				.findViewById(R.id.iv_free_price_tv1);
		iv_free_price_tv2=(ImageView) newsLayout
						.findViewById(R.id.iv_free_price_tv2);
		iv_free_price_tv3=(ImageView) newsLayout
						.findViewById(R.id.iv_free_price_tv3);
				
		iv_news_promotion1=(ImageView) newsLayout
						.findViewById(R.id.iv_news_promotion1);
		iv_news_promotion2=(ImageView) newsLayout
						.findViewById(R.id.iv_news_promotion2);
		iv_news_promotion3=(ImageView) newsLayout
						.findViewById(R.id.iv_news_promotion3);
		//第一排左边textview		
		news_minprice_price_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_price_tv1);
		news_minprice_price_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_price_tv2);
		news_minprice_price_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_price_tv3);
		//第一排右边textview
		news_minprice_desc_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_desc_tv1);
		news_minprice_desc_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_desc_tv2);
		news_minprice_desc_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_minprice_desc_tv3);
		
		//第二排左边textview		
		news_free_price_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_free_price_tv1);
		news_free_price_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_free_price_tv2);
		news_free_price_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_free_price_tv3);
		//第二排右边textview
		news_free_price_tv1_1 = (TextView) newsLayout
				.findViewById(R.id.news_free_desc_tv1);
		news_free_price_tv1_2 = (TextView) newsLayout
				.findViewById(R.id.news_free_desc_tv2);
		news_free_price_tv1_3 = (TextView) newsLayout
				.findViewById(R.id.news_free_desc_tv3);
		
		//第三排左边textview		
		news_promotion_price_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_promotion_price_tv1);
		news_promotion_price_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_promotion_price_tv2);
		news_promotion_price_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_promotion_price_tv3);
		//第三排右边textview
		news_promotion_desc_tv1 = (TextView) newsLayout
				.findViewById(R.id.news_promotion_desc_tv1);
		news_promotion_desc_tv2 = (TextView) newsLayout
				.findViewById(R.id.news_promotion_desc_tv2);
		news_promotion_desc_tv3 = (TextView) newsLayout
				.findViewById(R.id.news_promotion_desc_tv3);

		
		
		
		// 文本视图集合
		//左边第一个textview，第一排
		tv_list.add(news_minprice_price_tv1);
		tv_list.add(news_minprice_price_tv2);
		tv_list.add(news_minprice_price_tv3);
		
		tv_list.add(news_free_price_tv1);
		tv_list.add(news_free_price_tv2);
		tv_list.add(news_free_price_tv3);
		
		tv_list.add(news_promotion_price_tv1);
		tv_list.add(news_promotion_price_tv2);
		tv_list.add(news_promotion_price_tv3);
		

		tv_desclist.add(news_minprice_desc_tv1);
		tv_desclist.add(news_minprice_desc_tv2);
		tv_desclist.add(news_minprice_desc_tv3);
		
		tv_desclist.add(news_free_price_tv1_1);
		tv_desclist.add(news_free_price_tv1_2);
		tv_desclist.add(news_free_price_tv1_3);
		
		tv_desclist.add(news_promotion_desc_tv1);
		tv_desclist.add(news_promotion_desc_tv2);
		tv_desclist.add(news_promotion_desc_tv3);
		//更多 单击事件
		btn_businessF_more1.setOnClickListener(this);
		btn_businessF_more2.setOnClickListener(this);
		btn_businessF_more3.setOnClickListener(this);
		//图片的单击事件
		news_minprice_pic1.setOnClickListener(this);
		news_minprice_pic2.setOnClickListener(this);
		news_minprice_pic3.setOnClickListener(this);
	
		iv_free_price_tv1.setOnClickListener(this);
		iv_free_price_tv2.setOnClickListener(this);
		iv_free_price_tv3.setOnClickListener(this);
		
		iv_news_promotion1.setOnClickListener(this);
		iv_news_promotion2.setOnClickListener(this);
		iv_news_promotion3.setOnClickListener(this);
		
		
		minprice=new ImageView[] {news_minprice_pic1,news_minprice_pic2,news_minprice_pic3};
		free_price=new ImageView[]{iv_free_price_tv1,iv_free_price_tv2,iv_free_price_tv3};
		promotion=new ImageView[]{iv_news_promotion1,iv_news_promotion2,iv_news_promotion3};
		
		// 访问服务器调取活动要显示的文本信息
		BusinessNet.getDataFromSQL_zuidi(getActivity(), tv_list, tv_desclist, fb,minprice);
		BusinessNet.getDataFromSQL_baoyou(getActivity(), tv_list, tv_desclist, fb,free_price);
		BusinessNet.getDataFromSQL_cuxiao(getActivity(), tv_list, tv_desclist, fb,promotion);
		// 图片路径，并显示图片
//		String uri = VARIABLE.HOMEVIPAGER_URI6;
//		fb.display(news_minprice_pic1, uri);

		// news_minprice_tv1.setText(text);

		return newsLayout;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent();
		if (id == R.id.news_minprice_pic1) {
			//ShopInfo shop=my.getList_shopinfos().get(0).get("haoping");
			click_minprice(0);
			//Toast.makeText(getActivity(), shop.getF_c_Simagpath().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (id == R.id.news_minprice_pic2) {
			click_minprice(1);
		}
		else if (id == R.id.news_minprice_pic3) {
			click_minprice(2);
		}
		else if (id == R.id.iv_free_price_tv1) {
			click_minprice(3);
		}
		else if (id == R.id.iv_free_price_tv2) {
			click_minprice(4);
		}
		else if (id == R.id.iv_free_price_tv3) {
			click_minprice(5);
		}
		else if (id == R.id.iv_news_promotion1) {
			click_minprice(6);
		}
		else if (id == R.id.iv_news_promotion2) {
			click_minprice(7);
		}
		else if (id == R.id.iv_news_promotion3) {
			click_minprice(8);
		}
		
		else if (id == R.id.btn_more1) {
			intent.putExtra("BusinessF_zuidi", "BusinessF_zuidi");
			intent.setClass(getActivity(), Business_moreshop_click.class);
			startActivity(intent);
		}
		
		else if (id == R.id.btn_more2) {
			intent.putExtra("BusinessF_baoyou", "BusinessF_baoyou");
			intent.setClass(getActivity(), Business_moreshop_click.class);
			startActivity(intent);
		}
		
		else if (id == R.id.btn_more3) {
			intent.putExtra("BusinessF_cuxiao", "BusinessF_cuxiao");
			intent.setClass(getActivity(), Business_moreshop_click.class);
			startActivity(intent);
		}
		
	}

	private void click_minprice(int i) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		
		//如果全局缓存不为空
		if(my.getList_shopinfos()!=null&&i<3){
		ShopInfo shop;
		intent.putExtra("method", "home_buymore");
		intent.putExtra("activity", "活动： 最低价");
		Bundle bundle = new Bundle();
		shop=my.getList_shopinfos().get(i).get("haoping");
		bundle.putParcelable("buy_moreshop", shop);
		intent.setClass(this.getActivity(), OneSop_click.class);
		intent.putExtras(bundle);
		getActivity().startActivity(intent);
		
		}else if(my.getList_shopinfos_baoyou()!=null&&i>2&&i<6){
			ShopInfo shop;
			intent.putExtra("method", "home_buymore");
			intent.putExtra("activity", "活动：包邮");
			Bundle bundle = new Bundle();
			shop=my.getList_shopinfos_baoyou().get(i-3).get("baoyou");
//			Toast.makeText(getActivity(), shop.toString()+","+i, 1000).show();
			bundle.putParcelable("buy_moreshop", shop);
			intent.setClass(this.getActivity(), OneSop_click.class);
			intent.putExtras(bundle);
			getActivity().startActivity(intent);
			
		}else if(my.getList_shopinfos_cuxiao()!=null&&i>5&&i<9){
			ShopInfo shop;
			intent.putExtra("method", "home_buymore");
			intent.putExtra("activity", "活动：限时促销");
			Bundle bundle = new Bundle();
			shop=my.getList_shopinfos_cuxiao().get(i-6).get("cuxiao");
			bundle.putParcelable("buy_moreshop", shop);
			intent.setClass(this.getActivity(), OneSop_click.class);
			intent.putExtras(bundle);
			getActivity().startActivity(intent);
			
				}
		else if(my.getList_shopinfos()==null){
			Toast.makeText(getActivity(), "未联入后台", Toast.LENGTH_LONG).show();
			intent.setClass(this.getActivity(), OneSop_click.class);
			
		}
		
	}
}
