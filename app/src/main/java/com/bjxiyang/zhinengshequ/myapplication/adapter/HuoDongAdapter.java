package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class HuoDongAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;

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
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_activityplanning,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_nickname.setText("ddddd");
        viewHolder.tv_publish_date.setText("ddddd");
        viewHolder.tv_publish_time.setText("ddddd");
        viewHolder.tv_activity_status.setText("ddddd");
        viewHolder.tv_activity_distance.setText("ddddd");
        viewHolder.tv_end_date.setText("ddddd");
        viewHolder.tv_end_time.setText("ddddd");
        viewHolder.tv_intro.setText("ddddd");
        viewHolder.tv_go_address.setText("ddddd");
        viewHolder.tv_go_money.setText("ddddd");
        viewHolder.tv_go_require.setText("ddddd");
        viewHolder.tv_message_count.setText("ddddd");
        viewHolder.tv_join_count.setText("ddddd");
        //加入活动
        viewHolder.tv_btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= XY_Response2.URL_NEIGHBOR_JOINPARTY+"cmemberId="+
                        SPManager.getInstance().getString("c_memberId",null)+
                        "partyId=";
            }
        });


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
