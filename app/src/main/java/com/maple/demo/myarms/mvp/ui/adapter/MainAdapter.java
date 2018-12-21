package com.maple.demo.myarms.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.BannerEntity;
import com.maple.demo.myarms.mvp.model.entity.ListEntity;
import com.maple.demo.myarms.mvp.model.entity.MenuEntity;
import com.maple.demo.myarms.mvp.ui.adapter.holder.BannerHolder;
import com.maple.demo.myarms.mvp.ui.adapter.holder.ListHolder;
import com.maple.demo.myarms.mvp.ui.adapter.holder.MenuHolder;
import com.maple.demo.myarms.mvp.ui.adapter.litener.OnMainItemClickLitener;
import com.maple.demo.myarms.utils.LogUtils;
import com.maple.demo.myarms.widget.loadmore.LoadMoreHolder;

import java.util.List;

/**
 * author: gaogq
 * time: 2018/12/20 11:07
 * description:
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_MENU = 1;
    public static final int TYPE_LIST = 2;
    public static final int TYPE_LOAD_MORE = 3;

    private Context mContext;
    private List<BannerEntity> mBannerData;
    private List<MenuEntity> mMenuData;
    private List<ListEntity> mListData;
    private boolean isLoadMore;

    private LoadMoreHolder loadMoreHolder;

    private LayoutInflater mInflater;
    private OnMainItemClickLitener mLitener;

    public void setOnMainItemClickLitener(OnMainItemClickLitener litener){
        this.mLitener = litener;
    }

    public MainAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LogUtils.logGGQ("-type:::"+viewType);
        if (viewType == TYPE_BANNER) {
            return new BannerHolder(mInflater.inflate(R.layout.item_main_banner, parent, false),mLitener);
        } else if (viewType == TYPE_MENU) {
            return new MenuHolder(mInflater.inflate(R.layout.item_main_menu, parent, false),mLitener);
        } else if(viewType == TYPE_LIST){
            return new ListHolder(mInflater.inflate(R.layout.item_main_list, parent, false),mLitener);
        } else if(viewType == TYPE_LOAD_MORE){
            loadMoreHolder = new LoadMoreHolder(mInflater.inflate(R.layout.item_load_more, parent, false),mLitener);
            return loadMoreHolder;
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LogUtils.logGGQ("onBindViewHolder:::"+position);
        if (holder instanceof BannerHolder) {
            ((BannerHolder) holder).setData(mBannerData);
        }else if(holder instanceof MenuHolder){
            ((MenuHolder) holder).setData(mMenuData);
        }else if(holder instanceof ListHolder) {
            LogUtils.logGGQ("ListHolder:::"+position);
            ((ListHolder) holder).setData(mListData.get(position - 2));
        }else if(holder instanceof LoadMoreHolder) {
            ((LoadMoreHolder) holder).setData(isLoadMore);
        }
    }

    @Override
    public int getItemCount() {
        return mBannerData == null || mMenuData == null || mListData == null ? 0 : mListData.size()+3 ;
    }


    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        LogUtils.logGGQ("getItemViewType:::"+position +"--getItemCount::"+getItemCount());
        if (position == 0) {
            return TYPE_BANNER;
        }else if (position == 1) {
            return TYPE_MENU;
        }else if(position >= 2 && position < getItemCount() -1) {
            return TYPE_LIST;
        }else if(position + 1 == getItemCount()){
            return TYPE_LOAD_MORE;
        }else{
            return -1;
        }
    }

    public void setBannerData(List<BannerEntity> data) {
        this.mBannerData = data;
        notifyItemChanged(0);
    }

    public void setMenuData(List<MenuEntity> data) {
        this.mMenuData = data;
        notifyItemChanged(1);
    }

    public void setListData(List<ListEntity> data) {
        this.mListData = data;
        notifyDataSetChanged();
    }

    public void setLoadMoreData(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
       if(loadMoreHolder != null){
           loadMoreHolder.setData(isLoadMore);
           notifyItemChanged(getItemCount());
       }
    }

    public void setData(List<BannerEntity> bannerData, List<MenuEntity> menuData, List<ListEntity> listData, boolean isLoadMore) {
        this.mBannerData = bannerData;
        this.mMenuData = menuData;
        this.mListData = listData;
        this.isLoadMore = isLoadMore;
        notifyDataSetChanged();
    }

    public void setLoadMoreError(boolean isLoadMoreError) {
        if(loadMoreHolder != null){
            loadMoreHolder.setErrorData(isLoadMoreError);
            notifyItemChanged(getItemCount());
        }
    }
}
