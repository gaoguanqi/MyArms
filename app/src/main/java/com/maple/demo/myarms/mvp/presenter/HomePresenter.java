package com.maple.demo.myarms.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.mvp.contract.HomeContract;
import com.xuexiang.xupdate.XUpdate;


@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
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


    public void update() {
        String url = "https://raw.githubusercontent.com/xuexiangjys/XUpdate/master/jsonapi/update_test.json";
        XUpdate.newBuild(mRootView.getActivity())
                .updateUrl(url)
                .update();
    }
}
