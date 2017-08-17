package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.bean.FindHuoDongList;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class HuoDongAdapter extends BaseAdapter {
    private Context mContext;
    private List<FindHuoDongList.ObjBean> mList;

    public HuoDongAdapter(Context mContext, List mList) {
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
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_activityplanning,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final FindHuoDongList.ObjBean obj=mList.get(position);

        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_photo,obj.getUserUrl());
        for (int i=0;i<obj.getImgList().size();i++){
            if (i==0){
                ImageLoaderManager.getInstance(mContext)
                        .displayImage(viewHolder.iv_photo1,obj.getImgList().get(i).getImgUrl());
            }
            if (i==1){
                ImageLoaderManager.getInstance(mContext)
                        .displayImage(viewHolder.iv_photo2,obj.getImgList().get(i).getImgUrl());
            }
            if (i==2){
                ImageLoaderManager.getInstance(mContext)
                        .displayImage(viewHolder.iv_photo3,obj.getImgList().get(i).getImgUrl());
            }
        }


        viewHolder.tv_nickname.setText(obj.getUserName());
        viewHolder.tv_publish_date.setText(obj.getAddTime());
//        viewHolder.tv_publish_time.setText("ddddd");
        viewHolder.tv_activity_status.setText("ddddd");
        viewHolder.tv_activity_distance.setText("ddddd");
        viewHolder.tv_end_date.setText(obj.getEndTime());
//        viewHolder.tv_end_time.setText("ddddd");
        viewHolder.tv_intro.setText(obj.getPartyDescribe());
        viewHolder.tv_go_address.setText(obj.getDestination());
        if (obj.getCostType()==0){
            viewHolder.tv_go_money.setText("我买单");
        }
        if (obj.getCostType()==1){
            viewHolder.tv_go_money.setText("免费");
        }
        if (obj.getCostType()==2){
            viewHolder.tv_go_money.setText("线下AA");
        }

        viewHolder.tv_go_require.setText(obj.getPartyRequirement());
        viewHolder.tv_message_count.setText(obj.getReplyCount()+"");
        viewHolder.tv_join_count.setText(obj.getJoinCount()+"");

        if (obj.getHaveJoin()==0){
            viewHolder.tv_btn_join.setText("加入");
            //加入活动
            viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url= XY_Response2.URL_NEIGHBOR_JOINPARTY+"cmemberId="+
                            SPManager.getInstance().getString("c_memberId",null)+
                            "&partyId="+mList.get(position).getPartyId();
                    RequestCenter.neighbor_joinparty(url, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            FanHui2 fanHui= (FanHui2) responseObj;

                            if (fanHui.getCode()==1000){
                                Log.i("LLLL","加入活动请求成功");
                                obj.setHaveJoin(1);
                                obj.setJoinCount(obj.getJoinCount()+1);
                                viewHolder.tv_btn_join.setText("已加入");
                                notifyDataSetChanged();
                            }


                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            Log.i("LLLL","加入活动请求失败");
                        }
                    });
                }
            });
        }else if (obj.getHaveJoin()==1){
            viewHolder.tv_btn_join.setText("已加入");
            viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyUntil.show(mContext,"你已加入该活动");
                }
            });
        }




        return view;
    }
    class ViewHolder{
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

        @BindView(R.id.iv_photo)
        CircleImageView iv_photo;
        @BindView(R.id.tv_nickname)
        TextView tv_nickname;
        @BindView(R.id.tv_publish_date)
        TextView tv_publish_date;
        @BindView(R.id.tv_publish_time)
        TextView tv_publish_time;
        @BindView(R.id.tv_activity_status)
        TextView tv_activity_status;
        @BindView(R.id.tv_activity_distance)
        TextView tv_activity_distance;
        @BindView(R.id.tv_end_date)
        TextView tv_end_date;
        @BindView(R.id.tv_end_time)
        TextView tv_end_time;
        @BindView(R.id.tv_intro)
        TextView tv_intro;
        @BindView(R.id.tv_go_address)
        TextView tv_go_address;
        @BindView(R.id.tv_go_money)
        TextView tv_go_money;
        @BindView(R.id.tv_go_require)
        TextView tv_go_require;
        @BindView(R.id.iv_photo1)
        ImageView iv_photo1;
        @BindView(R.id.iv_photo2)
        ImageView iv_photo2;
        @BindView(R.id.iv_photo3)
        ImageView iv_photo3;
        @BindView(R.id.tv_message_count)
        TextView tv_message_count;
        @BindView(R.id.tv_join_count)
        TextView tv_join_count;
        @BindView(R.id.tv_btn_join)
        TextView tv_btn_join;




    }
}
