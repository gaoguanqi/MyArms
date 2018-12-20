package com.maple.demo.myarms.mvp.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.mvp.ui.adapter.MenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/20 11:23
 * description:
 */
public class MenuHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.menu_recycler)
    RecyclerView recycler;
    private MenuAdapter mMenuAdapter;


    public MenuHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mMenuAdapter = new MenuAdapter(itemView.getContext());
        GridLayoutManager manager = new GridLayoutManager(itemView.getContext(),4);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(mMenuAdapter);
    }

    public void setData(List<MenuEntity> data) {
        mMenuAdapter.setData(data);
    }
}
