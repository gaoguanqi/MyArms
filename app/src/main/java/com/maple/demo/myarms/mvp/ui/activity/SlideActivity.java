package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseViewActivity;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerSlideComponent;
import com.maple.demo.myarms.di.module.SlideModule;
import com.maple.demo.myarms.mvp.contract.SlideContract;
import com.maple.demo.myarms.mvp.presenter.SlidePresenter;
import com.maple.demo.myarms.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SlideActivity extends BaseViewActivity<SlidePresenter> implements SlideContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSlideComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .slideModule(new SlideModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_slide; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
                .setTitle("侧滑")
                .setToolbarLitener(this)
                .build();
    }
}
