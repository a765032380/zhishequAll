package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.LuXianXiangQingActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.bean.HuoDongDetails;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class PingLunDialog extends AlertDialog implements View.OnClickListener{

    /***
     * Data
     */

    private HuoDongDetails.ObjBean.ReplyListBean reply;
    private int partyId;// 活动ID
    private int commentId;// 要评论的评论ID，如果为0则为新帖
    private String replyContent;//添加的评论内容
    private int toUserId; //被评论的帖子作者ID  为0则为发新帖


    /***
     * UI
     */
    private TextView tv_liuyan_cancel;
    private TextView tv_liuyan_send;
    private EditText et_liuyan;



    public PingLunDialog(@NonNull Context context,HuoDongDetails.ObjBean.ReplyListBean reply,int partyId) {
        super(context);
        if (reply==null){
            this.partyId=partyId;
            commentId=0;
            toUserId=0;
        }else {
            this.partyId = reply.getPartyId();
            commentId = reply.getReplyId();
            toUserId = reply.getFromUserId();
        }
        this.reply=reply;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pinglun);
//        requestWindowFeature(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        initUI();
    }

    private void initUI() {
        tv_liuyan_cancel= (TextView) findViewById(R.id.tv_liuyan_cancel);
        tv_liuyan_cancel.setOnClickListener(this);
        tv_liuyan_send= (TextView) findViewById(R.id.tv_liuyan_send);
        tv_liuyan_send.setOnClickListener(this);
        et_liuyan= (EditText) findViewById(R.id.et_liuyan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_liuyan_cancel:
                LuXianXiangQingActivity.isScroll=false;
                cancel();
                break;
            case R.id.tv_liuyan_send:
                replyContent= String.valueOf(et_liuyan.getText());
                if (replyContent==null){
                    MyUntil.show(getContext(),"请输入留言内容");
                    break;
                }

//                Log.i("YYYY","reply:PartyId="+reply.getPartyId()+"---CommentId="+reply.getCommentId());

                String url= XY_Response2.URL_NEIGHBOR_ADDPARTYREPLY+"cmemberId="+
                        SPManager.getInstance().getString("c_memberId",null)+"&partyId="+partyId+
                        "&commentId="+commentId+"&replyContent="+replyContent+"&toUserId="+toUserId;

                RequestCenter.neighbor_addpartyreply(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        FanHui2 fanhui= (FanHui2) responseObj;
                        if(fanhui.getCode()==1000){
                            LuXianXiangQingActivity.isScroll=true;
                            cancel();
                        }else {
                            MyUntil.show(getContext(),fanhui.getMsg());
                        }

                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        MyUntil.show(getContext(),"网络连接失败");
                    }
                });

                break;

        }
    }
    public void showKeyboard() {
        if(et_liuyan!=null){
            //设置可获得焦点
            et_liuyan.setFocusable(true);
            et_liuyan.setFocusableInTouchMode(true);
            //请求获得焦点
            et_liuyan.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) et_liuyan
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(et_liuyan, 0);
        }
    }
}
