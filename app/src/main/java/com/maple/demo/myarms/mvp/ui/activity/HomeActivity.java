package com.maple.demo.myarms.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseViewActivity;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerHomeComponent;
import com.maple.demo.myarms.di.module.HomeModule;
import com.maple.demo.myarms.mvp.contract.HomeContract;
import com.maple.demo.myarms.mvp.presenter.HomePresenter;
import com.maple.demo.myarms.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeActivity extends BaseViewActivity<HomePresenter> implements HomeContract.View, ToolbarConfig.OnToolbarLitener {
    private long lastBackPressedMillis;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.update();
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
        ToastUtil.showToast(message);
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
    protected ToolbarConfig getToolbarConfig() {
        return ToolbarConfig.builder()
                .setHasBack(false)
                .setTitle("首页")
                .setToolbarLitener(this)
                .build();
    }

    @Override
    protected boolean useNetReceiver() {
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (lastBackPressedMillis + 2000 > System.currentTimeMillis()) {
                //moveTaskToBack(true);
                killMyself();
            } else {
                lastBackPressedMillis = System.currentTimeMillis();
                showMessage(getString(R.string.app_exit));
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
