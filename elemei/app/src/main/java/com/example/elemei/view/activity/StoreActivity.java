package com.example.elemei.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.elemei.R;
import com.example.elemei.view.adapter.CommodityAdapter;
import com.example.elemei.view.net.CommodityCall;
import com.example.elemei.view.net.ShoppingCarCall;
import com.example.elemei.view.pojo.Change;
import com.example.elemei.view.pojo.CheckedCommmodityBean;
import com.example.elemei.view.pojo.CheckedCommodity;
import com.example.elemei.view.pojo.Commodity;
import com.example.elemei.view.pojo.CommodityBean;
import com.example.elemei.view.util.MyItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private CommodityAdapter commodityAdapter = new CommodityAdapter();
    private CommodityCall commodityCall;
    private ShoppingCarCall shoppingCarCall;
    private List<CheckedCommodity> checkedCommodities;
    private TextView total;
    private int sum;
    private TextView counts;
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_store);
        id = getIntent().getIntExtra("id", 1);
        cover = getIntent().getStringExtra("cover");
        name = getIntent().getStringExtra("name");
        start_send = getIntent().getDoubleExtra("start_send", 1);
        distribution = getIntent().getDoubleExtra("distribution", 0.5);
        initView();
        commodityCall = new CommodityCall();
        shoppingCarCall = new ShoppingCarCall();
        shoppingCarCall.selectAll(id, 59, new Callback<CheckedCommmodityBean>() {
            @Override
            public void onResponse(Call<CheckedCommmodityBean> call, Response<CheckedCommmodityBean> response) {
                if (response.body().getResult() != null && response.body().getResult().size() > 0) {
                    checkedCommodities = response.body().getResult();
                    commodityAdapter.setCheckedCommodities(checkedCommodities);
                    for (CheckedCommodity checkedCommodity : checkedCommodities) {
                        sum += checkedCommodity.getPrice() * checkedCommodity.getNumber();
                        count += checkedCommodity.getNumber();
                    }
                    total.setText("￥" + sum);
                    counts.setText("" + count);
                    if (sum < start_send) {
                        store_start_send.setText("差￥" + (start_send - count) + "起送");
                    } else {
                        store_start_send.setText("去结算");
                    }
                }
                commodityCall.selectById(id, new Callback<CommodityBean>() {
                    @Override
                    public void onResponse(Call<CommodityBean> call, Response<CommodityBean> response) {
                        Log.e("TAG", "onResponse: " + response.body().toString());
                        List<Commodity> commodities = response.body().getResult();
                        commodityAdapter.setList(commodities);
                        commodity_recycle.setLayoutManager(new LinearLayoutManager(StoreActivity.this));
                        commodity_recycle.setAdapter(commodityAdapter);
                        commodity_recycle.addItemDecoration(new MyItemDecoration());
                    }

                    @Override
                    public void onFailure(Call<CommodityBean> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<CheckedCommmodityBean> call, Throwable t) {
                Log.e("TAG", "onFailure: hhhhhhhh");
            }
        });
    }

    //初始化view
    public void initView() {
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
        store_distribution.setText("另需要配送费￥" + distribution);
        store_start_send = findViewById(R.id.tv_homepage_store_start_send);
        store_start_send.setText("￥" + start_send + "起送");
        commodity_recycle = findViewById(R.id.rv_activity_store_commodity);
        total = findViewById(R.id.tv_homepage_sum);
        counts = findViewById(R.id.tv_homepage_count);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void update(Change change) {
        if (change.getOperation() == Change.Operation.INSERT) {
            checkedCommodities.add(change.getCheckedCommodity());
            counts.setText("" + (++count));
        } else if (change.getOperation() == Change.Operation.ADD) {
            int id = change.getCheckedCommodity().getcommodity_id();
            for (CheckedCommodity checkedCommodity : checkedCommodities) {
                if (checkedCommodity.getcommodity_id() == id) {
                    checkedCommodity.setNumber(checkedCommodity.getNumber() + 1);
                }
            }
            counts.setText("" + (++count));
        } else if (change.getOperation() == Change.Operation.SUBTRACT) {
            int id = change.getCheckedCommodity().getcommodity_id();
            for (CheckedCommodity checkedCommodity : checkedCommodities) {
                if (checkedCommodity.getcommodity_id() == id) {
                    checkedCommodity.setNumber(checkedCommodity.getNumber() - 1);
                }
            }
            counts.setText("" + (--count));
        } else {
            checkedCommodities.remove(change.getCheckedCommodity());
            counts.setText("" + (--count));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
