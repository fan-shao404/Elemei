package com.example.elemei.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elemei.R;
import com.example.elemei.view.net.CustomerCall;
import com.example.elemei.view.pojo.CountBean;
import com.example.elemei.view.pojo.Customer;
import com.example.elemei.view.pojo.CustomerBean;
import com.example.elemei.view.util.Const;
import com.example.elemei.view.util.SPUtils;

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
    private Context context = LoginActivity.this;
    private CheckBox remeber;
    private CheckBox auto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login:
                doHuaWeiToastCovered();
                String phone = etphone.getText().toString();
                String password = etpassword.getText().toString();
                if (phone == null || phone.equals("")) {
                    Toast.makeText(LoginActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password == null || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                customerCall.select(phone, password, new Callback<CustomerBean>() {
                    @Override
                    public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {
                        if (response.body().isResult() && response.body().getResult().size() == 1) {
                            Customer customer = response.body().getResult().get(0);
                            Const.customer_id = customer.getId();
                            Const.nickname = customer.getName();
                            if (!SPUtils.contains(context, "tel")) {
                                SPUtils.put(context,"tel" ,customer.getPhone());
                            }
                            if (remeber.isChecked()) {
                                if (!SPUtils.contains(context, "pwd")) {
                                    SPUtils.put(context, "pwd", customer.getPassword());
                                }
                            } else {
                                SPUtils.remove(context, "pwd");
                            }
                            if (auto.isChecked()) {
                                if (!SPUtils.contains(context, "auto")) {
                                    SPUtils.put(context, "auto", true);
                                }
                            } else {
                                SPUtils.remove(context, "auto");
                            }
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CustomerBean> call, Throwable t) {
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

    private void doHuaWeiToastCovered() {
        if (Build.MANUFACTURER.equalsIgnoreCase("huawei")) {
            View focus = getWindow().getDecorView().findFocus();
            if (focus instanceof EditText) {
                EditText ed = (EditText) focus;
                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(ed.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void initView() {
        findViewById(R.id.iv_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.iv_cancel).setOnClickListener(this);
        remeber = findViewById(R.id.cb_remeber);
        auto = findViewById(R.id.cb_auto);
        remeber.setChecked(true);
        auto.setChecked(true);
        etphone = findViewById(R.id.et_phone);
        etpassword = findViewById(R.id.et_password);
        customerCall = new CustomerCall();
        if (SPUtils.contains(context, "tel")) {
            etphone.setText(SPUtils.get(context,"tel","").toString());
        }
        if (SPUtils.contains(context, "pwd")) {
            etpassword.setText(SPUtils.get(context,"pwd","").toString());
        }
        remeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!remeber.isChecked()) {
                    auto.setChecked(false);
                    auto.setClickable(false);
                } else {
                    auto.setClickable(true);
                }
            }
        });
    }
}
