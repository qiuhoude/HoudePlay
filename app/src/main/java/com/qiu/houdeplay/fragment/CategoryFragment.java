package com.qiu.houdeplay.fragment;

import android.view.View;
import android.widget.TextView;

public class CategoryFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("CategoryFragment");
		return view;
	}

	@Override
	protected LoadResult load() {
		return LoadResult.error;
	}
}
