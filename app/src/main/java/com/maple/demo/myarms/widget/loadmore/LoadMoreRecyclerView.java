package com.maple.demo.myarms.widget.loadmore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * author: gaogq
 * time: 2018/12/21 9:24
 * description:
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private OnScrollBottomLitener litener;

    public void setOnScrollBottomLitener(OnScrollBottomLitener litener) {
        this.litener = litener;
    }

    public LoadMoreRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        // super.onScrolled(dx, dy);
        if (isScrollToBottom()) {
            litener.onScrollBottom();
        }
    }

    private boolean isScrollToBottom() {
        return this != null
                && this.computeVerticalScrollExtent() + this.computeVerticalScrollOffset()
                >= this.computeVerticalScrollRange();
    }

}