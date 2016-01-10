package com.qiu.houdeplay.base.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.qiu.houdeplay.base.presenter.Presenter;

/**
 *
 */
public abstract class MvpBaseActivity<T extends Presenter>
        extends ActionBarActivity implements MvpView {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = creatPresenter();
        //绑定presenter
        mPresenter.attachView(this);
    }

    protected abstract T creatPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
