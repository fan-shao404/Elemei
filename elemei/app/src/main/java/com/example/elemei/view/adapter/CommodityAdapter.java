package com.example.elemei.view.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.net.ShoppingCarCall;
import com.example.elemei.view.pojo.CheckedCommodity;
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
    private List<CheckedCommodity> checkedCommodities;

    public List<CheckedCommodity> getCheckedCommodities() {
        return checkedCommodities;
    }

    public void setCheckedCommodities(List<CheckedCommodity> checkedCommodities) {
        this.checkedCommodities = checkedCommodities;
    }

    public CommodityAdapter(@Nullable List<Commodity> data) {
        super(R.layout.commodity, data);
    }

    public CommodityAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Commodity commodity) {
        if (checkedCommodities != null) {
            for (CheckedCommodity checkedCommodity : checkedCommodities) {
                if (checkedCommodity.getcommodity_id() == commodity.getId()) {
                    baseViewHolder.setText(R.id.tv_commodity_sum, String.valueOf(checkedCommodity.getNumber()));
                }
            }
        }
        TextView textView = baseViewHolder.getView(R.id.tv_commodity_sum);
        baseViewHolder.getView(R.id.iv_commodity_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(String.valueOf(textView.getText())) == 0) {
                    baseViewHolder.getView(R.id.iv_commodity_subtract).setClickable(false);
                } else if (Integer.valueOf(String.valueOf(textView.getText())) == 1) {
                    shoppingCarCall.delete(commodity.getId(), 59, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            textView.setText("0");
                            baseViewHolder.getView(R.id.iv_commodity_subtract).setClickable(false);
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + "delete");
                        }
                    });
                } else {
                    shoppingCarCall.subtract(commodity.getId(), 59, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            Log.e("TAG", "onResponse: " + response.body().toString());
                            TextView textView = baseViewHolder.getView(R.id.tv_commodity_sum);
                            baseViewHolder.setText(R.id.tv_commodity_sum, String.valueOf(Integer.parseInt((String) textView.getText()) - 1));
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + t.toString());
                            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT);
                        }
                    });
                }
            }
        });
        baseViewHolder.getView(R.id.iv_commodity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(String.valueOf(textView.getText())) == 0) {
                    shoppingCarCall.insert(commodity.getId(), commodity.getStore_id(), 59, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            textView.setText("1");
                            baseViewHolder.getView(R.id.iv_commodity_subtract).setClickable(true);
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + "insert");
                        }
                    });

                } else {
                    shoppingCarCall.add(commodity.getId(), 59, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            Log.e("TAG", "onResponse: " + response.body().toString());
                            baseViewHolder.setText(R.id.tv_commodity_sum, String.valueOf(Integer.parseInt((String) textView.getText()) + 1));

                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + t.toString());
                            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT);
                        }
                    });
                }
            }
        });
        baseViewHolder.setText(R.id.tv_commodity_name, commodity.getName());
        ImageView cover = baseViewHolder.findView(R.id.iv_commodity_cover);
        Glide.with(getContext()).load(commodity.getCover())
                .transform(new RoundedCorners(15))
                .into(cover);
        baseViewHolder.setText(R.id.tv_commodity_price, "￥" + commodity.getPrice());
    }
}
