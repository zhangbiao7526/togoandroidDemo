package com.example.togodemo;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.example.togodemo.home.Home_moreshop_click;
import com.example.togodemo.mode.ShopInfo;
import com.example.togodemo.variable.VARIABLE;
import com.example.togodemo.ztest.HomeNet;
import com.example.togodemo.ztest.shop_NetUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements OnClickListener {

	private ImageView iv_HOMEFragment_buymore1, iv_HOMEFragment_buymore2,
			iv_HOMEFragment_buymore3, iv_HOMEFragment_good1,
			iv_HOMEFragment_good2, iv_HOMEFragment_good3;
	
	private TextView tv_HOMEFragment_buymore_click1,
			tv_HOMEFragment_buymore_click2;

	private int[] imgResIDs = new int[] { R.drawable.a, R.drawable.b,
			R.drawable.c, R.drawable.d, R.drawable.e };
	private int[] radioButtonID = new int[] { R.id.radio0, R.id.radio1,
			R.id.radio2, R.id.radio3, R.id.radio4 };
	private ViewPager pager;
	private RadioGroup mGroup;
	private ArrayList<View> items = new ArrayList<View>();
	private int mCurrentItem = 0;
	private int mItem;
	private Runnable mPagerAction;
	private boolean isFrist = true;
	private FinalBitmap fm;
	private LayoutInflater in;
	private String uri1 = VARIABLE.HOMEVIPAGER_URI;
	private String uri2 = VARIABLE.HOMEVIPAGER_URI2;
	private String uri3 = VARIABLE.HOMEVIPAGER_URI3;
	private String uri4 = VARIABLE.HOMEVIPAGER_URI4;
	private String uri5 = VARIABLE.HOMEVIPAGER_URI5;
	private String imgString[] = new String[] { uri1, uri2, uri3, uri4, uri5 };// 这个数组可以存在缓存中
	ListView list;
	final String data[] = { "a", "b", "c", "d", "newactivity", "自定义listview" };

	private ImageView[] buy_moreshop, good_moreshop;
	private ArrayList<ShopInfo> list_buymore,list_goodmore;
	private myApplication my;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fm = FinalBitmap.create(getActivity());
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		in = getActivity().getLayoutInflater();
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);
		pager = (ViewPager) messageLayout.findViewById(R.id.tuijian_pager1);
		mGroup = (RadioGroup) messageLayout.findViewById(R.id.radioGroup11);

		iv_HOMEFragment_buymore1 = (ImageView) messageLayout
				.findViewById(R.id.iv_HOMEFragment_buymore1);
		iv_HOMEFragment_buymore2 = (ImageView) messageLayout
				.findViewById(R.id.iv_HOMEFragment_buymore2);
		iv_HOMEFragment_buymore3 = (ImageView) messageLayout
				.findViewById(R.id.iv_HOMEFragment_buymore3);
		iv_HOMEFragment_good1 = (ImageView) messageLayout
				.findViewById(R.id.iv_HOMEFragment_good1);
		iv_HOMEFragment_good2 = (ImageView) messageLayout
				.findViewById(R.id.iv_HOMEFragment_good2);
		iv_HOMEFragment_good3 = (ImageView) messageLayout
				.findViewById(R.id.iv_HOMEFragment_good3);

		tv_HOMEFragment_buymore_click1 = (TextView) messageLayout
				.findViewById(R.id.tv_HOMEFragment_buymore_click1);
		tv_HOMEFragment_buymore_click2 = (TextView) messageLayout
				.findViewById(R.id.tv_HOMEFragment_buymore_click2);

		iv_HOMEFragment_buymore1.setOnClickListener(this);
		iv_HOMEFragment_buymore2.setOnClickListener(this);
		iv_HOMEFragment_buymore3.setOnClickListener(this);
		iv_HOMEFragment_good1.setOnClickListener(this);
		iv_HOMEFragment_good2.setOnClickListener(this);
		iv_HOMEFragment_good3.setOnClickListener(this);

		tv_HOMEFragment_buymore_click1.setOnClickListener(this);
		tv_HOMEFragment_buymore_click2.setOnClickListener(this);

		my = (myApplication) getActivity().getApplication();

		// 销量最多数组
		buy_moreshop =new ImageView[] { iv_HOMEFragment_buymore1,
				iv_HOMEFragment_buymore2, iv_HOMEFragment_buymore3 };
		good_moreshop =new ImageView[] { iv_HOMEFragment_good1,
				iv_HOMEFragment_good2, iv_HOMEFragment_good3 };
		// 销量最多集合：
		list_buymore = (ArrayList<ShopInfo>) my.getList_buymoreshop();
		//一进首页就去后台读取数据
		if (my.list_buymoreshop == null) {
			// 首页的图片后台处理,将所有字段读取出来，当某个页面需要用到时，在listview在进行区分，用fina框架进行读取图片
			HomeNet.Buy_Moreshop(getActivity(), buy_moreshop,fm);
			System.out.println("全局缓存没有值");
		} else {
			for (int i = 0; i < 3; i++) {
				fm.display(buy_moreshop[i], VARIABLE.IMAGE_URL+my.getList_buymoreshop().get(i).getF_c_Simagpath());
			}
			System.out.println("已有值");
			for (ShopInfo s : my.getList_buymoreshop()) {
				System.out.println(s.getF_c_Simagpath() + ","
						+ s.getF_c_Sname());
			}}
		//好评最多集合：
		list_goodmore=(ArrayList<ShopInfo>) my.getList_goodshop();
		if (my.list_goodshop == null) {
			// 首页的图片后台处理,将所有字段读取出来，当某个页面需要用到时，在listview在进行区分，用fina框架进行读取图片
			HomeNet.Good_Moreshop(getActivity(), good_moreshop,fm);
			System.out.println("全局缓存没有值");  
		} else {
			for (int i = 0; i < 3; i++) {
				fm.display(good_moreshop[i], VARIABLE.IMAGE_URL+my.getList_goodshop().get(i).getF_c_Simagpath());
			}
			System.out.println("已有值");
			for (ShopInfo s : my.getList_goodshop()) {
				System.out.println(s.getF_c_Simagpath() + ","
						+ s.getF_c_Sname());
			}}

		initAllItems();

		pager.setAdapter(new PagerAdapter() {
			// 创建
			@Override
			public Object instantiateItem(View container, int position) {
				View layout = items.get(position % items.size());
//				System.out.println("items.size:" + position % items.size()
//						+ "items.size():" + items.size());
				pager.addView(layout);
				return layout;
			}

			// 销毁
			@Override
			public void destroyItem(View container, int position, Object object) {
				View layout = items.get(position % items.size());
				pager.removeView(layout);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;

			}

			@Override
			public int getCount() {
//				System.out.println("imgResIDs.length:" + imgResIDs.length);
				return imgResIDs.length;
			}

		});
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(final int arg0) {
				// TODO Auto-generated method stub
				mCurrentItem = arg0 % items.size();
				pager.setCurrentItem(mCurrentItem);
				mGroup.check(radioButtonID[mCurrentItem]);
				items.get(arg0).findViewById(R.id.tuijian_header_img)
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) { // 在这获取用户单击了哪个图片，然后将这个图片的名字传递到另外一个activity，在那个activity进行线程处理
//								Toast.makeText(getActivity(),
//										arg0 + "img=" + imgString[arg0], 1000)
//										.show();
//								Intent in = new Intent();
//								in.putExtra("img_name", imgString[arg0]);
//								in.putExtra("method", "home_viewpg");
//								System.out.println(imgString[arg0]);
//								in.setClass(getActivity(), OneSop_click.class);
//								getActivity().startActivity(in);
//								Toast.makeText(getActivity(), imgString[arg0], duration)
								shop_NetUtil.getHome_ViewPage(getActivity(),imgString[arg0],  fm);	
							}
						});
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
		mPagerAction = new Runnable() {
			@Override
			public void run() {

				if (mItem != 0) {
					if (isFrist == true) {
						mCurrentItem = 0;
						isFrist = false;
					} else {
						if (mCurrentItem == items.size() - 1) {
							mCurrentItem = 0;
						} else {
							mCurrentItem++;
						}
					}

					pager.setCurrentItem(mCurrentItem);
					mGroup.check(radioButtonID[mCurrentItem]);

				}
				pager.postDelayed(mPagerAction, 5000);
			}

		};
		pager.postDelayed(mPagerAction, 100);
		return messageLayout;
	}

	/**
	 * 在这将单击的图片的名字传递出去，可以利用myApplication来传递
	 */
	@Override
	public void onClick(View v) {
		Intent in=new Intent();
		int id = v.getId();
		switch (id) {
		case R.id.iv_HOMEFragment_buymore1:
			click_more(0);
			break;
		case R.id.iv_HOMEFragment_buymore2:
			click_more(1);
			break;
		case R.id.iv_HOMEFragment_buymore3:
			click_more(2);
			break;
		case R.id.iv_HOMEFragment_good1:
			click_good(0);
			break;
		case R.id.iv_HOMEFragment_good2:
			click_good(1);
			break;
		case R.id.iv_HOMEFragment_good3:
			click_good(2);
			break;
		case R.id.tv_HOMEFragment_buymore_click1:
			in.putExtra("tv_HOMEFragment_buymore_click1", "buymore_shop");
			in.setClass(getActivity(), Home_moreshop_click.class);
			startActivity(in);
			break;
		case R.id.tv_HOMEFragment_buymore_click2:
			in.putExtra("tv_HOMEFragment_buymore_click2", "goodmore_shop");
			in.setClass(getActivity(), Home_moreshop_click.class);
			startActivity(in);
		default:
			break;
		}
	}
	/**
	 * 首页的6个图片单击事件,共用一个handle
	 * @param i
	 */
	private void click_more(int i){
		Intent intent=new Intent();
		ShopInfo shop;
		//如果全局缓存不为空
		if(my.getList_buymoreshop()!=null){
		intent.putExtra("method", "home_buymore");
		Bundle bundle = new Bundle();
		shop=my.getList_buymoreshop().get(i);
		bundle.putParcelable("buy_moreshop", shop);
		intent.setClass(this.getActivity(), OneSop_click.class);
		intent.putExtras(bundle);
		getActivity().startActivity(intent);
		}else{
			Toast.makeText(getActivity(), "未联入后台", Toast.LENGTH_LONG).show();
			intent.setClass(this.getActivity(), OneSop_click.class);
			getActivity().startActivity(intent);
		}
	}
	public void click_good(int i){
		Intent intent=new Intent();
		ShopInfo shop;
		if(my.getList_goodshop()!=null){
			intent.putExtra("method", "home_buymore");	
			Bundle bundle = new Bundle();	
			shop=my.getList_goodshop().get(i);
//			Toast.makeText(getActivity(), shop.toString(), 1000).show();
			bundle.putParcelable("buy_moreshop", shop);
			intent.setClass(this.getActivity(), OneSop_click.class);
			intent.putExtras(bundle);
			getActivity().startActivity(intent);
		}else{
			Toast.makeText(getActivity(), "未联入后台", Toast.LENGTH_LONG).show();
			intent.setClass(this.getActivity(), OneSop_click.class);
			getActivity().startActivity(intent);
		}
	}
	private void initAllItems() {
		// 初始化Viewpager的所有item
		for (int i = 0; i < imgResIDs.length; i++) {
			items.add(initPagerItem(imgResIDs[i], imgString[i]));
		}
		mItem = items.size();
	}

	private View initPagerItem(int resID, String imgString) {
		View layout1 = in.inflate(R.layout.homepage_viewpage, null);
		ImageView imageView1 = (ImageView) layout1
				.findViewById(R.id.tuijian_header_img);
		// imageView1.setImageResource(resID);
		fm.display(imageView1, imgString);
		return layout1;
	}

}
