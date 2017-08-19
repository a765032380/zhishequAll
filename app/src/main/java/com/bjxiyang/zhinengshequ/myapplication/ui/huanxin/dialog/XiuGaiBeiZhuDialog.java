package com.bjxiyang.zhinengshequ.myapplication.ui.huanxin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.domain.EaseUser;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class XiuGaiBeiZhuDialog extends Dialog implements View.OnClickListener{
    private EditText et_beizhu;
    private TextView tv_cancel;
    private TextView tv_ok;
    private String beizhu;
    private String friendPhone;


    public XiuGaiBeiZhuDialog(@NonNull Context context,String friendPhone) {
        super(context);
        this.friendPhone=friendPhone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_beizhu);
        initUI();
    }

    private void initUI() {
        et_beizhu=findViewById(R.id.et_beizhu);
        tv_cancel=findViewById(R.id.tv_cancel);
        tv_ok=findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_ok:
                beizhu = String.valueOf(et_beizhu.getText());

                if (beizhu==null){
                    break;
                }

                final EaseUser easeUser=new EaseUser(SPManager.getInstance().getString("hxIdFrom",null));
                String url= XY_Response2.URL_NEIGHBOR_EDITREMARK
                        +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                        +"&mobilePhone="+SPManager.getInstance().getString("mobilePhone",null)
                        +"&friendPhone="+friendPhone	//好友手机号
                        +"&friendRemark="+beizhu;		        //好友备注
                RequestCenter.neighbor_editremark(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        FanHui2 fanHui2= (FanHui2) responseObj;
                        if (fanHui2.getCode()==1000){
                            dismiss();
                        }else {
                            MyUntil.show(getContext(),fanHui2.getMsg());
                        }
//                        easeUser.setBeizhu();


                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                });




                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }
}
