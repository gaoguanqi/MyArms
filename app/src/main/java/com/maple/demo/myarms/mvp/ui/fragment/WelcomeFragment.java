package com.maple.demo.myarms.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.jess.arms.di.component.AppComponent;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.base.BaseFragment;
import com.maple.demo.myarms.app.config.AppContent;
import com.maple.demo.myarms.mvp.ui.activity.HomeActivity;

/**
 * author: gaogq
 * time: 2018/12/19 13:28
 * description:
 */
@SuppressLint("ValidFragment")
public class WelcomeFragment extends BaseFragment implements View.OnClickListener {

    private int page;
    public WelcomeFragment(int page) {
        this.page = page;
    }

    public static BaseFragment getInstance(int page) {
        return new WelcomeFragment(page);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        TextView tvPage = view.findViewById(R.id.tv_page);
        tvPage.setText(String.valueOf(page));
        Button btnAction = view.findViewById(R.id.btn_action);
        if(page == 3){
            btnAction.setOnClickListener(this);
            btnAction.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_action:
                SPUtils.getInstance().put(AppContent.SaveInfoKey.HASWECLOME,true);
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;
             default:
        }
    }
}
