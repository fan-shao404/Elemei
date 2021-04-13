package com.example.elemei.view.net;

import android.util.Log;

import com.example.elemei.view.pojo.CountBean;
import com.example.elemei.view.pojo.CustomerBean;
import com.example.elemei.view.pojo.InsertBean;
import retrofit2.Callback;

/**
 * Date:2021/4/9
 * Author:fanshaofeng
 */
public class CustomerCall extends BaseCall<CustomerServices> {

    private CustomerServices customerServices;
    {
        customerServices = this.obtain(CustomerServices.class);
    }

    @Override
    public CustomerServices obtain(Class<CustomerServices> service) {
        return retrofit.create(service);
    }
    //注册客户
    public void addCustomer(String phone,String email,String password,Callback<InsertBean> callback){
        customerServices.registerCustomer(phone,email,password).enqueue(callback);
    }
    //登录判断是否存在客户
    public void count(String phone, String password, Callback<CountBean> callback){
        customerServices.count(phone,password).enqueue(callback);
    }
    //测试用的测试selectall
    public void selectAll(Callback<CustomerBean> callback ){
        customerServices.selectAll().enqueue(callback);
    }
}
