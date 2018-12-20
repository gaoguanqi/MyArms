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
import com.maple.demo.myarms.mvp.ui.adapter.holder.FooterHolder;
import com.maple.demo.myarms.mvp.ui.adapter.holder.ListHolder;
import com.maple.demo.myarms.mvp.ui.adapter.holder.MenuHolder;

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
    public static final int TYPE_FOOTER = 3;

    private Context mContext;
    private List<BannerEntity> mBannerData;
    private List<MenuEntity> mMenuData;
    private List<ListEntity> mListData;
    private boolean mFooterData;


    private LayoutInflater mInflater;
    public MainAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerHolder(mInflater.inflate(R.layout.item_main_banner, parent, false));
        } else if (viewType == TYPE_MENU) {
            return new MenuHolder(mInflater.inflate(R.layout.item_main_menu, parent, false));
        } else if(viewType == TYPE_LIST){
            return new ListHolder(mInflater.inflate(R.layout.item_main_list, parent, false));
        } else if(viewType == TYPE_FOOTER){
            return new FooterHolder(mInflater.inflate(R.layout.item_main_footer, parent, false));
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            ((BannerHolder) holder).setData(mBannerData);
        }else if(holder instanceof MenuHolder){
            ((MenuHolder) holder).setData(mMenuData);
        }else if(holder instanceof ListHolder) {
            ((ListHolder) holder).setData(mListData.get(position -2));
        }else if(holder instanceof FooterHolder) {
            ((FooterHolder) holder).setData(mFooterData);
        }
    }

    @Override
    public int getItemCount() {
        return mBannerData == null || mMenuData == null || mListData == null ? 0 : mFooterData? mListData.size()+3:mListData.size()+2;
    }


    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (position == 0) {
            return TYPE_BANNER;
        }else if (position == 1) {
            return TYPE_MENU;
        }else if(position >= 2 && position < getItemCount()) {
            return TYPE_LIST;
        }else if(position == getItemCount()){
            return TYPE_FOOTER;
        }else{
            return -1;
        }
    }

    public void setBannerData(List<BannerEntity> data) {
        this.mBannerData = data;
        notifyDataSetChanged();
    }

    public void setMenuData(List<MenuEntity> data) {
        this.mMenuData = data;
        notifyDataSetChanged();
    }

    public void setListData(List<ListEntity> data) {
        this.mListData = data;
        notifyDataSetChanged();
    }

    public void setFooterData(boolean footerData) {
        this.mFooterData = footerData;
        notifyDataSetChanged();
    }

    public void setData(List<BannerEntity> bannerData, List<MenuEntity> menuData, List<ListEntity> listData, boolean footerData) {
        this.mBannerData = bannerData;
        this.mMenuData = menuData;
        this.mListData = listData;
        this.mFooterData = footerData;
        notifyDataSetChanged();
    }
}
