package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HomeAdapter;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean2;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.fragment.HomeFragment;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyListView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class BianLiDianListActivity extends MySwipeBackActivity {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private RelativeLayout iv_daifukuan_fanhui;
    private ListView lv_bianlidian;
    private int type;
    private boolean isOne=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianlidian);
        initUI();
        Intent intent=getIntent();
        type=intent.getIntExtra("type",0);
        mLocationClient = new LocationClient(GuardApplication.getContent());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        initLocation();
        mLocationClient.start();

//        initLocation();
    }

    private void initUI() {
        lv_bianlidian= (ListView) findViewById(R.id.lv_bianlidian);

        iv_daifukuan_fanhui= (RelativeLayout) findViewById(R.id.iv_daifukuan_fanhui);
        iv_daifukuan_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

//            if (location.getLocType() == BDLocation.TypeGpsLocation||
//                    location.getLocType() == BDLocation.TypeNetWorkLocation||
//                    location.getLocType() == BDLocation.TypeOffLineLocation) {
                location.getLatitude();    //获取纬度信息
                location.getLongitude();    //获取经度信息
                Log.i("llll","经度:"+location.getLatitude()+"纬度:"+location.getLongitude());

            if (isOne) {
                String url = XY_Response2.URL_HOME_SELLER + "cmemberId=" +
                        SPManager.getInstance().getString("c_memberId", "0")
                        + "&lng=" + location.getLongitude() + "&lat=" + location.getLatitude()
                        + "&type=" + type;
                Log.i("lll", url);
                RequestCenter.home_Seller(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        HomeBean2 homeBean2 = (HomeBean2) responseObj;
                        List<HomeBean.ObjBean.ShopObjBean> chaoshiList = homeBean2.getObj();
//                        List<HomeBean.ObjBean.ShopObjBean> chaoshiList= (List<HomeBean.ObjBean.ShopObjBean>) responseObj;
                        if (chaoshiList.size() > 0) {
                            setChaoShiList(chaoshiList);
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Log.i("LLLL", "请求失败");
                    }
                });
                isOne=false;

            }
                mLocationClient.stop();
//                Message message=new Message();
//                message.obj=location;
//                mHandle.sendMessage(message);

                //当前为GPS定位结果，可获取以下信息

//            }  else if (location.getLocType() == BDLocation.TypeServerError) {
//                Log.i("LLL","定位失败");
//                //当前网络定位失败
//                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                Log.i("LLL","定位失败");
//                //当前网络不通
//
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                Log.i("LLL","定位失败");
//                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
//                //可进一步参考onLocDiagnosticMessage中的错误返回码
//
//            }else {
//                mLocationClient.stop();
//            }
        }
    }
    private void setChaoShiList(List<HomeBean.ObjBean.ShopObjBean> chaoshiList){
        if (chaoshiList.size()>0) {
            HomeAdapter homeAdapter = new HomeAdapter(this, chaoshiList);
            lv_bianlidian.setAdapter(homeAdapter);
//            MyUntil.setListViewHeightBasedOnChildren(lv_shangping, homeAdapter);
        }
    }
}
