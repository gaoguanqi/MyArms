package com.maple.demo.myarms.mvp.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.BannerEntity;
import com.maple.demo.myarms.widget.banner.MyGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/20 11:21
 * description:
 */
public class BannerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.banner)
    Banner banner;

    private List<String> title,url;

    public BannerHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(List<BannerEntity> data) {

        if(data != null && !data.isEmpty()){
            title = new ArrayList<>(data.size());
            url = new ArrayList<>(data.size());

            for (BannerEntity entity : data) {
                title.add(entity.getTitle());
                url.add(entity.getUrl());
            }

            banner.setImages(url)
//                    .setBannerTitles(title)
//                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                    .setImageLoader(new MyGlideImageLoader())
                    .start();
        }
    }
}
