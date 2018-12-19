package com.maple.demo.myarms.app.observer;

import android.app.Activity;
import android.text.TextUtils;

import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.mvp.model.entity.BaseEntity;
import com.maple.demo.myarms.utils.LogUtils;

import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 *  <p>带进度条的网络请求观察者
 * 注意此观察者只能够用于网络请求
 * 如果有需要线程来做的异步事件
 * 请使用{@link BackgroundObserver}
 * </p>
 *
 */

public abstract class ProgressObserver<T extends BaseEntity> extends ErrorHandleSubscriber<T> {
    protected Activity mActivity;

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    protected ProgressObserver(RxErrorHandler rxErrorHandler, Activity activity) {
        super(rxErrorHandler);
        this.mActivity = activity;
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
    }

    @Override
    public void onNext(T response) {
        LogUtils.logGGQ("结果："+response.toString());
        if (response.isSuccess()) {
            onSuccess(response);
        } else {
            onFail(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String msg = response.getMsg();
        if (TextUtils.isEmpty(msg)) {
            ArmsUtils.snackbarText("onFail");
        } else {
            ArmsUtils.snackbarText(msg);
        }
    }
}
