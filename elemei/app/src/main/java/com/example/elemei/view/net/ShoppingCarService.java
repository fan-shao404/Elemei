package com.example.elemei.view.net;

import com.example.elemei.view.pojo.CheckedCommmodityBean;
import com.example.elemei.view.pojo.InsertBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Date:2021/4/16
 * Author:fanshaofeng
 */
public interface ShoppingCarService {

    @POST("shoppingcar/insert")
    @FormUrlEncoded
    Call<InsertBean> insert(@Field("commodity_id") int commodity_id, @Field("store_id") int store_id, @Field("customer_id") int customer_id);

    @POST("shoppingcar/add")
    @FormUrlEncoded
    Call<InsertBean> add(@Field("commodity_id") int commodity_id, @Field("customer_id") int customer_id);

    @POST("shoppingcar/subtract")
    @FormUrlEncoded
    Call<InsertBean> subtract(@Field("commodity_id") int commodity_id, @Field("customer_id") int customer_id);


    @POST("shoppingcar/delete")
    @FormUrlEncoded
    Call<InsertBean> delete(@Field("commodity_id") int commodity_id, @Field("customer_id") int customer_id);

    @POST("shoppingcar/deleteAll")
    @FormUrlEncoded
    Call<InsertBean> deleteAll(@Field("store_id") int store_id, @Field("customer_id") int customer_id);

    @POST("shoppingcar/selectAll")
    @FormUrlEncoded
    Call<CheckedCommmodityBean> selectAll(@Field("store_id") int store_id, @Field("customer_id") int customer_id);
}
