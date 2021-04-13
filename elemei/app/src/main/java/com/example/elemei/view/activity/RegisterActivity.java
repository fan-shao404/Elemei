package com.example.elemei.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elemei.R;
import com.example.elemei.view.net.CustomerCall;
import com.example.elemei.view.pojo.Customer;
import com.example.elemei.view.pojo.CustomerBean;
import com.example.elemei.view.pojo.InsertBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Date:2021/4/9
 * Author:fanshaofeng
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomerCall customerCall;
    private TextView test;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.tv_back_login).setOnClickListener(this);
        findViewById(R.id.tv_register_account).setOnClickListener(this);
        test = findViewById(R.id.tv_test);
        customerCall = new CustomerCall();
        etPhone = findViewById(R.id.et_register_phone);
        etEmail = findViewById(R.id.et_register_email);
        etPassword = findViewById(R.id.et_register_password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back_login:
                finish();
                break;
            case R.id.tv_register_account:
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (phone == null || phone.equals("")){
                    Toast.makeText(RegisterActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password == null || password.equals("")){
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                customerCall.addCustomer(phone, email, password, new Callback<InsertBean>() {
                    @Override
                    public void onResponse(Call<InsertBean> call, Response<InsertBean> response) {
                        if (response.body().getOkPacket().getInsertId() != 0){
                            Toast.makeText(RegisterActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertBean> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"创建失败",Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }
}
