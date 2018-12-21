package com.maple.demo.myarms.mvp.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.mvp.ui.adapter.MenuAdapter;
import com.maple.demo.myarms.mvp.ui.adapter.litener.OnMainItemClickLitener;
import com.maple.demo.myarms.mvp.ui.adapter.litener.OnMenuItemClickLitener;

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
    private OnMainItemClickLitener mListener;

    public MenuHolder(@NonNull View itemView,OnMainItemClickLitener listener) {
        super(itemView);
        this.mListener = listener;
        ButterKnife.bind(this,itemView);
        mMenuAdapter = new MenuAdapter(itemView.getContext());
        GridLayoutManager manager = new GridLayoutManager(itemView.getContext(),4);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(mMenuAdapter);
        if(mListener != null){
            mMenuAdapter.setOnMenuItemClickLitener(entity ->{
                mListener.onMenuItemClick(entity);
            });
        }
    }

    public void setData(List<MenuEntity> data) {
        mMenuAdapter.setData(data);
    }
}
