package com.qiu.houdeplay.model.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiu.houdeplay.model.entity.HomeInfo;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * 公共的api
 */
public interface HoudePlayApi {

    @Headers("Cache-Control: max-stale=60")
    @GET("home")
    Call<HomeInfo> getHomeData(@Query("index") int index);


    class Creator {
        public static HoudePlayApi newRibotsService() {
            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            final String HOST = "http://127.0.0.1:8090/";
            OkHttpClient client = ClientManager.getInstance().createCachedClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(HoudePlayApi.class);
        }
    }
}
