package com.maple.demo.myarms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
