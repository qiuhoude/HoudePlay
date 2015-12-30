package com.qiu.houdeplay.cache;

import com.google.gson.Gson;
import com.qiu.houdeplay.tools.UiUtils;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/12/30.
 */
public class RxDikCache implements RxCache {


    @Override
    public Observable<CacheData> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<CacheData>() {
            @Override
            public void call(Subscriber<? super CacheData> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                Gson gson = new Gson();
                String json = DiskLruCacheUtils.readStr(UiUtils.getContext(), url);
                CacheData data = gson.fromJson(json, CacheData.class);
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public void putData(final String key, final CacheData data) {
        Observable.create(new Observable.OnSubscribe<CacheData>() {
            @Override
            public void call(Subscriber<? super CacheData> subscriber) {
                //写入内存
                DiskLruCacheUtils.saveStr(UiUtils.getContext(), data.toJson(), key);
                if (subscriber.isUnsubscribed()) return;
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();

    }
}
