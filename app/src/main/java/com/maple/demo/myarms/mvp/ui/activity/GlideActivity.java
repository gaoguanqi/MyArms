package com.maple.demo.myarms.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseActivity;

import butterknife.BindView;

public class GlideActivity extends BaseActivity {


    @BindView(R.id.iv_blur_image)
    ImageView ivBlurImage;
    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_glide;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAppComponent =  ArmsUtils.obtainAppComponentFromContext(this);
        mImageLoader = mAppComponent.imageLoader();
        String url = "http://www.cwl.gov.cn/upload/resources/image/2019/01/03/312177.jpg";
        mImageLoader.loadImage(this,
                ImageConfigImpl
                        .builder()
                        .blurValue(25)
                        .url(url)
                        .imageView(ivBlurImage)
                        .build());
    }
}
