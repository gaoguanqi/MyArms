package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.WeclomeModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.WeclomeActivity;

@ActivityScope
@Component(modules = WeclomeModule.class, dependencies = AppComponent.class)
public interface WeclomeComponent {
    void inject(WeclomeActivity activity);
}