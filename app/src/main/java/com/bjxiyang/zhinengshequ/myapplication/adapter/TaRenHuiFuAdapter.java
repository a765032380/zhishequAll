package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class TaRenHuiFuAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;

    public TaRenHuiFuAdapter(Context mContext, List mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_huifunideren,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_photo,null);

        viewHolder.tv_huifunideren.setText("");
        viewHolder.tv_date.setText("");
        viewHolder.tv_huifu_fatie.setText("");
        viewHolder.tv_huifu_neirong.setText("");
        viewHolder.tv_wodefabiao.setText("");


        return view;
    }
    class ViewHolder{
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.ll_tarenhuifu)
        LinearLayout ll_tarenhuifu;
        @BindView(R.id.ll_xitongxiaoxi)
        LinearLayout ll_xitongxiaoxi;


        @BindView(R.id.iv_photo)
        CircleImageView iv_photo;
        @BindView(R.id.tv_huifunideren)
        TextView tv_huifunideren;
        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_huifu_fatie)
        TextView tv_huifu_fatie;
        @BindView(R.id.tv_huifu_neirong)
        TextView tv_huifu_neirong;
        @BindView(R.id.tv_wodefabiao)
        TextView tv_wodefabiao;



    }
}
