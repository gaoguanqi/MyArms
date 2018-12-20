package com.maple.demo.myarms.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseLazyFragment;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerMainComponent;
import com.maple.demo.myarms.di.module.MainModule;
import com.maple.demo.myarms.mvp.contract.MainContract;
import com.maple.demo.myarms.mvp.presenter.MainPresenter;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;
import com.maple.demo.myarms.utils.ToastUtil;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainFragment extends BaseLazyFragment<MainPresenter> implements MainContract.View {

    @BindView(R.id.main_refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.main_recycler)
    RecyclerView recycler;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if(isSafeMultipleStatusView()){
            mMultipleStatusView.showContent();
        }
        refresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.color_status));
        refresh.setOnRefreshListener(this::setupData);
        showLoading();
        setupData();
        hideLoading();
    }


    /**
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
        if (refresh != null && !refresh.isRefreshing()) {
            refresh.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (refresh != null && refresh.isRefreshing()) {
            refresh.setRefreshing(false);
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ToastUtil.showToast(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        ((HomeActivity)getActivity()).killMyself();
    }

    @Override
    protected boolean useToolBar() {
        return true;
    }

    @Override
    protected boolean useImmersionBar() {
        return true;
    }


    @Override
    protected ToolbarConfig getToolbarConfig() {
                return ToolbarConfig.builder()
                .setHasBack(true)
                .setTitle("首页")
                 .setHasSetting(true)
                .setToolbarLitener(this)
                .build();
    }

    private void setupData() {
        hideLoading();
    }

    @Override
    protected void onToolbarBack() {
        ((HomeActivity)getActivity()).openDrawer();
    }

    @Override
    protected void onToolbarSetting() {
        ((HomeActivity)getActivity()).openSlide();
    }
}
