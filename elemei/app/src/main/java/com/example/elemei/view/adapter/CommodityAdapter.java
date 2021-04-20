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
import com.example.elemei.view.pojo.Commodity;
import com.example.elemei.view.pojo.InsertBean;
import com.example.elemei.view.util.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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

    public CommodityAdapter() {
        super(R.layout.commodity);
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
                    Log.e("TAG", "convert: " + checkedCommodity.getName() + checkedCommodity.getNumber());
                }
            }
        }
        TextView textView = baseViewHolder.getView(R.id.tv_commodity_sum);
        Log.e("TAG", "update: sum");
        //向购物车-1
        baseViewHolder.getView(R.id.iv_commodity_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.customer_id == 0 ) {
                    Toast.makeText(getContext(),"登录后开启更多功能，测试账号tel：1，pwd：6",Toast.LENGTH_LONG).show();
                    return;
                }
                if (Integer.valueOf(String.valueOf(textView.getText())) == 0) {
                    baseViewHolder.getView(R.id.iv_commodity_subtract).setClickable(false);
                } else if (Integer.valueOf(String.valueOf(textView.getText())) == 1) {
                    shoppingCarCall.delete(commodity.getId(), Const.customer_id, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            baseViewHolder.getView(R.id.iv_commodity_subtract).setClickable(false);
                            CheckedCommodity checkedCommodity = new CheckedCommodity(commodity.getId(), commodity.getCover(), commodity.getName(), commodity.getPrice(), 1);
                            EventBus.getDefault().post(new Change(checkedCommodity, Change.Operation.DELETE));
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Toast.makeText(getContext(), "emmmmm............", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    shoppingCarCall.subtract(commodity.getId(), Const.customer_id, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            Log.e("TAG", "onResponse: " + response.body().toString());
                            CheckedCommodity checkedCommodity = new CheckedCommodity(commodity.getId(), commodity.getCover(), commodity.getName(), commodity.getPrice(), 1);
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
        //从购物车中+1
        baseViewHolder.getView(R.id.iv_commodity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.customer_id == 0 ) {
                    Toast.makeText(getContext(),"登录后开启更多功能，测试账号tel：1，pwd：6",Toast.LENGTH_LONG).show();
                    return;
                }
                Log.e("TAG", "onClick: ");
                if (Integer.valueOf(String.valueOf(textView.getText())) == 0) {
                    shoppingCarCall.insert(commodity.getId(), commodity.getStore_id(), Const.customer_id, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            Log.e("TAG", "onResponse:-----1 ");
                            baseViewHolder.getView(R.id.iv_commodity_subtract).setClickable(true);
                            CheckedCommodity checkedCommodity = new CheckedCommodity(commodity.getId(), commodity.getCover(), commodity.getName(), commodity.getPrice(), 1);
                            EventBus.getDefault().post(new Change(checkedCommodity, Change.Operation.INSERT));
                            Log.e("TAG", "onResponse:-----2 ");
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + "insert");
                            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    shoppingCarCall.add(commodity.getId(), Const.customer_id, new Callback<InsertBean>() {
                        @Override
                        public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                            Log.e("TAG", "onResponse: " + response.body().toString());
                            CheckedCommodity checkedCommodity = new CheckedCommodity(commodity.getId(), commodity.getCover(), commodity.getName(), commodity.getPrice(), 1);
                            EventBus.getDefault().post(new Change(checkedCommodity, Change.Operation.ADD));
                        }

                        @Override
                        public void onFailure(Call<InsertBean> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + t.toString());
                            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position, @NotNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        TextView textView = holder.getView(R.id.tv_commodity_sum);
        if (payloads != null && payloads.size() > 0) {
            Log.e("TAG", "onBindViewHolder: " + payloads.toString());
            if ((int) (payloads.get(0)) == 1) {
                Log.e("TAG", "onResponse:----- 1");
                holder.setText(R.id.tv_commodity_sum, String.valueOf(Integer.parseInt((String) textView.getText()) + 1));
            } else if ((int) (payloads.get(0)) == 2) {
                Log.e("TAG", "update: 2");
                holder.setText(R.id.tv_commodity_sum, String.valueOf(Integer.parseInt((String) textView.getText()) - 1));
            } else {
                Log.e("TAG", "update: 3");
                holder.setText(R.id.tv_commodity_sum, "0");
            }
        }
    }

}
