package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseViewActivity;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerRegisteComponent;
import com.maple.demo.myarms.di.module.RegisteModule;
import com.maple.demo.myarms.mvp.contract.RegisteContract;
import com.maple.demo.myarms.mvp.model.api.ApiService;
import com.maple.demo.myarms.mvp.presenter.RegistePresenter;
import com.maple.demo.myarms.utils.LogUtils;
import com.maple.demo.myarms.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class RegisteActivity extends BaseViewActivity<RegistePresenter> implements RegisteContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegisteComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .registeModule(new RegisteModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_registe; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        String id = "10011";
         mPresenter.getTest(id);
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
                .setTitle("注册")
                .setToolbarLitener(this)
                .build();
    }

    @Override
    protected void onToolbarBack() {
        killMyself();
    }

    @Override
    protected boolean useMultipleStatusView() {
        return false;
    }
}
