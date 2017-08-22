package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.LogOutBaseActivity;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.DiZhiAdd;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.DiZhiUpdate;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.dialog.CommonActionSheetDialog;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class AddShouHuoDiZhiActivity extends LogOutBaseActivity implements View.OnClickListener{

    private RelativeLayout rl_xinzengshouhuodizhi_fanghui;
    private RelativeLayout rl_xinzengshouhuodizhi_baocun;
    private EditText et_xinzengshouhuodizhi_name;
    private ImageView iv_xinzengshouhuodizhi_xuanze1;
    private ImageView iv_xinzengshouhuodizhi_xuanze2;
    private EditText et_xinzengshouhuodizhi_phone;
    private LinearLayout ll_xinzengshouhuodizhi_loudong;
    private TextView tv_title;
    private int sex=1;
    private EditText et_select_text;

    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private List<SelectPlot.Obj> mList;


    private String name;
    private String phone;
    private String address;

    private int type;
    private int addressId;

    public CommonActionSheetDialog commonActionSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinzengshouhuodizhi);
        initUI();
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        type=intent.getIntExtra("type",0);
        if (type==1) {
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            address = intent.getStringExtra("address");
            sex = intent.getIntExtra("sex", 1);
            addressId = intent.getIntExtra("addressId", 0);
            setContent();
        }
    }

    private void setContent() {
        tv_title.setText("修改收货地址");
        et_xinzengshouhuodizhi_name.setText(name);
        et_xinzengshouhuodizhi_phone.setText(phone);
        et_select_text.setText(address);
        if (sex==1){
            iv_xinzengshouhuodizhi_xuanze1.setImageResource(R.drawable.e_icon_choice);
            iv_xinzengshouhuodizhi_xuanze2.setImageResource(R.drawable.h_btn_xuanze);
        }else {
            iv_xinzengshouhuodizhi_xuanze1.setImageResource(R.drawable.h_btn_xuanze);
            iv_xinzengshouhuodizhi_xuanze2.setImageResource(R.drawable.e_icon_choice);
        }

    }

    private void initUI() {
        tv_title= (TextView) findViewById(R.id.tv_title);
        rl_xinzengshouhuodizhi_fanghui= (RelativeLayout) findViewById(R.id.rl_xinzengshouhuodizhi_fanghui);
        et_select_text= (EditText) findViewById(R.id.et_select_text);
        rl_xinzengshouhuodizhi_baocun= (RelativeLayout) findViewById(R.id.rl_xinzengshouhuodizhi_baocun);
        et_xinzengshouhuodizhi_name= (EditText) findViewById(R.id.et_xinzengshouhuodizhi_name);
        iv_xinzengshouhuodizhi_xuanze1= (ImageView) findViewById(R.id.iv_xinzengshouhuodizhi_xuanze1);
        iv_xinzengshouhuodizhi_xuanze2= (ImageView) findViewById(R.id.iv_xinzengshouhuodizhi_xuanze2);
        et_xinzengshouhuodizhi_phone= (EditText) findViewById(R.id.et_xinzengshouhuodizhi_phone);
        ll_xinzengshouhuodizhi_loudong= (LinearLayout) findViewById(R.id.ll_xinzengshouhuodizhi_loudong);
        rl_xinzengshouhuodizhi_fanghui.setOnClickListener(this);
        rl_xinzengshouhuodizhi_baocun.setOnClickListener(this);
        iv_xinzengshouhuodizhi_xuanze1.setOnClickListener(this);
        iv_xinzengshouhuodizhi_xuanze2.setOnClickListener(this);
        ll_xinzengshouhuodizhi_loudong.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_xinzengshouhuodizhi_fanghui:
                finish();
                break;
            case R.id.rl_xinzengshouhuodizhi_baocun:

                name = String.valueOf(et_xinzengshouhuodizhi_name.getText());
                phone = String.valueOf(et_xinzengshouhuodizhi_phone.getText());
                address = String.valueOf(et_select_text.getText());
                DialogUntil.showLoadingDialog(AddShouHuoDiZhiActivity.this,"正在提交",true);
                if (type==1){
                    String url_update= BianLiDianResponse.URL_ORDER_USER_ADDRESS_UPDATE+
                            "addressId="+addressId+
                            "&name=" + name + "&sex=" + sex + "&phone=" + phone +
                            "&communityId=" + communityId +
                            "&nperId=" + nperId + "&floorId=" + floorId +
                            "&unitId=" + unitId + "&doorId=" + doorId;

                    RequestCenter.order_user_address_update(url_update, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            DiZhiUpdate dizhiupdate= (DiZhiUpdate) responseObj;
                            if (dizhiupdate.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();

                        }
                    });


                }else {
                    String url_add = BianLiDianResponse.URL_ORDER_USER_ADDRESS_ADD +
                            "name=" + name + "&sex=" + sex + "&phone=" + phone + "&communityId=" + communityId +
                            "&nperId=" + nperId + "&floorId=" + floorId + "&unitId=" + unitId + "&doorId=" + doorId;
                    Log.i("YYYY","测试保存新增收货地址");
                    RequestCenter.order_user_address_add(url_add, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            DiZhiAdd dizhiadd= (DiZhiAdd) responseObj;
                            if (dizhiadd.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                                finish();
                            }else {
                                MyUntil.show(AddShouHuoDiZhiActivity.this,dizhiadd.getMsg());
                            }

                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();
                            Log.i("YYYY","测试保存新增收货地址ERROR");
                        }
                    });
                }

                //保存的按钮
                break;
            case R.id.iv_xinzengshouhuodizhi_xuanze1:
                sex=1;
                iv_xinzengshouhuodizhi_xuanze1.setBackgroundResource(R.drawable.b_btn_xuanze_pre);
                iv_xinzengshouhuodizhi_xuanze2.setBackgroundResource(R.drawable.h_btn_xuanze);
                break;
            case R.id.iv_xinzengshouhuodizhi_xuanze2:
                sex=2;
                iv_xinzengshouhuodizhi_xuanze1.setBackgroundResource(R.drawable.h_btn_xuanze);
                iv_xinzengshouhuodizhi_xuanze2.setBackgroundResource(R.drawable.b_btn_xuanze_pre);
                break;
            case R.id.ll_xinzengshouhuodizhi_loudong:
                    getData();
                break;
        }
    }
    private List<SelectPlot.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(AddShouHuoDiZhiActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone","");

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList=selectPlot.getObj();
                    showActionSheet();
                }else {
                    Toast.makeText(AddShouHuoDiZhiActivity.this,selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(AddShouHuoDiZhiActivity.this,"请检查网络连接");
            }
        });
        return mList;
    }
    public void showActionSheet()
    {
        if (mList.size()>0) {
            if (commonActionSheetDialog == null) {
                commonActionSheetDialog = new CommonActionSheetDialog(this);

                for (SelectPlot.Obj item : mList) {
                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
                            +item.getNperName()+item.getFloorName()
                            + item.getUnitName()
                            + item.getDoorName());
                }

                commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                    @Override
                    public void onItemSelected(int position, String item) {

                        communityId = mList.get(position).getCommunityId();
                        nperId = mList.get(position).getNperId();
                        floorId = mList.get(position).getFloorId();
                        unitId = mList.get(position).getUnitId();
                        doorId = mList.get(position).getDoorId();

                        et_select_text.setText(mList.get(position).getCommunityName()
                                + mList.get(position).getNperName() +
                                mList.get(position).getUnitName() +
                                mList.get(position).getDoorName());
                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
            commonActionSheetDialog.show();
        }else {
            et_select_text.setText("请添加小区");
        }

    }
}
