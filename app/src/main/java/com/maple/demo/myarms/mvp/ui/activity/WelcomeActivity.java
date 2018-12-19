package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.app.base.BaseFragment;
import com.maple.demo.myarms.di.component.DaggerWelcomeComponent;
import com.maple.demo.myarms.di.module.WelcomeModule;
import com.maple.demo.myarms.mvp.contract.WelcomeContract;
import com.maple.demo.myarms.mvp.presenter.WelcomePresenter;

import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.ui.adapter.WelcomePagerAdapter;
import com.maple.demo.myarms.mvp.ui.fragment.WelcomeFragment;
import com.maple.demo.myarms.utils.ToastUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.vp)
    ViewPager vp;

    private WelcomePagerAdapter mPagerAdapter;
    private List<BaseFragment> mFragmentList;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWelcomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .welcomeModule(new WelcomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_welcome; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(WelcomeFragment.getInstance(1));
        mFragmentList.add(WelcomeFragment.getInstance(2));
        mFragmentList.add(WelcomeFragment.getInstance(3));
        mPagerAdapter = new WelcomePagerAdapter(getSupportFragmentManager(),mFragmentList);
        vp.setAdapter(mPagerAdapter);
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
    protected boolean useToolBar() {
        return false;
    }

    @Override
    protected boolean useImmersionBar() {
        return false;
    }

    @Override
    public boolean useFragment() {
        return true;
    }
}
