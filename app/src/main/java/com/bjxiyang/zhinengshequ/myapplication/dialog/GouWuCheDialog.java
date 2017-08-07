package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.adapter.supermarket.GoodsAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.supermarket.ProductAdapter;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.view.MyListView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class GouWuCheDialog extends AlertDialog {
    private List<GouWuChe> mList;
    private ProductAdapter productAdapter;//底部购物车的adapter
    private SupermarketActivity supermarketActivity;
    private GoodsAdapter goodsAdapter;//分类下商品adapter

    protected GouWuCheDialog(@NonNull Context context) {
        super(context);
    }
    public GouWuCheDialog(@NonNull Context context, SupermarketActivity supermarketActivity,GoodsAdapter goodsAdapter) {
        super(context);
        this.supermarketActivity=supermarketActivity;
        this.goodsAdapter=goodsAdapter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_gouwuche);
        initUI();
    }

    private void initUI() {
        MyListView lv_product = (MyListView)findViewById(R.id.lv_product);

        mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        for (int i=0;i<mList.size();i++){
            if (mList.get(i).getCount()==0){
                DaoUtils.getStudentInstance().deleteObject(mList.get(i));
            }
        }

        LinearLayout clear = (LinearLayout) findViewById(R.id.ll_qingkong);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空购物车
            }
        });

        mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        for (int i=mList.size()-1;i>=0;i--){
            if (mList.get(i).getSellerId()!= SPManager.getInstance().getInt("sellerId",0)){
                mList.remove(i);
            }
        }
        productAdapter = new ProductAdapter(getContext(),
                supermarketActivity,goodsAdapter, mList);

//        int size = mList.size();
//        for(int i=0;i<size;i++){
//            int price=mList.get(i).getCount()*mList.get(i).getPrice();
//            totleMoney += price;
//        }
        lv_product.setAdapter(productAdapter);
    }




}
