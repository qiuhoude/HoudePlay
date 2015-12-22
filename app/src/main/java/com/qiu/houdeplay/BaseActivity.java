package com.qiu.houdeplay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.qiu.houdeplay.tools.AppManager;

/**
 * Created by Administrator on 2015/12/22.
 */
public abstract class BaseActivity extends ActionBarActivity {

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
