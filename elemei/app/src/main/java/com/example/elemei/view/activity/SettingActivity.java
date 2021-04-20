package com.example.elemei.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elemei.R;
import com.example.elemei.view.util.Const;
import com.example.elemei.view.util.SPUtils;

/**
 * Date:2021/4/20
 * Author:fanshaofeng
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login_out;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Const.customer_id != 0) {
            login_out.setVisibility(View.VISIBLE);
        } else {
            login_out.setVisibility(View.INVISIBLE);
        }
    }

    public void initView() {
        login_out = findViewById(R.id.tv_login_out);
        login_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_out:
                if (SPUtils.contains(SettingActivity.this, "auto")) {
                    SPUtils.remove(SettingActivity.this, "auto");
                }
                Const.customer_id = 0;
                Const.nickname = "立即登录";
                finish();
                break;
            default:
                break;
        }
    }
}
