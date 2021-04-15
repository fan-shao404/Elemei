package com.example.elemei.view.net;

import com.example.elemei.view.pojo.CommodityBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Date:2021/4/15
 * Author:fanshaofeng
 */
public interface CommodityService {

    @GET("commodity/selectbyid")
    Call<CommodityBean> selectById(@Query("store_id") int id);

}
