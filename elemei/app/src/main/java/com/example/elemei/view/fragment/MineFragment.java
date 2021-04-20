package com.example.elemei.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.elemei.R;
import com.example.elemei.view.activity.LoginActivity;
import com.example.elemei.view.activity.SettingActivity;
import com.example.elemei.view.util.Const;
import com.example.elemei.view.util.SPUtils;
import com.example.elemei.view.util.StatusBarUtils;

public class MineFragment extends Fragment implements View.OnClickListener {

    private View statusbar;
    private ImageView cover;
    private TextView nickname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Const.customer_id != 0) {
            nickname.setText(Const.nickname);
        } else {
            nickname.setText(Const.nickname);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_mine_nickname:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            default:
                break;
        }

    }
    public void initView() {
        statusbar = getView().findViewById(R.id.view_mine_statusbar);
        StatusBarUtils.setStatusBarHeight(getActivity(),statusbar);
        getView().findViewById(R.id.iv_setting).setOnClickListener(this);
        cover = getView().findViewById(R.id.iv_mine_cover);
        Glide.with(getActivity()).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2856753850,2250526222&fm=26&gp=0.jpg")
                .transform(new CircleCrop())
                .into(cover);
        nickname = getView().findViewById(R.id.tv_mine_nickname);
        nickname.setOnClickListener(this);
    }
}
