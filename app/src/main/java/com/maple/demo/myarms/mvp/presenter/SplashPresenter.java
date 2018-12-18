package com.maple.demo.myarms.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.blankj.utilcode.util.SPUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.maple.demo.myarms.app.config.AppContent;
import com.maple.demo.myarms.mvp.contract.SplashContract;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;
import com.maple.demo.myarms.mvp.ui.activity.WeclomeActivity;
import com.maple.demo.myarms.utils.LogUtils;
import com.maple.demo.myarms.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private List<String> mPermissionDesList;
    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void applyPermissions() {
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.applyPermissions(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //全部权限通过的回调
                LogUtils.logGGQ("onRequestPermissionSuccess");
                Intent intent;
                if(SPUtils.getInstance().getBoolean(AppContent.SaveInfoKey.HASWECLOME)){
                    intent = new Intent(mRootView.getActivity(), HomeActivity.class);
                }else{
                    intent = new Intent(mRootView.getActivity(), WeclomeActivity.class);
                }
                mRootView.launchActivity(intent);
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                //某一个权限拒绝的回调
                LogUtils.logGGQ("onRequestPermissionFailure");
                for (String permission : permissions) {
                    LogUtils.logGGQ(permission+"被拒绝了");
                }
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mPermissionDesList = new ArrayList<>();
                //拒绝后点击不再询问的回调
                LogUtils.logGGQ("onRequestPermissionFailureWithAskNeverAgain");
                for (String permission : permissions) {

                }
            }
        }, mRootView.getRxPermissions(), mErrorHandler);
    }
}
