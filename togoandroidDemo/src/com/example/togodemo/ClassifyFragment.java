package com.example.togodemo;

import com.example.togodemo.classifyLayout.ClassifyFragment_right1;
import com.example.togodemo.classifyLayout.ClassifyFragment_right2;
import com.example.togodemo.classifyLayout.ClassifyFragment_right3;
import com.example.togodemo.classifyLayout.ClassifyFragment_right4;
import com.example.togodemo.classifyLayout.ClassifyFragment_right5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 获得Fragment管理器
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

		btn_classify_1.setOnClickListener(this);
		btn_classify_2.setOnClickListener(this);
		btn_classify_3.setOnClickListener(this);
		btn_classify_4.setOnClickListener(this);
		btn_classify_5.setOnClickListener(this);
		return contactsLayout;
	}

	@Override
	public void onClick(View v) {
		// 开始事物
		FragmentTransaction tran = fm.beginTransaction();
		int id = v.getId();
		switch (id) {
		case R.id.btn_classify_left_linear1:
			// 因为当前的activity不是真正的activity，所以不能用常规的界面跳转，
			// 要用setClass来跳转，并且当前类也不能用this来表示，要用getActivity（）
			// 最后要getActivity().startActivity(i);执行跳转
			// Intent i=new Intent();
			// i.setClass(getActivity(), aaa.class);
			// getActivity().startActivity(i);
			fg_classify_right1 = new ClassifyFragment_right1();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right1);

			break;
		case R.id.btn_classify_left_linear2:
			fg_classify_right2 = new ClassifyFragment_right2();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right2);
			break;
		case R.id.btn_classify_left_linear3:
			fg_classify_right3 = new ClassifyFragment_right3();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right3);
			break;
		case R.id.btn_classify_left_linear4:
			fg_classify_right4 = new ClassifyFragment_right4();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right4);
			break;
		case R.id.btn_classify_left_linear5:
			fg_classify_right5 = new ClassifyFragment_right5();
			tran.replace(R.id.fg_classify_right_fragment, fg_classify_right5);
			break;
		}
		tran.commit();
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
