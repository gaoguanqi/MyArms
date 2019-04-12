package com.maple.demo.myarms.widget.web;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.maple.demo.myarms.mvp.presenter.WebPresenter;
import com.maple.demo.myarms.utils.LogUtils;

/**
 * 交互类
 * 创建日期:2017/11/14
 *
 * @author Yang
 */

public class WebJavascript {

    Activity mActivity;
    X5WebView mWebView;
    WebPresenter mPresenter;

    public WebJavascript(Activity activity, X5WebView mWebView, WebPresenter mPresenter) {
        this.mActivity = activity;
        this.mWebView = mWebView;
        this.mPresenter = mPresenter;
    }

    @JavascriptInterface
    public void close() {

    }

    @JavascriptInterface
    public void launchLogin() {

    }


    @JavascriptInterface
    public void showToast(String msg) {
        LogUtils.logGGQ("web："+msg);
    }

    @JavascriptInterface
    public void getShooseValue(String location,String longitude,String latitude) {
        LogUtils.logGGQ("选择的位置信息：：：位置："+location +"---经度："+longitude+"---纬度："+latitude);
    }
}
