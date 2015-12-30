package com.qiu.houdeplay.cache;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2015/12/30.
 */
public class RxSources {

    RxMemoryCache memoryCache;
    RxDikCache dikCache;

    public RxSources() {
        memoryCache = new RxMemoryCache();
        dikCache = new RxDikCache();
    }

    public Observable<CacheData> memory(String url) {
        return memoryCache.getObservable(url)
                .compose(logSource("MEMORY"));
    }

    public Observable<CacheData> disk(final String url) {
        return dikCache.getObservable(url)
                //将disk数据 -> memory
                .doOnNext(new Action1<CacheData>() {
                    @Override
                    public void call(CacheData data) {
                        memoryCache.putData(url, data);
                    }
                })
                .compose(logSource("DISK"));
    }




    // Simple logging to let us know what each source is returning
    Observable.Transformer<CacheData, CacheData> logSource(final String source) {
        return new Observable.Transformer<CacheData, CacheData>() {
            @Override
            public Observable<CacheData> call(Observable<CacheData> cacheDataObservable) {
                return cacheDataObservable
                        .doOnNext(new Action1<CacheData>() {
                            @Override
                            public void call(CacheData data) {
                                if (data == null) {
                                    System.out.println(source + " does not have any data.");
                                } else if (!data.isUpToDate()) {
                                    System.out.println(source + " has stale data.");
                                } else {
                                    System.out.println(source + " has the data you are looking for!");
                                }
                            }
                        });
            }
        };
    }
}
