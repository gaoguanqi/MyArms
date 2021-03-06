package com.maple.demo.myarms.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.maple.demo.myarms.mvp.contract.RegisteContract;
import com.maple.demo.myarms.mvp.model.api.ApiService;

import io.reactivex.Observable;


@ActivityScope
public class RegisteModel extends BaseModel implements RegisteContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public RegisteModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<String> test(String id) {
        return mRepositoryManager.obtainRetrofitService(ApiService.class).test(id);
    }
}