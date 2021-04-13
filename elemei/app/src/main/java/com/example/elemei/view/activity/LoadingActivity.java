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
                        startActivity(new Intent(LoadingActivity.this,MainActivity.class));
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
        handler.sendEmptyMessageDelayed(0, 2000);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeMessages(1);
                startActivity(new Intent(LoadingActivity.this,MainActivity.class));
                finish();
            }
        });
    }

}
