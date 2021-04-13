package com.example.elemei.view.net;

import com.example.elemei.view.pojo.StoreBean;

import retrofit2.Callback;

/**
 * Date:2021/4/13
 * Author:fanshaofeng
 */
public class StoreCall extends BaseCall<StoreService> {

    private StoreService storeService;
    {
        storeService = this.obtain(StoreService.class);
    }

    @Override
    public StoreService obtain(Class<StoreService> service) {
        return retrofit.create(service);
    }
    //查询所以的店铺
    public void selectAll(Callback<StoreBean> callback){
        storeService.selectAll().enqueue(callback);
    }
}
