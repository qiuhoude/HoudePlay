package com.qiu.houdeplay.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.houdeplay.ui.weight.EmptyLayout;

/**
 * Created by Administrator on 2015/12/29.
 */
public abstract class BaseFragment<V extends MvpView, T extends BasePresenter<V>> extends MvpBaseFragment<V, T> {

    protected EmptyLayout emptyLayout;
    protected Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity().getApplication();
        if (emptyLayout == null) {
            emptyLayout = new EmptyLayout(getActivity().getApplication());
        }
        emptyLayout.addView(createSuccessView());
        return emptyLayout;
    }

    protected abstract View createSuccessView();

    protected void initView() {
    }

    protected void initData() {
    }

    public void setState(int state) {
        emptyLayout.setErrorType(state);
    }
}
