package com.example.elemei.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.pojo.CheckedCommodity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Date:2021/4/21
 * Author:fanshaofeng
 */
public class ShoppingCommodityAdapter extends BaseQuickAdapter<CheckedCommodity, BaseViewHolder> {

    public ShoppingCommodityAdapter() {
        super(R.layout.shopping_commodity);
    }

    public ShoppingCommodityAdapter(int layoutResId, @Nullable List<CheckedCommodity> data) {
        super(layoutResId, data);
    }

    public ShoppingCommodityAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CheckedCommodity checkedCommmodity) {
        baseViewHolder.setText(R.id.tv_commodity_name, checkedCommmodity.getName());
        baseViewHolder.setText(R.id.tv_commodity_price, "￥" + checkedCommmodity.getNumber());
        baseViewHolder.setText(R.id.tv_commodity_sum, "" + checkedCommmodity.getNumber());
        ImageView cover = baseViewHolder.getView(R.id.iv_cover);
        Glide.with(getContext()).load(checkedCommmodity.getCover())
                .transform(new RoundedCorners(10))
                .into(cover);
    }
}
