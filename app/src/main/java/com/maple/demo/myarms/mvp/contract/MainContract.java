package com.maple.demo.myarms.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.maple.demo.myarms.mvp.model.entity.BannerEntity;
import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;

import java.util.List;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void loadMoreSuccess(List<ListEntity> listData);

        void initDataSuccess(List<BannerEntity> mBannerData, List<MenuEntity> mMenuData, List<ListEntity> mListData, boolean isLoadMore);

        void refreshDataSuccess(List<BannerEntity> mBannerData, List<MenuEntity> mMenuData, List<ListEntity> mListData, boolean isLoadMore);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

    }
}
