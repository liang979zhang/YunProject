package com.example.administrator.yunproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yunproject.Bean.VideoData;
import com.example.administrator.yunproject.Listener.OnRecyclerViewVideoClickListener;
import com.example.administrator.yunproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/5.
 */

public class CacheAdapter extends RecyclerView.Adapter<CacheAdapter.ViewHolder> {

    private Context mContext;
    private List<VideoData> mVideoData;
    private OnRecyclerViewVideoClickListener mOnRecyclerViewItemClickListener;


    public CacheAdapter(Context mContext, List<VideoData> mVideoData, OnRecyclerViewVideoClickListener mOnRecyclerViewItemClickListener) {
        this.mContext = mContext;
        this.mVideoData = mVideoData;
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cache_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.videoTitle.setText(mVideoData.get(position).getTitle());
        holder.videoRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRecyclerViewItemClickListener.onRecyclerViewItemClicked(position, holder, mVideoData.get(position).getVideoPath());
            }
        });

        holder.videoRel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnRecyclerViewItemClickListener.onRecyclerViewItemLongClicked(position,holder);
                return true;
            }
        });


        //创建MediaMetadataRetriever对象
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        //绑定资源
        mmr.setDataSource(mVideoData.get(position).getVideoPath());
        //获取第一帧图像的bitmap对象
        Bitmap bitmap = mmr.getFrameAtTime();
        //加载到ImageView控件上
        holder.videoImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return mVideoData != null ? mVideoData.size() : 0;
    }

    public void setData(List<VideoData> tblist) {
        if (mVideoData.size() > 0) {
            mVideoData.clear();
        }
        mVideoData.addAll(tblist);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_image)
        ImageView videoImage;
        @BindView(R.id.video_title)
        TextView videoTitle;
        @BindView(R.id.video_rel)
        RelativeLayout videoRel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
