package com.maple.demo.myarms.mvp.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/20 11:24
 * description:
 */
public class FooterHolder extends RecyclerView.ViewHolder {
    public FooterHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(boolean data) {
    }
}
