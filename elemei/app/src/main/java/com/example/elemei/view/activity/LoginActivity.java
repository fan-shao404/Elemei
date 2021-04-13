package com.example.elemei.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elemei.R;
import com.example.elemei.view.event.Login;
import com.example.elemei.view.net.CustomerCall;
import com.example.elemei.view.pojo.CountBean;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/6
 * Author:fanshaofeng
 * 登录的界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etphone;
    private EditText etpassword;
    private CustomerCall customerCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.iv_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.iv_cancel).setOnClickListener(this);
        etphone = findViewById(R.id.et_phone);
        etpassword = findViewById(R.id.et_password);
        customerCall = new CustomerCall();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login:
                String phone = etphone.getText().toString();
                String password = etpassword.getText().toString();
                customerCall.count(phone, password, new Callback<CountBean>() {
                    @Override
                    public void onResponse(Call<CountBean> call, Response<CountBean> response) {
                        Log.e("TAG", "onResponse: " + response.code() + response.body().getResult().get(0).getCount().toString());
                        if (response.body().isResult() && response.body().getResult().get(0).getCount() == 1) {
                            EventBus.getDefault().post(new Login(true));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CountBean> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.iv_cancel:
                finish();
                break;
        }
    }
}
