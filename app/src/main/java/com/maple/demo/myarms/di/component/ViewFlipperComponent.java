package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.ViewFlipperModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.ViewFlipperActivity;

@ActivityScope
@Component(modules = ViewFlipperModule.class, dependencies = AppComponent.class)
public interface ViewFlipperComponent {
    void inject(ViewFlipperActivity activity);
}