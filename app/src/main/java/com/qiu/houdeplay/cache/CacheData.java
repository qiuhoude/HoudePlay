package com.qiu.houdeplay.cache;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/30.
 */
public class CacheData implements Serializable {
    private static final long STALE_MS = 1 * 60 * 1000; //数据过期时间
    private long timestamp;
    private String json;

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
