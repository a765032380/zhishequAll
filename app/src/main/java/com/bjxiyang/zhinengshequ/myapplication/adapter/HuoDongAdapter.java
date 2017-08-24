package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.LuXianXiangQingActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.bean.FindHuoDongList;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.dialog.JoinLuXianDialog;
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

    private JoinLuXianDialog joinLuXianDialog;
    private ViewHolder viewHolder;
    private int partyId;
    private boolean isJoin;
    private int status;

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
    public View getView(int position, View view, ViewGroup parent) {
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_activityplanning,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        FindHuoDongList.ObjBean obj=mList.get(position);

        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_photo,obj.getUserUrl());

        if (obj.getImgList().size()==3){
            viewHolder.iv_photo1.setVisibility(View.VISIBLE);
            viewHolder.iv_photo2.setVisibility(View.VISIBLE);
            viewHolder.iv_photo3.setVisibility(View.VISIBLE);
        }else if (obj.getImgList().size()==2){
            viewHolder.iv_photo1.setVisibility(View.VISIBLE);
            viewHolder.iv_photo2.setVisibility(View.VISIBLE);
            viewHolder.iv_photo3.setVisibility(View.INVISIBLE);
        }else if (obj.getImgList().size()==1){
            viewHolder.iv_photo1.setVisibility(View.VISIBLE);
            viewHolder.iv_photo2.setVisibility(View.INVISIBLE);
            viewHolder.iv_photo3.setVisibility(View.INVISIBLE);
        }else if (obj.getImgList().size()==0){
            viewHolder.iv_photo1.setVisibility(View.INVISIBLE);
            viewHolder.iv_photo2.setVisibility(View.INVISIBLE);
            viewHolder.iv_photo3.setVisibility(View.INVISIBLE);
        }
        if (obj.getIsEnd()==0){
            viewHolder.tv_activity_status.setText("进行中");
        }else if (obj.getIsEnd()==1){
            viewHolder.tv_activity_status.setText("已结束");
        }

        if (obj.getImgList().size()>0) {
            viewHolder.ll_image_three.setVisibility(View.VISIBLE);
            for (int i = 0; i < obj.getImgList().size(); i++) {
                if (i == 0) {
                    ImageLoaderManager.getInstance(mContext)
                            .displayImage(viewHolder.iv_photo1, obj.getImgList().get(i).getImgUrl());
                } else if (i == 1) {

                    ImageLoaderManager.getInstance(mContext)
                            .displayImage(viewHolder.iv_photo2, obj.getImgList().get(i).getImgUrl());
                } else if (i == 2) {

                    ImageLoaderManager.getInstance(mContext)
                            .displayImage(viewHolder.iv_photo3, obj.getImgList().get(i).getImgUrl());
                }
            }
        }else {
            viewHolder.ll_image_three.setVisibility(View.GONE);
        }


        viewHolder.tv_nickname.setText(obj.getUserName());
        viewHolder.tv_publish_date.setText(obj.getAddTime());
        viewHolder.tv_end_date.setText(obj.getEndTime());
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
        if (obj.getCostType()==3){
            viewHolder.tv_go_money.setText("你请客");
        }

        viewHolder.tv_go_require.setText(obj.getPartyRequirement());
        viewHolder.tv_message_count.setText(obj.getReplyCount()+"");
        viewHolder.tv_join_count.setText(obj.getJoinCount()+"");
        partyId=mList.get(position).getPartyId();

        if (obj.getHaveJoin()==0&&obj.getIsEnd()==0){
            final FindHuoDongList.ObjBean obj1=obj;
            viewHolder.tv_btn_join.setText("加入");
            viewHolder.tv_btn_join.setBackgroundResource(R.drawable.a_btn_join);
            viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isJoin=false;
                    status=1;
                    showTiShiJoinDialog(obj1,status);
                }
            });
            //加入活动

        }else if (obj.getHaveJoin()==1&&obj.getIsEnd()==0){
            final FindHuoDongList.ObjBean obj1=obj;
            viewHolder.tv_btn_join.setBackgroundResource(R.drawable.a_btn_over);
            viewHolder.tv_btn_join.setText("退出活动");

            viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isJoin=true;
                    status=0;
                    showTiShiJoinDialog(obj1,status);
                }
            });
        }else {
            viewHolder.tv_btn_join.setBackgroundResource(R.drawable.a_btn_over);
            viewHolder.tv_btn_join.setText("已结束");
        }

//        if (obj.getHaveJoin()==0&&obj.getIsEnd()==0){
//            viewHolder.tv_btn_join.setClickable(true);
//            viewHolder.tv_btn_join.setBackgroundResource(R.drawable.a_btn_join);
//            viewHolder.tv_btn_join.setText("加入");
//            //加入活动
//            viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    String url= XY_Response2.URL_NEIGHBOR_JOINPARTY+"cmemberId="+
//                            SPManager.getInstance().getString("c_memberId",null)+
//                            "&partyId="+mList.get(position1).getPartyId();
//                    RequestCenter.neighbor_joinparty(url, new DisposeDataListener() {
//                        @Override
//                        public void onSuccess(Object responseObj) {
//                            FanHui2 fanHui= (FanHui2) responseObj;
//                            final FindHuoDongList.ObjBean obj=mList.get(position1);
//                            if (fanHui.getCode()==1000){
//                                Log.i("LLLL","加入活动请求成功");
//                                obj.setHaveJoin(1);
//                                obj.setJoinCount(obj.getJoinCount()+1);
//                                viewHolder1.tv_btn_join.setText("已加入");
//                                notifyDataSetChanged();
//                            }
//
//
//                        }
//
//                        @Override
//                        public void onFailure(Object reasonObj) {
//                            Log.i("LLLL","加入活动请求失败");
//                        }
//                    });
//                }
//            });
//        }else if (obj.getHaveJoin()==1&&obj.getIsEnd()==0){
//            viewHolder.tv_btn_join.setClickable(true);
//            viewHolder.tv_btn_join.setBackgroundResource(R.drawable.a_btn_over);
//            viewHolder.tv_btn_join.setText("已加入");
//            viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MyUntil.show(mContext,"你已加入该活动");
//                }
//            });
//        }else {
//            viewHolder.tv_btn_join.setBackgroundResource(R.drawable.a_btn_over);
//            viewHolder.tv_btn_join.setText("已结束");
//            viewHolder.tv_btn_join.setClickable(false);
//        }




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
        @BindView(R.id.ll_image_three)
        LinearLayout ll_image_three;



    }
    private void showTiShiJoinDialog(final FindHuoDongList.ObjBean obj, final int status) {

        joinLuXianDialog=new JoinLuXianDialog(mContext,status,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = XY_Response2.URL_NEIGHBOR_JOINPARTY + "cmemberId=" +
                                SPManager.getInstance().getString("c_memberId", null) +
                                "&partyId=" + obj.getPartyId()
                                +"&status="+status;
                        RequestCenter.neighbor_joinparty(url, new DisposeDataListener() {
                            @Override
                            public void onSuccess(Object responseObj) {
                                FanHui2 fanHui = (FanHui2) responseObj;

                                if (fanHui.getCode() == 1000) {
                                    if (!isJoin){
                                        isJoin=true;
                                        obj.setHaveJoin(1);
                                        obj.setJoinCount(obj.getJoinCount() + 1);
                                        viewHolder.tv_join_count.setText(obj.getJoinCount());
                                        viewHolder.tv_btn_join.setText("退出活动");
                                    }else {
                                        isJoin=false;
                                        obj.setHaveJoin(0);
                                        obj.setJoinCount(obj.getJoinCount() -1);
                                        viewHolder.tv_join_count.setText(obj.getJoinCount());
                                        viewHolder.tv_btn_join.setText("加入");
                                    }

                                } else {
                                    MyUntil.show(mContext, fanHui.getMsg());
                                }
                                joinLuXianDialog.dismiss();
                            }
                            @Override
                            public void onFailure(Object reasonObj) {
                                joinLuXianDialog.dismiss();
                            }
                        });

                    }
                });
        joinLuXianDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                notifyDataSetChanged();
            }
        });
        joinLuXianDialog.show();
    }
}
