package com.qiu.houdeplay.app;

import android.app.Application;
import android.content.Context;

import android.os.Handler;

/**
 * Created by Administrator on 2015/12/22.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static Handler handler;
    private static int mainTid;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mainTid = android.os.Process.myTid();
        handler=new Handler();
    }

    public static Context getApplication() {
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
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }

}
