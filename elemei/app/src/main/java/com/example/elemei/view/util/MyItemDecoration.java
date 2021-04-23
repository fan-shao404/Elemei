package com.example.elemei.view.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int top;
    private int bottom;
    private int left;
    private int right;

    public MyItemDecoration(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public MyItemDecoration(int top, int bottom, int left, int right) {
        this.bottom = bottom;
        this.top = top;
        this.left = left;
        this.right = right;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = bottom;
        outRect.top = top;
        outRect.left = left;
        outRect.right = right;
    }
}
