package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.WebModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.WebActivity;

@ActivityScope
@Component(modules = WebModule.class, dependencies = AppComponent.class)
public interface WebComponent {
    void inject(WebActivity activity);
}