package com.maple.demo.myarms.app.manager.callback;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * author: gaogq
 * time: 2018/11/15 16:50
 * description:
 */
public interface OnNetStateCallback {
    void onNetState(NetworkUtils.NetworkType type);
}
