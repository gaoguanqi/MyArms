package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.MainModule;

import com.jess.arms.di.scope.FragmentScope;
import com.maple.demo.myarms.mvp.ui.fragment.MainFragment;

@FragmentScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainFragment fragment);
}