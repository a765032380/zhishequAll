package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.select_date.JudgeDate;
import com.bjxiyang.zhinengshequ.myapplication.select_date.ScreenInfo;
import com.bjxiyang.zhinengshequ.myapplication.select_date.WheelMain;
import com.bjxiyang.zhinengshequ.myapplication.until.DateUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class AddHuoDongActivity extends MySwipeBackActivity implements View.OnClickListener {
    /***
     * UI
     */
    @BindView(R.id.iv_startActivities_fanhui)
    RelativeLayout iv_startActivities_fanhui;
    @BindView(R.id.et_startActivities_go)
    EditText et_startActivities_go;
    @BindView(R.id.et_startActivities_arrive)
    EditText et_startActivities_arrive;
    @BindView(R.id.et_startActivities_money)
    TextView et_startActivities_money;

    @BindView(R.id.et_startActivities_startdate)
    TextView et_startActivities_startdate;
    @BindView(R.id.et_startActivities_enddate)
    TextView et_startActivities_enddate;
    @BindView(R.id.et_baoming_enddate)
    TextView et_baoming_enddate;

    @BindView(R.id.et_startActivities_datecount)
    EditText et_startActivities_datecount;
    @BindView(R.id.et_startActivities_yaoqiu)
    EditText et_startActivities_yaoqiu;
    @BindView(R.id.tv_next)
    TextView tv_next;

    /***
     * Data
     */
    private String startAddress;
    private String toAddress;
    private String startTime;
    private String endTime;
    private String bmendTime;
    private String time;
    private String money;
    private String datecount;
    private String yaoqiu;

    private String beginTime;

    /***
     * Other
     */
    private WheelMain wheelMainDate;


    public static AddHuoDongActivity addHuoDongActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqihuodong);
        addHuoDongActivity=this;
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (startAddress!=null){
            et_startActivities_go.setText(startAddress);
        }
        if (toAddress!=null){
            et_startActivities_arrive.setText(toAddress);
        }

        if (money!=null){
            et_startActivities_money.setText(money);
        }
        if (datecount!=null){
            et_startActivities_datecount.setText(datecount);
        }
        if (yaoqiu!=null){
            et_startActivities_yaoqiu.setText(yaoqiu);
        }
        iv_startActivities_fanhui.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        et_startActivities_startdate.setOnClickListener(this);
        et_startActivities_enddate.setOnClickListener(this);
        et_baoming_enddate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_startActivities_fanhui:
                finish();
                break;
            case R.id.et_startActivities_startdate:
                showBottoPopupWindow(et_startActivities_startdate);
                break;
            case R.id.et_startActivities_enddate:
                showBottoPopupWindow(et_startActivities_enddate);
                break;
            case R.id.et_baoming_enddate:
                showBottoPopupWindow(et_baoming_enddate);
                break;

            case R.id.tv_next:
                startAddress=String.valueOf(et_startActivities_go.getText());
                toAddress=String.valueOf(et_startActivities_arrive.getText());
                startTime=String.valueOf(et_startActivities_startdate.getText());
                endTime=String.valueOf(et_startActivities_enddate.getText());
                bmendTime=String.valueOf(et_baoming_enddate.getText());
                money=String.valueOf(et_startActivities_money.getText());
                datecount=String.valueOf(et_startActivities_datecount.getText());
                yaoqiu=String.valueOf(et_startActivities_yaoqiu.getText());
//                if (startAddress==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加出发地");
//                    break;
//                }
//                if (toAddress==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加目的地");
//                    break;
//                }
//                if (startTime==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加出发时间");
//                    break;
//                }
//                if (endTime==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加返回时间");
//                    break;
//                }
//                if (bmendTime==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加报名截止时间");
//                    break;
//                }
//                if (money==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加费用说明");
//                    break;
//                }
//                if (datecount==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加约伴人数");
//                    break;
//                }
//                if (yaoqiu==null){
//                    MyUntil.show(AddHuoDongActivity.this,"请添加约伴要求");
//                    break;
//                }


                Intent intent=new Intent(AddHuoDongActivity.this,AddHuoDongNextActivity.class);
                intent.putExtra("startAddress",startAddress);
                intent.putExtra("toAddress",toAddress);
                intent.putExtra("startTime",startTime);
                intent.putExtra("endTime",endTime);
                intent.putExtra("bmendTime",bmendTime);
                intent.putExtra("money",money);
                intent.putExtra("datecount",datecount);
                intent.putExtra("yaoqiu",yaoqiu);
                startActivity(intent);
                break;
        }
    }
    public void showBottoPopupWindow(final TextView tv) {
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(this).inflate(R.layout.show_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width*0.8),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(this);
        wheelMainDate = new WheelMain(menuView, true);
        wheelMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelMainDate.initDateTimePicker(year, month, day, hours,minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //动画
        mPopupWindow.showAtLocation(tv, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
//        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择起始时间");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
//                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelMainDate.getTime().toString();
                tv.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
//                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

}
