package com.qiu.houdeplay.cache;

import rx.Observable;

/**
 * Created by Administrator on 2015/12/30.
 */
public class RxMemoryCache implements RxCache {

    private static int maxSize = 10 * 1024 * 1024;

    // 计算可使用的最大内存
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    // 取四分之一的可用内存作为缓存
    final int cacheSize = maxMemory / 4;


    @Override
    public Observable<Data> get(String url) {

//        return Observable.;
        return null;
    }
}
