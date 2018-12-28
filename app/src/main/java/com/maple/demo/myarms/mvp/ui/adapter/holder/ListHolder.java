package com.maple.demo.myarms.mvp.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.ui.adapter.listener.OnMainItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/20 11:25
 * description:
 */
public class ListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_list)
    ConstraintLayout root;
    @BindView(R.id.iv_image)
    ImageView image;
    @BindView(R.id.tv_desc)
    TextView desc;
    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;
    private OnMainItemClickListener mListener;
    public ListHolder(@NonNull View itemView,OnMainItemClickListener listener) {
        super(itemView);
        this.mListener = listener;
        ButterKnife.bind(this,itemView);
        mAppComponent =  ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }

    public void setData(ListEntity data) {
        desc.setText(data.getText());
        mImageLoader.loadImage(itemView.getContext(),
                ImageConfigImpl
                        .builder()
                        .url(data.getImage())
                        .imageView(image)
                        .build());

        if(mListener != null){
            root.setOnClickListener(v -> mListener.onListItemClick(data));
        }
    }
}
