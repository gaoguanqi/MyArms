package com.maple.demo.myarms.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jess.arms.mvp.IPresenter;

/**
 * author: gaogq
 * time: 2018/12/19 13:10
 * description: 懒加载的Fragment,主要用于状态栏、UI和数据的延迟加载
 *              如果不需要懒加载则继承{@link BaseViewFragment}
 */
public abstract class BaseLazyFragment<P extends IPresenter> extends BaseViewFragment<P> {
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     */
    protected boolean mIsPrepare;
    /**
     * 是否加载过view
     */
    protected boolean mHasLoadedView;
    /**
     * 是否加载过数据
     */
    protected boolean mHasLoadedData;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isLazyLoad()) {
            mIsPrepare = true;
        }else {
            if (useImmersionBar()){
                initImmersionBar();
            }
        }
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mIsVisible && useImmersionBar()) {
            initImmersionBar();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
            if (mIsPrepare && mIsVisible && useImmersionBar()) {
                initImmersionBar();
            }
            if (mIsPrepare && mIsVisible && !mHasLoadedView) {
                mHasLoadedView = true;
                lazyLoadView();
            }
            if (mIsPrepare && mIsVisible && !mHasLoadedData) {
                lazyLoadData();
            }
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * 是否懒加载
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 懒加载页面view
     */
    protected void lazyLoadView() {

    }

    /**
     * 懒加载页面网络数据
     */
    protected void lazyLoadData() {

    }

    /**
     * 页面不可见的时候回调
     */
    protected void onInvisible() {

    }

    /**
     * 页面可见的时候回调
     */
    protected void onVisible() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
            this.mImmersionBar = null;
        };
    }
}
