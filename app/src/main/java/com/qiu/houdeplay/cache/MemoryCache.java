package com.qiu.houdeplay.cache;

import android.util.LruCache;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/12/30.
 */
public class MemoryCache {
    // 计算可使用的最大内存
    private static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    // 取八分之一的可用内存作为缓存
    private static final int cacheSize = maxMemory / 8;

    private LruCache<String, CacheData> dataCache;

    private MemoryCache() {
        dataCache = new LruCache<String, CacheData>(cacheSize) {
            @Override
            protected int sizeOf(String key, CacheData value) {
                int size = 1000 * 1024;
                try {
                    size = value.getJson().getBytes("UTF-8").length / 1024;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return size;
            }
        };

    }

    private static MemoryCache instance;


    public static MemoryCache getInstance() {
        if (instance == null) {
            synchronized (MemoryCache.class) {
                if (instance == null) {
                    instance = new MemoryCache();
                }
            }
        }
        return instance;
    }


    public void putData(String key, CacheData data) {
        dataCache.put(key, data);
    }

    public CacheData getData(String key) {
        return dataCache.get(key);
    }
}
