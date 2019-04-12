package com.maple.demo.myarms.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.maple.demo.myarms.mvp.contract.ViewFlipperContract;
import com.maple.demo.myarms.mvp.model.ViewFlipperModel;


@Module
public class ViewFlipperModule {
    private ViewFlipperContract.View view;

    /**
     * 构建ViewFlipperModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ViewFlipperModule(ViewFlipperContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ViewFlipperContract.View provideViewFlipperView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ViewFlipperContract.Model provideViewFlipperModel(ViewFlipperModel model) {
        return model;
    }
}