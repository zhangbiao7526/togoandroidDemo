package com.example.togodemo;

import com.example.togodemo.classifyLayout.ClassifyFragment_right1;
import com.example.togodemo.classifyLayout.ClassifyFragment_right2;
import com.example.togodemo.classifyLayout.ClassifyFragment_right3;
import com.example.togodemo.classifyLayout.ClassifyFragment_right4;
import com.example.togodemo.classifyLayout.ClassifyFragment_right5;
import com.example.togodemo.ztest.SearchNet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ClassifyFragment extends Fragment implements OnClickListener {

	private Button btn_classify_1 = null, btn_classify_2 = null,
			btn_classify_3 = null, btn_classify_4 = null,
			btn_classify_5 = null;
	private ClassifyFragment_right1 fg_classify_right1;
	private ClassifyFragment_right2 fg_classify_right2;
	private ClassifyFragment_right3 fg_classify_right3;
	private ClassifyFragment_right4 fg_classify_right4;
	private ClassifyFragment_right5 fg_classify_right5;
	private FragmentManager fm;
	private EditText et_search;
	private Button btn_search;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//开始事物
		fm = getChildFragmentManager();
		setTabSelection(0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contactsLayout = inflater.inflate(R.layout.contacts_layout,
				container, false);

		btn_classify_1 = (Button) contactsLayout
				.findViewById(R.id.btn_classify_left_linear1);
		btn_classify_2 = (Button) contactsLayout
				.findViewById(R.id.btn_classify_left_linear2);
		btn_classify_3 = (Button) contactsLayout
				.findViewById(R.id.btn_classify_left_linear3);
		btn_classify_4 = (Button) contactsLayout
				.findViewById(R.id.btn_classify_left_linear4);
		btn_classify_5 = (Button) contactsLayout
				.findViewById(R.id.btn_classify_left_linear5);
		et_search=(EditText)contactsLayout.findViewById(R.id.et_search);
		btn_search=(Button)contactsLayout.findViewById(R.id.btn_search);

		btn_classify_1.setOnClickListener(this);
		btn_classify_2.setOnClickListener(this);
		btn_classify_3.setOnClickListener(this);
		btn_classify_4.setOnClickListener(this);
		btn_classify_5.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		
		btn_clear();
		btn_classify_1.setTextColor(Color.BLACK);
		
		return contactsLayout;
	}

	@Override
	public void onClick(View v) {
		// 事务管理
		FragmentTransaction tran = fm.beginTransaction();
		int id = v.getId();
		btn_clear();
		switch (id) {
		case R.id.btn_classify_left_linear1:
			// 鍥犱负褰撳墠鐨刟ctivity涓嶆槸鐪熸鐨刟ctivity锛屾墍浠ヤ笉鑳界敤甯歌鐨勭晫闈㈣烦杞紝
			// 瑕佺敤setClass鏉ヨ烦杞紝骞朵笖褰撳墠绫讳篃涓嶈兘鐢╰his鏉ヨ〃绀猴紝瑕佺敤getActivity锛堬級
			// 鏈€鍚庤getActivity().startActivity(i);鎵ц璺宠浆
			// Intent i=new Intent();
			// i.setClass(getActivity(), aaa.class);
			// getActivity().startActivity(i);
			
			btn_classify_1.setTextColor(Color.BLACK);
			fg_classify_right1 = new ClassifyFragment_right1();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right1);
			break;
		case R.id.btn_classify_left_linear2:
			
			btn_classify_2.setTextColor(Color.BLACK);
			fg_classify_right2 = new ClassifyFragment_right2();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right2);
			break;
		case R.id.btn_classify_left_linear3:
			
			btn_classify_3.setTextColor(Color.BLACK);
			fg_classify_right3 = new ClassifyFragment_right3();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right3);
			break;
		case R.id.btn_classify_left_linear4:
			
			btn_classify_4.setTextColor(Color.BLACK);
			fg_classify_right4 = new ClassifyFragment_right4();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right4);
			break;
		case R.id.btn_classify_left_linear5:
			
			btn_classify_5.setTextColor(Color.BLACK);
			fg_classify_right5 = new ClassifyFragment_right5();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right5);
			break;
			//1226
		case R.id.btn_search:
			String edittext=et_search.getText().toString();
			
//			Toast.makeText(getActivity(), edittext, Toast.LENGTH_LONG).show();
			SearchNet.getDataBySearch(getActivity(),edittext);
			break;
		}
		tran.commit();
	}


	private void btn_clear() {
		btn_classify_1.setTextColor(Color.parseColor("#9E9EA7"));
		btn_classify_2.setTextColor(Color.parseColor("#9E9EA7"));
		btn_classify_3.setTextColor(Color.parseColor("#9E9EA7"));
		btn_classify_4.setTextColor(Color.parseColor("#9E9EA7"));
		btn_classify_5.setTextColor(Color.parseColor("#9E9EA7"));
		
	}

	private void setTabSelection(int i) {
		FragmentTransaction tran = fm.beginTransaction();
		
		switch (i) {
		case 0:
			fg_classify_right1 = new ClassifyFragment_right1();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right1);
			break;

		default:
			break;
		}
		tran.commit();
	}
}
