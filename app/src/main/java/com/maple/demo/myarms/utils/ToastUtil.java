package com.maple.demo.myarms.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.app.global.AppController;

/**
 * author: gaogq
 * time: 2018/12/18 14:09
 * description:
 */
public class ToastUtil {
    private ToastUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }




    public static void showToast(String msg){
        if(TextUtils.isEmpty(msg)){
            return;
        }
        ToastUtils.setBgColor(ArmsUtils.getColor(AppController.getInstance().getContext(),R.color.colorAccent));
        ToastUtils.showShort(msg);
    }
}
