package com.maple.demo.myarms.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.ui.adapter.listener.OnPlayerItemClickListener;

import java.util.List;

import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/28 11:50
 * description:
 */
public class PalyerAdapter extends  RecyclerView.Adapter<PalyerAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mData;

    private OnPlayerItemClickListener mListener;

    public PalyerAdapter(Context context) {
        this.mContext = context;
    }
    public void setOnClickLitener(OnPlayerItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PalyerAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_player, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mData.get(position));
//        if(mListener != null){
//            holder.root.setOnClickListener(v ->{
//                mListener.onItemClickLitener(mData.get(position));
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 :mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(String data) {

        }
    }
}
