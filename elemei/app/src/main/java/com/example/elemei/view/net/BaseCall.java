package com.example.elemei.view.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date:2021/4/9
 * Author:fanshaofeng
 */
public abstract class BaseCall<T> {
    public Retrofit retrofit;
    {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://fanshao.top:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public abstract T obtain(Class<T> service);
}
