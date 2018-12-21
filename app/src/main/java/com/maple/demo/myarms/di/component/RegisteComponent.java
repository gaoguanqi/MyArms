package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.RegisteModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.RegisteActivity;

@ActivityScope
@Component(modules = RegisteModule.class, dependencies = AppComponent.class)
public interface RegisteComponent {
    void inject(RegisteActivity activity);
}