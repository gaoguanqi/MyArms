package com.maple.demo.myarms.mvp.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerWebComponent;
import com.maple.demo.myarms.di.module.WebModule;
import com.maple.demo.myarms.mvp.contract.WebContract;
import com.maple.demo.myarms.mvp.presenter.WebPresenter;
import com.maple.demo.myarms.widget.web.WebJavascript;
import com.maple.demo.myarms.widget.web.X5WebView;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    @BindView(R.id.x5_web_view)
    X5WebView mWebView;

    private String url = "http://111.202.89.254/lottery-nmg-mutual/nm_app/html/maps/searchDw.html?site=呼和浩特市";
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWebComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .webModule(new WebModule(this))
                .build()
                .inject(this);
    }
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initWebView();
    }
    private void initWebView() {
        mWebView.setWebViewClient(webViewClient);
        mWebView.setWebChromeClient(webChromeClient);
        mWebView.addJavascriptInterface(new WebJavascript(this, mWebView, mPresenter), "android");
        mWebView.loadUrl(url);
        //mPresenter.haveReadMessage(isMessage);
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
        ArmsUtils.snackbarText(message);
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
                .setTitle("web")
                .setToolbarLitener(this)
                .build();
    }


    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView webView, String url, Bitmap bitmap) {
            super.onPageStarted(webView, url, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String url) {
            super.onPageFinished(webView, url);

        }

        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            super.onReceivedError(webView, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.startsWith("https") || url.startsWith("http") || url.startsWith("file")) {
                webView.loadUrl(url);
            }else {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    //将功能Scheme以URI的方式传入data
                    Uri uri = Uri.parse(url);
                    intent.setData(uri);
                    startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
    };

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView webView, String title) {
            super.onReceivedTitle(webView, title);
//            if (!title.startsWith("http")) {
//                toolbarTitle.setText(title);
//            }
        }

        @Override
        public void onProgressChanged(WebView webView, int progress) {
            super.onProgressChanged(webView, progress);
            if (progress >= 100) {
                mWebView.hideProgress();
            } else {
                mWebView.showProgress();
            }
        }

        // For Android  >= 3.0
        public void openFileChooser(ValueCallback valueCallback, String acceptType) {
        }

        //For Android  >= 4.1
        @Override
        public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
        }

        // For Android >= 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            return true;
        }
    };
}
