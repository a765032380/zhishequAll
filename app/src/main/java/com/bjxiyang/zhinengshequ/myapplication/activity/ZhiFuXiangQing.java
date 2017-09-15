package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.base.LogOutBaseActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.AliZhiFu;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.bean.OrderWeiXin;
import com.bjxiyang.zhinengshequ.myapplication.bean.ProPayOrder;
import com.bjxiyang.zhinengshequ.myapplication.bean.ProPayOrderByAli;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.bjxiyang.zhinengshequ.myapplication.zhifubao.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/6/15 0015.
 */


//【程序猿的三重境界】第一重:无尽bug常作客,困闷调试伴不眠。第二重:千行代码过,bug不沾身。第三重:编码间,bug灰飞烟灭。
public class ZhiFuXiangQing extends LogOutBaseActivity implements View.OnClickListener{
    private RelativeLayout rl_zhifudingdan_fanghui;
    private TextView tv_zhifudingdan_dianming;
    private TextView tv_zhifudingdan_quedingzhifu;
    private LinearLayout zhifu_yinhangka;
    private LinearLayout ll_weixinzhifu;
    private LinearLayout ll_zhifubaozhifu;
    private TextView zhifu;
    private String jiage;
    private ImageView iv_item_chaoshifukuan_xiangqing_yinhanka;
    private ImageView iv_zhifudingdan_weixinxuanze;
    private ImageView iv_zhifudingdan_zhifubaoxuanze;
    private TextView tv_zhifudingdan_dingdanhao;
    private TextView tv_gudingjine;
    private TextView tv_zhifudingdan_time;
    DecimalFormat df=new DecimalFormat("0.##");
    private String propertyName;
    //支付
    private int select=0;
    public static String payOrder="";
    private int propertyId;
    private double fee;
//    private String fee;
    private int orderId;
    private int type;
    private int spId;
    private String time;
    Integer minute = null,second = null;
    TimerTask timerTask;
    private Timer mtimer;
    private List<GouWuChe> mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
    private static final int SDK_PAY_FLAG=1000;
    public static ZhiFuXiangQing zhiFuXiangQing;

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {//如果消息是 SDK正常运行
                    @SuppressWarnings("unchecked")
                    //将随该消息附带的msg.obj强转回map中，建立新的payresult支付结果
                            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息。从支付结果中取到resultinfo
                    String resultStatus = payResult.getResultStatus();//得到resultstatus
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ZhiFuXiangQing.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if (PlaceOrderActivity.placeorder!=null) {
                            PlaceOrderActivity.placeorder.finish();
                        }
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ZhiFuXiangQing.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifudingdan);
        zhiFuXiangQing=this;
        Intent intent=getIntent();
        fee=intent.getDoubleExtra("fee",0);
//        fee= Double.parseDouble(intent.getStringExtra("fee"));
//        fee=fee*100;
        time=intent.getStringExtra("time");
        orderId=intent.getIntExtra("orderId",0);
        propertyName=intent.getStringExtra("propertyName");
        propertyId=intent.getIntExtra("propertyId",0);
        type=intent.getIntExtra("type",0);
        spId=intent.getIntExtra("spId",-1);
        initUI();
    }

    private void initUI() {
        tv_zhifudingdan_dingdanhao= (TextView) findViewById(R.id.tv_zhifudingdan_dingdanhao);
        tv_zhifudingdan_time= (TextView) findViewById(R.id.tv_zhifudingdan_time);
//        tv_gudingjine= (TextView) findViewById(R.id.tv_gudingjine);
//        iv_item_chaoshifukuan_xiangqing_yinhanka= (ImageView) findViewById(R.id.iv_item_chaoshifukuan_xiangqing_yinhanka);
        iv_zhifudingdan_weixinxuanze= (ImageView) findViewById(R.id.iv_zhifudingdan_weixinxuanze);
        iv_zhifudingdan_zhifubaoxuanze= (ImageView) findViewById(R.id.iv_zhifudingdan_zhifubaoxuanze);
        rl_zhifudingdan_fanghui= (RelativeLayout) findViewById(R.id.rl_zhifudingdan_fanghui);
        rl_zhifudingdan_fanghui.setOnClickListener(this);
        tv_zhifudingdan_dianming= (TextView) findViewById(R.id.tv_zhifudingdan_dianming);
//        zhifu_yinhangka= (LinearLayout) findViewById(R.id.zhifu_yinhangka);
//        zhifu_yinhangka.setOnClickListener(this);
        ll_weixinzhifu= (LinearLayout) findViewById(R.id.ll_weixinzhifu);
        ll_weixinzhifu.setOnClickListener(this);
        ll_zhifubaozhifu= (LinearLayout) findViewById(R.id.ll_zhifubaozhifu);
        ll_zhifubaozhifu.setOnClickListener(this);
        tv_zhifudingdan_quedingzhifu= (TextView) findViewById(R.id.tv_zhifudingdan_quedingzhifu);
        tv_zhifudingdan_quedingzhifu.setOnClickListener(this);
//        zhifu= (TextView) findViewById(R.id.tv_item_chaoshifukuan_xiangqing_quedingzhifu);
//        zhifu.setOnClickListener(this);
        Log.i("YYYY",df.format(fee/100));
        tv_zhifudingdan_quedingzhifu.setText("确定支付  ￥"+String.valueOf(df.format(fee/100)));
        tv_zhifudingdan_dianming.setText(propertyName);
        startOutTime();
//        panduan(String.valueOf(tv_gudingjine.getText()));

//        et_item_chaoshifukuan_xiangqing_money.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////                panduan(String.valueOf(s));
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                panduan(String.valueOf(s));
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                panduan(String.valueOf(s));
//            }
//        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.rl_zhifudingdan_fanghui:
                    finish();
                break;
            //选择银行卡支付方式
//            case R.id.zhifu_yinhangka:
//                select=0;
////                iv_item_chaoshifukuan_xiangqing_yinhanka.setBackgroundResource(R.drawable.e_icon_choice);
//                iv_zhifudingdan_weixinxuanze.setBackgroundResource(R.drawable.h_btn_xuanze);
//                iv_zhifudingdan_zhifubaoxuanze.setBackgroundResource(R.drawable.h_btn_xuanze);
//                break;
            //选择微信支付方式
            case R.id.ll_weixinzhifu:
                select=1;
//                iv_item_chaoshifukuan_xiangqing_yinhanka.setBackgroundResource(R.drawable.e_btn_xuanze);
                iv_zhifudingdan_weixinxuanze.setBackgroundResource(R.drawable.e_icon_choice);
                iv_zhifudingdan_zhifubaoxuanze.setBackgroundResource(R.drawable.h_btn_xuanze);
                break;
            //选择支付宝支付方式
            case R.id.ll_zhifubaozhifu:
                select=2;
                iv_zhifudingdan_zhifubaoxuanze.setBackgroundResource(R.drawable.e_icon_choice);
                iv_zhifudingdan_weixinxuanze.setBackgroundResource(R.drawable.h_btn_xuanze);
//                iv_item_chaoshifukuan_xiangqing_yinhanka.setBackgroundResource(R.drawable.e_btn_xuanze);
                break;
            //提交按钮
            case R.id.tv_zhifudingdan_quedingzhifu:
                switch (select){
                    case 0:
                        break;
                    case 1:
                        sendRequestWeiXin();
                        break;
                    case 2:
                        sendRequestAli();
                        break;
                }
                break;

        }
    }
    private void panduan(String s){
        if (s.length()>0&&!s.equals("0")){
            zhifu.setEnabled(true);
            zhifu.setBackgroundResource(R.drawable.i_btn_quedingzhifu);
        }else {
            zhifu.setEnabled(false);
            zhifu.setBackgroundResource(R.drawable.h_btn_quedingzhifu);
        }
    }
    private void sendRequestWeiXin(){
        DialogUntil.showLoadingDialog(ZhiFuXiangQing.this,"正在提交",true);

        if (type==1){
            String url_wx= BianLiDianResponse.URL_ORDER_PROPAYORDERBWX+"orderId="+orderId;
            RequestCenter.order_proPayOrderByWx(url_wx, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    DialogUntil.closeLoadingDialog();
                    OrderWeiXin orderWeiXin = (OrderWeiXin) responseObj;
                    if (orderWeiXin.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS) {
                        MyUntil.show(ZhiFuXiangQing.this, "已提交订单");
                        sendReqToWeiXi_order(orderWeiXin);
                    }
                }
                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();

                }
            });

        }else {
            String url = XY_Response.URL_PROPAYORDER + "cmemberId=" +
                    SPManager.getInstance().getString("c_memberId","") +
                    "&propertyId=" + propertyId +
                    "&fee=" + fee;
            RequestCenter.proPayOrder(url, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    DialogUntil.closeLoadingDialog();
                    ProPayOrder proPayOrder = (ProPayOrder) responseObj;
                    if (proPayOrder.getCode().equals("1000")) {
                        MyUntil.show(ZhiFuXiangQing.this, "已提交订单");
                        sendReqToWeiXi(proPayOrder);
                    }
                }

                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();
                    MyDialog.showDialog(ZhiFuXiangQing.this);

                }
            });
        }
    }
    private void sendReqToWeiXi(ProPayOrder proPayOrder) {
        ProPayOrder.Obj obj=proPayOrder.getObj();
        IWXAPI msgApi = WXAPIFactory.createWXAPI(GuardApplication.getContent(),null);
        msgApi.registerApp(obj.getApp_id());
        // 将该app注册到微信
        PayReq request = new PayReq();
//        propertyId=obj.getPrepay_id();
//        payOrder=obj.getOrder_no();
        request.appId = obj.getApp_id();
        request.partnerId =obj.getPartnerid();
        request.prepayId= obj.getPrepay_id();
        request.packageValue = obj.getReturn_package();
        request.nonceStr= obj.getNonce_str();
        request.timeStamp= String.valueOf(obj.getTime_stamp());
        request.sign= obj.getPay_info_sign();
        msgApi.registerApp(obj.getApp_id());
        msgApi.sendReq(request);

//        }


    }

    private void sendReqToWeiXi_order(OrderWeiXin orderWeiXin) {

        OrderWeiXin.ResultBean obj=orderWeiXin.getResult();
        IWXAPI msgApi = WXAPIFactory.createWXAPI(GuardApplication.getContent(),null);
        msgApi.registerApp(obj.getAppid());
        // 将该app注册到微信
        PayReq request = new PayReq();
//        propertyId=obj.getPrepay_id();
//        payOrder=obj.getOrder_no();
        request.appId = obj.getAppid();
        request.partnerId =obj.getPartnerid();
        request.prepayId= obj.getPrepay_id();
        request.packageValue = obj.getReturn_package();
        request.nonceStr= obj.getNonce_str();
        request.timeStamp= String.valueOf(obj.getTime_stamp());
        request.sign= obj.getPay_info_sign();
        msgApi.registerApp(obj.getAppid());
        msgApi.sendReq(request);

//        }

        if (spId!=-1) {
            if (type == 0) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getSpid() == spId) {
                        DaoUtils.getStudentInstance().deleteObject(mList.get(i));
                    }
                }
            } else {
                DaoUtils.getStudentInstance().deleteAll(GouWuChe.class);
            }
        }


    }

    private void sendRequestAli(){
        String url;
        if (type==1){
            url= BianLiDianResponse.URL_ORDER_PROPAYORDERBYALI+"orderId="+orderId;
            RequestCenter.order_proPayOrderByAli(url, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    AliZhiFu aliZhiFu= (AliZhiFu) responseObj;
                    if (aliZhiFu.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                        sendReqToAli(aliZhiFu.getResult());

                    }
                }
                @Override
                public void onFailure(Object reasonObj) {

                }
            });

        }else {
            url= XY_Response.URL_PROPAYORDERBYALI+"cmemberId="+
                    SPManager.getInstance().getString("c_memberId","")+
                    "&propertyId="+propertyId+
                    "&fee="+fee;
            DialogUntil.showLoadingDialog(ZhiFuXiangQing.this,"正在提交",true);

            MyUntil.show(ZhiFuXiangQing.this, SPManager.getInstance().getString("c_memberId","")
                    +"--"+propertyId+"--"+fee);
            RequestCenter.proPayOrderByAli(url, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    DialogUntil.closeLoadingDialog();
                    ProPayOrderByAli proPayOrder= (ProPayOrderByAli) responseObj;
                    if (proPayOrder.getCode().equals("1000")){
//                    MyUntil.show(ZhiFuXiangQing.this,"已提交订单");
                        sendReqToAli(proPayOrder.getObj());
                    }
                }
                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();
                    MyDialog.showDialog(ZhiFuXiangQing.this);

                }
            });
        }

    }
    private void sendReqToAli(final String orderInfo){
        if (spId!=-1) {
            if (type == 0) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getSpid() == spId) {
                        DaoUtils.getStudentInstance().deleteObject(mList.get(i));
                    }
                }
            } else {
                DaoUtils.getStudentInstance().deleteAll(GouWuChe.class);
            }
        }

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ZhiFuXiangQing.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void startOutTime(){
        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int minute1 = t.minute;
        int second1 = t.second;


        String[] s1 = time.split(" ");//以","为分隔符，截取上面的字符串。结果为三段
//                    for (int i = 0; i < s1.length; i++) {
//                        if (s1.length - 1 == i) {
        time = s1[1];
//                        }
//                    }
        String[] s2 = time.split(":");//以","为分隔符，截取上面的字符串。结果为三段
//                    for (int i = 0; i < s2.length-1; i++) {
        int hour=Integer.parseInt(s2[0]);
        int minute2= Integer.parseInt(s2[1]);
        int second2= Integer.parseInt(s2[2]);
        Log.i("YYYY",minute2+"+1111+"+minute1);
        if (minute2<minute1){
            minute=Math.abs(minute2+(60-minute1));
        }else {
            minute=minute2-minute1;
        }
//                    }
        Log.i("YYYY",minute2+"PPPP");

        Log.i("YYYY",minute2+"+++++"+minute1);
//                    second=second-second1;
        if (second2<second1){
            second=Math.abs(second2+(60-second1));
//                        if (minute>0){
//                            minute--;
//                        }
        }else {
            second=second2-second1;
        }
        startCountdown();
    }
    //开始倒计时
    public void startCountdown(){

        changeSmsButton();
        setTimerTask();
    }
    //倒计时的方法
    public void setTimerTask(){
        mtimer = new Timer();
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                timerHandler.sendMessage(message);

            }
        },1000,1000);
    }
    //在倒计时的时候,对按钮上的字进行赋值
    public void changeSmsButton(){
//        Log.i("YYYY",second+"PPPPPPPPPPP");
//        second--;
//        viewHolder.tv_quzhifu.setText("去支付 还剩"+minute+":"+second);


        if (minute>0){
            if (second>=1){
                second--;
                if (second<10){
                    tv_zhifudingdan_time.setText(minute-1+":0"+second);
                }else {
                    tv_zhifudingdan_time.setText(minute-1 + ":" + second);
                }
            }
            if (second==0){
                if (minute>0){
                    minute--;
                    second=59;
                    tv_zhifudingdan_time.setText(minute-1+":"+second);
                }
            }
        }else {
            if (second>0){
                second--;
                tv_zhifudingdan_time.setText(0+":"+second);
            }
            if (second==0){
                tv_zhifudingdan_dingdanhao.setVisibility(View.GONE);
                tv_zhifudingdan_time.setText("订单超时");
            }
        }

    }
    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                changeSmsButton();
            }
        }
    };




}
