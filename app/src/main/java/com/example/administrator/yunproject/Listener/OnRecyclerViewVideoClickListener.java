package com.example.administrator.yunproject.Listener;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/4/5.
 */

public interface OnRecyclerViewVideoClickListener {
    void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder,String videourl);

    void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder);

}
