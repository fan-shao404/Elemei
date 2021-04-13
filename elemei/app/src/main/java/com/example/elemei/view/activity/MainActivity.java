package com.example.elemei.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.elemei.R;
import com.example.elemei.view.fragment.MainFragment;
import com.example.elemei.view.fragment.MineFragment;
import com.example.elemei.view.fragment.OrderFragment;
import com.example.elemei.view.fragment.VideoFragment;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentTransaction transaction;
    private MainFragment mainFragment;
    private VideoFragment videoFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;
    private Boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        findViewById(R.id.v_home).setOnClickListener(this);
        findViewById(R.id.v_video).setOnClickListener(this);
        findViewById(R.id.v_order).setOnClickListener(this);
        findViewById(R.id.v_my).setOnClickListener(this);
        switchFragment(R.id.v_home);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.v_home :
                switchFragment(R.id.v_home);
                break;
            case R.id.v_video:
                switchFragment(R.id.v_video);
                break;
            case R.id.v_order:
                switchFragment(R.id.v_order);
                break;
            case R.id.v_my:
                switchFragment(R.id.v_my);
                break;
        }
    }

    /**
     * 切换fragment(懒加载)
     * @param id
     */
    private void switchFragment(int id) {
        transaction = getFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (id){
            case R.id.v_home:
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.fl_main,mainFragment);
                }else {
                    transaction.show(mainFragment);
                }
                break;
            case R.id.v_video:
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    transaction.add(R.id.fl_main,videoFragment);
                }else {
                    transaction.show(videoFragment);
                }
                break;
            case R.id.v_order:
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.fl_main,orderFragment);
                }else {
                    transaction.show(orderFragment);
                }
                break;
            case R.id.v_my:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.fl_main,mineFragment);
                }else {
                    transaction.show(mineFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有fragment
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (videoFragment != null) {
            transaction.hide(videoFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }
}