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

public class ClassifyFragment_right2 extends Fragment {

	private GridView gridView2;
	private int[] image = {
	    R.drawable.c_102,
	    R.drawable.c_103,
	    R.drawable.c_104,
	    R.drawable.c_105,
	    R.drawable.c_106,
	    R.drawable.c_107,
	    R.drawable.c_108,
	    R.drawable.c_109,
	    R.drawable.c_110
	};
	private String[] imgText = {
	    "棉拖鞋", "消毒液", "口罩", "电热毯", "洗手液", "热水袋", "电暖垫", "保温杯","暖宝宝"
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.frg_classify_right2, container, false);
		
		
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
		  
		    gridView2 = (GridView)view.findViewById(R.id.gridViewRight2);
		    //取消默认的背景色
		   gridView2.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		    gridView2.setNumColumns(3);
		    gridView2.setAdapter(adapter);
		    gridView2.setOnItemClickListener(new OnItemClickListener(){
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		            Toast.makeText(getActivity(), "你按下 "+imgText[position],    
		                Toast.LENGTH_SHORT).show();   
		        }
		        
		    });
			return view;
		  }
};