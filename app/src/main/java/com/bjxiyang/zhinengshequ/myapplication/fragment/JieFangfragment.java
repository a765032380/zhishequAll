package com.bjxiyang.zhinengshequ.myapplication.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.MyFragmentAdapter;
import com.bjxiyang.zhinengshequ.myapplication.fragment_jiefang.JieFang_HaoYou;
import com.bjxiyang.zhinengshequ.myapplication.fragment_jiefang.JieFang_HuoDong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gll on 17-5-20.
 */

public class JieFangfragment extends Fragment{
    private static final String HAOYOU="好友";
    private static final String HUODONG="活动";
    private static final String ZHOUBIANSHI="周边事";

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.jiefang_fragment,container,false);
        init();
        //获取数据源
        getData();
        //实例化适配器
        mMyFragmentAdapter=new MyFragmentAdapter(fm,mFragmentList,mTitleList);
        //加载适配器
        mViewPager.setAdapter(mMyFragmentAdapter);


        tabs.setViewPager(mViewPager);

        return view;
    }
    private void init() {
        tabs= (PagerSlidingTabStrip)view.findViewById(R.id.tabs);
        tabs.setTextColor(Color.WHITE);
        //实例化UI
        mViewPager= (ViewPager) view.findViewById(R.id.pager);
//        mPagerTabStrip= (PagerTabStrip)view. findViewById(R.id.tab);
        //实例化Fragment管理者
        fm=getFragmentManager();
        //实例化集合
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
        //设置mPagerTabStrip属性
        //设置背景颜色
//        mPagerTabStrip.setBackgroundColor(Color.WHITE);
//        //设置字体颜色
//        mPagerTabStrip.setTextColor(Color.BLACK);
//        //取消分割线
//        mPagerTabStrip.setDrawFullUnderline(false);
//        //设置指示颜色
//        mPagerTabStrip.setTabIndicatorColor(Color.BLACK);
    }

    private void getData(){
        mTitleList.add(HAOYOU);
        mTitleList.add(HUODONG);
        mTitleList.add(ZHOUBIANSHI);

        mFragmentList.add(new JieFang_HaoYou());
        mFragmentList.add(new JieFang_HuoDong());
//        mFragmentList.add(new FragmentTwo());

    }

}
