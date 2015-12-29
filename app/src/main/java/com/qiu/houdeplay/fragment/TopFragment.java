package com.qiu.houdeplay.fragment;

import android.view.View;
import android.widget.TextView;

public class TopFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("TopFragment");
		return view;
	}

	@Override
	protected LoadResult load() {
		return LoadResult.error;
	}
}
