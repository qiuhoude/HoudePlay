package com.qiu.houdeplay.cache;

import com.qiu.houdeplay.utils.Logs;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2015/12/30.
 */
public class RxMemoryCache implements RxCache {

    @Override
    public Observable<CacheData> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<CacheData>() {
            @Override
            public void call(Subscriber<? super CacheData> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                Logs.d("search in memory");
                subscriber.onNext(MemoryCache.getInstance().getData(url));
                subscriber.onCompleted();
            }
        });
    }




    public void putData(String key, CacheData data) {
        MemoryCache.getInstance().putData(key, data);
    }
}
