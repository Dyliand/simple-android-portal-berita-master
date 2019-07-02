package com.khilman.www.aplikasiportalberita.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class InitRetrofit {
    // URL Server API
    public static String API_URL = "http://120.168.4.12/portal_berita/";

    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance() {
        return setInit().create(ApiServices.class);
    }
}