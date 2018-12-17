package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.HomeModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}