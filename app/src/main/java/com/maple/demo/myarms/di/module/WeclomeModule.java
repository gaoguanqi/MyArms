package com.maple.demo.myarms.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.maple.demo.myarms.mvp.contract.WeclomeContract;
import com.maple.demo.myarms.mvp.model.WeclomeModel;


@Module
public class WeclomeModule {
    private WeclomeContract.View view;

    /**
     * 构建WeclomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WeclomeModule(WeclomeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WeclomeContract.View provideWeclomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WeclomeContract.Model provideWeclomeModel(WeclomeModel model) {
        return model;
    }
}