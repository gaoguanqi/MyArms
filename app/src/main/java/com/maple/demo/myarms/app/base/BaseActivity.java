package com.maple.demo.myarms.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.manager.callback.OnNetStateCallback;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.app.receiver.NetBroadcastReceiver;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.aviran.cookiebar2.CookieBar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import static com.jess.arms.utils.ThirdViewUtil.convertAutoView;

/**
 * ================================================
 * 因为 Java 只能单继承, 所以如果要用到需要继承特定 {@link Activity} 的三方库, 那你就需要自己自定义 {@link Activity}
 * 继承于这个特定的 {@link Activity}, 然后再按照 {@link com.jess.arms.base.BaseActivity} 的格式, 将代码复制过去, 记住一定要实现{@link IActivity}
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki">请配合官方 Wiki 文档学习本框架</a>
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki/UpdateLog">更新日志, 升级必看!</a>
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki/Issues">常见 Issues, 踩坑必看!</a>
 * @see <a href="https://github.com/JessYanCoding/ArmsComponent/wiki">MVPArms 官方组件化方案 ArmsComponent, 进阶指南!</a>
 * Created by JessYan on 22/03/2016
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

/**
 * author: gaogq
 * time: 2018/12/17 13:13
 * description: 封装了沉浸式状态栏，网络状态监听广播
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, ActivityLifecycleable,ToolbarConfig.OnToolbarLitener, OnNetStateCallback {
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    private Cache<String, Object> mCache;
    protected Unbinder mUnbinder;
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    protected ImmersionBar mImmersionBar;
    private NetBroadcastReceiver mNetBroadcastReceiver;

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //设为竖屏
            if(usePortrait()){
                ScreenUtils.setPortrait(this);
            }
            setContentView(savedInstanceState);
        } catch (Exception e) {
            if (e instanceof InflateException) {throw e;}
            e.printStackTrace();
        }
        initData(savedInstanceState);
    }

    protected void setContentView(@Nullable Bundle savedInstanceState) {
        int layoutResID = initView(savedInstanceState);
        //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
        if (layoutResID != 0) {
            setContentView(layoutResID);
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
            if(useToolBar()){
                initToolbar();
            }
            if(useImmersionBar()){
                initImmersionBar();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须调用该方法，防止内存泄漏
        if(useImmersionBar() && mImmersionBar != null){
            mImmersionBar.destroy();
        }

        //注销广播
        if (mNetBroadcastReceiver != null) {
            unregisterReceiver(mNetBroadcastReceiver);
        }

        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
            this.mUnbinder = null;
        }

        if (mPresenter != null){
            mPresenter.onDestroy();//释放资源
            this.mPresenter = null;
        }
    }

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为使用 {@code true}), Arms 会自动注册 EventBus
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link com.jess.arms.base.BaseFragment} 的Fragment将不起任何作用
     *
     * @return
     */
    @Override
    public boolean useFragment() {
        return false;
    }

    /**
     * 是否使用ImmersionBar
     * @return
     */
    protected boolean useImmersionBar() { return true; }

    /**
     * 是否有titleBar
     * @return
     */
    protected boolean useToolBar() { return true; }


    /**
     * 是否为竖屏
     * @return
     */
    protected boolean usePortrait() { return true; }

    /**
     * 是否注册网络状态广播
     * @return
     */
    protected boolean useNetReceiver() { return true; }


    @Override
    protected void onStart() {
        super.onStart();
        if(useNetReceiver()){
            //注册网络状态监听的广播
            registerBroadcastReceiver();
        }
    }

    /**
     * 注册网络状态广播
     */
    private void registerBroadcastReceiver() {
        //注册广播
        if (mNetBroadcastReceiver == null) {
            mNetBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetBroadcastReceiver, filter);
            //设置监听
            mNetBroadcastReceiver.setNetStateCallback(this);
        }
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

    public void initToolbar() {
        View toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar((android.support.v7.widget.Toolbar) toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ToolbarConfig config = getToolbarConfig();
        if(config == null){
            return;
        }

        //设置UI标题
        if(config.hasTitle){
            findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.toolbar_title)).setText(config.title);
        }
        //返回文字
        if (config.hasBackText) {
            TextView tvBackText = (TextView)findViewById(R.id.tv_bar_back);
            tvBackText.setVisibility(View.VISIBLE);
            tvBackText.setText(config.backText);
            tvBackText.setOnClickListener(v -> config.litener.onToolbarClick(R.id.tv_bar_back));
        }
        //设置文字
        if (config.hasSettingText) {
            TextView tvSettingText = (TextView)findViewById(R.id.tv_bar_setting);
            tvSettingText.setVisibility(View.VISIBLE);
            tvSettingText.setText(config.settingText);
            tvSettingText.setOnClickListener(v -> config.litener.onToolbarClick(R.id.tv_bar_setting));
        }
        //返回按钮
        if (config.hasBack) {
            ImageButton ibtnBack = (ImageButton)findViewById(R.id.ibtn_bar_back);
            ibtnBack.setVisibility(View.VISIBLE);
            ibtnBack.setOnClickListener(v -> config.litener.onToolbarClick(R.id.ibtn_bar_back));
        }
        //设置按钮
        if (config.hasSetting) {
            ImageButton ibtnSetting = (ImageButton)findViewById(R.id.ibtn_bar_setting);
            ibtnSetting.setVisibility(View.VISIBLE);
            ibtnSetting.setOnClickListener(v -> config.litener.onToolbarClick(R.id.ibtn_bar_setting));
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(useImmersionBar()){
            // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
            mImmersionBar = ImmersionBar.with(this);
            //设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度
            mImmersionBar.init();
        }
    }

    protected ToolbarConfig getToolbarConfig(){
        return null;
    }

    @Override
    public void onToolbarClick(int id) {
        switch (id){
            case R.id.tv_bar_back:
                onToolbarBackText();
                break;
            case R.id.ibtn_bar_back:
                onToolbarBack();
                break;
            case R.id.tv_bar_setting:
                onToolbarSettingText();
                break;
            case R.id.ibtn_bar_setting:
                onToolbarSetting();
                break;
             default:
        }
    }

    protected void onToolbarSetting() {

    }

    protected void onToolbarSettingText() {

    }

    protected void onToolbarBack() {
        this.finish();
    }

    protected void onToolbarBackText() {

    }

    @Override
    public void onNetState(NetworkUtils.NetworkType type) {
        //ArmsUtils.snackbarText("网络监听："+type);
        CookieBar.build(this)
                .setTitle("您正在使用")
                .setBackgroundColor(R.color.color_toolbar)
                .setMessage(type.toString())
                .setCookiePosition(CookieBar.TOP)  // Cookie will be displayed at the bottom
                .show();
    }
}
