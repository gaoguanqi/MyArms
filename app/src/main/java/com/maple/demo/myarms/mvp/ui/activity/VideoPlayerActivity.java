package com.maple.demo.myarms.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseViewActivity;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerVideoPlayerComponent;
import com.maple.demo.myarms.di.module.VideoPlayerModule;
import com.maple.demo.myarms.mvp.contract.VideoPlayerContract;
import com.maple.demo.myarms.mvp.model.entity.PlayerEntity;
import com.maple.demo.myarms.mvp.presenter.VideoPlayerPresenter;
import com.maple.demo.myarms.mvp.ui.adapter.PlayerAdapter;
import com.maple.demo.myarms.utils.LogUtils;
import com.maple.demo.myarms.utils.ToastUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class VideoPlayerActivity extends BaseViewActivity<VideoPlayerPresenter> implements VideoPlayerContract.View {

    @BindView(R.id.player_recycler)
    RecyclerView recycler;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    private PlayerAdapter mAdapter;
    private List<PlayerEntity> mData;
    private PlayerEntity mEntity;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerVideoPlayerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .videoPlayerModule(new VideoPlayerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_video_player; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    protected boolean usePortrait() {
        return false;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        recycler.setNestedScrollingEnabled(false);
        recycler.setLayoutManager(mLinearLayoutManager);
        mData = new ArrayList<>();
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            // 在 Adapter的 onBindViewHolder 之后执行
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                //return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                showMessage("滑到到 " + targetPos + "位置");
                return targetPos;
            }

            // 在 Adapter的 onBindViewHolder 之后执行
            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                //return super.findSnapView(layoutManager);
                View view = super.findSnapView(layoutManager);
                return view;
            }
        };
        snapHelper.attachToRecyclerView(recycler);

        mEntity = new PlayerEntity();
        mEntity.setTitle("1");
        mEntity.setUrl("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
        mEntity.setImage("http://img5.imgtn.bdimg.com/it/u=517297135,2728824910&fm=26&gp=0.jpg");
        mData.add(mEntity);

        mEntity = new PlayerEntity();
        mEntity.setTitle("2");
        mEntity.setUrl("http://wdquan-space.b0.upaiyun.com/VIDEO/2018/11/22/ae0645396048_hls_time10.m3u8");
        mEntity.setImage("http://img5.imgtn.bdimg.com/it/u=517297135,2728824910&fm=26&gp=0.jpg");
        mData.add(mEntity);


        if(isSafeMultipleStatusView()){
            mMultipleStatusView.showContent();
        }


        mAdapter = new PlayerAdapter(this,mData);
        recycler.setAdapter(mAdapter);


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ToastUtil.showToast(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected ToolbarConfig getToolbarConfig() {
        return ToolbarConfig.builder()
                .setTitle("视频播放")
                .setToolbarLitener(this)
                .build();
    }



    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
