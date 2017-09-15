package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.view.MyListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class ShouSuoActivity extends MySwipeBackActivity {


    @BindView(R.id.et_allapp)
    EditText et_allapp;
//    @BindView(R.id.iv_search_fanhui)
//    RelativeLayout iv_search_fanhui;
    @BindView(R.id.ll_activity_wujilu)
    LinearLayout ll_activity_wujilu;



    @BindView(R.id.tv_car)
    TextView tv_car;
    @BindView(R.id.bv_unm)
    TextView bv_unm;
    @BindView(R.id.tv_totle_money)
    TextView tv_totle_money;
    @BindView(R.id.xuanhaole)
    TextView xuanhaole;
    @BindView(R.id._lv_search)
    MyListView _lv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

    }
}
