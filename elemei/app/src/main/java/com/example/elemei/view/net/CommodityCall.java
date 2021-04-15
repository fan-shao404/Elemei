package com.example.elemei.view.net;

import com.example.elemei.view.pojo.CommodityBean;

import retrofit2.Callback;

/**
 * Date:2021/4/15
 * Author:fanshaofeng
 */
public class CommodityCall extends BaseCall<CommodityService> {

    private CommodityService commodityService;
    {
        commodityService = this.obtain(CommodityService.class);
    }

    @Override
    public CommodityService obtain(Class<CommodityService> service) {
        return retrofit.create(service);
    }

    //查询店铺的菜单
    public void selectById(int id, Callback<CommodityBean> callback){
        commodityService.selectById(id).enqueue(callback);
    }
}
