package com.example.elemei.view.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elemei.R;
import com.example.elemei.view.adapter.ShoppingCarAdapter;
import com.example.elemei.view.net.ShoppingCarCall;
import com.example.elemei.view.pojo.Change;
import com.example.elemei.view.pojo.CheckedCommodity;
import com.example.elemei.view.pojo.InsertBean;
import com.example.elemei.view.util.MyItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ShoppingCarCall shoppingCarCall = new ShoppingCarCall();
    private int customer_id;
    private int store_id;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public ShoppingCarPopupWindow(Context context, List<CheckedCommodity> data) {
        this.view = LayoutInflater.from(context).inflate(R.layout.popupwindow_shopping_car, null);
        setContentView(view);
        this.data = data;
        this.context = context;
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
            case R.id.tv_popup_empty:
                deleteAll();
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
        recyclerView.addItemDecoration(new MyItemDecoration(0, 12));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void deleteAll() {
        shoppingCarCall.deleteAll(store_id, customer_id, new Callback<InsertBean>() {
            @Override
            public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                if (response.body().getOkPacket() != null && response.body().getOkPacket().getAffectedRows() > 0) {
                    EventBus.getDefault().post(new Change(null, Change.Operation.EMPTY));
                    shoppingCarAdapter.setList(null);
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<InsertBean> call, Throwable t) {
                Toast.makeText(context, "emmmmm............", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
