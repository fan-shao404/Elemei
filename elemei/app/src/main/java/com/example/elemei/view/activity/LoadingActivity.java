package com.example.elemei.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elemei.R;
import com.example.elemei.view.net.CustomerCall;
import com.example.elemei.view.pojo.Customer;
import com.example.elemei.view.pojo.CustomerBean;
import com.example.elemei.view.util.Const;
import com.example.elemei.view.util.SPUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/3/29
 * Author:fanshaofeng
 */
public class LoadingActivity extends AppCompatActivity {

    private ImageView advertisement;
    private TextView time;
    private int countdown = 3;

    private Handler handler;

    {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0) {
                    advertisement.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
                if (msg.what == 1) {
                    countdown--;
                    if (countdown >= 0) {
                        time.setText(countdown + "跳过广告");
                        Log.e("TAG", "handleMessage: 1");
                        handler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                        finish();
                    }
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        advertisement = findViewById(R.id.iv_advertisement);
        time = findViewById(R.id.tv_time);
        handler.sendEmptyMessageDelayed(0, 1000);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeMessages(1);
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }
        });
        CustomerCall customerCall = new CustomerCall();
        if (SPUtils.contains(LoadingActivity.this, "auto")) {
            String tel = (String) SPUtils.get(LoadingActivity.this, "tel", "");
            String pwd = (String) SPUtils.get(LoadingActivity.this, "pwd", "");
            customerCall.select(tel, pwd, new Callback<CustomerBean>() {
                @Override
                public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {
                    if (response.body().isResult() && response.body().getResult().size() == 1) {
                        Customer customer = response.body().getResult().get(0);
                        Const.customer_id = customer.getId();
                        Const.nickname = customer.getName();
                    } else {
                        Toast.makeText(LoadingActivity.this, "系统异常", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CustomerBean> call, Throwable t) {
                    Toast.makeText(LoadingActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
