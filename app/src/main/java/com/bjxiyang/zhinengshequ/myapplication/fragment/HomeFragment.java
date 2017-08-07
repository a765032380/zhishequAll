package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HomeAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HomeJinRongAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.RollViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.ViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.bean.Banner;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.test.HomeItem;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyListView;
import com.google.gson.Gson;
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
    private static final int ERSHOUFANG=1;
    private static final int DIANZI=2;
    private static final int XINYONG=3;
    private static final int DIYA=4;


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

    @BindView(R.id.list_view_home_jinrong)
    public ListView list_view_home_jinrong;

    /**
     * DATE
     */
    private List<Banner.Obj> list = new ArrayList<>();
    private Banner banner;
    private int myScrollViewHeight=0;

    public List<HomeBean.ObjBean.BannerObjBean> bannerBean;
    public List<HomeBean.ObjBean.FinanceObjBean> financeBean;
    public List<HomeBean.ObjBean.NewestObjBean> newestBean;
    public List<HomeBean.ObjBean.NoticeObjBean> noticeBean;
    public List<HomeBean.ObjBean.SpecialObjBean> specialBean;


    /**
     * Other
     */


    private RollViewAdapter adapter;
    private View view;
    private static OngetData ongetData;
    private boolean isone=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home2,container,false);
        ButterKnife.bind(this,view) ;
        initUI();
        getDate();
        title_gone.setText("请选择地址");

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){
            if (isone){
                myScrollViewHeight=0;
                isone=false;
            }else {
                myScrollViewHeight = myScrollView.getScrollY();
                //TextView停止滚动
                tv_jinrongtuijian.stopAutoScroll();
                tv_xiaoqugonggao.stopAutoScroll();
            }
        }else {
            myScrollView.post(new Runnable() {
                //让scrollview跳转到顶部，必须放在runnable()方法中
                @Override
                public void run() {
                    myScrollView.scrollTo(0,myScrollViewHeight);
                }
            });
            if (!isone) {
                //TextView开始滚动
                tv_jinrongtuijian.startAutoScroll();
                tv_xiaoqugonggao.startAutoScroll();
            }
        }
        super.onHiddenChanged(hidden);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initUI() {




        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);

        home_viewpage_btn.setHintView(new ColorPointHintView(getContext(),0xff4183ff,0xffc6daff));
        ViewAdapter viewAdapter=new ViewAdapter(home_viewpage_btn,getActivity());
        home_viewpage_btn.setAdapter(viewAdapter);




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
                    if(scrollY<=lHeight){
                        float progress1=new Float(progress)/200;

                        title_gone.setAlpha(progress1);
                }
            }
        });

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
        ongetData.OngetData(bannerBean);
    }
    public static void setOngetData(OngetData ongetDat){
        ongetData=ongetDat;
    }

    public void getDate() {
        String url= XY_Response2.URL_HOME+"cmemberId=81";
        RequestCenter.home_2(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                HomeBean homebean= (HomeBean) responseObj;
                if (homebean.getCode()==1000){
                    bannerBean=homebean.getObj().getBannerObj();
                    financeBean=homebean.getObj().getFinanceObj();
                    newestBean=homebean.getObj().getNewestObj();
                    noticeBean=homebean.getObj().getNoticeObj();
                    specialBean=homebean.getObj().getSpecialObj();

//                    list= bannerBean.getObj();
                    if (bannerBean.size()>0){
                        local();
                    }
                    if (noticeBean.size()>0){
                        setGongGao(noticeBean);
                    }
                    if (financeBean.size()>0){
                        setJinRong(financeBean);
                    }
                    if (newestBean.size()>0){
                        setJinRongGongGao(newestBean);
                    }
                    if (specialBean.size()>0){
                        setChaoShiList(specialBean);
                    }


                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i("yyyy","请求失败");
            }
        });
    }

    public interface OngetData{
        public void OngetData(List<HomeBean.ObjBean.BannerObjBean> mList);
    }
    private void setGongGao(List<HomeBean.ObjBean.NoticeObjBean> list){
        ArrayList<String> titleList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            titleList.add(list.get(i).getTitle());
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
    }
    private void setJinRong(List<HomeBean.ObjBean.FinanceObjBean> jinrongList){
        HomeJinRongAdapter adapter=new HomeJinRongAdapter(getContext(),jinrongList);
        list_view_home_jinrong.setAdapter(adapter);
        MyUntil.setListViewHeightBasedOnChildren(list_view_home_jinrong,adapter);
    }

    private void setJinRongGongGao(List<HomeBean.ObjBean.NewestObjBean> jinronggongGaoList){

        ArrayList<String> jinrongList=new ArrayList<>();
        for (int i=0;i<jinronggongGaoList.size();i++) {
            jinrongList.add(jinronggongGaoList.get(i).getLoanName());
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

    }
    private void setChaoShiList(List<HomeBean.ObjBean.SpecialObjBean> chaoshiList){

        HomeAdapter homeAdapter=new HomeAdapter(getContext(),chaoshiList);
        lv_shangping.setAdapter(homeAdapter);
        MyUntil.setListViewHeightBasedOnChildren(lv_shangping,homeAdapter);
    }
}
