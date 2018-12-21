package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.lang.ref.WeakReference;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SlideActivity extends BaseViewActivity<SlidePresenter> implements SlideContract.View {
    private static final int WHAT_INIT_DATA = 0x0001;
    private static final int WHAT_RETRY_DATA = 0x0010;
    private MyHandler mHandler;

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

        mHandler = new MyHandler(this);
        mHandler.sendEmptyMessageDelayed(WHAT_INIT_DATA,2000);
    }


    @Override
    protected void onClickRetry() {
        super.onClickRetry();
        mHandler.sendEmptyMessage(WHAT_RETRY_DATA);
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


    private static class MyHandler extends Handler{
        final WeakReference<SlideActivity> weakReference;
        public MyHandler(SlideActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(weakReference.get() == null ){
                return;
            }
            switch (msg.what){
                case WHAT_INIT_DATA:
                    if(weakReference.get().isSafeMultipleStatusView()){
                        weakReference.get().mMultipleStatusView.showError();
                    }
                    break;
                case WHAT_RETRY_DATA:
                    if(weakReference.get().isSafeMultipleStatusView()){
                        weakReference.get().mMultipleStatusView.showContent();
                    }
                    break;
                default:
            }
        }
    }
}
