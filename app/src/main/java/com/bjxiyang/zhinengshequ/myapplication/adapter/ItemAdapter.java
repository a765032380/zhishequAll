package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.test.HomeItem;

import java.util.List;

/**
 * Created by gll on 2017/8/2.
 */

public class ItemAdapter extends BaseAdapter {
    private List<HomeItem> mList;
    private Context mContext;

    public ItemAdapter(Context mContext,List<HomeItem> mList){
        this.mContext=mContext;
        this.mList=mList;

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
        ViewHolder viewHoder=null;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.fragment_item,null);
            viewHoder=new ViewHolder();
            viewHoder.fragment_image= (ImageView) view.findViewById(R.id.fragment_image);
            viewHoder.home_product_sign= (TextView) view.findViewById(R.id.home_product_sign);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHolder) view.getTag();
        }
//        viewHoder.fragment_image
        viewHoder.home_product_sign.setText(mList.get(i).getName());

        return view;
    }
    class ViewHolder{
        ImageView fragment_image;
        TextView home_product_sign;

    }
}
