package com.maple.demo.myarms.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.maple.demo.myarms.app.observer.RxUtils;
import com.maple.demo.myarms.mvp.contract.RegisteContract;
import com.maple.demo.myarms.mvp.model.api.ApiService;
import com.maple.demo.myarms.mvp.model.entity.BannerEntity;
import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.utils.LogUtils;

import java.util.concurrent.TimeUnit;


@ActivityScope
public class RegistePresenter extends BasePresenter<RegisteContract.Model, RegisteContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public RegistePresenter(RegisteContract.Model model, RegisteContract.View rootView) {
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

    public void getTest(String id) {
        mModel.test(id).compose(RxUtils.applySchedulers(mRootView)).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.logGGQ("onSubscribe："+d.toString());
            }

            @Override
            public void onNext(String s) {
                LogUtils.logGGQ("测试接口onNext："+s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.logGGQ("onError："+e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.logGGQ("onComplete");
            }
        });
    }
}
