package com.bjxiyang.zhinengshequ.myapplication.fragment_jiefang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HuoDongAdapter;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class JieFang_HuoDong extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RefreshLayout.OnLoadListener {


    /***
     * UI
     */
    private RefreshLayout swipeRefreshLayout;
    private ListView lv_avtivityplanning;
    private View view;

    /**
     * DATA
     */
    private List mList;
    private List mListAll;
    private int pageCount;
    private int pageSize;
    private boolean isScoll=true;

    /***
     * OTHER
     */
    private HuoDongAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getContext(), R.layout.fragment_avtivityplanning,null);
        initUI();
        getData();
        return view;
    }

    private void getData() {
        String url= XY_Response2.URL_NEIGHBOR_FINDPARTY+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null)+
                "&pageCount="+pageCount+"&pageSize="+pageSize;




        mList=new ArrayList();
        mListAll=new ArrayList();
        mList.add("d");

        adapter=new HuoDongAdapter(getContext(),mList);
        lv_avtivityplanning.setAdapter(adapter);
    }

    private void initUI() {
        swipeRefreshLayout= (RefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        lv_avtivityplanning= (ListView) view.findViewById(R.id.lv_avtivityplanning);





    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void onLoad() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新
                swipeRefreshLayout.setLoading(false);
                if (isScoll){
                    pageCount=pageCount+1;
                    upData();
                }else {
                    MyUntil.show(getContext(),"无更多数据");
                }
            }
        }, 1000);
    }
    private void upData() {
        mList=new ArrayList<>();
        String url= XY_Response2.URL_NEIGHBOR_FINDPARTY+"cmemberId="+
                UserManager.getInstance().getUser().getObj().getC_memberId()+
                "&pageCount="+pageCount+"&pageSize="+pageSize;

        RequestCenter.neighbor_findparty(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                FanHui fanhui= (FanHui) responseObj;
                if (fanhui.getCode().equals("1000")){
//                    mList=fanhui.getObj();
                    if (mList.size()<pageSize){
                        isScoll=false;
                    }
                    for (int i = 0; i < mList.size(); i++) {
                        mListAll.add(mList.get(i));
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),fanhui.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }


}
