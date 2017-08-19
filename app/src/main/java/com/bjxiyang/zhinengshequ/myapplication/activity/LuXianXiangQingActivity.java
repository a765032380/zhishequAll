package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.dialog.JoinLuXianDialog;
import com.bjxiyang.zhinengshequ.myapplication.dialog.PingLunDialog;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;


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
    @BindView(R.id.id_xiangqing_listview)
    ListView id_xiangqing_listview;
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
    @BindView(R.id.ll_ScrollView_nei)
    LinearLayout ll_ScrollView_nei;

    private JoinLuXianDialog joinLuXianDialog;
    public static LuXianXiangQingActivity luXianXiangQingActivity;
    /***
     * Data
     */
    private int partyId;
    private boolean isJoin;
    private HuoDongDetails.ObjBean obj;
    private int status;
    private boolean isOne=true;
    public static int heightY;
    public static boolean isScroll=false;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxianxiangqing);
        luXianXiangQingActivity=this;
        ButterKnife.bind(this);
        Intent intent=getIntent();
        partyId = intent.getIntExtra("partyId",-1);
        initUI();
        getData();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initUI() {
        iv_luxianxiangqing_fanhui.setOnClickListener(this);
        ll_luxianxiangqing_more.setOnClickListener(this);
        tv_pinglun.setOnClickListener(this);
        tv_yijiaru.setOnClickListener(this);
        tv_fabiaoyanlun.setOnClickListener(this);
        tv_join.setOnClickListener(this);
        myScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                heightY=scrollY;
            }
        });

    }

    private void getData(){
        DialogUntil.showLoadingDialog(LuXianXiangQingActivity.this,"正在提交",false);
        String url= XY_Response2.URL_NEIGHBOR_FINDPARTYDETAILS+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null)+"&partyId="+partyId;
        RequestCenter.neighbor_findpartydetails(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                HuoDongDetails huoDongDetails= (HuoDongDetails) responseObj;
                if (huoDongDetails.getCode()==1000){
                    obj=huoDongDetails.getObj();

                    setData();
                    if (isOne){
                        myScrollView.post(new Runnable() {
                            //让scrollview跳转到顶部，必须放在runnable()方法中
                            @Override
                            public void run() {
                                myScrollView.scrollTo(0,0);
                            }
                        });
                        isOne=false;
                    }else {
                            heightY = myScrollView.getChildAt(0).getHeight();
//                            .getMeasuredHeight() - myScrollView.getHeight();
                            myScrollView.post(new Runnable() {
                                //让scrollview跳转到顶部，必须放在runnable()方法中
                                @Override
                                public void run() {
                                    myScrollView.scrollTo(0, heightY);
                                    isScroll=false;
                                }
                            });

                    }

                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

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
                showShare();
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
                if (isJoin){
                    status=0;
                    showTiShiJoinDialog(status);
                }else {
                    status=1;
                    showTiShiJoinDialog(status);
                }

                break;
        }
    }
    private void setData(){
        if (obj.getUserUrl()!=null&&!obj.getUserUrl().equals("")) {

            ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                    .displayImage(iv_photo, obj.getUserUrl());
        }
        for (int i=0;i<obj.getImgList().size();i++){
            if (i==0){
                if (obj.getImgList().get(i).getImgUrl()!=null&&!obj.getImgList().get(i).getImgUrl().equals("")) {
                    ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                            .displayImage(iv_photo1, obj.getImgList().get(i).getImgUrl());
                }
            }
            if (i==1){
                if (obj.getImgList().get(i).getImgUrl()!=null&&!obj.getImgList().get(i).getImgUrl().equals("")) {

                    ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                            .displayImage(iv_photo2, obj.getImgList().get(i).getImgUrl());
                }
            }
            if (i==2){
                if (obj.getImgList().get(i).getImgUrl()!=null&&!obj.getImgList().get(i).getImgUrl().equals("")) {

                    ImageLoaderManager.getInstance(LuXianXiangQingActivity.this)
                            .displayImage(iv_photo3, obj.getImgList().get(i).getImgUrl());
                }
            }
        }


        setLuXianJoin(obj.getJoinList());
        setLuXianPingLun(obj.getReplyList());
//        tv_date_count.setText(obj.getPeopleCount());
        tv_nickname.setText(obj.getUserName());
        tv_publish_date.setText(obj.getAddTime());
        tv_go_time.setText(obj.getPartyBeginTime());

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
        }else if (obj.getCostType()==1){
            tv_go_money.setText("免费");
        }else if (obj.getCostType()==2){
            tv_go_money.setText("线下AA");
        }else if (obj.getCostType()==2){
            tv_go_money.setText("你请客");
        }

        tv_go_require.setText(obj.getPartyRequirement());
        tv_message_count.setText(obj.getReplyCount()+"");
        tv_join_count.setText(obj.getJoinCount()+"");
        partyId=obj.getPartyId();

        if (obj.getHaveJoin()==0){
            tv_join.setText("加入");
            isJoin=false;

            tv_join.setBackgroundResource(R.drawable.a_btn_join);

            //加入活动

        }else if (obj.getHaveJoin()==1){

            tv_join.setBackgroundResource(R.drawable.a_btn_over);
            tv_join.setText("退出活动");
            isJoin=true;
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
        LuXianPingLunAdapter adapter=new LuXianPingLunAdapter(LuXianXiangQingActivity.this,pinglunList);
        lv_luxianxiangqing.setAdapter(adapter);
        MyUntil.setListViewHeightBasedOnChildren(lv_luxianxiangqing,adapter);
    }

    private void setLuXianJoin(List<HuoDongDetails.ObjBean.JoinListBean> joinListList){
        LuXianJoinAdapter joinAdapter=new LuXianJoinAdapter(LuXianXiangQingActivity.this,joinListList);
        id_xiangqing_listview.setAdapter(joinAdapter);
        MyUntil.setListViewHeightBasedOnChildren(id_xiangqing_listview,joinAdapter);
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
        pingLunDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                upData();
            }
        });
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
    private void showTiShiJoinDialog(final int status) {

        joinLuXianDialog=new JoinLuXianDialog(LuXianXiangQingActivity.this,status,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = XY_Response2.URL_NEIGHBOR_JOINPARTY + "cmemberId=" +
                                SPManager.getInstance().getString("c_memberId", null) +
                                "&partyId=" + partyId+"&status="+status;
                        RequestCenter.neighbor_joinparty(url, new DisposeDataListener() {
                            @Override
                            public void onSuccess(Object responseObj) {
                                FanHui2 fanHui = (FanHui2) responseObj;

                                if (fanHui.getCode() == 1000) {
                                    MyUntil.show(LuXianXiangQingActivity.this,"测试");
                                    if (!isJoin){
                                        obj.setHaveJoin(1);
                                        obj.setJoinCount(obj.getJoinCount() + 1);
                                        tv_join_count.setText(obj.getJoinCount());
                                        tv_join.setText("退出活动");
                                    }else {
                                        obj.setHaveJoin(0);
                                        obj.setJoinCount(obj.getJoinCount() - 1);
                                        tv_join_count.setText(obj.getJoinCount());
                                        tv_join.setText("加入");
                                    }
                                } else {
                                    MyUntil.show(LuXianXiangQingActivity.this, fanHui.getMsg());
                                }
                            }
                            @Override
                            public void onFailure(Object reasonObj) {

                            }
                        });
                        joinLuXianDialog.dismiss();
                    }
                });
        joinLuXianDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                getData();
            }
        });
        joinLuXianDialog.show();
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
    public void upData(){
        if (isScroll){
            getData();
        }

    }
}
