package com.maple.demo.myarms.di.module;

import android.support.v7.widget.LinearLayoutManager;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.jess.arms.di.scope.FragmentScope;
import com.maple.demo.myarms.mvp.contract.VideoPlayerContract;
import com.maple.demo.myarms.mvp.model.VideoPlayerModel;
import com.maple.demo.myarms.mvp.ui.adapter.PlayerAdapter;


@Module
public class VideoPlayerModule {
    private VideoPlayerContract.View view;

    /**
     * 构建VideoPlayerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public VideoPlayerModule(VideoPlayerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    VideoPlayerContract.View provideVideoPlayerView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    VideoPlayerContract.Model provideVideoPlayerModel(VideoPlayerModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(view.getActivity());
    }
}