package com.maple.demo.myarms.widget.player;

import android.content.Context;
import android.util.AttributeSet;

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

}
