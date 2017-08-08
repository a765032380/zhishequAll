package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.MyFragmentAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.fragment_jinrong.FragmentFour;
import com.bjxiyang.zhinengshequ.myapplication.fragment_jinrong.FragmentOne;
import com.bjxiyang.zhinengshequ.myapplication.fragment_jinrong.FragmentThree;
import com.bjxiyang.zhinengshequ.myapplication.fragment_jinrong.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class JinRongActivity extends MySwipeBackActivity{
    private static final String ERSHOUFANG="二手房";
    private static final String DIYA="抵押";
    private static final String SHOULOUGUOQIAO="垫资";
    private static final String XINYONG="信用";

    private View view;
    private Context mContext;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking);
        iniUI();
    }

    private void iniUI() {
        //实例化UI
        mViewPager= (ViewPager)findViewById(R.id.pager);
//        mPagerTabStrip= (PagerTabStrip)view. findViewById(R.id.tab);
        //实例化Fragment管理者
        fm=getSupportFragmentManager();
        //实例化集合
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
        //获取数据源
        getData();
        //实例化适配器
        mMyFragmentAdapter=new MyFragmentAdapter(fm,mFragmentList,mTitleList);
        //加载适配器
        mViewPager.setAdapter(mMyFragmentAdapter);
        tabs= (PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);
    }
    private void getData(){
        mTitleList.add(SHOULOUGUOQIAO);
        mTitleList.add(XINYONG);
        mTitleList.add(DIYA);
        mTitleList.add(ERSHOUFANG);

        mFragmentList.add(new FragmentOne());
        mFragmentList.add(new FragmentFour());
        mFragmentList.add(new FragmentTwo());
        mFragmentList.add(new FragmentThree());

    }
}
