/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maple.demo.myarms.app.config;

import org.greenrobot.eventbus.EventBus;

/**
 * ================================================
 * 放置 {@link EventBus} 的 Tag ,便于检索
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#3.5">EventBusTags wiki 官方文档</a>
 * Created by JessYan on 8/30/2016 16:39
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface EventBusTags {
    /**
     * 认证中心切换页面
     */
    String CERTIFY_CHANGE_STEP = "certify_change_step";
    /**
     * 地图定位通知
     */
    String MAP_LOCATION_RESULT = "map_location_result";
    /**
     * 通知个人中心数据
     */
    String HOME_STATUS = "home_status";
}
