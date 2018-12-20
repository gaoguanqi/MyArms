package com.maple.demo.myarms.widget.banner;

import android.content.Context;
import android.view.View;

import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * @Author: Gao
 * @Time: 2018/8/10 13:44
 * @Description: 功能描述
 */
public abstract class MyImageLoader implements ImageLoaderInterface<View> {
    @Override
    public View createImageView(Context context) {
        View view = ArmsUtils.inflate(context, R.layout.item_main_banner);
        return view;
    }
}
