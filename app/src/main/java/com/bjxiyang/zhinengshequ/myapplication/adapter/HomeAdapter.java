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
 * Created by Administrator on 2017/8/3 0003.
 */

public class HomeAdapter extends BaseAdapter {

    public HomeAdapter(Context mContext,List<HomeItem> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    private List<HomeItem> mList;
    private Context mContext;

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
        ViewHolder viewHolder=null;
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_home_super,null);
            viewHolder.iv_item_tijiaodingdan_shangpinimage=
                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_shangpinimage);
            viewHolder.tv_item_tijiaodingdan_shangpinname=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_shangpinname);
            viewHolder.tv_item_tijiaodingdan_shangpinguige=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_shangpinguige);
            viewHolder.tv_item_tijiaodingdan_money=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_money);
            viewHolder.tv_item_tijiaodingdan_count=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_count);
            viewHolder.iv_item_tijiaodingdan_jian=
                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_jian);
            viewHolder.iv_item_tijiaodingdan_jia=
                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_jia);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_tijiaodingdan_shangpinname.setText(mList.get(position).getName());

        return view;
    }
    class ViewHolder{
        ImageView iv_item_tijiaodingdan_shangpinimage;
        TextView tv_item_tijiaodingdan_shangpinname;
        TextView tv_item_tijiaodingdan_shangpinguige;
        TextView tv_item_tijiaodingdan_money;
        TextView tv_item_tijiaodingdan_count;
        ImageView iv_item_tijiaodingdan_jian;
        ImageView iv_item_tijiaodingdan_jia;
    }
}
