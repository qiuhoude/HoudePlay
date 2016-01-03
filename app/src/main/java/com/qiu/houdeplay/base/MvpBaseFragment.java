package com.qiu.houdeplay.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/1/2.
 */
public abstract class MvpBaseFragment<V extends MvpView, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = creatPresenter();
        //绑定presenter
        mPresenter.attachView((V) this);
    }

    protected abstract T creatPresenter();

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }
}
