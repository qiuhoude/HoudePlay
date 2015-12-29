package com.qiu.houdeplay.fragment;

import android.view.View;
import android.widget.TextView;

public class GameFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("GameFragment");
		return view;
	}

	@Override
	protected LoadResult load() {
		return LoadResult.error;
	}
}
