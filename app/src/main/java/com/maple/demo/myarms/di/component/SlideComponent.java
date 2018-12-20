package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.SlideModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.SlideActivity;

@ActivityScope
@Component(modules = SlideModule.class, dependencies = AppComponent.class)
public interface SlideComponent {
    void inject(SlideActivity activity);
}