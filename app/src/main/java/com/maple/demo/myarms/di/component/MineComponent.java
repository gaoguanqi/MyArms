package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.MineModule;

import com.jess.arms.di.scope.FragmentScope;
import com.maple.demo.myarms.mvp.ui.fragment.MineFragment;

@FragmentScope
@Component(modules = MineModule.class, dependencies = AppComponent.class)
public interface MineComponent {
    void inject(MineFragment fragment);
}