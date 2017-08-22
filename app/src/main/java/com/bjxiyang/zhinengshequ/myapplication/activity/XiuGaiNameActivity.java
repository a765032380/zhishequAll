package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.LogOutBaseActivity;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class XiuGaiNameActivity extends LogOutBaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_username_fanghui)
    RelativeLayout rl_username_fanghui;
    @BindView(R.id.tv_baocun)
    TextView tv_baocun;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.ll_username_delete)
    LinearLayout ll_username_delete;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        et_name.setText(name);
        rl_username_fanghui.setOnClickListener(this);
        tv_baocun.setOnClickListener(this);
        ll_username_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回的按键
            case R.id.rl_username_fanghui:
                finish();
                break;
            //保存的按键
            case R.id.tv_baocun:
                name= String.valueOf(et_name.getText());
                if (name==null||name.equals("")){
                    MyUntil.show(XiuGaiNameActivity.this,"请输入用户名");
                    break;
                }
                DialogUntil.showLoadingDialog(this,"正在提交",false);
                String url= XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                        +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                        +"&nickName="+name;
                RequestCenter.usercenter_updateUserInfo(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui2 fanHui2= (FanHui2) responseObj;
                        if (fanHui2.getCode()==1000){
                            MyUntil.show(XiuGaiNameActivity.this,"修改成功");

                            Intent mIntent = new Intent();
                            mIntent.putExtra("name", name);
                            // 设置结果，并进行传送
                            setResult(RESULT_OK, mIntent);
                            finish();
                        }else {
                            DialogUntil.closeLoadingDialog();
                            MyUntil.show(XiuGaiNameActivity.this,fanHui2.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                    }
                });



                break;
            //清空名字的按键
            case R.id.ll_username_delete:
                et_name.setText("");
                break;



        }
    }
}
