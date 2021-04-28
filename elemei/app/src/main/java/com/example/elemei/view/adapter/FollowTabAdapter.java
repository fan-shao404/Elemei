package com.example.elemei.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.elemei.view.fragment.FollowBrandFragment;
import com.example.elemei.view.fragment.FollowStoreFragment;

/**
 * Date:2021/4/24
 * Author:fanshaofeng
 */
public class FollowTabAdapter extends FragmentStateAdapter {


    public FollowTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FollowTabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FollowTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FollowStoreFragment();
        } else {
            return new FollowBrandFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
