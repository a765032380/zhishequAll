package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.BianLiDianListActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.JinRongActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.MyWebViewActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.ShengHuoJiaoFeiActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYKeyAccredit;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYMenJinJiLuActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XuanZeXiaoQuActivity;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;

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
    @BindView(R.id.ll_jinrongfuwu2)
    public LinearLayout ll_jinrongfuwu2;
    @BindView(R.id.ll_jinrongfuwu3)
    public LinearLayout ll_jinrongfuwu3;
    @BindView(R.id.ll_jinrongfuwu4)
    public LinearLayout ll_jinrongfuwu4;
    @BindView(R.id.ll_jinrongfuwu5)
    public LinearLayout ll_jinrongfuwu5;








    @BindView(R.id.ll_xiyanglvyou)
    public LinearLayout ll_xiyanglvyou;
    @BindView(R.id.ll_zhoubianyou)
    public LinearLayout ll_zhoubianyou;
    @BindView(R.id.ll_service_selectaddress)
    public LinearLayout ll_service_selectaddress;
    @BindView(R.id.mTextSwitcher)
    public TextView mTextSwitcher;

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
        ll_jinrongfuwu2.setOnClickListener(this);
        ll_jinrongfuwu3.setOnClickListener(this);
        ll_jinrongfuwu4.setOnClickListener(this);
        ll_jinrongfuwu5.setOnClickListener(this);


        ll_xiyanglvyou.setOnClickListener(this);
        ll_zhoubianyou.setOnClickListener(this);
        ll_service_selectaddress.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        if (SPManager.getInstance().getString("communityName",null)!=null) {
            mTextSwitcher.setText(SPManager.getInstance().getString("communityName",null));
        }else {
            mTextSwitcher.setText("请选择地址");
        }
        super.onResume();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //添加房屋
            case R.id.ll_tianjiafangwu:
                startActivity(XYKeyAccredit.class);
                break;
            //便利店
            case R.id.ll_bianlidian:
                startActivity(BianLiDianListActivity.class);
                break;
            //生活缴费
            case R.id.ll_sehnghuojiaofei:
                startActivity(ShengHuoJiaoFeiActivity.class);
                break;
            //门禁记录
            case R.id.ll_menjinjilu:
                startActivity(XYMenJinJiLuActivity.class);
                break;
            //充值卡
            case R.id.ll_chongzhika:
                break;
            case R.id.ll_bianlidian2:
                startActivity(BianLiDianListActivity.class);
                break;
            //特价商品
            case R.id.ll_tejiashangpin:
                Intent spTeJia=new Intent(getContext(),BianLiDianListActivity.class);
                spTeJia.putExtra("type",1);
                startActivity(spTeJia);
                break;
            //商品推荐
            case R.id.ll_shangpintuijia:
                Intent spTuiJian=new Intent(getContext(),BianLiDianListActivity.class);
                spTuiJian.putExtra("type",2);
                startActivity(spTuiJian);
                break;
            //金融服务
            case R.id.ll_jinrongfuwu1:
                startActivity(JinRongActivity.class);
                break;
            //过桥垫资
            case R.id.ll_jinrongfuwu2:
                startActivity(JinRongActivity.class);
                break;
            //抵押
            case R.id.ll_jinrongfuwu3:
                Intent intent=new Intent(getContext(),JinRongActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            //信用
            case R.id.ll_jinrongfuwu4:
                Intent intent2=new Intent(getContext(),JinRongActivity.class);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;
            //二手房
            case R.id.ll_jinrongfuwu5:
                Intent intent3=new Intent(getContext(),JinRongActivity.class);
                intent3.putExtra("type",3);
                startActivity(intent3);
                break;
            //希洋旅游
            case R.id.ll_xiyanglvyou:
                Intent intent4=new Intent(getContext(), MyWebViewActivity.class);
                intent4.putExtra("url","");
                startActivity(intent4);

                break;
            //周边游
            case R.id.ll_zhoubianyou:
                Intent intent5=new Intent(getContext(), MyWebViewActivity.class);
                intent5.putExtra("url","");
                startActivity(intent5);
                break;
            //选择地址
            case R.id.ll_service_selectaddress:
                MyUntil.mStartActivity(getContext(), XuanZeXiaoQuActivity.class);
                break;
        }
    }
    public void startActivity(Class mClass){
        Intent intent=new Intent(getContext(),mClass);
        startActivity(intent);

    }
}
