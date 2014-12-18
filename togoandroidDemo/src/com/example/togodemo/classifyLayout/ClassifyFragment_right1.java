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

public class ClassifyFragment_right1 extends Fragment {
	
	private GridView gridView;
	private int[] image = {
	    R.drawable.cimageview11,
	    R.drawable.cimageview12,
	    R.drawable.cimageview13,
	    R.drawable.cimageview14,
	    R.drawable.cimageview15,
	    R.drawable.cimageview16,
	    R.drawable.cimageview17,
	    R.drawable.cimageview18,
	    R.drawable.cimageview19
	};
	private String[] imgText = {
	    "卫衣", "连衣裙", "短靴", "针织衫", "毛呢外套", "羽绒服", "高帮鞋", "单鞋","毛衣"
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.frg_classify_right1, container, false);
		
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
		  
		    gridView = (GridView)view.findViewById(R.id.gridViewRight1);
		    //取消默认的背景色
		    gridView.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		    gridView.setNumColumns(3);
		    gridView.setAdapter(adapter);
		    gridView.setOnItemClickListener(new OnItemClickListener(){
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		            Toast.makeText(getActivity(), "你按下 "+imgText[position],    
		                Toast.LENGTH_SHORT).show();   
		        }
		        
		    });
		return view;
	}


}
