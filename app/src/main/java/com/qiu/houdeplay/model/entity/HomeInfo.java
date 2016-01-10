package com.qiu.houdeplay.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 */
public class HomeInfo {
    private List<String> picture;
    private List<AppInfo> list;

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<AppInfo> getList() {
        return list;
    }

    public void setList(List<AppInfo> list) {
        this.list = list;
    }
}
