package com.example.elemei.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elemei.R;
import com.example.elemei.view.adapter.FollowStoreAdapter;
import com.example.elemei.view.pojo.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Date:2021/4/24
 * Author:fanshaofeng
 * Email:1614110190@qq.com
 */
public class FollowStoreFragment extends Fragment {

    private RecyclerView recyclerView;
    private FollowStoreAdapter followStoreAdapter;
    private List<Store> stores = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_store, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getData();
    }

    public void initView() {
        recyclerView = getView().findViewById(R.id.rv_fragment_follow_store);
    }

    public void getData() {
        for (int i=0;i<10;i++) {
            stores.add(new Store());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        followStoreAdapter = new FollowStoreAdapter(stores);
        recyclerView.setAdapter(followStoreAdapter);
    }
}
