package com.qiu.houdeplay.cache;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/30.
 */
public class Data<T extends Serializable> implements Serializable {
    private static final long STALE_MS = 1 * 60 * 1000; //数据过期时间
    private long timestamp;
    private T t;

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
