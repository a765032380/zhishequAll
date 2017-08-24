package com.bjxiyang.zhinengshequ.myapplication.fragment_jiefang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

import static android.app.Activity.RESULT_OK;

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
    private int height;
    private int partyType;
    private boolean isxiangqing=false;

    /***
     * OTHER
     */
    private HuoDongAdapter adapter;
    private boolean isOne=true;
    public boolean isJoin;
    private int position;
    public static JieFang_HuoDong jieFang_huoDong;


    @SuppressLint({"NewApi", "ValidFragment"})
    public JieFang_HuoDong(){
    }
    @SuppressLint({"NewApi", "ValidFragment"})
    public JieFang_HuoDong(int partyType){
        this.partyType=partyType;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getContext(), R.layout.fragment_avtivityplanning,null);
        jieFang_huoDong=this;
        mList=new ArrayList();
        mListAll=new ArrayList();
        initUI();
        getData();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){
                height = swipeRefreshLayout.getScrollY();
        }else {
            swipeRefreshLayout.post(new Runnable() {
                //让scrollview跳转到顶部，必须放在runnable()方法中
                @Override
                public void run() {
                    swipeRefreshLayout.scrollTo(0,height);
                }
            });
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onPause() {
        if (swipeRefreshLayout!=null) {
            height = swipeRefreshLayout.getScrollY();
        }
        super.onPause();
    }

    @Override
    public void onResume() {

//        if (onJoinListener!=null) {
//            isJoin = onJoinListener.OnJoin();
//            if (isJoin) {
//                mListAll.get(position).setHaveJoin(0);
//                mListAll.get(position).setJoinCount(mListAll.get(position).getJoinCount() - 1);
//            } else {
//                mListAll.get(position).setHaveJoin(1);
//                mListAll.get(position).setJoinCount(mListAll.get(position).getJoinCount() + 1);
//            }
//            if (adapter!=null){
//                adapter.notifyDataSetChanged();
//            }
//        }
//        getData();
        swipeRefreshLayout.post(new Runnable() {
            //让scrollview跳转到顶部，必须放在runnable()方法中
            @Override
            public void run() {
                swipeRefreshLayout.scrollTo(0,height);
            }
        });
        super.onResume();
    }

    private void getData() {


        String url= XY_Response2.URL_NEIGHBOR_FINDPARTY+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null)+
                "&partyType="+partyType+
                "&pageCount="+pageCount+"&pageSize="+pageSize;
        RequestCenter.neighbor_findparty(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                FindHuoDongList fanhui= (FindHuoDongList) responseObj;
                if (fanhui.getCode()==1000){
                    mList = fanhui.getObj();
//                    if (isOne) {
                        mListAll=mList;
                        adapter = new HuoDongAdapter(getContext(), mListAll);
                        lv_avtivityplanning.setAdapter(adapter);
                        swipeRefreshLayout.post(new Runnable() {
                            //让scrollview跳转到顶部，必须放在runnable()方法中
                            @Override
                            public void run() {
                                swipeRefreshLayout.scrollTo(0,height);
                            }
                        });

//                        isOne=false;
//                    }else {
//                        mListAll=mList;
//                        adapter.notifyDataSetChanged();
//                    }
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

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        swipeRefreshLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                height=i1;
            }
        });



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
                "&partyType="+partyType+
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
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        this.position=position;
        Intent intent=new Intent(getContext(), LuXianXiangQingActivity.class);
        intent.putExtra("partyId",mListAll.get(position).getPartyId());
        if (mListAll.get(position).getHaveJoin()==0){
            isJoin=false;
        }else {
            isJoin=true;
        }

        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 0:
                if (data!=null) {
                    int inJoin = data.getIntExtra("isJoin", 0);
                    if (inJoin == 1) {
                        mListAll.get(position).setHaveJoin(0);
                        mListAll.get(position).setJoinCount(mListAll.get(position).getJoinCount() - 1);
                    } else if (inJoin == 2) {
                        mListAll.get(position).setHaveJoin(1);
                        mListAll.get(position).setJoinCount(mListAll.get(position).getJoinCount() + 1);
                    }
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
