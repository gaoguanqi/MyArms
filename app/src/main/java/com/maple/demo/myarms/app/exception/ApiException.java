package com.maple.demo.myarms.app.exception;

/**
 * Author：gaoguanqi
 * Time：  2018/9/29 上午9:51
 * Desc：  ApiException
 */
public class ApiException extends RuntimeException {
    public ApiException() { }
    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}

