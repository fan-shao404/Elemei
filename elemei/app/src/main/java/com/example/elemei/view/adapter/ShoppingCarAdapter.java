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
import com.example.elemei.view.pojo.Change;
import com.example.elemei.view.pojo.CheckedCommodity;
import com.example.elemei.view.pojo.InsertBean;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/18
 * Author:fanshaofeng
 */
public class ShoppingCarAdapter extends BaseQuickAdapter<CheckedCommodity, BaseViewHolder> {

    private ShoppingCarCall shoppingCarCall = new ShoppingCarCall();


    public ShoppingCarAdapter(int layoutResId, @Nullable List<CheckedCommodity> data) {
        super(layoutResId, data);
    }

    public ShoppingCarAdapter(@NotNull List<CheckedCommodity> data) {
        super(R.layout.shopping_car_commodity, data);
    }

    public ShoppingCarAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CheckedCommodity checkedCommodity) {
        baseViewHolder.setText(R.id.tv_commodity_name, checkedCommodity.getName());
        ImageView cover = baseViewHolder.findView(R.id.iv_commodity_cover);
        Glide.with(getContext()).load(checkedCommodity.getCover())
                .transform(new RoundedCorners(15))
                .into(cover);
        baseViewHolder.setText(R.id.tv_commodity_price, "￥" + checkedCommodity.getPrice());
        baseViewHolder.setText(R.id.tv_commodity_sum, "" + checkedCommodity.getNumber());
        TextView textView = baseViewHolder.getView(R.id.tv_commodity_sum);
        baseViewHolder.getView(R.id.iv_commodity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCarCall.add(checkedCommodity.getcommodity_id(), 59, new Callback<InsertBean>() {
                    @Override
                    public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                        baseViewHolder.setText(R.id.tv_commodity_sum, String.valueOf(Integer.parseInt((String) textView.getText()) + 1));
                        EventBus.getDefault().post(new Change(checkedCommodity, Change.Operation.ADD));
                    }

                    @Override
                    public void onFailure(Call<InsertBean> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.toString());
                        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT);
                    }
                });
            }
        });
        baseViewHolder.getView(R.id.iv_commodity_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(String.valueOf(textView.getText())) == 1) {
                    shoppingCarCall.delete(checkedCommodity.getcommodity_id(), 59, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            remove(checkedCommodity);
                            EventBus.getDefault().post(new Change(checkedCommodity, Change.Operation.DELETE));
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Toast.makeText(getContext(), "emmmmm............", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    shoppingCarCall.subtract(checkedCommodity.getcommodity_id(), 59, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            baseViewHolder.setText(R.id.tv_commodity_sum, String.valueOf(Integer.parseInt((String) textView.getText()) - 1));
                            EventBus.getDefault().post(new Change(checkedCommodity, Change.Operation.SUBTRACT));
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + t.toString());
                            Toast.makeText(getContext(), "emmmmm............", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
