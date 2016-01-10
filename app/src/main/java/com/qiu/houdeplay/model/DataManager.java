package com.qiu.houdeplay.model;


import com.qiu.houdeplay.model.local.PreferencesHelper;
import com.qiu.houdeplay.model.remote.HoudePlayApi;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private final HoudePlayApi mHoudePlayApi;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(HoudePlayApi mHoudePlayApi, PreferencesHelper mPreferencesHelper) {
        this.mHoudePlayApi = mHoudePlayApi;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


}
