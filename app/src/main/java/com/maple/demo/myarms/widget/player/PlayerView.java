package com.maple.demo.myarms.widget.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.global.AppController;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * author: gaogq
 * time: 2018/12/28 14:40
 * description:
 */
public class PlayerView extends StandardGSYVideoPlayer {

    public PlayerView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public PlayerView(Context context) {
        super(context);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_player_view;
    }



//    public void setMyThumbImageView(ImageLoader mImageLoader,String url) {
//        ImageView imageView = new ImageView(mContext);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setBackgroundResource(R.mipmap.ic_launcher);
//                mImageLoader.loadImage(mContext,
//                ImageConfigImpl
//                        .builder()
//                        .url(url)
//                        .imageView(imageView)
//                        .build());
//        setThumbImageView(imageView);
 //   }
}
