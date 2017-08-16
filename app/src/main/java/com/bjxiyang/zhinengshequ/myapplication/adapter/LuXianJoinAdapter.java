package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class LuXianJoinAdapter extends RecyclerView.Adapter<LuXianJoinAdapter.ViewHolder>   {

    private View view;
    private ViewHolder vh;
    private List<HuoDongDetails.ObjBean.JoinListBean> mList;

    public LuXianJoinAdapter(List<HuoDongDetails.ObjBean.JoinListBean> mList) {
        Log.i("YYYY","JoinList="+mList.size());
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_join,parent,false);
        vh= new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageLoaderManager.getInstance(view.getContext())
                .displayImage(holder.iv_join_img,mList.get(position).getUserUrl());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_join_img;
        public ViewHolder(View view){
            super(view);
            iv_join_img = (ImageView) view.findViewById(R.id.iv_join_img);
        }
    }
}
