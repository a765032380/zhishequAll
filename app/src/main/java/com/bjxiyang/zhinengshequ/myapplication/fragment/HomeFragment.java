package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.BianLiDianListActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.JinRongActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.MyWebViewActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XiaoQuGongGaoActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XuanZeXiaoQuActivity;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HomeAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HomeJinRongAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.RollViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.ViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.bean.Banner;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean2;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.test.HomeItem;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyListView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyScrollView;
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

public class HomeFragment extends BaseFragment implements View.OnClickListener{
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
    public MyScrollView myScrollView;
    @BindView(R.id.title_gone)
    public TextView title_gone;
    @BindView(R.id.tv_more)
    public TextView tv_more;
    @BindView(R.id.list_view_home_jinrong)
    public ListView list_view_home_jinrong;
    @BindView(R.id.tv_chaoshitejia_more)
    public TextView tv_chaoshitejia_more;

    /**
     * DATE
     */
    private List<Banner.Obj> list = new ArrayList<>();
    private Banner banner;
    private int myScrollViewHeight=0;
    /***
     * 百度地图
     */
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public List<HomeBean.ObjBean.BannerObjBean> bannerBean;
    public List<HomeBean.ObjBean.FinanceObjBean> financeBean;
    public List<HomeBean.ObjBean.NewestObjBean> newestBean;
    public List<HomeBean.ObjBean.NoticeObjBean> noticeBean;
    public List<HomeBean.ObjBean.ShopObjBean> specialBean;


    /**
     * Other
     */


    private RollViewAdapter adapter;
    private View view;
    private static OngetData ongetData;
    private boolean isone=true;



    public Handler mHandle=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            BDLocation location= (BDLocation) msg.obj;


            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home2,container,false);
        ButterKnife.bind(this,view) ;

        mLocationClient = new LocationClient(GuardApplication.getContent());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        initLocation();
        mLocationClient.start();
        //注册监听函数
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
                if(tv_jinrongtuijian.isScrollContainer()) {
                    tv_jinrongtuijian.stopAutoScroll();
                }
                if(tv_xiaoqugonggao.isScrollContainer()) {
                    tv_xiaoqugonggao.stopAutoScroll();
                }


//                tv_jinrongtuijian.stopAutoScroll();
//                tv_xiaoqugonggao.stopAutoScroll();
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
                if(tv_jinrongtuijian.isScrollContainer()) {
                    tv_jinrongtuijian.startAutoScroll();
                }
                if(tv_xiaoqugonggao.isScrollContainer()) {
                    tv_xiaoqugonggao.startAutoScroll();
                }
            }
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        if (SPManager.getInstance().getString("communityName",null)!=null) {
            title_gone.setText(SPManager.getInstance().getString("communityName",null));
            mTextSwitcher.setText(SPManager.getInstance().getString("communityName",null));
        }else {
            title_gone.setText("请选择地址");
            mTextSwitcher.setText("请选择地址");
        }
        super.onResume();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initUI() {

        tv_chaoshitejia_more.setOnClickListener(this);
        ll_xuanzedizhi.setOnClickListener(this);
        tv_more.setOnClickListener(this);

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

        myScrollView.setOnScrollChanged(new MyScrollView.OnScrollChanged() {
            @Override
            public void onScroll(int l, int scrollY, int oldl, int oldt) {
                int lHeight = 400;
                int progress = (int)(new Float(scrollY)/new Float(lHeight)*255);
                if(scrollY<=lHeight){
                    float progress1=new Float(progress)/200;

                    title_gone.setAlpha(progress1);
                }
            }
        });
//        myScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                int lHeight = 400;
//                int progress = (int)(new Float(scrollY)/new Float(lHeight)*255);
//                    if(scrollY<=lHeight){
//                        float progress1=new Float(progress)/200;
//
//                        title_gone.setAlpha(progress1);
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
        ongetData.OngetData(bannerBean);
    }
    public static void setOngetData(OngetData ongetDat){
        ongetData=ongetDat;
    }

    public void getDate() {
        String url= XY_Response2.URL_HOME+"cmemberId=99";
        RequestCenter.home_2(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                HomeBean homebean= (HomeBean) responseObj;
                if (homebean.getCode()==1000){
                    bannerBean=homebean.getObj().getBannerObj();
                    financeBean=homebean.getObj().getFinanceObj();
                    newestBean=homebean.getObj().getNewestObj();
                    noticeBean=homebean.getObj().getNoticeObj();
                    specialBean=homebean.getObj().getShopObj();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_xuanzedizhi:
                MyUntil.mStartActivity(getContext(), XuanZeXiaoQuActivity.class);
                break;
            case R.id.tv_more:
                Intent intent=new Intent(getContext(), JinRongActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chaoshitejia_more:
                Intent intent1=new Intent(getContext(), BianLiDianListActivity.class);
                startActivity(intent1);
                break;
        }
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
                Intent intent=new Intent(getContext(), XiaoQuGongGaoActivity.class);
                startActivity(intent);
                // TO DO
            }
        });
        tv_xiaoqugonggao.startAutoScroll();
    }
    private void setJinRong(final List<HomeBean.ObjBean.FinanceObjBean> jinrongList){
        HomeJinRongAdapter adapter=new HomeJinRongAdapter(getContext(),jinrongList);
        list_view_home_jinrong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getContext(), MyWebViewActivity.class);
                intent.putExtra("url",jinrongList.get(position).getFinanceUrl());
                startActivity(intent);

            }
        });
        list_view_home_jinrong.setAdapter(adapter);

        MyUntil.setListViewHeightBasedOnChildren(list_view_home_jinrong,adapter);
    }

    private void setJinRongGongGao(final List<HomeBean.ObjBean.NewestObjBean> jinronggongGaoList){

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

//                Intent intent=new Intent(getContext(), MyWebViewActivity.class);
//                intent.putExtra("url",jinronggongGaoList.get(position).getLoanName());
//                startActivity(intent);

                // TO DO
            }
        });
        tv_jinrongtuijian.startAutoScroll();

    }
    private void setChaoShiList(List<HomeBean.ObjBean.ShopObjBean> chaoshiList){
        if (chaoshiList.size()>0) {
            HomeAdapter homeAdapter = new HomeAdapter(getContext(), chaoshiList);
            lv_shangping.setAdapter(homeAdapter);
            MyUntil.setListViewHeightBasedOnChildren(lv_shangping, homeAdapter);
        }
    }
    private void initLocation(){

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("gcj02");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        mLocationClient.setLocOption(option);
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            if (location.getLocType() == BDLocation.TypeGpsLocation||
                    location.getLocType() == BDLocation.TypeNetWorkLocation||
                    location.getLocType() == BDLocation.TypeOffLineLocation) {
//                location.getLatitude();    //获取纬度信息
//                location.getLongitude();    //获取经度信息
                Log.i("llll","经度:"+location.getLatitude()+"纬度:"+location.getLongitude());

                String url=XY_Response2.URL_HOME_SELLER+"cmemberId="+
                        SPManager.getInstance().getString("c_memberId","")
                        +"&lng="+location.getLongitude()+"&lat="+location.getLatitude();

                RequestCenter.home_Seller(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        HomeBean2 homeBean2= (HomeBean2) responseObj;
                        List<HomeBean.ObjBean.ShopObjBean> chaoshiList=homeBean2.getObj();
//                        List<HomeBean.ObjBean.ShopObjBean> chaoshiList= (List<HomeBean.ObjBean.ShopObjBean>) responseObj;
                        if (chaoshiList.size()>0){
                            setChaoShiList(chaoshiList);
                        }

                        Log.i("LLLL","请求成功");
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Log.i("LLLL","请求失败");
                    }
                });
//                Message message=new Message();
//                message.obj=location;
//                mHandle.sendMessage(message);

                //当前为GPS定位结果，可获取以下信息

            }  else if (location.getLocType() == BDLocation.TypeServerError) {
                Log.i("LLL","定位失败");
                //当前网络定位失败
                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                Log.i("LLL","定位失败");
                //当前网络不通

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                Log.i("LLL","定位失败");
                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                //可进一步参考onLocDiagnosticMessage中的错误返回码

            }
        }
    }

}
