package com.example.elemei.view.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elemei.R;
import com.example.elemei.view.adapter.ShoppingCarAdapter;
import com.example.elemei.view.pojo.CheckedCommodity;
import com.example.elemei.view.util.MyItemDecoration;

import java.util.List;

/**
 * Date:2021/4/18
 * Author:fanshaofeng
 */
public class ShoppingCarPopupWindow extends PopupWindow implements View.OnClickListener {

    private Context context;
    private View view;
    private View dismess;
    private TextView tv_empty;
    private ImageView iv_empty;
    private RecyclerView recyclerView;
    private List<CheckedCommodity> data;
    private ShoppingCarAdapter shoppingCarAdapter;

    public ShoppingCarPopupWindow(Context context, List<CheckedCommodity> data) {
        this.view = LayoutInflater.from(context).inflate(R.layout.popupwindow_shopping_car,null);
        setContentView(view);
        this.data = data;
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setClippingEnabled(false);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_dismess:
                dismiss();
                break;
        }
    }

    public void initView() {
        dismess = view.findViewById(R.id.view_dismess);
        dismess.setOnClickListener(this);
        tv_empty = view.findViewById(R.id.tv_popup_empty);
        tv_empty.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.rv_popup_shopping_car);
        shoppingCarAdapter = new ShoppingCarAdapter(data);
        recyclerView.setAdapter(shoppingCarAdapter);
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
