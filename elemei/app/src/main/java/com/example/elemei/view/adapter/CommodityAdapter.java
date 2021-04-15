package com.example.elemei.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.pojo.Commodity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Date:2021/4/14
 * Author:fanshaofeng
 */
public class CommodityAdapter extends BaseQuickAdapter<Commodity, BaseViewHolder> {

    public CommodityAdapter(@Nullable List<Commodity> data) {
        super(R.layout.commodity, data);
    }

    public CommodityAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Commodity commodity) {
        baseViewHolder.setText(R.id.tv_commodity_name,commodity.getName());
        ImageView cover = baseViewHolder.findView(R.id.iv_commodity_cover);
        Glide.with(getContext()).load(commodity.getCover())
                .transform(new RoundedCorners(15))
                .into(cover);
        baseViewHolder.setText(R.id.tv_commodity_price,"￥"+commodity.getPrice());
    }
}
