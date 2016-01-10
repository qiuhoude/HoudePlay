package com.qiu.houdeplay;

import android.content.Context;
import android.test.AndroidTestCase;

import com.qiu.houdeplay.app.BaseApplication;
import com.qiu.houdeplay.cache.DiskLruCacheUtils;
import com.qiu.houdeplay.model.entity.HomeInfo;
import com.qiu.houdeplay.model.remote.HoudePlayApi;
import com.qiu.houdeplay.utils.Logs;

import java.io.IOException;

import retrofit.Call;

/**
 * Created by Administrator on 2015/12/30.
 */
public class AppTest extends AndroidTestCase {
    private Context mContext;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = getContext();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        android.os.SystemClock.sleep(1000);
    }

    public void testCacheDir() {
        System.out.println(DiskLruCacheUtils.getDiskCacheDir(mContext, "cache").getAbsolutePath());
    }

    public void testSaveCache() {
        DiskLruCacheUtils.saveObject(mContext, "呵呵对象", "111");
        DiskLruCacheUtils.saveObject(mContext, "嘻嘻对象", "222");


    }

    public void testReadCache() {
//        Logs.d("" + DiskLruCacheUtils.readStr(mContext, "333"));
        Logs.d("" + DiskLruCacheUtils.readObject(mContext, "111"));
        Logs.d("" + DiskLruCacheUtils.readObject(mContext, "222"));
    }

    public void testApi() throws IOException {
        HoudePlayApi houdePlayApi= ((BaseApplication) BaseApplication.getApplication()).getComponent().houdePlayApi();
        Call<HomeInfo> homeData = houdePlayApi.getHomeData(0);

        homeData.execute();

    }



}
