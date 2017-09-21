package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.MyFragmentAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.LogOutBaseActivity;
import com.bjxiyang.zhinengshequ.myapplication.fragment_xiaoxi.TaRenHuiFuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class HuiFuActivity extends LogOutBaseActivity {
//    private static final String TARENHUIFU="他人回复";
    private static final String XITONGXIAOXI="系统消息";
    private static final int TARENHUIFU_INT=1;//暂时无用
    private static final int XITONGXIAOXI_INT=2;//暂时无用


    private MyFragmentAdapter mMyFragmentAdapter;
    //声明ViewPager
    private ViewPager mViewPager;
    //ViewPager的指示器
    private PagerTabStrip mPagerTabStrip;
    //储存ViewPager的指示器文本内容的集合
    private List<String> mTitleList;
    //储存Fragment的集合
    private List<Fragment> mFragmentList;
    //Fragment管理者
    private FragmentManager fm;

    private PagerSlidingTabStrip tabs;
    @BindView(R.id.rl_wodeyueyou_fanghui)
    RelativeLayout rl_wodeyueyou_fanghui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wofaqideyueyou);
        ButterKnife.bind(this);
        initUI();
    }
    private void initUI() {
        rl_wodeyueyou_fanghui= (RelativeLayout) findViewById(R.id.rl_wodeyueyou_fanghui);
        rl_wodeyueyou_fanghui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tabs= (PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabs.setTextColor(Color.WHITE);
        //实例化UI
        mViewPager= (ViewPager) findViewById(R.id.pager);
//        mPagerTabStrip= (PagerTabStrip)view. findViewById(R.id.tab);
        //实例化Fragment管理者
        fm=getSupportFragmentManager();
        getData();
        //实例化适配器
        mMyFragmentAdapter=new MyFragmentAdapter(fm,mFragmentList,mTitleList);
        //加载适配器
        mViewPager.setAdapter(mMyFragmentAdapter);

        tabs.setViewPager(mViewPager);
    }
    private void getData(){
        //实例化集合
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
//        mTitleList.add(TARENHUIFU);
        mTitleList.add(XITONGXIAOXI);

//        mFragmentList.add(new TaRenHuiFuFragment(TARENHUIFU_INT));
        mFragmentList.add(new TaRenHuiFuFragment(XITONGXIAOXI_INT));

    }


}
