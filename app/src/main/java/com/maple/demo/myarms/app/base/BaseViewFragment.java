package com.maple.demo.myarms.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.mvp.IPresenter;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.utils.ToastUtil;

/**
 * author: gaogq
 * time: 2018/12/19 11:54
 * description: 主要功能是封装多状态布局的fragment
 *              如果即需要多状态布局又需要懒加载的fragment，请使用{@link BaseLazyFragment}
 */
public abstract class BaseViewFragment<P extends IPresenter> extends BaseFragment<P> implements View.OnClickListener {
    protected MultipleStatusView mMultipleStatusView;
    /**
     * 沉浸式以及bar的管理
     */
    protected ImmersionBar mImmersionBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_base, container, false);
        View view = initView(inflater, container, savedInstanceState);
        if(view != null){
            mMultipleStatusView =  (MultipleStatusView)rootView.findViewById(R.id.multiple_status_view);
            mMultipleStatusView.showContent(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置重试视图点击事件
            mMultipleStatusView.setOnRetryClickListener(this);
            if(useToolBar()){
                ViewStub viewStubTitle = rootView.findViewById(R.id.view_stub_title);
                if (viewStubTitle != null) {
                    viewStubTitle.inflate();
                    initToolbar(rootView);
                }
            }
            if(useImmersionBar()){
                initImmersionBar();
            }
            mMultipleStatusView.showLoading();
        }
        return rootView;
    }

    public void initToolbar(ViewGroup rootView) {
        View toolbar = rootView.findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        ToolbarConfig config = getToolbarConfig();
        if(config == null){
            return;
        }

        //设置UI标题
        if(config.hasTitle){
            rootView.findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
            ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(config.title);
        }
        //返回文字
        if (config.hasBackText) {
            TextView tvBackText = (TextView)rootView.findViewById(R.id.tv_bar_back);
            tvBackText.setVisibility(View.VISIBLE);
            tvBackText.setText(config.backText);
            tvBackText.setOnClickListener(v -> config.litener.onToolbarClick(R.id.tv_bar_back));
        }
        //设置文字
        if (config.hasSettingText) {
            TextView tvSettingText = (TextView)rootView.findViewById(R.id.tv_bar_setting);
            tvSettingText.setVisibility(View.VISIBLE);
            tvSettingText.setText(config.settingText);
            tvSettingText.setOnClickListener(v -> config.litener.onToolbarClick(R.id.tv_bar_setting));
        }
        //返回按钮
        if (config.hasBack) {
            ImageButton ibtnBack = (ImageButton)rootView.findViewById(R.id.ibtn_bar_back);
            ibtnBack.setVisibility(View.VISIBLE);
            ibtnBack.setOnClickListener(v -> config.litener.onToolbarClick(R.id.ibtn_bar_back));
        }
        //设置按钮
        if (config.hasSetting) {
            ImageButton ibtnSetting = (ImageButton)rootView.findViewById(R.id.ibtn_bar_setting);
            ibtnSetting.setVisibility(View.VISIBLE);
            ibtnSetting.setOnClickListener(v -> config.litener.onToolbarClick(R.id.ibtn_bar_setting));
        }
    }

    /**
     * 是否有titleBar
     * @return
     */
    protected boolean useToolBar() { return false; }

    /**
     * 是否使用ImmersionBar
     * @return
     */
    protected boolean useImmersionBar() { return false; }

    protected ToolbarConfig getToolbarConfig(){
        return null;
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.color_status);
        mImmersionBar.fitsSystemWindows(true);
        //设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度
        mImmersionBar.init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empty_retry_view:
            case R.id.error_retry_view:
            case R.id.no_network_retry_view:
                if(isSafeMultipleStatusView()){
                    onClickRetry();
                }
                break;
            default:
        }
    }

    protected boolean isSafeMultipleStatusView(){
        return mMultipleStatusView != null ? true:false;
    }

    private void onClickRetry() {
        ToastUtil.showToast("刷新数据");
    }
}
