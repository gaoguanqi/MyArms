package com.maple.demo.myarms.widget.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;

import com.blankj.utilcode.util.NetworkUtils;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;


public class X5WebView extends WebView {

   // private SmoothProgressBar progressBar;

    public X5WebView(Context context) {
        this(context, null);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        progressBar = (SmoothProgressBar) LayoutInflater.from(context)
//                .inflate(R.layout.layout_progress_webview, this, false);
//        addView(progressBar);
        initWebViewSettings();
        this.getView().setClickable(true);
        this.setDownloadListener(new MyWebViewDownLoadListener());
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
            Uri uri = Uri.parse(s);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            getContext().startActivity(intent);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setDisplayZoomControls(false);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setGeolocationEnabled(true);
        String userAgent = webSetting.getUserAgentString();
        webSetting.setUserAgentString(userAgent);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setMediaPlaybackRequiresUserGesture(false);
        if (NetworkUtils.isConnected()) {
            // 根据cache-control决定是否从网络上取数据
            webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            // 没网，离线加载，优先加载缓存(即使已经过期)
            webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

    }

    public void showProgress() {
//        if (progressBar != null && progressBar.getVisibility() == GONE) {
//            progressBar.setVisibility(VISIBLE);
//        }
    }

    public void hideProgress() {
//        if (progressBar != null && progressBar.getVisibility() == VISIBLE) {
//            progressBar.setVisibility(GONE);
//            PropertyValuesHolder alphaToG = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
//            ObjectAnimator mFadeOutAnimator = ObjectAnimator.ofPropertyValuesHolder(progressBar, alphaToG);
//            mFadeOutAnimator.setDuration(200);
//            mFadeOutAnimator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    progressBar.setVisibility(GONE);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//            mFadeOutAnimator.start();
//        }
    }
}
