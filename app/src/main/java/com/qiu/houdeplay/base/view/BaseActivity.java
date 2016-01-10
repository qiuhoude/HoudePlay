package com.qiu.houdeplay.base.view;

import android.os.Bundle;

import com.qiu.houdeplay.base.presenter.Presenter;
import com.qiu.houdeplay.utils.AppManager;

/**
 * Created by Administrator on 2015/12/22.
 */
public abstract class BaseActivity<T extends Presenter> extends MvpBaseActivity<T> {

    public final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加到栈
        AppManager.getAppManager().addActivity(this);
        init();
        initActionBar();
        initView();
    }

    protected void initActionBar() {
    }

    protected void initView() {
    }

    protected void init() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }
}
