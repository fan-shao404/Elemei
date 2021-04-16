package com.example.elemei.view.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.net.ShoppingCarCall;
import com.example.elemei.view.pojo.Commodity;
import com.example.elemei.view.pojo.InsertBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/14
 * Author:fanshaofeng
 */
public class CommodityAdapter extends BaseQuickAdapter<Commodity, BaseViewHolder> {

    private ShoppingCarCall shoppingCarCall = new ShoppingCarCall();

    public CommodityAdapter(@Nullable List<Commodity> data) {
        super(R.layout.commodity, data);
    }

    public CommodityAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Commodity commodity) {
        baseViewHolder.getView(R.id.iv_commodity_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCarCall.subtract(2, 59, new Callback<InsertBean>() {
                    @Override
                    public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                        Log.e("TAG", "onResponse: "+response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<InsertBean> call, Throwable t) {
                        Log.e("TAG", "onFailure: "+t.toString());
                    }
                });
            }
        });
        baseViewHolder.getView(R.id.iv_commodity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCarCall.add(2, 59, new Callback<InsertBean>() {
                    @Override
                    public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                        Log.e("TAG", "onResponse: "+response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<InsertBean> call, Throwable t) {
                        Log.e("TAG", "onFailure: "+t.toString());
                    }
                });
            }
        });
        baseViewHolder.setText(R.id.tv_commodity_name,commodity.getName());
        ImageView cover = baseViewHolder.findView(R.id.iv_commodity_cover);
        Glide.with(getContext()).load(commodity.getCover())
                .transform(new RoundedCorners(15))
                .into(cover);
        baseViewHolder.setText(R.id.tv_commodity_price,"￥"+commodity.getPrice());
    }
}
