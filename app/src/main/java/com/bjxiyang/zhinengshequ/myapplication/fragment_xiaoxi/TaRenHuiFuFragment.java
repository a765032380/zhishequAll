package com.bjxiyang.zhinengshequ.myapplication.fragment_xiaoxi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.TaRenHuiFuAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class TaRenHuiFuFragment extends BaseFragment {
    private int type;
    private View view;
    private ListView list_view;
    private TaRenHuiFuAdapter adapter;
    private List mList;
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
        if (type==1){
            getData1();
        }else if (type==2){
            getData2();
        }

        adapter=new TaRenHuiFuAdapter(getContext(),mList);
        list_view.setAdapter(adapter);
        return view;

    }
    private void getData1(){
        mList=new ArrayList();
        mList.add("123456");
    }
    private void getData2(){
        String url= XY_Response2.URL_USERCENTER_GETSYSMSG
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                +"&pageCount="+pageCount_sys
                +"&pageSize="+pageSize_sys;
        RequestCenter.usercenter_getSysMsg(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });



        mList=new ArrayList();
        mList.add("123456");
    }
}
