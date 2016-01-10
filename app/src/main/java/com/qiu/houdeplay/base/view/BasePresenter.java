package com.qiu.houdeplay.base.view;

import com.qiu.houdeplay.base.presenter.Presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * presenter基类,使用弱引用和生命周期来解决
 * activity和fragment对象在presenter不能被回收，发生内存泄露
 */
public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {
    protected Reference<V> mViewRef; //view接口类型的弱引用

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);//建立联系
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
