package com.qiu.houdeplay.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Administrator on 2016/1/2.
 */
public class NetUtils {

    public static boolean isNetworkAvailable(Context context) {
        boolean flag;
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
