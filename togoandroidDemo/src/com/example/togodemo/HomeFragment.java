package com.example.togodemo;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;


import com.example.togodemo.R;
import com.example.togodemo.home.OneSop_click;
import com.example.togodemo.variable.VARIABLE;

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

public class HomeFragment extends Fragment implements OnClickListener{
	
	private ImageView iv_HOMEFragment_buymore1,iv_HOMEFragment_buymore2,iv_HOMEFragment_buymore3,
	  iv_HOMEFragment_good1,iv_HOMEFragment_good2,iv_HOMEFragment_good3;
	private TextView tv_HOMEFragment_buymore_click1,tv_HOMEFragment_buymore_click2;

	private int[] imgResIDs = new int[]
		    { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		    private int[] radioButtonID = new int[]
		    { R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4 };
		    private ViewPager pager;
		    private RadioGroup mGroup;
		    private ArrayList<View> items = new ArrayList<View>();
		    private int mCurrentItem = 0;
		    private int mItem;
		    private Runnable mPagerAction;
		    private boolean isFrist = true;	
	private FinalBitmap fm;	    
	private LayoutInflater in;
	private String uri1=VARIABLE.HOMEVIPAGER_URI;
	private String uri2=VARIABLE.HOMEVIPAGER_URI2;
	private String uri3=VARIABLE.HOMEVIPAGER_URI3;
	private String uri4=VARIABLE.HOMEVIPAGER_URI4;
	private String uri5=VARIABLE.HOMEVIPAGER_URI5;
	private String imgString[]=new String[]{uri1,uri2,uri3,uri4,uri5};//这个数组可以存在缓存中
	ListView list;
	final String data[]={"a","b","c","d","newactivity","自定义listview"};
	
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		    	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	fm=FinalBitmap.create(getActivity());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		in=getActivity().getLayoutInflater();
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);
		pager = (ViewPager) messageLayout.findViewById(R.id.tuijian_pager1);
        mGroup = (RadioGroup) messageLayout.findViewById(R.id.radioGroup11);
        
        iv_HOMEFragment_buymore1=(ImageView) messageLayout.findViewById(R.id.iv_HOMEFragment_buymore1);
        iv_HOMEFragment_buymore2=(ImageView) messageLayout.findViewById(R.id.iv_HOMEFragment_buymore2);
        iv_HOMEFragment_buymore3=(ImageView) messageLayout.findViewById(R.id.iv_HOMEFragment_buymore3);
        iv_HOMEFragment_good1=(ImageView) messageLayout.findViewById(R.id.iv_HOMEFragment_good1);
        iv_HOMEFragment_good2=(ImageView) messageLayout.findViewById(R.id.iv_HOMEFragment_good2);
        iv_HOMEFragment_good3=(ImageView) messageLayout.findViewById(R.id.iv_HOMEFragment_good3);
        
        tv_HOMEFragment_buymore_click1=(TextView) messageLayout.findViewById(R.id.tv_HOMEFragment_buymore_click1);
        tv_HOMEFragment_buymore_click2=(TextView) messageLayout.findViewById(R.id.tv_HOMEFragment_buymore_click2);

        iv_HOMEFragment_buymore1.setOnClickListener(this);
        iv_HOMEFragment_buymore2.setOnClickListener(this);
        iv_HOMEFragment_buymore3.setOnClickListener(this);
        iv_HOMEFragment_good1.setOnClickListener(this);
        iv_HOMEFragment_good2.setOnClickListener(this);
        iv_HOMEFragment_good3.setOnClickListener(this);
        
        tv_HOMEFragment_buymore_click1.setOnClickListener(this);
        tv_HOMEFragment_buymore_click2.setOnClickListener(this);
        //        list=(ListView) messageLayout.findViewById(R.id.android_list);
//       
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1 ,data);
//		list.setAdapter(adapter);
		
        
        initAllItems();	
        
        pager.setAdapter(new PagerAdapter()
        {
            // 创建
            @Override
            public Object instantiateItem(View container, int position)
            {
                View layout = items.get(position % items.size());
                System.out.println("items.size:"+ position % items.size()+"items.size():"+items.size());
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
            	System.out.println("imgResIDs.length:"+imgResIDs.length);
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
	                items.get(arg0).findViewById(R.id.tuijian_header_img).setOnClickListener(new OnClickListener()
	                {
	                    @Override
	                    public void onClick(View v)
	                    {	//在这获取用户单击了哪个图片，然后将这个图片的名字传递到另外一个activity，在那个activity进行线程处理
	                        Toast.makeText(getActivity(), arg0 + "img="+imgString[arg0], 1000).show();
	                        Intent  in=new Intent();
	                        in.putExtra("img_name", imgString[arg0]);
	                        System.out.println(imgString[arg0]);
	                        in.setClass(getActivity(), OneSop_click.class);
	                        getActivity().startActivity(in);
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
        mPagerAction = new Runnable()
        {
            @Override
            public void run()
            {
               
                if (mItem != 0)
                {
                    if (isFrist == true)
                    {
                        mCurrentItem = 0;
                        isFrist = false;
                    }
                    else
                    {
                        if (mCurrentItem == items.size() - 1)
                        {
                            mCurrentItem = 0;
                        }
                        else
                        {
                            mCurrentItem++;
                        }
                    }
                    
                    pager.setCurrentItem(mCurrentItem);
                    mGroup.check(radioButtonID[mCurrentItem]);
                    
                }
                pager.postDelayed(mPagerAction, 2500);
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
		int id=v.getId();
		switch (id) {
		case R.id.iv_HOMEFragment_buymore1:
			Intent in=new Intent();
			in.setClass(getActivity(),OneSop_click.class);
			getActivity().startActivity(in);
			break;

		default:
			break;
		}
	}
	
	private void initAllItems()
	    {
	        // 初始化Viewpager的所有item
	        for (int i = 0; i < imgResIDs.length; i++)
	        {
	            items.add(initPagerItem(imgResIDs[i],imgString[i]));
	        }
	        mItem = items.size();
	    }
	    
	    private View initPagerItem(int resID, String imgString)
	    {
	        View layout1 = in.inflate(R.layout.homepage_viewpage, null);
	        ImageView imageView1 = (ImageView) layout1.findViewById(R.id.tuijian_header_img);
	       // imageView1.setImageResource(resID);
	        fm.display(imageView1, imgString);
	        return layout1;
	    }
		
}
