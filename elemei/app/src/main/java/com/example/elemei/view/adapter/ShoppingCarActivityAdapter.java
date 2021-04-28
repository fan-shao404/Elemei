package com.example.elemei.view.adapter;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.net.ShoppingCarCall;
import com.example.elemei.view.pojo.CheckedCommodityBean;
import com.example.elemei.view.pojo.InsertBean;
import com.example.elemei.view.pojo.Store;
import com.example.elemei.view.util.Const;
import com.example.elemei.view.util.MyItemDecoration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/21
 * Author:fanshaofeng
 */
public class ShoppingCarActivityAdapter extends BaseQuickAdapter<Store, BaseViewHolder> {

    private ShoppingCarCall shoppingCarCall = new ShoppingCarCall();

    public ShoppingCarActivityAdapter(@NotNull List<Store> data) {
        super(R.layout.shopping_car_store, data);
    }

    public ShoppingCarActivityAdapter(int layoutResId, @Nullable List<Store> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Store store) {
        baseViewHolder.setText(R.id.tv_shopping_store_name, store.getName());
        RecyclerView recyclerView = baseViewHolder.getView(R.id.rv_shopping_car_commodity);
        ShoppingCommodityAdapter shoppingCommodityAdapter = new ShoppingCommodityAdapter();
        shoppingCarCall.selectAll(store.getId(), Const.customer_id, new Callback<CheckedCommodityBean>() {
            @Override
            public void onResponse(Call<CheckedCommodityBean> call, Response<CheckedCommodityBean> response) {
                if (response.body().getResult() != null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.addItemDecoration(new MyItemDecoration(5, 5));
                    shoppingCommodityAdapter.setList(response.body().getResult());
                    recyclerView.setAdapter(shoppingCommodityAdapter);
                }
            }

            @Override
            public void onFailure(Call<CheckedCommodityBean> call, Throwable t) {

            }
        });
        ImageView empty = baseViewHolder.findView(R.id.iv_shopping_car_empty);
        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCarCall.deleteAllByStore(store.getId(), Const.customer_id, new Callback<InsertBean>() {
                    @Override
                    public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                        if (response.body().getOkPacket() != null && response.body().getOkPacket().getAffectedRows() > 0) {
                            remove(store);
                            Log.e("TAG", "onResponse:1 ");
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertBean> call, Throwable t) {
                        Log.e("TAG", "onFailure: "+t.toString());
                    }
                });
            }
        });
    }


}
