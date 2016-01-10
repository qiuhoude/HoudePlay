package com.qiu.houdeplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.qiu.houdeplay.base.view.BaseFragment;
import com.qiu.houdeplay.base.presenter.Presenter;

public class SubjectFragment extends BaseFragment {

	@Override
	protected View createSuccessView() {
		TextView view=new TextView(getActivity());
		view.setText("SubjectFragment");
		return view;}


	@Override
	protected Presenter creatPresenter() {
		return null;
	}
}
