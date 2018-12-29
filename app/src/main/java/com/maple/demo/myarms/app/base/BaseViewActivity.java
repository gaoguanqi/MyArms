package com.maple.demo.myarms.app.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.mvp.IPresenter;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.utils.LogUtils;
import com.maple.demo.myarms.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/17 13:13
 * description: 封装了多状态视图
 */
public abstract class BaseViewActivity<T extends IPresenter> extends BaseActivity<T> implements View.OnClickListener {
    protected MultipleStatusView mMultipleStatusView;
    protected ViewStub viewStubTitle;
    @Override
    protected void setContentView(@Nullable Bundle savedInstanceState) {
        int layoutResID = initView(savedInstanceState);
        //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
        if (layoutResID != 0) {
            setContentView(R.layout.layout_base);
            mMultipleStatusView =  (MultipleStatusView)findViewById(R.id.multiple_status_view);
            mMultipleStatusView.showContent(layoutResID,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置重试视图点击事件
            mMultipleStatusView.setOnRetryClickListener(this);
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
            if(useToolBar()){
                 viewStubTitle = findViewById(R.id.view_stub_title);
                if (viewStubTitle != null) {
                    viewStubTitle.inflate();
                    initToolbar();
                }
            }
            if(useImmersionBar()){
                initImmersionBar();
            }

            if(useMultipleStatusView()){
                mMultipleStatusView.showLoading();
            }else{
                mMultipleStatusView.showContent();
            }
        }
    }


    protected boolean useMultipleStatusView(){
        return true;
    }

    protected boolean isSafeMultipleStatusView(){
        return mMultipleStatusView != null ? true:false;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empty_retry_view:
            case R.id.error_retry_view:
            case R.id.no_network_retry_view:
                if(isSafeMultipleStatusView()){
                    onClickRetry();
                }
                break;
             default:
        }
    }

    protected void onClickRetry() {
        ToastUtil.showToast("刷新数据");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(useImmersionBar()){
            LogUtils.logGGQ("屏幕旋转："+newConfig.orientation);

            if(newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) { //屏幕布局模式为横排时
                ToastUtil.showToast("横屏");
                mImmersionBar = ImmersionBar.with(this);
                mImmersionBar.statusBarColor(R.color.trans);
                mImmersionBar.fitsSystemWindows(false);
                mImmersionBar.init();
                if(useToolBar()){
                    if(viewStubTitle != null){
                        viewStubTitle.setVisibility(View.GONE);
                    }
                }
            }else if(newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){ //屏幕布局模式为竖排时
                ToastUtil.showToast("竖屏");
                mImmersionBar = ImmersionBar.with(this);
                mImmersionBar.statusBarColor(R.color.color_status);
                mImmersionBar.fitsSystemWindows(true);
                mImmersionBar.init();
                if(useToolBar()){
                    if(viewStubTitle != null){
                        viewStubTitle.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
