package com.example.elemei.view.util;

import android.util.Log;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Date:2021/4/8
 * Author:fanshaofeng
 * 折叠布局的折叠与展开监听
 */
public abstract class AppBarStateChangeListenner implements AppBarLayout.OnOffsetChangedListener {
        public enum State {
            EXPANDED,
            COLLAPSED,
            IDLE
        }
        private State mCurrentState = State.EXPANDED;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.e("TAG", "onOffsetChanged: "+mCurrentState);
        if (verticalOffset == 0){
            if (mCurrentState != State.EXPANDED){
                onStateChanged(appBarLayout,State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        }else if (verticalOffset > 60){
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout,State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        }else {
            if (mCurrentState != State.IDLE){
                onStateChanged(appBarLayout,State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
