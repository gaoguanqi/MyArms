package com.maple.demo.myarms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseActivity;
import com.maple.demo.myarms.app.base.BaseViewActivity;
import com.maple.demo.myarms.app.db.UserDao;
import com.maple.demo.myarms.app.manager.toolbar.ToolbarConfig;
import com.maple.demo.myarms.di.component.DaggerLoginComponent;
import com.maple.demo.myarms.di.module.LoginModule;
import com.maple.demo.myarms.mvp.contract.LoginContract;
import com.maple.demo.myarms.mvp.model.entity.UserEntity;
import com.maple.demo.myarms.mvp.presenter.LoginPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseViewActivity<LoginPresenter> implements LoginContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
                .setTitle("登录")
                .setToolbarLitener(this)
                .build();
    }


    @OnClick({R.id.btn_login, R.id.btn_go_registe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                UserEntity user = new UserEntity();
                user.setPhone("13797591366");
                UserDao.getInstance().insertOrReplaceData(user);
                killMyself();
                //这里使用eventBus 或 startActivityForResult 更新 Main 的数据
                break;
            case R.id.btn_go_registe:
                launchActivity(new Intent(this,RegisteActivity.class));
                break;
        }
    }


    @Override
    protected void onToolbarBack() {
        killMyself();
    }

    @Override
    protected boolean useMultipleStatusView() {
        return false;
    }
}
