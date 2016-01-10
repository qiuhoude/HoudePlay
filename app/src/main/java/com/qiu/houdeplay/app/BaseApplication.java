package com.qiu.houdeplay.app;

import android.app.Application;
import android.os.Handler;

import com.qiu.houdeplay.BuildConfig;
import com.qiu.houdeplay.injection.component.ApplicationComponent;
import com.qiu.houdeplay.injection.component.DaggerApplicationComponent;
import com.qiu.houdeplay.injection.module.ApplicationModule;

import timber.log.Timber;

/**
 * Created by Administrator on 2015/12/22.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static Handler handler;
    private static int mainTid;

    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        getComponent();

        application = this;
        mainTid = android.os.Process.myTid();
        handler = new Handler();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * 获取主线程的id号
     */
    public static int getMainTid() {
        return mainTid;
    }

    /**
     * 获取主线程的handler
     *
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }

}
