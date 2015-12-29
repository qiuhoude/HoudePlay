package com.qiu.houdeplay.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeFragment extends BaseFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    protected View createSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setText("加载成功了....");
        tv.setTextSize(30);
        return tv;
    }

    protected LoadResult load() {
        return LoadResult.success;
    }

}
