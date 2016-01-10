package com.qiu.houdeplay.base.view;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;

/**
 * Created by Administrator on 2016/1/9.
 */
public abstract class BaseLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpLceViewStateActivity<CV, M, V, P> {


}
