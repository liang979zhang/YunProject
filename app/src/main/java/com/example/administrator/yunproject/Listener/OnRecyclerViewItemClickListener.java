package com.example.administrator.yunproject.Listener;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.yunproject.Bean.VideoListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public interface OnRecyclerViewItemClickListener {
    void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder, String id, TextView updataVideo);

    void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder);
}
