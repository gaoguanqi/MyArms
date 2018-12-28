package com.maple.demo.myarms.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.mvp.ui.adapter.listener.OnMenuItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/20 13:18
 * description:
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuEntity> mData;

    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    private OnMenuItemClickListener mListener;

    public void setData(List<MenuEntity> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnMenuItemClickListener listener){
        this.mListener = listener;
    }

    public MenuAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mData.get(position));
        if(mListener != null){
            holder.root.setOnClickListener(v ->{
                mListener.onItemClickLitener(mData.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 :mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_menu)
        ConstraintLayout root;
        @BindView(R.id.iv_icon)
        ImageView icon;
        @BindView(R.id.tv_text)
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mAppComponent =  ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
            mImageLoader = mAppComponent.imageLoader();
        }

        public void setData(MenuEntity data) {
            text.setText(data.getText());
            mImageLoader.loadImage(itemView.getContext(),
                    ImageConfigImpl
                            .builder()
                            .url(data.getImage())
                            .imageView(icon)
                            .build());
        }
    }
}
