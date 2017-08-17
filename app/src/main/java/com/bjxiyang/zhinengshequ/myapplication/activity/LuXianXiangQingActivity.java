package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.LuXianJoinAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.LuXianPingLunAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.Text;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.dialog.PingLunDialog;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class LuXianXiangQingActivity extends MySwipeBackActivity
        implements View.OnClickListener{
    /**
     * UI
     */
    @BindView(R.id.iv_luxianxiangqing_fanhui)
    RelativeLayout iv_luxianxiangqing_fanhui;
    @BindView(R.id.ll_luxianxiangqing_more)
    LinearLayout ll_luxianxiangqing_more;
    @BindView(R.id.iv_photo)
    CircleImageView iv_photo;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_publish_date)
    TextView tv_publish_date;
    @BindView(R.id.tv_publish_time)
    TextView tv_publish_time;
    @BindView(R.id.tv_end_date)
    TextView tv_end_date;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_intro)
    TextView tv_intro;
    @BindView(R.id.iv_photo1)
    ImageView iv_photo1;
    @BindView(R.id.iv_photo2)
    ImageView iv_photo2;
    @BindView(R.id.iv_photo3)
    ImageView iv_photo3;
    @BindView(R.id.tv_go_time)
    TextView tv_go_time;
    @BindView(R.id.tv_come_time)
    TextView tv_come_time;
    @BindView(R.id.tv_go_address)
    TextView tv_go_address;
    @BindView(R.id.tv_arrive_address)
    TextView tv_arrive_address;
    @BindView(R.id.tv_go_money)
    TextView tv_go_money;
    @BindView(R.id.tv_date_count)
    TextView tv_date_count;
    @BindView(R.id.tv_go_require)
    TextView tv_go_require;
    @BindView(R.id.tv_message_count)
    TextView tv_message_count;
    @BindView(R.id.tv_join_count)
    TextView tv_join_count;
    @BindView(R.id.lv_luxianxiangqing)
    ListView lv_luxianxiangqing;
    @BindView(R.id.tv_pinglun_none)
    TextView tv_pinglun_none;
    @BindView(R.id.id_recyclerview_horizontal)
    RecyclerView id_recyclerview_horizontal;
    @BindView(R.id.tv_fabiaoyanlun)
    TextView tv_fabiaoyanlun;
    @BindView(R.id.tv_join)
    TextView tv_join;
    @BindView(R.id.tv_yijiaru)
    LinearLayout tv_yijiaru;
    @BindView(R.id.tv_pinglun)
    LinearLayout tv_pinglun;
    @BindView(R.id.ll_pinglun)
    LinearLayout ll_pinglun;
    @BindView(R.id.ll_join)
    LinearLayout ll_join;
    @BindView(R.id.iv_pinglun_heidian)
    ImageView iv_pinglun_heidian;
    @BindView(R.id.iv_yijiaru_heidian)
    ImageView iv_yijiaru_heidian;
    @BindView(R.id.myScrollView)
    ScrollView myScrollView;


    /***
     * Data
     */
    private int partyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxianxiangqing);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        partyId = intent.getIntExtra("partyId",-1);
        initUI();
        getData();
    }

    private void initUI() {
        iv_luxianxiangqing_fanhui.setOnClickListener(this);
        ll_luxianxiangqing_more.setOnClickListener(this);
        tv_pinglun.setOnClickListener(this);
        tv_yijiaru.setOnClickListener(this);
        tv_fabiaoyanlun.setOnClickListener(this);
        tv_join.setOnClickListener(this);

    }

    private void getData(){
        String url= XY_Response2.URL_NEIGHBOR_FINDPARTYDETAILS+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null)+"&partyId="+partyId;
        RequestCenter.neighbor_findpartydetails(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                HuoDongDetails huoDongDetails= (HuoDongDetails) responseObj;
                if (huoDongDetails.getCode()==1000){
                    setData(huoDongDetails.getObj());
                    myScrollView.post(new Runnable() {
                        //让scrollview跳转到顶部，必须放在runnable()方法中
                        @Override
                        public void run() {
                            myScrollView.scrollTo(0,0);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_luxianxiangqing_fanhui:
                finish();
                break;
            case R.id.ll_luxianxiangqing_more:
                break;
            case R.id.tv_pinglun:
                showPingLun();
                break;
            case R.id.tv_yijiaru:
                showJoinList();
                break;
            case R.id.tv_fabiaoyanlun:
                showYanLun();
                break;
            case R.id.tv_join:
                join();
                break;
        }
    }
    private void join(){

    }
    private void setData(final HuoDongDetails.ObjBean obj){

        ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                .displayImage(iv_photo,obj.getUserUrl());
        for (int i=0;i<obj.getImgList().size();i++){
            if (i==0){
                ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                        .displayImage(iv_photo1,obj.getImgList().get(i).getImgUrl());
            }
            if (i==1){
                ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                        .displayImage(iv_photo2,obj.getImgList().get(i).getImgUrl());
            }
            if (i==2){
                ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                        .displayImage(iv_photo3,obj.getImgList().get(i).getImgUrl());
            }
        }


        setLuXianJoin(obj.getJoinList());
        setLuXianPingLun(obj.getReplyList());

        tv_nickname.setText(obj.getUserName());
        tv_publish_date.setText(obj.getAddTime());
        tv_go_time.setText(obj.getPartyBeginTime());

//        viewHolder.tv_publish_time.setText("ddddd");
//        tv_activity_status.setText("ddddd");
//        tv_activity_distance.setText("ddddd");
        tv_end_date.setText(obj.getEndTime());
        tv_come_time.setText(obj.getPartyEndTime());
//        viewHolder.tv_end_time.setText("ddddd");
        tv_intro.setText(obj.getPartyDescribe());
        tv_go_address.setText(obj.getCollectionPlace());

        tv_arrive_address.setText(obj.getDestination());
        if (obj.getCostType()==0){
            tv_go_money.setText("我买单");
        }
        if (obj.getCostType()==1){
            tv_go_money.setText("免费");
        }
        if (obj.getCostType()==2){
            tv_go_money.setText("线下AA");
        }

        tv_go_require.setText(obj.getPartyRequirement());
        tv_message_count.setText(obj.getReplyCount()+"");
        tv_join_count.setText(obj.getJoinCount()+"");
        partyId=obj.getPartyId();
        if (obj.getHaveJoin()==0){
            tv_join.setText("加入");
            //加入活动
            tv_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url= XY_Response2.URL_NEIGHBOR_JOINPARTY+"cmemberId="+
                            SPManager.getInstance().getString("c_memberId",null)+
                            "&partyId="+partyId;
                    RequestCenter.neighbor_joinparty(url, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            FanHui2 fanHui= (FanHui2) responseObj;

                            if (fanHui.getCode()==1000){
                                Log.i("LLLL","加入活动请求成功");
                                obj.setHaveJoin(1);
                                obj.setJoinCount(obj.getJoinCount()+1);
                                tv_join_count.setText(obj.getJoinCount());
                                tv_join.setText("已加入");
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
            tv_join.setText("已加入");
            tv_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyUntil.show(LuXianXiangQingActivity.this,"你已加入该活动");
                }
            });
        }



        //加入活动
//        tv_join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url= XY_Response2.URL_NEIGHBOR_JOINPARTY+"cmemberId="+
//                        SPManager.getInstance().getString("c_memberId",null)+
//                        "&partyId="+partyId;
//                RequestCenter.neighbor_joinparty(url, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//                        FanHui2 fanHui= (FanHui2) responseObj;
//
//                        if (fanHui.getCode()==1000){
//                            Log.i("LLLL","加入活动请求成功");
//                            tv_join.setText("已加入");
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//                        Log.i("LLLL","加入活动请求失败");
//                    }
//                });
//            }
//        });


    }
    private void setLuXianPingLun(List<HuoDongDetails.ObjBean.ReplyListBean> pinglunList){
        LuXianPingLunAdapter adapter=new LuXianPingLunAdapter(this,pinglunList);
        lv_luxianxiangqing.setAdapter(adapter);
        MyUntil.setListViewHeightBasedOnChildren(lv_luxianxiangqing,adapter);
    }

    private void setLuXianJoin(List<HuoDongDetails.ObjBean.JoinListBean> joinListList){
        LuXianJoinAdapter joinAdapter=new LuXianJoinAdapter(joinListList);
        id_recyclerview_horizontal.setAdapter(joinAdapter);
    }

    private void showPingLun(){
        iv_pinglun_heidian.setVisibility(View.VISIBLE);
        iv_yijiaru_heidian.setVisibility(View.INVISIBLE);

        ll_pinglun.setVisibility(View.VISIBLE);
        ll_join.setVisibility(View.GONE);
    }
    private void showJoinList(){
        iv_pinglun_heidian.setVisibility(View.INVISIBLE);
        iv_yijiaru_heidian.setVisibility(View.VISIBLE);

        ll_pinglun.setVisibility(View.GONE);
        ll_join.setVisibility(View.VISIBLE);
    }
    private void showYanLun(){
        final PingLunDialog pingLunDialog=new PingLunDialog(LuXianXiangQingActivity.this,null,partyId);
        pingLunDialog.show();
        pingLunDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        pingLunDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                pingLunDialog.showKeyboard();
//            }
//        }, 200);
    }



}
