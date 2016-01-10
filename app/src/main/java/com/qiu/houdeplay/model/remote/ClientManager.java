package com.qiu.houdeplay.model.remote;

import android.content.Context;

import com.qiu.houdeplay.app.BaseApplication;
import com.qiu.houdeplay.utils.NetUtils;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import timber.log.Timber;


public class ClientManager {

    public static final int DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024; //10MB

    private Context context;
    private OkHttpClient okCacheHttpClient;

    private static ClientManager mInstance;

    private ClientManager() {
        context = ((BaseApplication) BaseApplication.getApplication()).getComponent().context();
    }

    public static ClientManager getInstance() {
        if (mInstance == null) {
            synchronized (ClientManager.class) {
                if (mInstance == null) {
                    mInstance = new ClientManager();
                }
            }
        }
        return mInstance;
    }


    private final Interceptor cacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (NetUtils.isNetworkAvailable(context)) {
                int maxAge = 60; // read from cache for 1 minute
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    private final Interceptor loggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            System.out.println("访问服务器");
            long t1 = System.nanoTime();
            Timber.d(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Timber.d(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };


    public OkHttpClient createCachedClient() {
        if (okCacheHttpClient != null) {
            return okCacheHttpClient;
        }
        final File baseDir = context.getCacheDir();
        File cacheDir = new File(baseDir, "HttpResponseCache");
        Cache cache = new Cache(cacheDir, DISK_CACHE_MAX_SIZE);
        okCacheHttpClient = new OkHttpClient();
        okCacheHttpClient.setCache(cache);
        okCacheHttpClient.interceptors().add(cacheControlInterceptor);
        okCacheHttpClient.networkInterceptors().add(loggingInterceptor);

        return okCacheHttpClient;
    }
}
