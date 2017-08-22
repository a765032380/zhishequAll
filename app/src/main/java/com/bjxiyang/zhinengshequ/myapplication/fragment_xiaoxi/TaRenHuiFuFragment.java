package com.bjxiyang.zhinengshequ.myapplication.fragment_xiaoxi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.TaRenHuiFuAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.bean.XiTongXiaoXi;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class TaRenHuiFuFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private int type;
    private View view;
    private ListView list_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TaRenHuiFuAdapter adapter;
    private List<XiTongXiaoXi.ObjBean> mList2;
    @SuppressLint({"NewApi", "ValidFragment"})
    public TaRenHuiFuFragment(int type){
        this.type=type;
    }
    @SuppressLint({"NewApi", "ValidFragment"})
    public TaRenHuiFuFragment(){
    }

    private int pageCount_sys=1;
    private int pageSize_sys=5;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tarenhuifu,null);
        list_view=view.findViewById(R.id.list_view);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (type==1){
            getData1();
        }else if (type==2){
            getData2();
        }
        return view;

    }
    private void getData1(){

    }
    private void getData2(){
        mList2=new ArrayList<>();
        String url= XY_Response2.URL_USERCENTER_GETSYSMSG
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                +"&pageCount="+pageCount_sys
                +"&pageSize="+pageSize_sys;
        RequestCenter.usercenter_getSysMsg(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                XiTongXiaoXi xiTongXiaoXi= (XiTongXiaoXi) responseObj;
                if (xiTongXiaoXi.getCode()==1000){
                    mList2=xiTongXiaoXi.getObj();
                    adapter=new TaRenHuiFuAdapter(getContext(),mList2,type);
                    list_view.setAdapter(adapter);
                }else {
                    MyUntil.show(getContext(),xiTongXiaoXi.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    @Override
    public void onRefresh() {
        if (type==1){
            getData1();
        }else if (type==2){
            getData2();
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
