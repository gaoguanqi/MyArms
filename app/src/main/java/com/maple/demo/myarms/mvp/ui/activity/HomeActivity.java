package com.maple.demo.myarms.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.app.base.BaseFragment;
import com.maple.demo.myarms.app.db.UserDao;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerHomeComponent;
import com.maple.demo.myarms.di.module.HomeModule;
import com.maple.demo.myarms.mvp.contract.HomeContract;
import com.maple.demo.myarms.mvp.presenter.HomePresenter;
import com.maple.demo.myarms.mvp.ui.adapter.HomePagerAdapter;
import com.maple.demo.myarms.mvp.ui.fragment.MainFragment;
import com.maple.demo.myarms.mvp.ui.fragment.MineFragment;
import com.maple.demo.myarms.utils.ToastUtil;
import com.maple.demo.myarms.widget.SwitchSlideViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View, ToolbarConfig.OnToolbarLitener {
    private long lastBackPressedMillis;

    private static final int TAB_HOME = 0;
    private static final int TAB_MINE = 1;

    @BindView(R.id.viewPager)
    SwitchSlideViewPager viewPager;
    @BindView(R.id.rbtn_home)
    RadioButton rbtnHome;
    @BindView(R.id.rbtn_mine)
    RadioButton rbtnMine;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.layout_menu)
    LinearLayout layoutMenu;

    private List<BaseFragment> fragments;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case TAB_HOME:
                    rbtnHome.setChecked(true);
                    rbtnMine.setChecked(false);
                    break;
                case TAB_MINE:
                    if(UserDao.getInstance().isLogin()){
                        viewPager.setCurrentItem(TAB_MINE, false);
                    }else{
                        rbtnHome.setChecked(true);
                        rbtnMine.setChecked(false);
                        launchActivity(new Intent(HomeActivity.this,LoginActivity.class));
                    }
                    break;
                default:
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //禁止手势滑动
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        viewPager.addOnPageChangeListener(pageChangeListener);
        fragments = new ArrayList<>();
        fragments.add(MainFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        viewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setCurrentItem(TAB_HOME);
        mPresenter.update();
    }

    @Override
    public void showLoading() {
        showMyLoading();
    }

    @Override
    public void hideLoading() {
        hideMyLoading();
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
        finish();
    }


    @Override
    protected ToolbarConfig getToolbarConfig() {
//        return ToolbarConfig.builder()
//                .setHasBack(false)
//                .setTitle("首页")
//                .setToolbarLitener(this)
//                .build();
        return null;
    }

    @Override
    protected boolean useNetReceiver() {
        return true;
    }

    @OnClick({R.id.rbtn_home, R.id.rbtn_mine,R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbtn_home:
                viewPager.setCurrentItem(TAB_HOME, false);
                break;
            case R.id.rbtn_mine:
                if(UserDao.getInstance().isLogin()){
                    viewPager.setCurrentItem(TAB_MINE, false);
                }else{
                    rbtnHome.setChecked(true);
                    rbtnMine.setChecked(false);
                    launchActivity(new Intent(this,LoginActivity.class));
                }
                break;
            case R.id.btn_logout:
                UserDao.getInstance().deleteAllData(this);
                if(drawer.isDrawerOpen(layoutMenu)){
                    drawer.closeDrawer(layoutMenu);
                }
                break;
             default:
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (lastBackPressedMillis + 2000 > System.currentTimeMillis()) {
                //moveTaskToBack(true);
                killMyself();
            } else {
                lastBackPressedMillis = System.currentTimeMillis();
                showMessage(getString(R.string.app_exit));
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected boolean useToolBar() {
        return false;
    }

    @Override
    public boolean useFragment() {
        return true;
    }

    public void openDrawer(){
        if(!drawer.isDrawerOpen(layoutMenu)){
            drawer.openDrawer(layoutMenu);
        }
    }

    public void openSlide(){
        launchActivity(new Intent(this,SlideActivity.class));
    }
}
