package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.OpenDoorList;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 17-5-23.
 */

public class XYMenJinJiLuAdapter extends BaseAdapter {
    private Context mContext;
    private List<OpenDoorList.Obj> list;
    private String date_date;

    public XYMenJinJiLuAdapter(Context mContext, List<OpenDoorList.Obj> list) {
        this.mContext = mContext;
        this.list = list;
    }
    public void setList(List<OpenDoorList.Obj> list){
        for (int i=0;i<list.size();i++){
            this.list.add(list.get(i));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_menjinjilu,null);
            viewHolder=new ViewHolder(view);
//            viewHolder.menjinjilu_name= (TextView) view.findViewById(R.id.menjinjilu_name);
//            viewHolder.menjinjilu_address= (TextView) view.findViewById(R.id.menjinjilu_address);
//            viewHolder.menjinjilu_date= (TextView) view.findViewById(R.id.menjinjilu_date);
//            viewHolder.im_menjinjilu= (ImageView) view.findViewById(R.id.im_menjinjilu);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        if (position==0) {
            OpenDoorList.Obj obj = list.get(position);
            String date = obj.getOpenTime();
            String[] strs = date.split(" ");
            date_date = strs[0];
            viewHolder.iv_menjinjilu_up.setVisibility(View.INVISIBLE);
            viewHolder.view_gone.setVisibility(View.VISIBLE);
            viewHolder.ll_date_gone.setVisibility(View.VISIBLE);
            viewHolder.tv_menjinjilu_date.setText(date_date);
            viewHolder.menjinjilu_name.setText(obj.getUserName());
            viewHolder.tv_menjinjilu_item_time.setText(strs[1]);
            viewHolder.menjinjilu_address.setText(obj.getCommunityName()+obj.getNperName());
            ImageLoaderManager.getInstance(mContext)
                    .displayImage(viewHolder.iv_menjinjilu_touxiang,obj.getHeadPhotoUrl());
        }else {
            String date_date2;
            OpenDoorList.Obj obj = list.get(position);
            String date = obj.getOpenTime();
            String[] strs = date.split(" ");
            date_date2 = strs[0];
            if (date_date2.equals(date_date)){
                viewHolder.view_gone.setVisibility(View.GONE);
                viewHolder.ll_date_gone.setVisibility(View.GONE);
//                viewHolder.iv_menjinjilu_up.setVisibility(View.GONE);
            }else {
                viewHolder.view_gone.setVisibility(View.VISIBLE);
                viewHolder.ll_date_gone.setVisibility(View.VISIBLE);
                viewHolder.tv_menjinjilu_date.setText(date_date2);
                viewHolder.iv_menjinjilu_up.setVisibility(View.VISIBLE);
            }
            date_date=date_date2;

            viewHolder.menjinjilu_name.setText(obj.getUserName());
            viewHolder.tv_menjinjilu_item_time.setText(strs[1]);
            viewHolder.menjinjilu_address.setText(obj.getCommunityName()+obj.getNperName());
            ImageLoaderManager.getInstance(mContext)
                    .displayImage(viewHolder.iv_menjinjilu_touxiang,obj.getHeadPhotoUrl());
        }
//        viewHolder.menjinjilu_name.setText(list.get(position).getUserName());

//        Plot plot=list.get(position).getCommunityName();
//        viewHolder.menjinjilu_address.setText(
//                list.get(position).getCommunityName()+list.get(position).getNperName()+
//                        list.get(position).getFloorName()+list.get(position).getUnitName());
//        viewHolder.menjinjilu_address.setText(plot.getPlot()+" " +
//                ""+plot.getBuildingNo()+"号楼"+plot.getUnitNumber()+"单元");

//        viewHolder.menjinjilu_date.setText(list.get(position).getOpenTime());
//        if (list.get(position).get== UserType.USER_FOLK){
//            viewHolder.im_menjinjilu.setBackgroundResource(R.drawable.c_icon_jiaren);
//        }else if(list.get(position).getRoleType()== UserType.USER_LESSEE){
//            viewHolder.im_menjinjilu.setBackgroundResource(R.drawable.c_icon_zuge);
//        }
        return view;
    }

    class ViewHolder{
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.tv_menjinjilu_date)
        TextView tv_menjinjilu_date;
        @BindView(R.id.tv_menjinjilu_xingqi)
        TextView tv_menjinjilu_xingqi;
//        @BindView(R.id.lv_menjinjilu_item)
//        ListView lv_menjinjilu_item;
        @BindView(R.id.ll_date_gone)
        LinearLayout ll_date_gone;
        @BindView(R.id.iv_menjinjilu_up)
        ImageView iv_menjinjilu_up;
        @BindView(R.id.view_gone)
        View view_gone;

        @BindView(R.id.tv_menjinjilu_item_time)
        TextView tv_menjinjilu_item_time;
        @BindView(R.id.iv_menjinjilu_touxiang)
        CircleImageView iv_menjinjilu_touxiang;
        @BindView(R.id.menjinjilu_name)
        TextView menjinjilu_name;
        @BindView(R.id.menjinjilu_address)
        TextView menjinjilu_address;

//        TextView menjinjilu_name;
//        TextView menjinjilu_address;
//        TextView menjinjilu_date;
//        ImageView im_menjinjilu;

    }
}
