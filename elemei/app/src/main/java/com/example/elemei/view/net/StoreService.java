package com.example.elemei.view.net;

import com.example.elemei.view.pojo.StoreBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Date:2021/4/13
 * Author:fanshaofeng
 */
public interface StoreService {

    @GET(value = "store/selectAll")
    Call<StoreBean> selectAll();
}
