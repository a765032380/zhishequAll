package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class PingLunItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<HuoDongDetails.ObjBean.ReplyListBean> mList;

    public PingLunItemAdapter(Context mContext, List<HuoDongDetails.ObjBean.ReplyListBean> mList) {
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
            view= LayoutInflater.from(mContext).inflate(R.layout.item_pinglun_item,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
//        viewHolder.tv_huifu_name.setText();



        return view;
    }
    class ViewHolder{
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.tv_huifu_name)
        TextView tv_huifu_name;
        @BindView(R.id.tv_huifu_date)
        TextView tv_huifu_date;
        @BindView(R.id.tv_huifu_floor)
        TextView tv_huifu_floor;
        @BindView(R.id.tv_huifupinglun)
        TextView tv_huifupinglun;



    }


}
