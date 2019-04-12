package com.maple.demo.myarms.mvp.model.api;


import com.maple.demo.myarms.app.config.AppConfig;
import com.maple.demo.myarms.mvp.model.entity.BaseEntity;
import com.maple.demo.myarms.mvp.model.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author: gaogq
 * time: 2018/12/13 11:12
 * description:
 */
public interface ApiService {
    /**
     *  登录
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.BASE_URL + "/user/account/login")
    Observable<BaseEntity<UserEntity>> login(@Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("http://172.16.11.145:8080/zch/provinces/city.do")
    Observable<String> test(@Field("provincesId") String id);
}
