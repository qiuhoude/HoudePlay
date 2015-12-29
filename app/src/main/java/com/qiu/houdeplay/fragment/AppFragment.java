package com.qiu.houdeplay.fragment;

import android.view.View;
import android.widget.TextView;

public class AppFragment extends BaseFragment {

    @Override
    protected View createSuccessView() {
        TextView view = new TextView(getActivity());
        view.setText("AppFragment");
        return view;
    }

    @Override
    protected BaseFragment.LoadResult load() {
        return BaseFragment.LoadResult.error;
    }

}
