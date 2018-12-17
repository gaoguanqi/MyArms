package com.maple.demo.myarms.app.config;

/**
 * author: gaogq
 * time: 2018/12/14 17:19
 * description:
 */
public interface AppConfig {

    //测试接口  172.16.13.116  曹振
    //String BASE_URL = "http://172.16.13.116/"; // 测试

    //演示接口
    //String BASE_URL = "http://120.92.20.196/"; // 测试

    // 正式
    String BASE_URL = "https://zfzxvip.zhcvideo.com/";

    /**
     * 请求成功状态码
     */
    //int RequestSuccess = 200;
    String ResultCode = "SUCCESS";
}
