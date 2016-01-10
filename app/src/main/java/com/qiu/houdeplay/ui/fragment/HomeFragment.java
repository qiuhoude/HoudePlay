package com.qiu.houdeplay.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.qiu.houdeplay.base.view.BaseFragmentList;
import com.qiu.houdeplay.base.view.BasePresenter;
import com.qiu.houdeplay.presenter.HomePresenter;

public class HomeFragment extends BaseFragmentList {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected View createSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setText("加载成功了....");
        tv.setTextSize(30);
        return tv;
    }


    @Override
    protected ListAdapter getAdapter() {
        return null;
    }


    @Override
    protected BasePresenter creatPresenter() {
        return new HomePresenter();
    }
}
