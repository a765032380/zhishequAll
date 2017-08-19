package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.LuXianXiangQingActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;
import com.bjxiyang.zhinengshequ.myapplication.dialog.PingLunDialog;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class LuXianPingLunAdapter extends BaseAdapter {
    private Context mContext;
    private List<HuoDongDetails.ObjBean.ReplyListBean> mList;
//    private List<HuoDongDetails.ObjBean.ReplyListBean> mList1=new ArrayList<>();
//    private List<HuoDongDetails.ObjBean.ReplyListBean> mList2=new ArrayList<>();

    public LuXianPingLunAdapter(Context mContext, List<HuoDongDetails.ObjBean.ReplyListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;

//        Log.i("YYYY","ReplyList="+mList.size());
//        for (int i=0;i<mList.size();i++){
//            Log.i("YYYY","测试");
//            Log.i("YYYY",mList.get(i).getCommentId()+"");
//
//            if (mList.get(i).getCommentId()==0){
//                mList1.add(mList.get(i));
//            }else if (mList.get(i).getCommentId()==1){
//                mList2.add(mList.get(i));
//            }
//        }

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
            view= LayoutInflater.from(mContext).inflate(R.layout.item_luxianxiangqing_pinglun,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        HuoDongDetails.ObjBean.ReplyListBean reply=mList.get(position);


        if (reply.getToUserUrl()!=null&&!reply.getToUserUrl().equals("")) {
            ImageLoaderManager.getInstance(mContext)
                    .displayImage(viewHolder.tv_touxiang, reply.getToUserUrl());
        }
        viewHolder.tv_nickname.setText(reply.getToUserName());
        viewHolder.tv_neirong.setText(reply.getReplyContent());
        viewHolder.tv_pinglunriqi.setText(reply.getReplyTime());
        if (position==0){
            viewHolder.tv_shafa.setText("沙发");
        }else if (position==1){
            viewHolder.tv_shafa.setText("板凳");
        }else if (position==2){
            viewHolder.tv_shafa.setText("地板");
        }else{
            viewHolder.tv_shafa.setText((position+1)+"楼");
        }

        HuoDongDetails.ObjBean.ReplyListBean.ParentItemBean parentItem=reply.getParentItem() ;
        if (parentItem!=null) {
                viewHolder.ll_pinglun_item.setVisibility(View.VISIBLE);
                viewHolder.tv_huifu_name.setText(parentItem.getFromUserName());
                viewHolder.tv_huifu_date.setText(parentItem.getReplyTime());
                viewHolder.tv_huifupinglun.setText(parentItem.getReplyContent());
                viewHolder.tv_huifu_floor.setText("回复在几楼");
        }else {
            viewHolder.ll_pinglun_item.setVisibility(View.GONE);
        }





//        for (int i=0;i<mList2.size();i++){
//            if (mList2.get(i).getCommentId()==mList1.get(position).getReplyId()){
//                PingLunItemAdapter adapter=new PingLunItemAdapter(mContext,mList2);
//                viewHolder.lv_item_pinglun.setAdapter(adapter);
//            }
//        }


        final HuoDongDetails.ObjBean.ReplyListBean reply2=reply;
        viewHolder.tv_pinglunhuifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("YYYY","reply2:PartyId="+reply2.getPartyId()+"---CommentId="+reply2.getCommentId());
                PingLunDialog pingLunDialog=new PingLunDialog(mContext,reply2,0);
                pingLunDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        LuXianXiangQingActivity.luXianXiangQingActivity.upData();
//                        notifyDataSetChanged();
                    }
                });
                pingLunDialog.show();
                pingLunDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                pingLunDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });

        return view;
    }
    class ViewHolder {
        @BindView(R.id.tv_touxiang)
        CircleImageView tv_touxiang;
        @BindView(R.id.tv_nickname)
        TextView tv_nickname;
        @BindView(R.id.tv_neirong)
        TextView tv_neirong;
//        @BindView(R.id.lv_item_pinglun)
//        ListView lv_item_pinglun;
        @BindView(R.id.tv_pinglunriqi)
        TextView tv_pinglunriqi;
        @BindView(R.id.tv_pinglunhuifu)
        TextView tv_pinglunhuifu;
        @BindView(R.id.tv_shafa)
        TextView tv_shafa;


        @BindView(R.id.tv_huifu_name)
        TextView tv_huifu_name;
        @BindView(R.id.tv_huifu_date)
        TextView tv_huifu_date;
        @BindView(R.id.tv_huifupinglun)
        TextView tv_huifupinglun;
        @BindView(R.id.tv_huifu_floor)
        TextView tv_huifu_floor;
        @BindView(R.id.ll_pinglun_item)
        LinearLayout ll_pinglun_item;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
