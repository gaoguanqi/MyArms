package com.maple.demo.myarms.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maple.demo.myarms.app.base.BaseFragment;

import java.util.List;

/**
 * author: gaogq
 * time: 2018/12/19 11:45
 * description:
 */
public class HomePagerAdapter extends FragmentPagerAdapter{
    private List<BaseFragment> mList;
    public HomePagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        if (mList != null && mList.size() > 0) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0: mList.size();
    }
}
