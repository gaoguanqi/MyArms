package com.maple.demo.myarms.mvp.ui.adapter.litener;

import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;

/**
 * author: gaogq
 * time: 2018/12/21 11:26
 * description:
 */
public interface OnMainItemClickLitener {
    void onBannerItemClick(int pos);
    void onMenuItemClick(MenuEntity entity);
    void onListItemClick(ListEntity entity);
    void onLoadMoreClick(boolean isLoadMore);
}
