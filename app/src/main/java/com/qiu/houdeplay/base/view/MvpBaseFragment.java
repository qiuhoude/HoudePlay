package com.qiu.houdeplay.base.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qiu.houdeplay.base.presenter.Presenter;

/**
 * Created by Administrator on 2016/1/2.
 */
public abstract class MvpBaseFragment<T extends Presenter>
        extends Fragment implements MvpView {
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) {
            mPresenter = creatPresenter();
        }
        mPresenter.attachView(this);
    }

    protected abstract T creatPresenter();

    @Override
    public void onDestroyView() {
        super.onDetach();
        mPresenter.detachView();
    }
}
