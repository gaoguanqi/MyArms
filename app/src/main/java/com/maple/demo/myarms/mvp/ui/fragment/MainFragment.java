package com.maple.demo.myarms.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import com.maple.demo.myarms.mvp.model.entity.BannerEntity;
import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.mvp.presenter.MainPresenter;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;
import com.maple.demo.myarms.mvp.ui.adapter.MainAdapter;
import com.maple.demo.myarms.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainFragment extends BaseLazyFragment<MainPresenter> implements MainContract.View {

    @BindView(R.id.main_refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.main_recycler)
    RecyclerView recycler;

    private List<BannerEntity> mBannerData;
    private List<MenuEntity> mMenuData;
    private List<ListEntity> mListData;
    private boolean mFooterData;

    private BannerEntity mBannerEntity;
    private MenuEntity mMenuEntity;
    private ListEntity mListEntity;

    private MainAdapter mMainAdapter;


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
        mBannerData = new ArrayList<>();
        mMenuData = new ArrayList<>();
        mListData = new ArrayList<>();

        mBannerEntity = new BannerEntity();
        mBannerEntity.setUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=4f7bf38ac3fc1e17fdbf8b3772ab913e/d4628535e5dde7119c3d076aabefce1b9c1661ba.jpg");
        mBannerEntity.setTitle("title1");
        mBannerData.add(mBannerEntity);

        mBannerEntity = new BannerEntity();
        mBannerEntity.setUrl("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/whfpf%3D268%2C152%2C50/sign=41cf35669425bc312b0852d838e2bf87/eaf81a4c510fd9f9bb10b5c1282dd42a2834a417.jpg");
        mBannerEntity.setTitle("title2");
        mBannerData.add(mBannerEntity);

        mMenuEntity = new MenuEntity();
        mMenuEntity.setImage("https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=f23b0796b74543a9f11bfdce2e178a7b/8b13632762d0f703d0ad4cbe08fa513d2697c5b1.jpg");
        mMenuEntity.setText("V百科");
        mMenuData.add(mMenuEntity);
        mMenuData.addAll(mMenuData);
        mMenuData.addAll(mMenuData);
        mMenuData.addAll(mMenuData);

        for (int i = 0; i < 20; i++) {
            mListEntity = new ListEntity();
            mListEntity.setImage("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=d034324c01f79052eb1f403c3cf2d738/0dd7912397dda1445da42dedbab7d0a20df486c4.jpg");
            mListEntity.setText("条目"+i);
            mListData.add(mListEntity);
        }

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMainAdapter = new MainAdapter(getActivity());
        mMainAdapter.setData(mBannerData,mMenuData,mListData,mFooterData);
        recycler.setAdapter(mMainAdapter);
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
