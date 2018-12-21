package com.maple.demo.myarms.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.maple.demo.myarms.mvp.contract.RegisteContract;
import com.maple.demo.myarms.mvp.model.RegisteModel;


@Module
public class RegisteModule {
    private RegisteContract.View view;

    /**
     * 构建RegisteModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public RegisteModule(RegisteContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RegisteContract.View provideRegisteView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RegisteContract.Model provideRegisteModel(RegisteModel model) {
        return model;
    }
}