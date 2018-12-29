package com.maple.demo.myarms.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;
import com.maple.demo.myarms.mvp.model.entity.PlayerEntity;
import com.maple.demo.myarms.mvp.ui.adapter.listener.OnPlayerItemClickListener;
import com.maple.demo.myarms.widget.player.PlayerView;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: gaogq
 * time: 2018/12/28 11:50
 * description:
 */
public class PlayerAdapter extends  RecyclerView.Adapter<PlayerAdapter.ViewHolder> {


    public static final String TAG = "PLAYER";
    private Context mContext;

    private List<PlayerEntity> mData;

    private OnPlayerItemClickListener mListener;

    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public PlayerAdapter(Context context,List<PlayerEntity> data) {
        this.mContext = context;
        this.mData = data;
    }
    public void setOnClickLitener(OnPlayerItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_player, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position,mData.get(position));
        if(mListener != null){
            holder.player.setOnClickListener(v ->{
                mListener.onItemClickLitener();
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData == null || mData.isEmpty() ? 0 :mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.player)
        PlayerView player;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mAppComponent =  ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
            mImageLoader = mAppComponent.imageLoader();
        }

        public void setData(int position,PlayerEntity data) {

            //增加封面
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageLoader.loadImage(itemView.getContext(),
                    ImageConfigImpl
                            .builder()
                            .url(data.getImage())
                            .imageView(imageView)
                            .build());
            player.setThumbImageView(imageView);

            player.setUpLazy(data.getUrl(), true, null, null, data.getTitle());
            //增加title
            player.getTitleTextView().setVisibility(View.GONE);
            //设置返回键
            player.getBackButton().setVisibility(View.GONE);

            //设置全屏按键功能
            player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.startWindowFullscreen(mContext, false, true);
                }
            });

            //防止错位设置
            player.setPlayTag(TAG);
            player.setPlayPosition(position);
            //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
            player.setAutoFullWithSize(true);
            //音频焦点冲突时是否释放
            player.setReleaseWhenLossAudio(true);
            //全屏动画
            player.setShowFullAnimation(true);
            //小屏时不触摸滑动
            player.setIsTouchWiget(false);



        }
    }
}
