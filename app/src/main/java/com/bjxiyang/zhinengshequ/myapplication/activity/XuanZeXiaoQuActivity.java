package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.XYXuanZeXiaoQuAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class XuanZeXiaoQuActivity extends MySwipeBackActivity
        implements View.OnClickListener,AdapterView.OnItemClickListener{
    /***
     * UI
     */
    @BindView(R.id.lv_fanhui)
    LinearLayout lv_fanhui;
    @BindView(R.id.ll_xuanzexiaoqu1_add)
    LinearLayout ll_xuanzexiaoqu1_add;
    @BindView(R.id.tv_dingweidizhi)
    TextView tv_dingweidizhi;
    @BindView(R.id.lv_qitashequ)
    ListView lv_qitashequ;
    /***
     * Date
     */
    private List<String> list;
    private List<SelectPlot.Obj> mList;
    private XYXuanZeXiaoQuAdapter adapter;
    public static XuanZeXiaoQuActivity xuanzexiaoquactivity;
    private int communityId=-1;
    private int nperId;
    private int floorId;
    private int unitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzexiaoqu1);
        xuanzexiaoquactivity=this;
        ButterKnife.bind(this);
        initUI();
        getData();
    }

    private void initUI() {
        lv_fanhui.setOnClickListener(this);
        ll_xuanzexiaoqu1_add.setOnClickListener(this);
        tv_dingweidizhi.setOnClickListener(this);
        lv_qitashequ.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lv_fanhui:
                finish();
                break;
            case R.id.ll_xuanzexiaoqu1_add:
                MyUntil.mStartActivity(XuanZeXiaoQuActivity.this,XYXuanZeXiaoQuActivity.class);
                break;
            case R.id.tv_dingweidizhi:
                break;
        }
    }
    private List<SelectPlot.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(XuanZeXiaoQuActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone();

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList = selectPlot.getObj();
                    if (mList==null){
                        Intent intent = new Intent(XuanZeXiaoQuActivity.this,XYXuanZeXiaoQuActivity.class);
                        startActivity(intent);
                    }else {
                        if (mList.size()<0){
                            SPManager.getInstance().putBoolean("isOne",false);
                        }else {

                            list=new ArrayList<String>();
                            for (int i=0;i<mList.size();i++){
                                list.add(mList.get(i).getCommunityName()+mList.get(i).getNperName()
                                +mList.get(i).getFloorName()+mList.get(i).getUnitName()+mList.get(i).getDoorName());
                            }

                            adapter=new XYXuanZeXiaoQuAdapter(XuanZeXiaoQuActivity.this,list);
                            lv_qitashequ.setAdapter(adapter);
//                            showActionSheet(mList);
                        }
                    }

                }else {
//                    Toast.makeText(getActivity(),selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
        return mList;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SPManager.getInstance().putString("communityName",list.get(position));
        nperId=mList.get(position).getNperId();
        communityId=mList.get(position).getCommunityId();
        floorId=mList.get(position).getFloorId();
        unitId=mList.get(position).getUnitId();
        SPManager.getInstance().putBoolean("isOne",true);
        SPManager.getInstance().putInt("communityId_one",communityId);
        SPManager.getInstance().putInt("communityId",communityId);
        SPManager.getInstance().putInt("nperId_one",nperId);
        SPManager.getInstance().putInt("floorId_one",floorId);
        SPManager.getInstance().putInt("unitId_one",unitId);
        SPManager.getInstance().putString("test_men",mList.get(position).getCommunityName()
                +mList.get(position).getNperName());

        finish();
    }
}
