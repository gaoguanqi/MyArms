package com.maple.demo.myarms.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.maple.demo.myarms.mvp.contract.WelcomeContract;
import com.maple.demo.myarms.mvp.model.WelcomeModel;


@Module
public class WelcomeModule {
    private WelcomeContract.View view;

    /**
     * 构建WelcomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WelcomeModule(WelcomeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WelcomeContract.View provideWelcomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WelcomeContract.Model provideWelcomeModel(WelcomeModel model) {
        return model;
    }
}