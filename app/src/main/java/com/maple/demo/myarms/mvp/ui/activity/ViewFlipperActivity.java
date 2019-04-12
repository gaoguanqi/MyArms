package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.di.component.DaggerViewFlipperComponent;
import com.maple.demo.myarms.di.module.ViewFlipperModule;
import com.maple.demo.myarms.mvp.contract.ViewFlipperContract;
import com.maple.demo.myarms.mvp.presenter.ViewFlipperPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ViewFlipperActivity extends BaseActivity<ViewFlipperPresenter> implements ViewFlipperContract.View {

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    private boolean isNext;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerViewFlipperComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .viewFlipperModule(new ViewFlipperModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_view_flipper; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        String url = "http://120.131.8.61/f/img/videoandlive/20190125/97ab4d5f8ee64e4e9453190c65bc44c1.png";
        ArmsUtils.obtainAppComponentFromContext(this).imageLoader()
        .loadImage(this,
                ImageConfigImpl
                        .builder()
                        .url(url)
                        .imageView(ivImage)
                        .build());
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


    @OnClick(R.id.btn_change)
    public void onViewClicked() {

        if(isNext){
            viewFlipper.showNext();
        }else {
            viewFlipper.showPrevious();
        }
    }
}
