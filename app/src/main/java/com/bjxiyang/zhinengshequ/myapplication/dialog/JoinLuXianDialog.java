package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class JoinLuXianDialog extends Dialog implements View.OnClickListener {
    private TextView tv_cancel;
    private TextView tv_ok;
    private TextView tv_context;
    private View.OnClickListener listener;
    private  int status;


    public JoinLuXianDialog(@NonNull Context context,int status, View.OnClickListener listener) {
        super(context);
        this.listener=listener;
        this.status=status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_jiaruluxian);
        initUI();
    }

    private void initUI() {
        tv_context= (TextView) findViewById(R.id.tv_context);
        tv_cancel= (TextView) findViewById(R.id.tv_cancel);
        tv_ok= (TextView) findViewById(R.id.tv_ok);
        tv_cancel.setOnClickListener(this);
        tv_ok.setOnClickListener(listener);
        if (status==0){
            tv_context.setText("您确定退出此活动吗？");
        }else {
            tv_context.setText("您确定加入此活动吗？");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
//            case R.id.tv_ok:
//                dismiss();
//                break;
        }
    }


}
