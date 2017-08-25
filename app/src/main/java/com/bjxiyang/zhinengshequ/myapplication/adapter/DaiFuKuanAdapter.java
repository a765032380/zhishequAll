package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.DingDanXiangQingActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.ZhiFuXiangQing;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.DianMing;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.DingDan;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.OrderDelete;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.until.DateUtils;
import com.bjxiyang.zhinengshequ.myapplication.until.DiaLogUnit;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.TimeTextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/7/1 0001.
 */

public class DaiFuKuanAdapter extends BaseAdapter {

    /***
     * 倒计时
     */
    Integer minute = null,second = null;
    Timer timer;
    TimerTask  timerTask;


    private long timeCount = 0;
    private Timer mtimer;


    private int type;
    private Context mContext;
    private List<DingDan.ResultBean> mList;
    DecimalFormat df=new DecimalFormat("0.##");
    public DaiFuKuanAdapter(Context mContext, List<DingDan.ResultBean> mList, int type) {
        this.mContext = mContext;
        this.mList = mList;
        this.type=type;
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
        ViewHolder viewHolder=null;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_dingdan,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_item_dingdan_shopname= (TextView) view.findViewById(R.id.tv_item_dingdan_shopname);
            viewHolder.tv_item_dingdan_count= (TextView) view.findViewById(R.id.tv_item_dingdan_count);
            viewHolder.lv_item_dingdan= (ListView) view.findViewById(R.id.lv_item_dingdan);
            viewHolder.tv_item_dingdan_price= (TextView) view.findViewById(R.id.tv_item_dingdan_price);
//            viewHolder.tv_item_dingdan_shengyushijian= (TextView) view.findViewById(R.id.tv_item_dingdan_shengyushijian);
            viewHolder.tv_item_dingdan_quzhifu= (LinearLayout) view.findViewById(R.id.tv_item_dingdan_quzhifu);
            viewHolder.tv_item_dingdan_quxiaodingdan= (LinearLayout) view.findViewById(R.id.tv_item_dingdan_quxiaodingdan);
            viewHolder.tv_quzhifu= (TimeTextView) view.findViewById(R.id.tv_quzhifu);
            viewHolder.tv_item_dingdan_zhifuzhuangtai= (TextView) view.findViewById(R.id.tv_item_dingdan_zhifuzhuangtai);
            viewHolder.lv_daishouhuo_gone= (LinearLayout) view.findViewById(R.id.lv_daishouhuo_gone);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        //填充数据
        viewHolder.tv_item_dingdan_shopname.setText(mList.get(position).getSeller().getShopName());
        int count=0;
        int allFee=0;
        for (int i=0;i<mList.get(position).getOrderInfoProducts().size();i++){
            count=mList.get(position).getOrderInfoProducts().get(i).getNum()+count;
        }
        allFee = mList.get(position).getOrderInfo().getTotalAmount();
        double fee=0;
        for (int i=0;i<mList.get(position).getOrderInfoProducts().size();i++){
            fee=fee+mList.get(position).getOrderInfoProducts().get(i).getPrice()
                    *mList.get(position).getOrderInfoProducts().get(i).getNum();
        }
        final double fee1=fee;
        final int position1=position;
        if (mList.get(position1).getOrderInfo().getSubStatus()==0) {
            switch (mList.get(position).getOrderInfo().getStatus()) {
                //未支付，按键有两个，一个是删除订单，一个是去支付
                case 10:
//                    viewHolder.tv_item_dingdan_shengyushijian.setText("15分00秒");
                    //去支付按键

                    Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
                    t.setToNow(); // 取得系统时间。
                    int minute1 = t.minute;
                    int second1 = t.second;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date= sdf.format(new Date());

                    String time=mList.get(position1).getOrderInfo().getPayLimitTime();
                    Log.i("YYYY",time);
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
                    SimpleDateFormat formatter=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);


                    long between=(DateUtils.getDate(mList.get(position).getOrderInfo().getPayLimitTime()).getTime()-curDate.getTime());//除以1000是为了转换成秒
//                    long day3=between/(24*3600);
//                    long hour3=between%(24*3600)/3600;
//                    long minute3=between%3600/60;
//                    long second3=between%60/60;

//                    DateUtils
                    viewHolder.tv_quzhifu.setVisibility(View.VISIBLE);
                    viewHolder.tv_quzhifu.setTimes(between,viewHolder);

//                    startCountdown(viewHolder);
//                    final ArrayList<Integer> list = new ArrayList<Integer>();
//                    list.add(minute);
//                    list.add(second);
//
//                    timerTask = new TimerTask() {
//
//                        @Override
//                        public void run() {
//                            Message msg = new Message();
//                            msg.obj=list;
//                            msg.what = 0;
//                            handler.sendMessage(msg);
//                        }
//                    };
//
//                    timer = new Timer();
//                    timer.schedule(timerTask,0,1000);

//                    Log.i("YYYY",time);
//
//                    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", java.util.Locale.US);
//                    try {
//                        Date date = sdf.parse(time.toString());
//
//                        timeCount=date.getTime()/1000;
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    
//                    Date date=new Date(mList.get(position1).getOrderInfo().getPayLimitTime());
//                    timeCount=date.getTime();

//                    getDays(mList.get(position1).getOrderInfo().getPayLimitTime());
//                    startCountdown();
                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_quxiaodingdan.setVisibility(View.VISIBLE);
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.VISIBLE);

                    viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("YYYY", "测试按键");
                            Intent intent = new Intent(mContext, ZhiFuXiangQing.class);
                            intent.putExtra("time", mList.get(position1).getOrderInfo().getPayLimitTime());
                            intent.putExtra("fee", fee1);
                            intent.putExtra("orderId", mList.get(position1).getOrderInfo().getId());
                            intent.putExtra("type", 1);
                            mContext.startActivity(intent);
//                        propertyName=intent.getStringExtra("propertyName");
//
//                        propertyId=intent.getIntExtra("propertyId",0);
//                        type=intent.getIntExtra("type",0);
                        }
                    });
                    viewHolder.tv_item_dingdan_quxiaodingdan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = BianLiDianResponse.URL_ORDER_DELETE + "orderId="
                                    + mList.get(position1).getOrderInfo().getId();
                            RequestCenter.order_delete(url, new DisposeDataListener() {
                                @Override
                                public void onSuccess(Object responseObj) {
                                    Log.i("YYYY", "测试订单删除的成功回调");
                                    OrderDelete orderDelete = (OrderDelete) responseObj;
                                    if (orderDelete.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {
                                        mList.remove(position1);
                                        notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onFailure(Object reasonObj) {
                                    Log.i("YYYY", "测试订单删除的失败回调");
                                }
                            });
                        }
                    });

                    break;
                case 20:
                    //已支付，代发货。有一个按键，取消订单的按键
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
                    viewHolder.tv_quzhifu.setText("取消订单");
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("待发货");
                    viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = BianLiDianResponse.URL_ORDER_CANCEL + "orderId="
                                    + mList.get(position1).getOrderInfo().getId();
                            RequestCenter.order_cancel(url, new DisposeDataListener() {
                                @Override
                                public void onSuccess(Object responseObj) {
                                    DianMing dianMing= (DianMing) responseObj;
                                    if (dianMing.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                                        mList.remove(position1);
                                        notifyDataSetChanged();
                                    }else {
                                        MyUntil.show(mContext,dianMing.getMsg());
                                    }

                                }

                                @Override
                                public void onFailure(Object reasonObj) {

                                }
                            });
                        }
                    });
                    break;
                case 30:
                    //待收货，有一个取消订单的按键
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("配送中");
                    viewHolder.tv_quzhifu.setText("取消订单");
                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.GONE);

//                    viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String url = BianLiDianResponse.URL_ORDER_CANCEL + "orderId="
//                                    + mList.get(position1).getOrderInfo().getId();
//                            RequestCenter.order_cancel(url, new DisposeDataListener() {
//                                @Override
//                                public void onSuccess(Object responseObj) {
//                                    DianMing dianMing= (DianMing) responseObj;
//                                    if (dianMing.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
//                                        mList.remove(position1);
//                                        notifyDataSetChanged();
//                                    }else {
//                                        MyUntil.show(mContext,dianMing.getMsg());
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Object reasonObj) {
//
//                                }
//                            });
//                        }
//                    });
//                    viewHolder.tv_item_dingdan_shengyushijian.setVisibility(View.GONE);
//                    viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String url = BianLiDianResponse.URL_ORDER_RECEIVE + "orderId="
//                                    + mList.get(position1).getOrderInfo().getId();
//                            RequestCenter.order_receive(url, new DisposeDataListener() {
//                                @Override
//                                public void onSuccess(Object responseObj) {
//                                    OrderDelete orderDelete = (OrderDelete) responseObj;
//                                    if (orderDelete.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {
//                                        mList.remove(position1);
//                                        notifyDataSetChanged();
//                                    } else {
//                                        MyUntil.show(mContext, orderDelete.getMsg());
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Object reasonObj) {
//
//                                }
//                            });
//                        }
//                    });
                    break;
//                viewHolder.tv_item_dingdan_shengyushijian.setText("取消订单");
//                viewHolder.tv_item_dingdan_shengyushijian.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String url=BianLiDianResponse.URL_ORDER_CANCEL+"orderId="
//                                +mList.get(position1).getOrderInfo().getId();
//                        RequestCenter.order_cancel(url, new DisposeDataListener() {
//                            @Override
//                            public void onSuccess(Object responseObj) {
//
//                            }
//                            @Override
//                            public void onFailure(Object reasonObj) {
//
//                            }
//                        });
//                    }
//                });tv_item_dingdan_quzhifu

//            case 50:
//                //已收货，有一个申请退货的按键
//                viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("已收货");
//                viewHolder.tv_item_dingdan_quxiaodingdan.setVisibility(View.GONE);
//                viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
//                viewHolder.tv_quzhifu.setText("申请退款");
//                viewHolder.tv_item_dingdan_shengyushijian.setVisibility(View.GONE);
//                viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String url=BianLiDianResponse.URL_ORDER_REBACK_APPLY+"orderId="
//                                +mList.get(position1).getOrderInfo().getId();
//                        RequestCenter.order_reback_apply(url, new DisposeDataListener() {
//                            @Override
//                            public void onSuccess(Object responseObj) {
//                                OrderDelete orderDelete= (OrderDelete) responseObj;
//                                if (orderDelete.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
//                                    mList.remove(position1);
//                                    notifyDataSetChanged();
//                                }else {
//                                    MyUntil.show(mContext,orderDelete.getMsg());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Object reasonObj) {
//
//                            }
//                        });
//                    }
//                });
//                break;
                case 40:
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("待收货");
                    viewHolder.tv_quzhifu.setText("确认收货");
                    viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = BianLiDianResponse.URL_ORDER_RECEIVE + "orderId="
                                    + mList.get(position1).getOrderInfo().getId();
                            RequestCenter.order_receive(url, new DisposeDataListener() {
                                @Override
                                public void onSuccess(Object responseObj) {
                                    OrderDelete orderDelete = (OrderDelete) responseObj;
                                    if (orderDelete.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {
                                        mList.get(position1).getOrderInfo().setStatus(50);
                                        notifyDataSetChanged();
                                    } else {
                                        MyUntil.show(mContext, orderDelete.getMsg());
                                    }
                                }
                                @Override
                                public void onFailure(Object reasonObj) {

                                }
                            });
                        }
                    });
                    break;
                case 50:
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("已收货");
                    viewHolder.tv_quzhifu.setText("退货");
                    final ViewHolder finalViewHolder = viewHolder;
                    viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (DiaLogUnit.showDialogTishi(mContext)) {
                                String url = BianLiDianResponse.URL_ORDER_REBACK_APPLY + "orderId="
                                        + mList.get(position1).getOrderInfo().getId();
                                RequestCenter.order_reback_apply(url, new DisposeDataListener() {
                                    @Override
                                    public void onSuccess(Object responseObj) {
                                        OrderDelete orderDelete = (OrderDelete) responseObj;
                                        if (orderDelete.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {
                                            finalViewHolder.tv_quzhifu.setText("退货中");
                                            mList.get(position1).getOrderInfo().setSubStatus(100);
                                            notifyDataSetChanged();
                                        } else {
                                            MyUntil.show(mContext, orderDelete.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Object reasonObj) {

                                    }
                                });
                            }
                        }
                    });
                    break;
                case 91:
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("订单超时");
                    break;
                case 92:
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("已支付取消");
                    break;
                case 93:
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("拒单取消");
                    break;
                case 100:
                    viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("已完结");
                    break;
                default:
                    break;
            }
        }else {
            fenzhi(viewHolder,position1);
//            switch (mList.get(position1).getOrderInfo().getSubStatus()){
//                case 100:
//                    //已收货，有一个申请退货的按键tv_item_dingdan_quxiaodingdan
//                    viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
//                    viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退货中");
//                    viewHolder.tv_item_dingdan_quxiaodingdan.setVisibility(View.GONE);
//                    viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.GONE);
////                viewHolder.tv_quzhifu.setText("取消退货");
//                    viewHolder.tv_item_dingdan_shengyushijian.setVisibility(View.GONE);
////                viewHolder.tv_item_dingdan_quzhifu.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        String url=BianLiDianResponse.URL_ORDER_REBACK_APPLY+"orderId="
////                                +mList.get(position1).getOrderInfo().getId();
////                        RequestCenter.order_reback_apply(url, new DisposeDataListener() {
////                            @Override
////                            public void onSuccess(Object responseObj) {
////                                OrderDelete orderDelete= (OrderDelete) responseObj;
////                                if (orderDelete.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
////                                    mList.remove(position1);
////                                    notifyDataSetChanged();
////                                }else {
////                                    MyUntil.show(mContext,orderDelete.getMsg());
////                                }
////                            }
////
////                            @Override
////                            public void onFailure(Object reasonObj) {
////
////                            }
////                        });
////                    }
////                });
//                    break;
//                default:
//                    break;
//            }
        }
//        if (mList.get(position).getOrderInfo().getStatus()==20){
//            viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.GONE);
//        }else {
//            viewHolder.tv_item_dingdan_quzhifu.setVisibility(View.VISIBLE);
//        }
        viewHolder.tv_item_dingdan_count.setText(count+"");
        viewHolder.tv_item_dingdan_price.setText(df.format((double) allFee/100)+"");
//        viewHolder.tv_item_dingdan_shengyushijian
//        viewHolder.tv_item_dingdan_quzhifu

//        嵌套的ListView
        List<DingDan.ResultBean.OrderInfoProductsBean> list=mList.get(position).getOrderInfoProducts();
        DingDanItemAdapter adapter = new DingDanItemAdapter(mContext,list);
        viewHolder.lv_item_dingdan.setAdapter(adapter);
        setListViewHeightBasedOnChildren(viewHolder.lv_item_dingdan);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,DingDanXiangQingActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("xiangqing",mList.get(position));
                intent.putExtra("xiangqing",mList.get(position));
                mContext.startActivity(intent);
            }
        });
        return view;
    }
    public class ViewHolder{

        public TextView tv_item_dingdan_shopname;
        public TextView tv_item_dingdan_count;
        public ListView lv_item_dingdan;
        public TextView tv_item_dingdan_price;
        public TextView tv_item_dingdan_shengyushijian;
        public LinearLayout tv_item_dingdan_quzhifu;
        public LinearLayout tv_item_dingdan_quxiaodingdan;
        public TimeTextView tv_quzhifu;
        public TextView tv_item_dingdan_zhifuzhuangtai;
        public LinearLayout lv_daishouhuo_gone;

    }
    private void sub100(ViewHolder viewHolder, int position){
        final int  position1= position;
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退货中");
    }
    private void sub200(ViewHolder viewHolder, int position){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退款中");
    }
    private void sub110(ViewHolder viewHolder, int position1){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退货中");
//        viewHolder.button_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_jujue_tuikuan=RequestURL.URL_ORDER_REFUND_CONFIRM+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_refund_confirm(url_jujue_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
//        viewHolder.button_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_tuikuan=RequestURL.URL_ORDER_REBACK_REFUSE+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_reback_refuse(url_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
    }
    private void sub210(ViewHolder viewHolder, int position1){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退款中");
//        viewHolder.button_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_jujue_tuikuan=RequestURL.URL_ORDER_REFUND_CONFIRM+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_refund_confirm(url_jujue_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
//        viewHolder.button_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_tuikuan=RequestURL.URL_ORDER_REBACK_REFUSE+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_reback_refuse(url_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
    }
    private void sub120(ViewHolder viewHolder, int position1){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("已退货");
//        viewHolder.button_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_jujue_tuikuan=RequestURL.URL_ORDER_REFUND_CONFIRM+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_refund_confirm(url_jujue_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
//        viewHolder.button_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_tuikuan=RequestURL.URL_ORDER_REBACK_REFUSE+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_reback_refuse(url_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
    }
    private void sub220(ViewHolder viewHolder, int position1){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("已退款");
//        viewHolder.button_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_jujue_tuikuan=RequestURL.URL_ORDER_REFUND_CONFIRM+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_refund_confirm(url_jujue_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
//        viewHolder.button_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_tuikuan=RequestURL.URL_ORDER_REBACK_REFUSE+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_reback_refuse(url_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
    }
    private void sub130(ViewHolder viewHolder, int position1){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退货拒绝");
//        viewHolder.button_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_jujue_tuikuan=RequestURL.URL_ORDER_REFUND_CONFIRM+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_refund_confirm(url_jujue_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
//        viewHolder.button_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_tuikuan=RequestURL.URL_ORDER_REBACK_REFUSE+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_reback_refuse(url_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
    }
    private void sub230(ViewHolder viewHolder, int position1){
        viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("退款拒绝");
//        viewHolder.button_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_jujue_tuikuan=RequestURL.URL_ORDER_REFUND_CONFIRM+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_refund_confirm(url_jujue_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
//        viewHolder.button_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url_tuikuan=RequestURL.URL_ORDER_REBACK_REFUSE+"orderId="
//                        +mList.get(position1).getOrderInfo().getId();
//                RequestCenter.order_reback_refuse(url_tuikuan, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                });
//            }
//        });
    }

    private void fenzhi(ViewHolder viewHolder,int position1){
        viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
        switch (mList.get(position1).getOrderInfo().getSubStatus()){
            case 100:
                sub100(viewHolder,position1);
                break;
            case 200:
                sub200(viewHolder,position1);
                break;
            case 110:
                sub110(viewHolder,position1);
                break;
            case 210:
                sub210(viewHolder,position1);
                break;
            case 120:
                sub120(viewHolder,position1);
                break;
            case 220:
                sub220(viewHolder,position1);
                break;
            case 130:
                sub130(viewHolder,position1);
                break;
            case 230:
                sub230(viewHolder,position1);
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {        return;    }
        int totalHeight = 0;
        for (int i = 1, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        Log.i("YYYY",totalHeight+"---"+listView.getDividerHeight() * (listAdapter.getCount() - 1));

        params.height = ((int)(totalHeight))+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                ViewHolder v= (ViewHolder) msg.obj;
//                timeCount --;
//                if (timeCount >= 0){
                    changeSmsButton(v);
//                }else{
//                    mtimer.cancel();
//                }
            }
        }
    };

    //开始倒计时
    public void startCountdown(ViewHolder viewHolder){

        changeSmsButton(viewHolder);
        setTimerTask(viewHolder);
    }
    //倒计时的方法
    public void setTimerTask(final ViewHolder viewHolder){
        mtimer = new Timer();
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj=viewHolder;
                message.what = 1;
                timerHandler.sendMessage(message);

            }
        },1000,1000);
    }
    //在倒计时的时候,对按钮上的字进行赋值
    public void changeSmsButton(ViewHolder viewHolder){
//        Log.i("YYYY",second+"PPPPPPPPPPP");
//        second--;
//        viewHolder.tv_quzhifu.setText("去支付 还剩"+minute+":"+second);


        if (minute>0){
            if (second>=1){
                second--;
                viewHolder.tv_quzhifu.setText("去支付 还剩"+minute+":"+second);
            }
            if (second==0){
                if (minute>0){
                    minute--;
                    second=59;
                    viewHolder.tv_quzhifu.setText("去支付 还剩"+minute+":"+second);
                }
            }
        }else {
            if (second>0){
                second--;
                viewHolder.tv_quzhifu.setText("去支付 还剩"+0+":"+second);
            }
            if (second==0){
                viewHolder.lv_daishouhuo_gone.setVisibility(View.GONE);
                viewHolder.tv_item_dingdan_zhifuzhuangtai.setText("订单超时");
            }
        }

    }
    public void getDays(String date1) {
        Log.i("DATA",date1);
        Date date=new Date(date1);
        timeCount=date.getTime();


//        if (date1 == null || date1.equals(""))
//            return 0;
//
//        // 转换为标准时间
//        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date date = null;
//        try {
//            date = myFormatter.parse(date1);
//        } catch (Exception e) {
//        }
//        long day = (date.getTime()) / (24 * 60 * 60 * 1000);
//        return day;
    }

}
