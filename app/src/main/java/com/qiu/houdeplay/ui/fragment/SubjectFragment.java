package com.qiu.houdeplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.qiu.houdeplay.base.BaseFragment;

public class SubjectFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("SubjectFragment");
		return view;}


}
