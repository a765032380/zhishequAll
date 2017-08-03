package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HomeAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.RollViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.ViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.bean.Banner;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.test.HomeItem;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyListView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyScrollView;
import com.bjxiyang.zhinengshequ.myapplication.view.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.paradoxie.autoscrolltextview.VerticalTextview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/8/2.
 */

public class HomeFragment extends BaseFragment{
    /**
     * UI
     */
    @BindView(R.id.ll_xuanzedizhi)
    public LinearLayout ll_xuanzedizhi;
    @BindView(R.id.roll_view_pager)
    public RollPagerView mRollViewPager;
    @BindView(R.id.mTextSwitcher)
    public TextView mTextSwitcher;
    @BindView(R.id.tv_xiaoqugonggao)
    public VerticalTextview tv_xiaoqugonggao;
    @BindView(R.id.ll_xiaoqugonggaoxiangqing)
    public LinearLayout ll_xiaoqugonggaoxiangqing;
    @BindView(R.id.tv_jinrongtuijian)
    public VerticalTextview tv_jinrongtuijian;
    @BindView(R.id.ll_jinrongtuijian)
    public LinearLayout ll_jinrongtuijian;
    @BindView(R.id.ll_chaoshitejia)
    public LinearLayout ll_chaoshitejia;
    @BindView(R.id.lv_shangping)
    public MyListView lv_shangping;
    @BindView(R.id.home_viewpage_btn)
    public RollPagerView home_viewpage_btn;
    @BindView(R.id.myScrollView)
    public ScrollView myScrollView;
    @BindView(R.id.title_gone)
    public TextView title_gone;
//    @BindView(R.id.linearlayout_title)
//    public LinearLayout linearlayout_title;
    /**
     * DATE
     */
    private List<Banner.Obj> list = new ArrayList<>();
    private Banner banner;
    private int myScrollViewHeight=0;

    /**
     * Other
     */


    private RollViewAdapter adapter;
    private View view;
    private static OngetData ongetData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home2,container,false);
        ButterKnife.bind(this,view) ;
        initUI();
        initDate();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){
            myScrollViewHeight=myScrollView.getScrollY();
            //TextView停止滚动
            tv_jinrongtuijian.stopAutoScroll();
            tv_xiaoqugonggao.stopAutoScroll();
        }else {
            myScrollView.post(new Runnable() {
                //让scrollview跳转到顶部，必须放在runnable()方法中
                @Override
                public void run() {
                    myScrollView.scrollTo(0,myScrollViewHeight);
                }
            });
            //TextView开始滚动
            tv_jinrongtuijian.startAutoScroll();
            tv_xiaoqugonggao.startAutoScroll();
        }
        super.onHiddenChanged(hidden);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initUI() {

        ArrayList<String> titleList=new ArrayList<>();
        for (int i=0;i<3;i++) {
            titleList.add("测试数据"+i);
        }
        tv_xiaoqugonggao.setTextList(titleList);//加入显示内容,集合类型
        tv_xiaoqugonggao.setText(16,5, Color.GRAY);//设置属性,具体跟踪源码
        tv_xiaoqugonggao.setTextStillTime(3000);//设置停留时长间隔
        tv_xiaoqugonggao.setAnimTime(300);//设置进入和退出的时间间隔
        //对单条文字的点击监听
        tv_xiaoqugonggao.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TO DO
            }
        });
        tv_xiaoqugonggao.startAutoScroll();



        ArrayList<String> jinrongList=new ArrayList<>();
        for (int i=0;i<3;i++) {
            jinrongList.add("测试数据"+i);
        }
        tv_jinrongtuijian.setTextList(jinrongList);//加入显示内容,集合类型
        tv_jinrongtuijian.setText(16,5, Color.GRAY);//设置属性,具体跟踪源码
        tv_jinrongtuijian.setTextStillTime(3000);//设置停留时长间隔
        tv_jinrongtuijian.setAnimTime(300);//设置进入和退出的时间间隔
        //对单条文字的点击监听
        tv_jinrongtuijian.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TO DO
            }
        });
        tv_jinrongtuijian.startAutoScroll();


        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);

        home_viewpage_btn.setHintView(new ColorPointHintView(getContext(),0xff4183ff,0xffc6daff));
        ViewAdapter viewAdapter=new ViewAdapter(home_viewpage_btn,getActivity());
        home_viewpage_btn.setAdapter(viewAdapter);
        List<HomeItem> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            HomeItem homeItem=new HomeItem();
            homeItem.setName("测试"+i);
            list.add(homeItem);
        }
        HomeAdapter homeAdapter=new HomeAdapter(getActivity(),list);

        lv_shangping.setAdapter(homeAdapter);
        MyUntil.setListViewHeightBasedOnChildren(lv_shangping,homeAdapter);

        myScrollView.post(new Runnable() {
            //让scrollview跳转到顶部，必须放在runnable()方法中
            @Override
            public void run() {
                myScrollView.scrollTo(0, 0);
            }
        });

        myScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                int lHeight = 400;
                int progress = (int)(new Float(scrollY)/new Float(lHeight)*255);

//                if (scrollY<40){
//                    Message message=new Message();
//                    message.what=1000;
//                    message.arg1=scrollY;
//                    handler.sendMessage(message);
////                    title_gone.setVisibility(View.GONE);
//                }else
                    if(scrollY<=lHeight){
                        float progress1=new Float(progress)/100;
                        Log.i("YYYY","progress="+progress1);
//                    title_gone.setVisibility(View.VISIBLE);
                        title_gone.setAlpha(progress1);
                }
//                else{
////                    title_gone.setVisibility(View.GONE);
//                        title_gone.setAlpha(AlphaAnimation.ABSOLUTE);
//                }
            }
        });
//        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
//            @Override
//            public void onScroll(int scrollY) {
//                myScrollViewHeight=scrollY;
//                int lHeight = 400;
//                int progress = (int)(new Float(scrollY)/new Float(lHeight)*255);
//
//                if (scrollY<50){
//                    title_gone.getBackground().setBounds(0,progress,0,0);
////                    title_gone.setVisibility(View.GONE);
//                }else if(scrollY<=lHeight){
//                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,scrollY);
//                    title_gone.setLayoutParams(params);
////                    title_gone.setVisibility(View.VISIBLE);
//                    title_gone.getBackground().setAlpha(progress);
//                }else{
////                    title_gone.setVisibility(View.GONE);
//                    title_gone.getBackground().setAlpha(255);
//                }
//            }
//        });

    }


    private  List<Banner.Obj> initDate() {

        list=new ArrayList<>();
        String url= XY_Response.URL_BANNERLIST+"mobilePhone=18813045215";
//                +SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.bannerList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                banner = (Banner) responseObj;
                if (banner.getCode().equals("1000")){
                    list= banner.getObj();
                    if (list.size()>0){
                        local();
                    }
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
            }
        });
        return list;
    }

    public void local() {
        //设置适配器
        adapter = new RollViewAdapter(mRollViewPager);
        mRollViewPager.setAdapter(adapter);
        ongetData.OngetData(list);
    }
    public static void setOngetData(OngetData ongetDat){
        ongetData=ongetDat;
    }

    public interface OngetData{
        public void OngetData(List<Banner.Obj> mList);
    }

}
