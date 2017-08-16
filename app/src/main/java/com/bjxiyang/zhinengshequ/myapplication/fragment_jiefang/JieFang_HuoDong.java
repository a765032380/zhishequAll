package com.bjxiyang.zhinengshequ.myapplication.fragment_jiefang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.AddHuoDongActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.LuXianXiangQingActivity;
import com.bjxiyang.zhinengshequ.myapplication.adapter.HuoDongAdapter;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.bean.FindHuoDongList;
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

public class JieFang_HuoDong extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener,RefreshLayout.OnLoadListener,
        AdapterView.OnItemClickListener {


    /***
     * UI
     */
    private RefreshLayout swipeRefreshLayout;
    private ListView lv_avtivityplanning;
    private View view;
    private ImageView iv_add_activityplanning;

    /**
     * DATA
     */
    private List<FindHuoDongList.ObjBean> mList;
    private List<FindHuoDongList.ObjBean> mListAll;
    private int pageCount=1;
    private int pageSize=5;
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
        mList=new ArrayList();
        mListAll=new ArrayList();
        String url= XY_Response2.URL_NEIGHBOR_FINDPARTY+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null)+
                "&pageCount="+pageCount+"&pageSize="+pageSize;
        RequestCenter.neighbor_findparty(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                FindHuoDongList fanhui= (FindHuoDongList) responseObj;
                if (fanhui.getCode()==1000){
                    mList=fanhui.getObj();
                    mListAll=mList;
                    adapter=new HuoDongAdapter(getContext(),mListAll);
                    lv_avtivityplanning.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),fanhui.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });

//        mList.add("d");
//        adapter=new HuoDongAdapter(getContext(),mList);
//        lv_avtivityplanning.setAdapter(adapter);
    }

    private void initUI() {
        iv_add_activityplanning= (ImageView) view.findViewById(R.id.iv_add_activityplanning);
        iv_add_activityplanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUntil.mStartActivity(getContext(), AddHuoDongActivity.class);
            }
        });
        swipeRefreshLayout= (RefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        lv_avtivityplanning= (ListView) view.findViewById(R.id.lv_avtivityplanning);
        lv_avtivityplanning.setOnItemClickListener(this);




    }

    @Override
    public void onRefresh() {
        getData();
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
                FindHuoDongList fanhui= (FindHuoDongList) responseObj;
                if (fanhui.getCode()==1000){
                    mList=fanhui.getObj();
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(), LuXianXiangQingActivity.class);
        intent.putExtra("partyId",mList.get(position).getPartyId());
        startActivity(intent);
    }
}
