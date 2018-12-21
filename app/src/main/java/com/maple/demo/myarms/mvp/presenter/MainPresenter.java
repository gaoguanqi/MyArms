package com.maple.demo.myarms.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.blankj.utilcode.util.SPUtils;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.jess.arms.utils.RxLifecycleUtils;
import com.maple.demo.myarms.app.config.AppContent;
import com.maple.demo.myarms.mvp.contract.MainContract;
import com.maple.demo.myarms.mvp.model.entity.BannerEntity;
import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;
import com.maple.demo.myarms.mvp.ui.activity.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@FragmentScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private List<BannerEntity> mBannerData;
    private List<MenuEntity> mMenuData;
    private List<ListEntity> mListData;
    private boolean mIsLoadMore;

    private BannerEntity mBannerEntity;
    private MenuEntity mMenuEntity;
    private ListEntity mListEntity;




    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
        mBannerData = new ArrayList<>();
        mMenuData = new ArrayList<>();
        mListData = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void initData() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Long>(mErrorHandler) {
                    @Override
                    public void onNext(Long aLong) {
                        mBannerData.clear();
                        mMenuData.clear();
                        mListData.clear();
                        mIsLoadMore = false;
                        //banner
                        mBannerEntity = new BannerEntity();
                        mBannerEntity.setUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=4f7bf38ac3fc1e17fdbf8b3772ab913e/d4628535e5dde7119c3d076aabefce1b9c1661ba.jpg");
                        mBannerEntity.setTitle("title1");
                        mBannerData.add(mBannerEntity);

                        mBannerEntity = new BannerEntity();
                        mBannerEntity.setUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/whfpf%3D268%2C152%2C50/sign=41cf35669425bc312b0852d838e2bf87/eaf81a4c510fd9f9bb10b5c1282dd42a2834a417.jpg");
                        mBannerEntity.setTitle("title2");
                        mBannerData.add(mBannerEntity);

                        mBannerEntity = new BannerEntity();
                        mBannerEntity.setUrl("http://img5.imgtn.bdimg.com/it/u=517297135,2728824910&fm=26&gp=0.jpg");
                        mBannerEntity.setTitle("title3");
                        mBannerData.add(mBannerEntity);

                        // menu
                        mMenuEntity = new MenuEntity();
                        mMenuEntity.setImage("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=f23b0796b74543a9f11bfdce2e178a7b/8b13632762d0f703d0ad4cbe08fa513d2697c5b1.jpg");
                        mMenuEntity.setText("V百科");
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);
                        mMenuData.add(mMenuEntity);

                        //list
                        for (int i = 1; i <= 20; i++) {
                            mListEntity = new ListEntity();
                            mListEntity.setImage("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=d034324c01f79052eb1f403c3cf2d738/0dd7912397dda1445da42dedbab7d0a20df486c4.jpg");
                            mListEntity.setText("条目"+i);
                            mListData.add(mListEntity);
                        }
                        //loadmore
                        mRootView.initDataSuccess(mBannerData,mMenuData,mListData,mIsLoadMore);
                    }
                });
    }

    public void refreshData() {
        mBannerData.clear();
        mBannerEntity = new BannerEntity();
        mBannerEntity.setUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=4f7bf38ac3fc1e17fdbf8b3772ab913e/d4628535e5dde7119c3d076aabefce1b9c1661ba.jpg");
        mBannerEntity.setTitle("title1");
        mBannerData.add(mBannerEntity);
        mBannerEntity = new BannerEntity();
        mBannerEntity.setUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/whfpf%3D268%2C152%2C50/sign=41cf35669425bc312b0852d838e2bf87/eaf81a4c510fd9f9bb10b5c1282dd42a2834a417.jpg");
        mBannerEntity.setTitle("title2");
        mBannerData.add(mBannerEntity);
        mBannerEntity = new BannerEntity();
        mBannerEntity.setUrl("http://img5.imgtn.bdimg.com/it/u=517297135,2728824910&fm=26&gp=0.jpg");
        mBannerEntity.setTitle("title3");
        mBannerData.add(mBannerEntity);

        // menu
        mMenuData.clear();
        mMenuEntity = new MenuEntity();
        mMenuEntity.setImage("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=f23b0796b74543a9f11bfdce2e178a7b/8b13632762d0f703d0ad4cbe08fa513d2697c5b1.jpg");
        mMenuEntity.setText("新百科");
        mMenuData.add(mMenuEntity);
        mMenuData.addAll(mMenuData);
        mMenuData.addAll(mMenuData);
        mMenuData.addAll(mMenuData);


        mListData.clear();
        for (int i = 1; i <= 20; i++) {
            mListEntity = new ListEntity();
            mListEntity.setImage("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=d034324c01f79052eb1f403c3cf2d738/0dd7912397dda1445da42dedbab7d0a20df486c4.jpg");
            mListEntity.setText("条目"+i);
            mListData.add(mListEntity);
        }

        //loadmore
        mRootView.refreshDataSuccess(mBannerData,mMenuData,mListData,mIsLoadMore);
    }

    public void loadMoreData(int size) {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Long>(mErrorHandler) {
                    @Override
                    public void onNext(Long aLong) {
                        for (int i = 1; i <= 20; i++) {
                            mListEntity = new ListEntity();
                            mListEntity.setImage("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=d034324c01f79052eb1f403c3cf2d738/0dd7912397dda1445da42dedbab7d0a20df486c4.jpg");
                            mListEntity.setText("条目"+(size+i));
                            mListData.add(mListEntity);
                        }
                        mRootView.loadMoreSuccess(mListData);
                    }
                });
    }
}
