package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.ByCom;
import com.bjxiyang.zhinengshequ.myapplication.bean.OpenDoor;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.dialog.KaiMenYouXiDialog;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.util.GetHeaders;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class MyMenJinAdapter extends RecyclerView.Adapter<MyMenJinAdapter.ViewHolder>  {
//    public List<String> mList;
    private List<ByCom.Obj> mList;
    private int lockId;
    private int customberId;
    private AnimationDrawable anim;
    public MyMenJinAdapter(List<ByCom.Obj> mList) {
        this.mList = mList;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_radial_buttons,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.btn_orange.setText(mList.get(position).getLockName());
        final int selectColor=position%3;

        if (selectColor==0){
            viewHolder.image_tu.setBackgroundResource(R.drawable.a_icon_purple);
        }else if (selectColor==1){
            viewHolder.image_tu.setBackgroundResource(R.drawable.a_icon_red);
        }else {
            viewHolder.image_tu.setBackgroundResource(R.drawable.a_icon_yellow);
        }

        viewHolder.image_tu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int selectColor=position%3;
                if (selectColor==0){
                    viewHolder.image_tu.setBackgroundResource(R.drawable.anim_purple);
                }else if (selectColor==1){
                    viewHolder.image_tu.setBackgroundResource(R.drawable.anim_red);
                }else {
                    viewHolder.image_tu.setBackgroundResource(R.drawable.anim_yellow);
                }
                anim = (AnimationDrawable) viewHolder.image_tu.getBackground();

                anim.start();

                DialogUntil.showLoadingDialog(v.getContext(),"正在开门",true);
                customberId= Integer.valueOf(SPManager.getInstance().getString("c_memberId",""));
                lockId=mList.get(position).getLockId();
                String url= XY_Response.URL_OPENDOOR+"customberId="+customberId+"&lockId="+lockId;

                Log.i("LLLL",url);

                RequestCenter.openDoor(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        anim.stop();

                        int selectColor=position%3;
                        if (selectColor==0){
                            viewHolder.image_tu.setBackgroundResource(R.drawable.purple_00000);
                        }else if (selectColor==1){
                            viewHolder.image_tu.setBackgroundResource(R.drawable.red_00000);
                        }else {
                            viewHolder.image_tu.setBackgroundResource(R.drawable.yellow_00000);
                        }

                        OpenDoor openDoor= (OpenDoor) responseObj;
                        if (openDoor.getCode().equals("1000")){
                            Toast.makeText(v.getContext(),"开门成功",Toast.LENGTH_LONG).show();
                            if (openDoor.getObj().getType()==1){
                                KaiMenYouXiDialog kaiMenYouXiDialog=new KaiMenYouXiDialog(v.getContext(),openDoor.getObj());
                                kaiMenYouXiDialog.show();
                            }
//                            showDialog("开门成功",v.getContext());
                        }else {
                            showDialog(openDoor.getMsg(),v.getContext());
//                            Toast.makeText(v.getContext(),fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        int selectColor=position%3;
                        if (selectColor==0){
                            viewHolder.image_tu.setBackgroundResource(R.drawable.purple_00000);
                        }else if (selectColor==1){
                            viewHolder.image_tu.setBackgroundResource(R.drawable.red_00000);
                        }else {
                            viewHolder.image_tu.setBackgroundResource(R.drawable.yellow_00000);
                        }
                        DialogUntil.closeLoadingDialog();
                        anim.stop();
                        MyDialog.showDialog(v.getContext());
                    }
                });
            }
        });
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mList.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_orange;
        public TextView image_tu;
        public ViewHolder(View view){
            super(view);
            btn_orange = (TextView) view.findViewById(R.id.btn_orange);
            image_tu= (TextView) view.findViewById(R.id.image_tu);
        }
    }

    private void showDialog(String message, Context mContext){

        final AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton("确定", null);
        builder.show();


    }
    
}