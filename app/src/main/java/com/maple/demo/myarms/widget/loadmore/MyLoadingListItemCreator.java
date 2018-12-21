package com.maple.demo.myarms.widget.loadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.demo.myarms.R;
import com.paginate.recycler.LoadingListItemCreator;


public class MyLoadingListItemCreator implements LoadingListItemCreator {

    private Context mContext;
    private LoadMoreHolder loadMoreHolder;

    public MyLoadingListItemCreator(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
        loadMoreHolder = new LoadMoreHolder(view);
        return loadMoreHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

//    public void setViewLoading(){
//        if(loadMoreHolder != null){
//            loadMoreHolder.setViewLoading();
//        }
//    }
//
//
//    public void setViewState(boolean state){
//        if(loadMoreHolder != null){
//            loadMoreHolder.setViewState(mContext,state);
//        }
//    }


}
