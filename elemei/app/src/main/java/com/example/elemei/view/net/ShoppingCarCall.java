package com.example.elemei.view.net;

import com.example.elemei.view.pojo.InsertBean;

import retrofit2.Callback;

/**
 * Date:2021/4/16
 * Author:fanshaofeng
 */
public class ShoppingCarCall extends BaseCall<ShoppingCarService> {

    private ShoppingCarService shoppingCarService;

    {
        shoppingCarService = this.obtain(ShoppingCarService.class);
    }

    @Override
    public ShoppingCarService obtain(Class<ShoppingCarService> service) {
        return retrofit.create(service);
    }

    //将商品加入到购物车
    public void insert(int commodity_id, int store_id, int customer_id, Callback<InsertBean> callback) {
        shoppingCarService.insert(commodity_id, store_id, customer_id).enqueue(callback);
    }

    //将购物车里的商品+1
    public void add(int commodity_id, int customer_id, Callback<InsertBean> callback) {
        shoppingCarService.add(commodity_id, customer_id).enqueue(callback);
    }

    //将购物车里的商品-1
    public void subtract(int commodity_id, int customer_id, Callback<InsertBean> callback) {
        shoppingCarService.subtract(commodity_id, customer_id).enqueue(callback);
    }

    //将购物车里的一条记录删掉
    public void delete(int commodity_id, int customer_id, Callback<InsertBean> callback) {
        shoppingCarService.delete(commodity_id, customer_id).enqueue(callback);
    }

    //将整个购物车清空
    public void deleteAll(int store_id, int customer_id, Callback<InsertBean> callback) {
        shoppingCarService.deleteAll(store_id, customer_id).enqueue(callback);
    }

}