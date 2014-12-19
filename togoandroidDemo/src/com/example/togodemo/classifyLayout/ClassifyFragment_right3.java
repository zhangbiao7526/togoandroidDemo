package com.example.togodemo.classifyLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.togodemo.R;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ClassifyFragment_right3 extends Fragment {

	private GridView gridView3;
	private int[] image = {
	    R.drawable.c_202,
	    R.drawable.c_203,
	    R.drawable.c_204,
	    R.drawable.c_205,
	    R.drawable.c_206,
	    R.drawable.c_207,
	    R.drawable.c_208,
	    R.drawable.c_209,
	    R.drawable.c_210
	};
	private String[] imgText = {
	    "整机DIY", "苹果手机", "无线路由", "MP3", "办公设备", "三星手机", "苹果平板", "笔记本电脑","小米手机"
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.frg_classify_right3, container, false);
		
		  List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
		    for (int i = 0; i < image.length; i++) {
		        Map<String, Object> item = new HashMap<String, Object>();
		        item.put("image", image[i]);
		        item.put("text", imgText[i]);
		        items.add(item);
		    }
		    SimpleAdapter adapter = new SimpleAdapter(getActivity(), 
		        items, R.layout.gridview_item, new String[]{"image", "text"},
		        new int[]{R.id.gridmageViewitem, R.id.gridtextviewitem});
		  
		    gridView3 = (GridView)view.findViewById(R.id.gridViewRight3);
		    //取消默认的背景色
		   gridView3.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		    gridView3.setNumColumns(3);
		    gridView3.setAdapter(adapter);
		    gridView3.setOnItemClickListener(new OnItemClickListener(){
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		            Toast.makeText(getActivity(), "你按下 "+imgText[position],    
		                Toast.LENGTH_SHORT).show();   
		        }
		        
		    });
			return view;
		  }
};