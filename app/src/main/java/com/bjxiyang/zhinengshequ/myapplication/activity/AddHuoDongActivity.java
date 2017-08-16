package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;

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
    @BindView(R.id.et_startActivities_time)
    EditText et_startActivities_time;
    @BindView(R.id.et_startActivities_money)
    EditText et_startActivities_money;
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
    private String time;
    private String money;
    private String datecount;
    private String yaoqiu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqihuodong);
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
        if (time!=null){
            et_startActivities_time.setText(time);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_startActivities_fanhui:
                finish();
                break;
            case R.id.tv_next:
                startAddress=String.valueOf(et_startActivities_go.getText());
                toAddress=String.valueOf(et_startActivities_arrive.getText());
                time=String.valueOf(et_startActivities_time.getText());
                money=String.valueOf(et_startActivities_money.getText());
                datecount=String.valueOf(et_startActivities_datecount.getText());
                yaoqiu=String.valueOf(et_startActivities_yaoqiu.getText());
                Intent intent=new Intent(AddHuoDongActivity.this,AddHuoDongNextActivity.class);
                intent.putExtra("startAddress",startAddress);
                intent.putExtra("toAddress",toAddress);
                intent.putExtra("time",time);
                intent.putExtra("money",money);
                intent.putExtra("datecount",datecount);
                intent.putExtra("yaoqiu",yaoqiu);
                startActivity(intent);
                break;
        }
    }
}
