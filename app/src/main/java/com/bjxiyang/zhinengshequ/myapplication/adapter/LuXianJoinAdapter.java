package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class LuXianJoinAdapter extends BaseAdapter{
    private Context mContext;
    private List<HuoDongDetails.ObjBean.JoinListBean> mList;


    public LuXianJoinAdapter(Context mContext,List<HuoDongDetails.ObjBean.JoinListBean> mList) {
        this.mList = mList;
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_joinpersonnel,null);
            holder= new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        if (!mList.get(position).getUserUrl().equals("")&&mList.get(position).getUserUrl()!=null) {
            ImageLoaderManager.getInstance(view.getContext())
                    .displayImage(holder.iv_joinpersonnel_photo, mList.get(position).getUserUrl());
        }
        holder.tv_joinpersonnel_name.setText(mList.get(position).getUserName());
        return view;
    }

    class ViewHolder{
        ImageView iv_joinpersonnel_photo;
        TextView tv_joinpersonnel_name;
        TextView tv_joinpersonnel_joinstate;
        public ViewHolder(View view){
            iv_joinpersonnel_photo = (ImageView) view.findViewById(R.id.iv_joinpersonnel_photo);
            tv_joinpersonnel_name= (TextView) view.findViewById(R.id.tv_joinpersonnel_name);
            tv_joinpersonnel_joinstate= (TextView) view.findViewById(R.id.tv_joinpersonnel_joinstate);
        }
    }
}
