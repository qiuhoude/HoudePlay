package com.qiu.houdeplay.fragment;

import android.view.View;
import android.widget.TextView;

public class SubjectFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("SubjectFragment");
		return view;}

	@Override
	protected LoadResult load() {
		return LoadResult.error;
	}
}
