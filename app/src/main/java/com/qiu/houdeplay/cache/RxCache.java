package com.qiu.houdeplay.cache;

import rx.Observable;

/**
 * Created by Administrator on 2015/12/30.
 */
public interface RxCache {
    Observable<Data> get(String url);
}
