package com.maple.demo.myarms.widget.loadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maple.demo.myarms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadMoreHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.load_more_root)
    LinearLayout root;
    @BindView(R.id.pb_load_more)
    ProgressBar pb;
    @BindView(R.id.tv_load_more)
    TextView tv;

    public LoadMoreHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


//    public void setViewLoading(){
//
//        if(pb.getVisibility() == View.GONE){
//            pb.setVisibility(View.VISIBLE);
//        }
//
//        if(tv.getVisibility() == View.VISIBLE){
//            tv.setVisibility(View.GONE);
//        }
//    }
//
//
//    public void setViewState(Context context, boolean b){
//        if(pb.getVisibility() == View.VISIBLE){
//            pb.setVisibility(View.GONE);
//        }
//
//        if(tv.getVisibility() == View.GONE){
//            tv.setVisibility(View.VISIBLE);
//        }
//
//        if(b){
//            tv.setText("加载更多");
//        }else{
//            tv.setText("没有更多数据啦～");
//        }
//    }

    public void setData(boolean isLoadMore) {
        if(isLoadMore){
            root.setVisibility(View.VISIBLE);
        }else{
            root.setVisibility(View.GONE);
        }
    }

    public void setErrorData(boolean isLoadMoreError) {
        if(isLoadMoreError){
            root.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);
            tv.setText("没有数据了~~~");
        }else{
            root.setVisibility(View.GONE);
            pb.setVisibility(View.VISIBLE);
            tv.setText("正在加载...");
        }
    }
}
