package com.maple.demo.myarms.app.global;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.util.Map;

/**
 * author: gaogq
 * time: 2018/10/18 14:10
 * description:
 */
public class AppController {

    private static volatile AppController sInstance = null;
    private Application mApplication = null;
    private Context mContext = null;



    public void init(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }


    /**
     * 获取实例
     *
     * @return AppController的实例
     */
    public static AppController getInstance() {
        if (sInstance == null) {
            synchronized (AppController.class) {
                if (sInstance == null) {
                    sInstance = new AppController();
                }
            }
        }
        return sInstance;
    }

    public Context getContext() {
        return mContext;
    }

    public Application getApplication() {
        return mApplication;
    }


    /**
     * 去往任意页面
     *
     * @param context       上下文
     * @param activityClass 目标类
     * @param map           参数
     */
    public static void forwardPage(Context context, Class activityClass, Map<String, Object> map, int flags) {
        Intent intent = new Intent(context, activityClass);
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                intent.putExtra(item.getKey().toString(), item.getValue() == null ? "" : item.getValue().toString());
            }
        }
        intent.setFlags(flags);
        context.startActivity(intent);
    }




}