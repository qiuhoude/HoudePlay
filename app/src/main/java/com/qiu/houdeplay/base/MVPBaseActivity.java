package com.qiu.houdeplay.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 *
 */
public abstract class MvpBaseActivity<V extends MvpView, T extends BasePresenter<V>>
        extends ActionBarActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = creatPresenter();
        //绑定presenter
        mPresenter.attachView((V) this);
    }

    protected abstract T creatPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
