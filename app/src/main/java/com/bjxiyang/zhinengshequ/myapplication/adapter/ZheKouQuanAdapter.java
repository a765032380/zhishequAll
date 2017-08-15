package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.YouHuiQuan;

import java.util.List;

/**
 * Created by Administrator on 2017/7/2 0002.
 */

public class ZheKouQuanAdapter extends BaseAdapter {

    private Context mContext;
    private List<YouHuiQuan.ResultBean> mList;

    public ZheKouQuanAdapter(Context mContext, List<YouHuiQuan.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_zhekouquan,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_yopuhuiquan_hongbaoleixing= (TextView) view.findViewById(R.id.tv_yopuhuiquan_hongbaoleixing);
            viewHolder.tv_yopuhuiquan_youxiaoqixian= (TextView) view.findViewById(R.id.tv_yopuhuiquan_youxiaoqixian);
            viewHolder.tv_zhekouquan_xianzhishoujihao= (TextView) view.findViewById(R.id.tv_zhekouquan_xianzhishoujihao);
            viewHolder.tv_yopuhuiquan_zhekou= (TextView) view.findViewById(R.id.tv_yopuhuiquan_zhekou);
            viewHolder.tv_zhekouquan_status= (TextView) view.findViewById(R.id.tv_zhekouquan_status);
            viewHolder.tv_zhe= (TextView) view.findViewById(R.id.tv_zhe);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_yopuhuiquan_hongbaoleixing.setText(mList.get(position).getName());
        viewHolder.tv_yopuhuiquan_youxiaoqixian.setText(mList.get(position).getEndDate());
        viewHolder.tv_zhekouquan_xianzhishoujihao.setText(mList.get(position).getCouponNo()+"");
        Log.i("DiscountType",mList.get(position).getDiscountType()+"");
        if (mList.get(position).getDiscountType()==1) {
            viewHolder.tv_zhe.setText("折");
            viewHolder.tv_yopuhuiquan_zhekou.setText(((double) mList.get(position).getDiscount() / 10) + "");
            if (mList.get(position).getStatus() == 0) {
                viewHolder.tv_zhekouquan_status.setText("无效");
            } else {
                viewHolder.tv_zhekouquan_status.setText("有效");
            }
        }else if (mList.get(position).getDiscountType()==0) {
            viewHolder.tv_zhe.setText("元");
            viewHolder.tv_yopuhuiquan_zhekou.setText(((double) mList.get(position).getDiscount()) + "");
            if (mList.get(position).getStatus() == 0) {
                viewHolder.tv_zhekouquan_status.setText("无效");
            } else {
                viewHolder.tv_zhekouquan_status.setText("满"+mList.get(position).getMinConsume()/100+"元可用");
            }
        }

        return view;
    }

    class ViewHolder{
        TextView tv_zhe;
        TextView tv_yopuhuiquan_hongbaoleixing;
        TextView tv_yopuhuiquan_youxiaoqixian;
        TextView tv_zhekouquan_xianzhishoujihao;
        TextView tv_yopuhuiquan_zhekou;
        TextView tv_zhekouquan_status;

    }

}
