package com.qiu.houdeplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.qiu.houdeplay.base.BaseFragment;
import com.qiu.houdeplay.base.BasePresenter;

public class AppFragment extends BaseFragment {

    @Override
    protected View createSuccessView() {
        TextView view = new TextView(getActivity());
        view.setText("AppFragment");
        return view;
    }


    @Override
    protected BasePresenter creatPresenter() {
        return null;
    }
}
