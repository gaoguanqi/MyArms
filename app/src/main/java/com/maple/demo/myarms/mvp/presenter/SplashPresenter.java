package com.maple.demo.myarms.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.widget.Button;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.config.AppContent;
import com.maple.demo.myarms.mvp.contract.SplashContract;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;
import com.maple.demo.myarms.mvp.ui.activity.WeclomeActivity;
import com.maple.demo.myarms.utils.LogUtils;
import com.maple.demo.myarms.utils.PermissionUtil;
import com.maple.demo.myarms.widget.dialog.CustomDialog;

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

    private CustomDialog mDialog;

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
                launchTarget();
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                //某一个权限拒绝的回调
                LogUtils.logGGQ("onRequestPermissionFailure");
                for (String permission : permissions) {
                    LogUtils.logGGQ(permission+"被拒绝了");
                }
                launchTarget();
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                //拒绝后点击不再询问的回调
                LogUtils.logGGQ("onRequestPermissionFailureWithAskNeverAgain");
                for (String permission : permissions) {
                    LogUtils.logGGQ(permission+"不在询问");
                }
                showPermissionSettingsDialog();
            }
        }, mRootView.getRxPermissions(), mErrorHandler);
    }

    private void showPermissionSettingsDialog() {
        if(mDialog == null){
            mDialog = new CustomDialog.Builder(mRootView.getActivity())
                    .view(R.layout.dialog_permission)
                    .setTextView(R.id.tv_title, "我们需要您的一些权限")
                    .setTextView(R.id.tv_content, "权限说明权限说明权限说明权限说明权限说明权限说明权限说明")
                    .addViewOnclick(R.id.btn_confirm, view -> {
                        mDialog.cancel();
                        PermissionUtils.launchAppDetailsSettings();
                    })
                    .setGravity(Gravity.CENTER)//设置dialog显示位置
                    .setWidthDP(310) //设置宽
                    .setHeightDP(190)//设置高
                    .build();
        }
        if(mDialog != null && !mDialog.isShowing()){
            mDialog.show();
        }
    }

    private void launchTarget() {
        Intent intent;
        if(SPUtils.getInstance().getBoolean(AppContent.SaveInfoKey.HASWECLOME)){
            intent = new Intent(mRootView.getActivity(), HomeActivity.class);
        }else{
            intent = new Intent(mRootView.getActivity(), WeclomeActivity.class);
        }
        mRootView.launchActivity(intent);
        mRootView.killMyself();
    }
}
