package com.bjxiyang.zhinengshequ.myapplication.ui.huanxin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class ShanChuHaoYouDialog extends Dialog implements View.OnClickListener {
    private TextView tv_cancel;
    private TextView tv_ok;
    private TextView tv_context;
    private View.OnClickListener listener;


    public ShanChuHaoYouDialog(@NonNull Context context, View.OnClickListener listener) {
        super(context);
        this.listener=listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_jiaruluxian);
        initUI();
    }

    private void initUI() {
        tv_context= (TextView) findViewById(R.id.tv_context);
        tv_context.setText("您确认要删除该好友吗?");
        tv_cancel= (TextView) findViewById(R.id.tv_cancel);
        tv_ok= (TextView) findViewById(R.id.tv_ok);
        tv_cancel.setOnClickListener(this);
        tv_ok.setOnClickListener(listener);


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
