package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class HomeAdapter_Adapter extends RecyclerView.Adapter<HomeAdapter_Adapter.ViewHolder> {
    private View view;
    private List<HomeBean.ObjBean.ShopObjBean.ProductObjBean> mList;
    private DecimalFormat df = new DecimalFormat("0.00");
    public HomeAdapter_Adapter(List<HomeBean.ObjBean.ShopObjBean.ProductObjBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_adapter_adapter,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ImageLoaderManager.getInstance(view.getContext())
                .displayImage(holder.goods_img3,mList.get(position).getLogo());
        holder.tv_goodsname3.setText(mList.get(position).getName());
        holder.tv_zhehoujia3.setText(df.format(mList.get(position).getDiscountPrice()/100)+"");
        holder.tv_yuanjia3.setText(df.format(mList.get(position).getPrice()/100)+"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPManager.getInstance().putInt("sellerId", mList.get(position).getSellerId());
                SPManager.getInstance().putString("shopName",mList.get(position).getSellerName());
                Intent intent=new Intent(view.getContext(), SupermarketActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("product", mList.get(position));
                view.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView goods_img3;
        public TextView tv_goodsname3;
        public TextView tv_zhehoujia3;
        public TextView tv_yuanjia3;

        public ViewHolder(View view){
            super(view);
            tv_goodsname3 = (TextView) view.findViewById(R.id.tv_goodsname3);
            tv_zhehoujia3 = (TextView) view.findViewById(R.id.tv_zhehoujia3);
            tv_yuanjia3 = (TextView) view.findViewById(R.id.tv_yuanjia3);
            goods_img3 = (ImageView) view.findViewById(R.id.goods_img3);

        }
    }
}
