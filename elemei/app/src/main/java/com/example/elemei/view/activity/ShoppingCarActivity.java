package com.example.elemei.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elemei.R;
import com.example.elemei.view.adapter.ShoppingCarActivityAdapter;
import com.example.elemei.view.net.ShoppingCarCall;
import com.example.elemei.view.pojo.Store;
import com.example.elemei.view.pojo.StoreBean;
import com.example.elemei.view.util.Const;
import com.example.elemei.view.util.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/21
 * Author:fanshaofeng
 */
public class ShoppingCarActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ShoppingCarActivityAdapter shoppingCarActivityAdapter;
    private ShoppingCarCall shoppingCarCall = new ShoppingCarCall();
    private List<Store> stores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        initView();
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shopping_car_back:
                finish();
                break;
            default:
                break;
        }
    }

    public void initView() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.blue));
        ImageView back = findViewById(R.id.iv_shopping_car_back);
        back.setOnClickListener(this);
        recyclerView = findViewById(R.id.rv_activity_shopping_car);
    }
    public void getData() {
        stores = new ArrayList<>();
        shoppingCarCall.selectByCustomer(Const.customer_id, new Callback<StoreBean>() {
            @Override
            public void onResponse(Call<StoreBean> call, Response<StoreBean> response) {
                if (response.body().getResult() != null) {
                    stores = response.body().getResult();
                    shoppingCarActivityAdapter = new ShoppingCarActivityAdapter(stores);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingCarActivity.this));
                    recyclerView.addItemDecoration(new MyItemDecoration(8,8));
                    recyclerView.setAdapter(shoppingCarActivityAdapter);
                }
            }

            @Override
            public void onFailure(Call<StoreBean> call, Throwable t) {

            }
        });

    }
}
