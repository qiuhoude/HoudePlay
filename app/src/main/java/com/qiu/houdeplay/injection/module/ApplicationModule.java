package com.qiu.houdeplay.injection.module;


import android.app.Application;
import android.content.Context;

import com.qiu.houdeplay.injection.ApplicationContext;
import com.qiu.houdeplay.model.remote.HoudePlayApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    //application级别的context
    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    HoudePlayApi provideHoudeApi() {
        return HoudePlayApi.Creator.newRibotsService();
    }



//    @Provides
//    @Singleton
//    RibotsService provideRibotsService() {
//        return RibotsService.Creator.newRibotsService();
//    }
}
