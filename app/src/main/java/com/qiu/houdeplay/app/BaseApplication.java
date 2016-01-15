package com.qiu.houdeplay.app;

import android.app.Application;
import android.os.Handler;

import com.qiu.houdeplay.BuildConfig;

import timber.log.Timber;

public class BaseApplication extends Application {
    private static BaseApplication application;
    private static Handler handler;
    private static int mainTid;


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        application = this;
        mainTid = android.os.Process.myTid();
        handler = new Handler();
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
