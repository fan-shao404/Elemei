package com.example.elemei.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.elemei.R;
import com.example.elemei.view.util.StatusBarUtils;

public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView card;
    private View statusbar;
    private ImageView cover;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        statusbar = getView().findViewById(R.id.view_mine_statusbar);
        StatusBarUtils.setStatusBarHeight(getActivity(),statusbar);
        cover = getView().findViewById(R.id.iv_mine_cover);
        Glide.with(getActivity()).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2856753850,2250526222&fm=26&gp=0.jpg")
                .transform(new CircleCrop())
                .into(cover);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().findViewById(R.id.iv_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_card:
                break;
        }

    }
}
