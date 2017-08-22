package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.XYKeyaccreditAdapter;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.base.LogOutBaseActivity;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.PermissionList;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.SwipeListLayout;
import com.bjxiyang.zhinengshequ.myapplication.view.SwipeListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gll on 17-5-23.
 */

public class XYKeyAccredit extends LogOutBaseActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    /**
     *
     */
    private List<PermissionList.Obj> mList;
    private SwipeListView mListView;
    private RelativeLayout ib_fanghui;
    private RelativeLayout ib_tianjia;
    private LinearLayout ll_activity_wushouquan;
    private LinearLayout ll_activity_listview;
    private LinearLayout ll_activity_wuwangluo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private Set<SwipeListLayout> sets = new HashSet();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_keyaccredit);
        initUI();
        initData();
    }

    private void initData() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        if (sets.size() > 0) {
                            for (SwipeListLayout s : sets) {
                                s.setStatus(SwipeListLayout.Status.Close, true);
                                sets.remove(s);
                            }
                        }
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
    }
    private void initUI() {
        ll_activity_wushouquan= (LinearLayout) findViewById(R.id.ll_activity_wushouquan);
        ll_activity_wuwangluo= (LinearLayout) findViewById(R.id.ll_activity_wuwangluo);
        mListView= (SwipeListView) findViewById(R.id.lv_keyaccredit);
        ib_fanghui= (RelativeLayout) findViewById(R.id.ib_fanghui);
        ib_fanghui.setOnClickListener(this);
        ib_tianjia= (RelativeLayout) findViewById(R.id.ib_tianjia);
        ib_tianjia.setOnClickListener(this);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout1= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout1.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout2= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout2.setOnRefreshListener(this);
        swipeRefreshLayout2.setColorSchemeResources(R.color.color_898787);
    }
    public List<PermissionList.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(XYKeyAccredit.this,"正在加载",true);
        String url= XY_Response.URL_FINDPERMISSIONS+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.findPermissions(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                PermissionList permissionList= (PermissionList) responseObj;
                if (permissionList.getCode().equals("1000")){
                    mList= permissionList.getObj();
                    if (mList.size()>0){
                        XYKeyaccreditAdapter adapter=new XYKeyaccreditAdapter(XYKeyAccredit.this,mList);
                        mListView.setAdapter(adapter);


                        showShuJu();
                    }else {
                        showWuShuJu();
                    }
                }else {
                    showWuShuJu();
                    Toast.makeText(GuardApplication.getContent(),permissionList.getMsg(),Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();
//                    MyDialog.showDialog(XYKeyAccredit.this,"请检查网络连接");
                    showWuWangLuo();
            }
        });
        return mList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_fanghui:
                finish();
                break;
            case R.id.ib_tianjia:
                Intent intent=new Intent(this,XY_AddKeyaccreditActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void showShuJu(){
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    private void showWuWangLuo(){
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void showWuShuJu(){
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);
    }
}