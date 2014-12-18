package com.example.togodemo;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyAdapter extends PagerAdapter{
	private int[] imgResIDs;
	private ViewPager pager;
	private ArrayList<View> items;
	private View messageLayout;
	 public MyAdapter(View messageLayout, int[] imgResIDs, ViewPager pager,
			ArrayList<View> items) {
		this.imgResIDs=imgResIDs;
		this.messageLayout=messageLayout;
		this.pager=pager;
		this.items=items;
	}

// 创建
  @Override
  public Object instantiateItem(View container, int position)
  {
      View layout = items.get(position % items.size());
      pager.addView(layout);
      return layout;
  }

  // 销毁
  @Override
  public void destroyItem(View container, int position, Object object)
  {
      View layout = items.get(position % items.size());
      pager.removeView(layout);
  }
  
  @Override
  public boolean isViewFromObject(View arg0, Object arg1)
  {
      return arg0 == arg1;
      
  }
  
  @Override
  public int getCount()
  {
      return imgResIDs.length;
  }

}
