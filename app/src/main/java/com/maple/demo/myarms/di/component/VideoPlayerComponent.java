package com.maple.demo.myarms.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.maple.demo.myarms.di.module.VideoPlayerModule;

import com.jess.arms.di.scope.ActivityScope;
import com.maple.demo.myarms.mvp.ui.activity.VideoPlayerActivity;

@ActivityScope
@Component(modules = VideoPlayerModule.class, dependencies = AppComponent.class)
public interface VideoPlayerComponent {
    void inject(VideoPlayerActivity activity);
}