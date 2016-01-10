package com.qiu.houdeplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.qiu.houdeplay.base.view.BaseFragment;
import com.qiu.houdeplay.base.view.BasePresenter;

public class GameFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("GameFragment");
		return view;
	}


	@Override
	protected BasePresenter creatPresenter() {
		return null;
	}
}
