package com.example.administrator.yunproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yunproject.Bean.VideoListBean;
import com.example.administrator.yunproject.Listener.OnRecyclerViewItemClickListener;
import com.example.administrator.yunproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/31.
 */

public class VideoDetailAdapter extends RecyclerView.Adapter<VideoDetailAdapter.ViewHolder> {

    private Context mContext;
    private List<VideoListBean.DataBean.CatalogBean> mChartsInfoList;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private int defItem = -1;

    public VideoDetailAdapter(Context mContext, List<VideoListBean.DataBean.CatalogBean> mChartsInfoList, OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener) {
        this.mContext = mContext;
        this.mChartsInfoList = mChartsInfoList;
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
    }

    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_detaillayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(mChartsInfoList.get(position).getVideo_title());
        if (mChartsInfoList.get(position).getIs_down() == 0) {
            holder.loadVideo.setVisibility(View.VISIBLE);    //下载按钮隐藏
            holder.updataVideo.setVisibility(View.GONE);
        } else {
            holder.loadVideo.setVisibility(View.GONE);    //下载按钮隐藏
            holder.updataVideo.setVisibility(View.VISIBLE);
            holder.updataVideo.setText("下载完成");
        }
        if (position == 0) {
            holder.name.setTextColor(Color.parseColor("#03C777"));
            holder.buleIamge.setImageResource(R.drawable.bule_image);
        }

        holder.videoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRecyclerViewItemClickListener.onRecyclerViewItemClicked(position, holder, mChartsInfoList.get(position).getId(), holder.updataVideo);
                setDefSelect(position);
            }
        });

        holder.loadVideo.setOnClickListener(new View.OnClickListener() {   //下载
            @Override
            public void onClick(View view) {
                holder.loadVideo.setVisibility(View.GONE);    //下载按钮隐藏
                holder.updataVideo.setVisibility(View.VISIBLE);
                mOnRecyclerViewItemClickListener.onRecyclerViewItemClicked(-50, holder, mChartsInfoList.get(position).getId(), holder.updataVideo);

            }
        });


        if (defItem != -1) {
            if (defItem == position) {
                holder.name.setTextColor(Color.parseColor("#03C777"));
                holder.buleIamge.setImageResource(R.drawable.bule_image);
//                holder.tv.setBackgroundResource(R.drawable.buttonstyle_ba_on);
            } else {
                holder.name.setTextColor(Color.parseColor("#000000"));
                holder.buleIamge.setImageResource(R.drawable.green_image);
//                holder.tv.setBackgroundResource(R.drawable.buttonstyle_ba);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mChartsInfoList != null ? mChartsInfoList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bule_iamge)
        ImageView buleIamge;
        @BindView(R.id.play_image)
        ImageView playImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.load_video)
        TextView loadVideo;
        @BindView(R.id.updata_video)
        TextView updataVideo;
        @BindView(R.id.video_detail)
        RelativeLayout videoDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
