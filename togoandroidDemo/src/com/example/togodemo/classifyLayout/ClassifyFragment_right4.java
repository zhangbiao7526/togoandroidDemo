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

public class ClassifyFragment_right4 extends Fragment {

	private GridView gridView4;
	private int[] image = {
	    R.drawable.c_302,
	    R.drawable.c_303,
	    R.drawable.c_304,
	    R.drawable.c_305,
	    R.drawable.c_306,
	    R.drawable.c_307,
	    R.drawable.c_308,
	    R.drawable.c_309,
	    R.drawable.c_310
	};
	private String[] imgText = {
	    "餐桌", "鞋柜", "茶几", "椅子", "床垫", "电视柜", "沙发", "床","衣柜"
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.frg_classify_right4, container, false);
		
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
		  
		    gridView4 = (GridView)view.findViewById(R.id.gridViewRight4);
		    //取消默认的背景色
		   gridView4.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		    gridView4.setNumColumns(3);
		    gridView4.setAdapter(adapter);
		    gridView4.setOnItemClickListener(new OnItemClickListener(){
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		            Toast.makeText(getActivity(), "你按下 "+imgText[position],    
		                Toast.LENGTH_SHORT).show();   
		        }
		        
		    });
			return view;
		  }
};