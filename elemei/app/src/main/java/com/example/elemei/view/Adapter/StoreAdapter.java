package com.example.elemei.view.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.pojo.Store;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class StoreAdapter extends BaseQuickAdapter<Store, BaseViewHolder> {

    public StoreAdapter(@Nullable List<Store> data) {
        super(R.layout.store, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Store store) {
        baseViewHolder.setText(R.id.tv_store_name,store.getName());
    }
}
