package com.qiu.houdeplay.cache;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/12/30.
 */
public class RxNetwork implements RxCache {

    @Override
    public Observable<CacheData> getObservable(String url) {

        return Observable.create(new Observable.OnSubscribe<CacheData>() {
            @Override
            public void call(Subscriber<? super CacheData> subscriber) {
                if (subscriber.isUnsubscribed()) return;

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public void putData(String key, CacheData data) {

    }
}
