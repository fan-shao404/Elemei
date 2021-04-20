package com.example.elemei.view.net;

import com.example.elemei.view.pojo.CountBean;
import com.example.elemei.view.pojo.CustomerBean;
import com.example.elemei.view.pojo.InsertBean;
import com.example.elemei.view.pojo.OKPacket;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Date:2021/4/9
 * Author:fanshaofeng
 */
public interface CustomerServices {
    @POST(value = "customer/add")
    @FormUrlEncoded
    Call<InsertBean> registerCustomer(@Field("phone") String phone, @Field("email") String email, @Field("password") String password);

    @POST("customer/select")
    @FormUrlEncoded
    Call<CustomerBean> select(@Field("phone") String phone,@Field("password") String password);

    @GET(value = "customer/selectAll")
    Call<CustomerBean> selectAll();
}
