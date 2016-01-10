package com.qiu.houdeplay.injection.component;


import android.app.Application;
import android.content.Context;

import com.qiu.houdeplay.injection.ApplicationContext;
import com.qiu.houdeplay.injection.module.ApplicationModule;
import com.qiu.houdeplay.model.local.PreferencesHelper;
import com.qiu.houdeplay.model.remote.HoudePlayApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    PreferencesHelper preferencesHelper();

    HoudePlayApi houdePlayApi();


//    DatabaseHelper databaseHelper();
//    DataManager dataManager();
}
