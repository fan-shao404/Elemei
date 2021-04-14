package com.example.elemei.view.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.elemei.R;
import com.example.elemei.view.activity.StoreActivity;
import com.example.elemei.view.pojo.Store;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class StoreAdapter extends BaseQuickAdapter<Store, BaseViewHolder> {

    public StoreAdapter(int layoutResId) {
        super(layoutResId);
    }

    public StoreAdapter(@Nullable List<Store> data) {
        super(R.layout.store, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Store store) {
        Glide.with(getContext()).load(store.getCover())
                .transform(new RoundedCorners(20))
                .placeholder(R.mipmap.image_error)
                .into((ImageView) baseViewHolder.getView(R.id.iv_store_cover));
        baseViewHolder.setText(R.id.tv_store_name,store.getName());
        baseViewHolder.setText(R.id.tv_store_score,store.getScore()+"分");
        baseViewHolder.setText(R.id.tv_store_start_send,"起送￥"+store.getStart_send());
        baseViewHolder.setText(R.id.tv_store_distribution,"配送￥"+store.getDistribution());
        baseViewHolder.findView(R.id.view_store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StoreActivity.class);
                intent.putExtra("id",store.getId());
                intent.putExtra("cover",store.getCover());
                intent.putExtra("name",store.getName());
                intent.putExtra("start_send",store.getStart_send());
                intent.putExtra("distribution",store.getDistribution());
                getContext().startActivity(intent);
            }
        });
    }
}
