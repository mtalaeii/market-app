package com.mtalaeii.marketapp.ApiStructure;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService2 extends Application {
    private Retrofit productRequest;
    private Retrofit commentRequest;
    private static Api productApi;
    private static Api commentApi;
    @Override
    public void onCreate() {
        super.onCreate();
        productRequest = new Retrofit.Builder()
                .baseUrl(Details.Main.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productApi = productRequest.create(Api.class);
        commentRequest = new Retrofit.Builder()
                .baseUrl(Details.Main.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentApi= commentRequest.create(Api.class);
    }
    public static Api getCommentApi(){
        return commentApi;
    }
    public static Api getProductApi(){
        return productApi;
    }
}
