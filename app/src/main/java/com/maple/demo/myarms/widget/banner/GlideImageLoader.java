package com.maple.demo.myarms.widget.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.youth.banner.loader.ImageLoader;

/**
 * @Author: Gao
 * @Time: 2018/8/9 18:06
 * @Description: 功能描述
 *  banner 默认的 item ，本项目使用cardView {@link MyGlideImageLoader }
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //Glide 加载图片简单用法
        ArmsUtils.obtainAppComponentFromContext(context).imageLoader().loadImage(context, ImageConfigImpl.builder().url(path.toString()).imageView(imageView).build());
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        return iv;
    }
}
