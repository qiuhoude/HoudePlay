package com.qiu.houdeplay.base.view;

import android.view.View;
import android.widget.ListAdapter;

import com.qiu.houdeplay.ui.weight.BaseListView;
import com.qiu.houdeplay.utils.UiUtils;

/**
 * Created by Administrator on 2016/1/2.
 */
public abstract class BaseFragmentList extends BaseFragment {

    @Override
    protected View createSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        listView.setAdapter(getAdapter());
        return listView;
    }

    protected abstract ListAdapter getAdapter();

}
