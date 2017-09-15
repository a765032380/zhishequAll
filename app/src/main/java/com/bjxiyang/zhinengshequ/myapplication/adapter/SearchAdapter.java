package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.Bean;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.until.SPToGouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.until.UnitGetGouWuChe;

import java.util.List;

/**
 * Created by yetwish on 2015-05-11
 */

public class SearchAdapter extends CommonAdapter<ShangPinList.Result.Products>{

    private GouWuChe gouwuche;
    private List<GouWuChe> mList;


    public SearchAdapter(Context context, List<ShangPinList.Result.Products> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final int position) {


        holder.setImageResource(R.id.iv_pic,mData.get(position).getLogo())
                .setText(R.id.tv_name,mData.get(position).getName())
                .setText(R.id.tv_price,mData.get(position).getPrice()/100+"")
                .setText(R.id.tv_original_price,mData.get(position).getDiscountPrice()/100+"")
                .setText(R.id.tv_acount,mData.get(position).getCount()+"")
                .setOnClickListener(R.id.iv_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gouwuche=null;
                        int position2=position;
                        mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
                        if (mList!=null&&mList.size()>0) {
                            for (int i = 0; i < mList.size(); i++) {
                                if (mList.get(i).getSpid() == mData.get(position2).getSpId()) {
                                    gouwuche = mList.get(i);
                                }
                            }
                        }
                        if (gouwuche==null){
                            gouwuche= SPToGouWuChe.splistToGouWuChe(mData.get(position2));
                            DaoUtils.getStudentInstance().insertObject(gouwuche);
                        }
//                        supermarketfragment.handlerCarNum(1,gouwuche,true);

//                        if (catograyAdapter!=null) {
//                            catograyAdapter.notifyDataSetChanged();
//                        }

//                        int[] loc = new int[2];
//                        viewholder.iv_add.getLocationInWindow(loc);
//                        for (int i=0;i<loc.length;i++)
//                        {
//                            Log.i("fyg", String.valueOf(loc[i]));
//                        }
//                        int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
//                        v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
//                        ImageView ball = new ImageView(context);
//                        ball.setImageResource(R.drawable.number);
//                        supermarketfragment.setAnim(ball, startLocation);// 开始执行动画
                        handlerCarNum(1,gouwuche,false);

                    }
                })
                .setOnClickListener(R.id.iv_remove, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
//                .setText(R.id.item_search_tv_comments,mData.get(position).getComments());
    }


    public void handlerCarNum(int type, GouWuChe gouWuChe1, boolean refreshGoodList){
        if (type==1) {
            gouWuChe1.setCount(gouWuChe1.getCount() + 1);
            DaoUtils.getStudentInstance().updateObject(gouWuChe1);
        }else if (type==0){
            if (gouWuChe1!=null){
                if (gouWuChe1.getCount()>1){
                    gouWuChe1.setCount(gouWuChe1.getCount()-1);
                    DaoUtils.getStudentInstance().updateObject(gouWuChe1);
                }else {

//                    if (dialog!=null&&dialog.isShowing()&& UnitGetGouWuChe.getConuntAll()==0){
//                        dialog.cancel();
//                    }
                    gouWuChe1.setCount(0);
                    DaoUtils.getStudentInstance().updateObject(gouWuChe1);
                }
            }
        }
        this.notifyDataSetChanged();

    }
}
