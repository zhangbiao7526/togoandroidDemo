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

public class ClassifyFragment_right5 extends Fragment {

	private GridView gridView5;
	private int[] image = {
	    R.drawable.c_402,
	    R.drawable.c_403,
	    R.drawable.c_404,
	    R.drawable.c_405,
	    R.drawable.c_406,
	    R.drawable.c_407,
	    R.drawable.c_408,
	    R.drawable.c_409,
	    R.drawable.c_410
	};
	private String[] imgText = {
	    "糖果", "饼干", "巧克力", "肉干肉脯", "方便面", "葡萄干", "糕点", "枣类","坚果"
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.frg_classify_right5, container, false);
		
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
		  
		    gridView5 = (GridView)view.findViewById(R.id.gridViewRight5);
		    //取消默认的背景色
		   gridView5.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		    gridView5.setNumColumns(3);
		    gridView5.setAdapter(adapter);
		    gridView5.setOnItemClickListener(new OnItemClickListener(){
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		            Toast.makeText(getActivity(), "你按下 "+imgText[position],    
		                Toast.LENGTH_SHORT).show();   
		        }
		        
		    });
			return view;
		  }
};