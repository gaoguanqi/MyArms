package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.di.component.DaggerWeclomeComponent;
import com.maple.demo.myarms.di.module.WeclomeModule;
import com.maple.demo.myarms.mvp.contract.WeclomeContract;
import com.maple.demo.myarms.mvp.presenter.WeclomePresenter;

import com.maple.demo.myarms.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WeclomeActivity extends BaseActivity<WeclomePresenter> implements WeclomeContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWeclomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .weclomeModule(new WeclomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_weclome; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected boolean useToolBar() {
        return false;
    }

    @Override
    protected boolean useImmersionBar() {
        return false;
    }
}
