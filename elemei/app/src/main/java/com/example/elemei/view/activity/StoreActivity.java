package com.example.elemei.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.elemei.R;
import com.example.elemei.view.adapter.CommodityAdapter;
import com.example.elemei.view.net.CommodityCall;
import com.example.elemei.view.pojo.Commodity;
import com.example.elemei.view.pojo.CommodityBean;
import com.example.elemei.view.util.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/14
 * Author:fanshaofeng
 */
public class StoreActivity extends AppCompatActivity {

    private String cover;
    private int id;
    private String name;
    private double start_send;
    private double distribution;
    private ImageView header;
    private ImageView store_cover;
    private TextView store_name;
    private TextView store_distribution;
    private TextView store_start_send;
    private RecyclerView commodity_recycle;
    private CommodityAdapter commodityAdapter;
    private CommodityCall commodityCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_store);
        id = getIntent().getIntExtra("id",1);
        cover = getIntent().getStringExtra("cover");
        name =getIntent().getStringExtra("name");
        start_send = getIntent().getDoubleExtra("start_send",1);
        distribution = getIntent().getDoubleExtra("distribution",0.5);
//        Log.e("TAG", "onCreate: id"+id+cover+name+start_send+distribution);
        initView();
        commodityCall = new CommodityCall();
        commodityCall.selectById(id, new Callback<CommodityBean>() {
            @Override
            public void onResponse(Call<CommodityBean> call, Response<CommodityBean> response) {
                Log.e("TAG", "onResponse: "+response.body().toString());
                List<Commodity> commodities = response.body().getResult();
                commodityAdapter = new CommodityAdapter(commodities);
                commodity_recycle.setLayoutManager(new LinearLayoutManager(StoreActivity.this));
                commodity_recycle.setAdapter(commodityAdapter);
                commodity_recycle.addItemDecoration(new MyItemDecoration());
            }

            @Override
            public void onFailure(Call<CommodityBean> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.toString());
            }
        });
    }

    //初始化view
    public void initView(){
        header = findViewById(R.id.iv_homepage_store_header);
        Glide.with(StoreActivity.this).load(cover)
                .into(header);
        store_cover = findViewById(R.id.iv_homepage_store_cover);
        Glide.with(StoreActivity.this).load(cover)
                .transform(new RoundedCorners(10))
                .into(store_cover);
        store_name = findViewById(R.id.tv_homepage_store_name);
        store_name.setText(name);
        store_distribution = findViewById(R.id.tv_homepage_store_distribution);
        store_distribution.setText("另需要配送费￥"+distribution);
        store_start_send = findViewById(R.id.tv_homepage_store_start_send);
        store_start_send.setText("￥"+start_send+"起送");
        commodity_recycle = findViewById(R.id.rv_activity_store_commodity);
    }
}
