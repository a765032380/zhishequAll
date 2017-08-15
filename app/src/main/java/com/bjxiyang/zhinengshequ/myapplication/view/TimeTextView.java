package com.bjxiyang.zhinengshequ.myapplication.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.myapplication.adapter.DaiFuKuanAdapter;


public class TimeTextView extends android.support.v7.widget.AppCompatTextView {
    private DaiFuKuanAdapter.ViewHolder viewHolder;
    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
    }

    long Time;
    private boolean run = true; // 是否启动了
    @SuppressLint("NewApi")
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (run) {
                        long mTime = Time;
                        Log.i("dateyyy","Time:"+Time+"测试");
                        if (mTime > 0) {
                            int second3 = (int) (Time/1000 % 60);
                            int minute3 = (int) (Time/1000 / 60 % 60);
//                            long minute3=Time%3600/60;
//                            long second3=(Time-Time%3600/60)%60/60;
                            if (second3<10){
                                viewHolder.tv_quzhifu.setText(minute3+":0"+second3);
                            }else {
                                viewHolder.tv_quzhifu.setText(minute3+":"+second3);
                            }

                            Time = Time - 1000;
                            handler.sendEmptyMessageDelayed(0, 1000);
                        }
                    } else {
                        TimeTextView.this.setVisibility(View.GONE);
                    }
                    break;
            }

        }

};


public TimeTextView(Context context) {
        super(context);
        }

public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        }

public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        }

@SuppressLint("NewApi")
    public void setTimes(long mT, DaiFuKuanAdapter.ViewHolder viewHolder) {
    this.viewHolder=viewHolder;
            // 标示已经启动
//            Date date = new Date();
//            long t2 = date.getTime();
//            Time = mT - t2;
            Time = mT;
    Log.i("dateyyy","Time:"+Time+"测试1");
//            date = null;

            if (Time > 0) {
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
            } else {
//            TimeTextView.this.setVisibility(View.GONE);
            }
    }

    public void stop() {
            run = false;
    }
}