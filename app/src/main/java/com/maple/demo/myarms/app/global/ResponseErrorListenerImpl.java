package com.maple.demo.myarms.app.global;

import android.content.Context;
import android.net.ParseException;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import retrofit2.HttpException;

/**
 * ================================================
 * 展示 {@link ResponseErrorListener} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ResponseErrorListenerImpl implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        //Timber.tag("Catch-Error").w(t.getMessage());
        //这里不光只能打印错误, 还可以根据不同的错误做出不同的逻辑处理
        //这里只是对几个常用错误进行简单的处理, 展示这个类的用法, 在实际开发中请您自行对更多错误进行更严谨的处理

        String msg = ArmsUtils.getString(context, R.string.unknown_error);
        if (t instanceof UnknownHostException) {
            msg = ArmsUtils.getString(context, R.string.connect_error);
        } else if (t instanceof SocketTimeoutException) {
            msg = ArmsUtils.getString(context, R.string.connect_timeout);
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            msg = convertStatusCode(context,httpException);
        } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException || t instanceof JsonIOException) {
            msg = ArmsUtils.getString(context, R.string.parse_error);
        }
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    private String convertStatusCode(Context context, HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = ArmsUtils.getString(context, R.string.bad_network);
        } else if (httpException.code() == 404) {
            msg = ArmsUtils.getString(context, R.string.url_error);
        } else if (httpException.code() == 403) {
            msg = ArmsUtils.getString(context, R.string.service_refuse);
        } else if (httpException.code() == 307) {
            msg = ArmsUtils.getString(context, R.string.request_error);
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
