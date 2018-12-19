package com.maple.demo.myarms.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.maple.demo.myarms.mvp.contract.MainContract;
import com.maple.demo.myarms.mvp.model.MainModel;


@Module
public class MainModule {
    private MainContract.View view;

    /**
     * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }
}