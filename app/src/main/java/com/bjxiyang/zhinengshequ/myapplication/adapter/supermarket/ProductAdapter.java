package com.bjxiyang.zhinengshequ.myapplication.adapter.supermarket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.until.StringUtils;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by fengyongge on 2016/5/24 0024.
 */

/***
 * 底部购物车
 */
public class ProductAdapter extends BaseAdapter {
    GoodsAdapter goodsAdapter;
    private SupermarketActivity activity;
    private List<GouWuChe> dataList;
    private Context mContext;
    private List<GouWuChe> mList;
    private GouWuChe gouWuChe;
    DecimalFormat df=new DecimalFormat("0.##");
    public ProductAdapter(Context mContext,SupermarketActivity activity,
                          GoodsAdapter goodsAdapter, List<GouWuChe> dataList) {
        this.goodsAdapter =goodsAdapter;
        this.activity = activity;
        this.mContext=mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Viewholder viewholder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.super_product_item, null);
            viewholder = new Viewholder();
            viewholder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewholder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            viewholder.iv_add= (ImageView) view.findViewById(R.id.iv_add);
            viewholder.iv_remove= (ImageView) view.findViewById(R.id.iv_remove);
            viewholder.tv_count= (TextView) view.findViewById(R.id.tv_count);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        gouWuChe =dataList.get(position);
            if (gouWuChe != null) {
                //默认进来数量
                if (gouWuChe.getCount() < 1) {
                    DaoUtils.getStudentInstance().deleteObject(gouWuChe);
                    notifyDataSetChanged();
                } else {
                    viewholder.tv_count.setText(String.valueOf(gouWuChe.getCount()));
                    notifyDataSetChanged();
                }
            }
            StringUtils.filtNull(viewholder.tv_name, dataList.get(position).getName());//商品名称
            if (dataList.get(position).getIfDiscount() == 0) {
                StringUtils.filtNull(viewholder.tv_price, "￥" + df.format((double) dataList.get(position).getPrice() / 100));//商品价格
            } else {
                StringUtils.filtNull(viewholder.tv_price, "￥" + df.format((double) dataList.get(position).getDiscountPrice() / 100));//商品价格
            }


            viewholder.tv_count.setText(String.valueOf(dataList.get(position).getCount()));//商品数量

            final int position1 = position;
            final Viewholder viewholder1=viewholder;
            viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    GouWuChe gouwuche1=dataList.get(position1);
//                    gouwuche1.setCount(gouwuche1.getCount()+1);
//                    dataList.set(position1,gouwuche1);
                    viewholder1.tv_count.setText((dataList.get(position1).getCount())+"");
                    activity.handlerCarNum(1, dataList.get(position1), true);
                    goodsAdapter.notifyDataSetChanged();

                }
            });
            viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    GouWuChe gouwuche1=dataList.get(position1);
//                    gouwuche1.setCount(gouwuche1.getCount()-1);
//                    dataList.set(position1,gouwuche1);
                    if ((dataList.get(position1).getCount()-1)>0){
                        viewholder1.tv_count.setText((dataList.get(position1).getCount())+"");
                        activity.handlerCarNum(0, dataList.get(position1), true);
                    }else {
                        activity.handlerCarNum(0, dataList.get(position1), true);
                        Log.i("yyyy","测试刷新");
                        dataList.remove(position1);
                        notifyDataSetChanged();

                    }


                    goodsAdapter.notifyDataSetChanged();
                }
            });


        return view;
    }

    class Viewholder {
        TextView tv_price;
        TextView tv_name;
        ImageView iv_add,iv_remove;
        TextView tv_count;
    }


}