package com.example.elemei.view.adapter;

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

    }
}
