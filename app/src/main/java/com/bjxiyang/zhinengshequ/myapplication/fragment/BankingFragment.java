package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYKeyAccredit;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/8/2.
 */

public class BankingFragment extends BaseFragment implements View.OnClickListener {
    private View view;

    /***
     * UI
     */
    @BindView(R.id.ll_tianjiafangwu)
    public LinearLayout ll_tianjiafangwu;
    @BindView(R.id.ll_bianlidian)
    public LinearLayout ll_bianlidian;
    @BindView(R.id.ll_sehnghuojiaofei)
    public LinearLayout ll_sehnghuojiaofei;
    @BindView(R.id.ll_menjinjilu)
    public LinearLayout ll_menjinjilu;
    @BindView(R.id.ll_chongzhika)
    public LinearLayout ll_chongzhika;
    @BindView(R.id.ll_bianlidian2)
    public LinearLayout ll_bianlidian2;
    @BindView(R.id.ll_tejiashangpin)
    public LinearLayout ll_tejiashangpin;
    @BindView(R.id.ll_shangpintuijia)
    public LinearLayout ll_shangpintuijia;
    @BindView(R.id.ll_jinrongfuwu1)
    public LinearLayout ll_jinrongfuwu1;
    @BindView(R.id.ll_xiyanglvyou)
    public LinearLayout ll_xiyanglvyou;
    @BindView(R.id.ll_zhoubianyou)
    public LinearLayout ll_zhoubianyou;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_service,container,false);
        ButterKnife.bind(this,view) ;
        ll_tianjiafangwu.setOnClickListener(this);
        ll_bianlidian.setOnClickListener(this);
        ll_sehnghuojiaofei.setOnClickListener(this);
        ll_menjinjilu.setOnClickListener(this);
        ll_chongzhika.setOnClickListener(this);
        ll_bianlidian2.setOnClickListener(this);
        ll_tejiashangpin.setOnClickListener(this);
        ll_shangpintuijia.setOnClickListener(this);
        ll_jinrongfuwu1.setOnClickListener(this);
        ll_xiyanglvyou.setOnClickListener(this);
        ll_zhoubianyou.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_tianjiafangwu:
                startActivity(XYKeyAccredit.class);
                break;
            case R.id.ll_bianlidian:
                startActivity(SupermarketActivity.class);
                break;
            case R.id.ll_sehnghuojiaofei:
                break;
            case R.id.ll_menjinjilu:
                break;
            case R.id.ll_chongzhika:
                break;
            case R.id.ll_bianlidian2:
                break;
            case R.id.ll_tejiashangpin:
                break;
            case R.id.ll_shangpintuijia:
                break;
            case R.id.ll_jinrongfuwu1:
                break;
            case R.id.ll_xiyanglvyou:
                break;
            case R.id.ll_zhoubianyou:
                break;
        }
    }
    public void startActivity(Class mClass){
        Intent intent=new Intent(getContext(),mClass);
        startActivity(intent);

    }
}
