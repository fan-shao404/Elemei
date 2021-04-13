package com.example.elemei.view.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elemei.R;
import com.example.elemei.view.Adapter.StoreAdapter;
import com.example.elemei.view.activity.LoginActivity;
import com.example.elemei.view.event.Login;
import com.example.elemei.view.net.StoreCall;
import com.example.elemei.view.pojo.Store;
import com.example.elemei.view.pojo.StoreBean;
import com.example.elemei.view.util.AppBarStateChangeListenner;
import com.example.elemei.view.util.MyItemDecoration;
import com.google.android.material.appbar.AppBarLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements View.OnClickListener {

    private boolean login;
    private View view;
    private RecyclerView recyclerView;
    private StoreCall storeCall = new StoreCall();
    private List<Store> stores = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }


    @Override
    public void onResume() {
//        StatusBarUtils.setWindowStatusBarColor(getActivity(),R.color.blue);
        if (login) {
            view.setVisibility(View.INVISIBLE);
        }
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.tv_login).setOnClickListener(this);
        getView().findViewById(R.id.iv_message).setOnClickListener(this);
        view = getView().findViewById(R.id.view_login);
        EventBus.getDefault().register(this);
        View statusbar = getView().findViewById(R.id.view_statusbar);
        setStatusBarHeight(statusbar);
        AppBarLayout appBarLayout = getView().findViewById(R.id.abl_main);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListenner() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.IDLE){
                    statusbar.setBackgroundColor(getResources().getColor(R.color.blue_trans));
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.blue_trans));
                }
                if (state == State.EXPANDED){
                    statusbar.setBackgroundColor(getResources().getColor(R.color.blue));
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                }
                if (state == State.COLLAPSED){
                    statusbar.setBackgroundColor(getResources().getColor(R.color.white));
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        storeCall.selectAll(new Callback<StoreBean>() {
            @Override
            public void onResponse(Call<StoreBean> call, Response<StoreBean> response) {
                Log.e("TAG", "onResponse: "+response.code()+response.body().getResult().size());
                if (response.body().isResult && response.body().getResult()!=null){
                    stores = response.body().getResult();
                    recyclerView = getView().findViewById(R.id.rv_fragment_main_store);
                    recyclerView.setAdapter(new StoreAdapter(stores));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.addItemDecoration(new MyItemDecoration());
                }
            }

            @Override
            public void onFailure(Call<StoreBean> call, Throwable t) {
                Toast.makeText(getActivity(),"系统错误",Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_message:
                if (!login) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Toast.makeText(getActivity(),"功能还在开发中",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.view_search:
                if (!login) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Toast.makeText(getActivity(),"功能还在开发",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Subscribe
    public void login(Login login) {
        this.login = login.isLogin();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void setStatusBarHeight(View view){
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = statusBarHeight;
        view.setLayoutParams(layoutParams);
    }
}
