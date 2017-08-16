package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;
import com.bjxiyang.zhinengshequ.myapplication.dialog.PingLunDialog;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class LuXianPingLunAdapter extends BaseAdapter {
    private Context mContext;
    private List<HuoDongDetails.ObjBean.ReplyListBean> mList;
    private List<HuoDongDetails.ObjBean.ReplyListBean> mList1=new ArrayList<>();
    private List<HuoDongDetails.ObjBean.ReplyListBean> mList2=new ArrayList<>();

    public LuXianPingLunAdapter(Context mContext, List<HuoDongDetails.ObjBean.ReplyListBean> mList) {
        this.mContext = mContext;
//        this.mList = mList;

        Log.i("YYYY","ReplyList="+mList.size());
        for (int i=0;i<mList.size();i++){
            Log.i("YYYY","测试");
            Log.i("YYYY",mList.get(i).getCommentId()+"");

            if (mList.get(i).getCommentId()==0){
                mList1.add(mList.get(i));
            }else if (mList.get(i).getCommentId()==1){
                mList2.add(mList.get(i));
            }
        }

    }

    @Override
    public int getCount() {
        return mList1.size();
    }

    @Override
    public Object getItem(int position) {
        return mList1.get(position);
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
        final HuoDongDetails.ObjBean.ReplyListBean reply=mList1.get(position);
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.tv_touxiang,reply.getToUserUrl());
        viewHolder.tv_nickname.setText(reply.getToUserName());
        viewHolder.tv_neirong.setText(reply.getReplyContent());
        viewHolder.tv_pinglunriqi.setText(reply.getReplyTime());
        for (int i=0;i<mList2.size();i++){
            if (mList2.get(i).getCommentId()==mList1.get(position).getReplyId()){
                PingLunItemAdapter adapter=new PingLunItemAdapter(mContext,mList2);
                viewHolder.lv_item_pinglun.setAdapter(adapter);
            }
        }



        viewHolder.tv_pinglunhuifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PingLunDialog pingLunDialog=new PingLunDialog(mContext,reply,0);
                pingLunDialog.show();
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
        @BindView(R.id.lv_item_pinglun)
        ListView lv_item_pinglun;
        @BindView(R.id.tv_pinglunriqi)
        TextView tv_pinglunriqi;
        @BindView(R.id.tv_pinglunhuifu)
        TextView tv_pinglunhuifu;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
