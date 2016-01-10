package com.qiu.houdeplay.base.presenter;

import com.qiu.houdeplay.base.view.MvpView;

/**
 * Created by Administrator on 2016/1/2.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
