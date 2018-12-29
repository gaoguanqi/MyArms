package com.maple.demo.myarms.utils;


import android.util.Log;

import com.maple.demo.myarms.BuildConfig;

public final class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static boolean isShow = BuildConfig.DEBUG;
    //private static boolean isShow = true;
    public static void logGGQ(String msg){
        if(isShow){
            Log.i("GGQ", msg);
        }
    }

    public static void logCrash(String msg){
        if(isShow){
            Log.e("Crash", msg);
        }
    }
}