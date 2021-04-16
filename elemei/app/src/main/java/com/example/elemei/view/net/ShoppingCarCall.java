package com.example.elemei.view.net;

import com.example.elemei.view.pojo.CheckedCommmodityBean;
import com.example.elemei.view.pojo.InsertBean;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;

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

    //将一个商家整个购物车清空
    public void deleteAll(int store_id, int customer_id, Callback<InsertBean> callback) {
        shoppingCarService.deleteAll(store_id, customer_id).enqueue(callback);
    }

    //查询一个商家的购物车列表
    public Response<CheckedCommmodityBean> selectAll(int store_id, int customer_id) {
        try {
            return shoppingCarService.selectAll(store_id, customer_id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
