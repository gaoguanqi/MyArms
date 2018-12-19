package com.maple.demo.myarms.app.observer;

import android.app.Activity;

import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * * <p>带进度条的异步线程观察者
 * 注意此观察者不能够用于网络请求
 * 只是处理作为线程的异步事件
 * 网络请求请使用{@link ProgressObserver}
 * 不需要进度条请使用{@link ErrorHandleSubscriber}
 * </p>
 * author: gaogq
 * time: 2018/12/13 9:53
 * description:
 */
public abstract class BackgroundObserver<T> extends ErrorHandleSubscriber<T> {
    protected Activity mActivity;
    abstract public void onSuccess(T response);

    public BackgroundObserver(RxErrorHandler rxErrorHandler, Activity activity) {
        super(rxErrorHandler);
        this.mActivity = activity;
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
    }


    @Override
    public void onComplete() {
        super.onComplete();
    }
}
