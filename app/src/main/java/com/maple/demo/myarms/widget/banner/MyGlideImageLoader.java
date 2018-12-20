package com.maple.demo.myarms.widget.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;

/**
 * @Author: Gao
 * @Time: 2018/8/9 18:06
 * @Description: 功能描述
 */
public class MyGlideImageLoader extends MyImageLoader {
    @Override
    public void displayImage(Context context, Object path, View view) {
        String url = String.valueOf(path);
        ImageView imageView = view.findViewById(R.id.banner_image);
         ArmsUtils.obtainAppComponentFromContext(context).imageLoader().loadImage(context,
                ImageConfigImpl
                        .builder()
                        .url(url)
                        .imageView(imageView)
                        .build());;
    }

    @Override
    public View createImageView(Context context) {
        View view =  ArmsUtils.inflate(context,R.layout.item_view_pager);
        return view;
    }
}
