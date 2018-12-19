package com.maple.demo.myarms.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.maple.demo.myarms.app.manager.callback.OnNetStateCallback;

/**
* 网络状态监听的广播
*
*/
public class NetBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "NetBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkUtils.NetworkType type = NetworkUtils.getNetworkType();
            setNetState(type);
        }
    }

    private void setNetState(NetworkUtils.NetworkType type) {
        if(callback != null){
            callback.onNetState(type);
        }
    }


    private OnNetStateCallback callback;
    public void setNetStateCallback(OnNetStateCallback callback) {
        this.callback = callback;
    }
}
