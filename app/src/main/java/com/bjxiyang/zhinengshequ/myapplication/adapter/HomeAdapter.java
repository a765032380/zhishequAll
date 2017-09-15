package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class HomeAdapter extends BaseAdapter {
    private List<HomeBean.ObjBean.ShopObjBean> mList;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;

    public HomeAdapter(Context mContext,List<HomeBean.ObjBean.ShopObjBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
//        if (view==null){
//            viewHolder=new ViewHolder();
//            view= LayoutInflater.from(mContext).inflate(R.layout.item_shangjialiebiao,null);
//            viewHolder.iv_item_tijiaodingdan_shangpinimage=
//                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_shangpinimage);
//            viewHolder.tv_item_tijiaodingdan_shangpinname=
//                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_shangpinname);
//            viewHolder.tv_item_tijiaodingdan_shangpinguige=
//                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_shangpinguige);
//            viewHolder.tv_item_tijiaodingdan_money=
//                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_money);
//            viewHolder.tv_item_tijiaodingdan_count=
//                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_count);
//            viewHolder.iv_item_tijiaodingdan_jian=
//                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_jian);
//            viewHolder.iv_item_tijiaodingdan_jia=
//                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_jia);
//            view.setTag(viewHolder);
//        }else {
//            viewHolder= (ViewHolder) view.getTag();
//        }
//        viewHolder.tv_item_tijiaodingdan_shangpinname.setText(mList.get(position).getName());
//        ImageLoaderManager.getInstance(mContext)
//                .displayImage(viewHolder.iv_item_tijiaodingdan_shangpinimage,mList.get(position).getLogo());
//        if (mList.get(position).getIf_discount()==0){
//            viewHolder.tv_item_tijiaodingdan_shangpinguige.setText(mList.get(position).getPrice()+"");
//        }else {
//            viewHolder.tv_item_tijiaodingdan_shangpinguige.setText(mList.get(position).getDiscount_price()+"");
//            viewHolder.tv_item_tijiaodingdan_money.setText(mList.get(position).getPrice()+"");
//            viewHolder.tv_item_tijiaodingdan_money.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
//        }
        if (view==null) {

            view = LayoutInflater.from(mContext).inflate(R.layout.item_shangjialiebiao, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        final HomeBean.ObjBean.ShopObjBean shopObjBean = mList.get(position);

//            ImageLoaderManager.getInstance(mContext)
//                    .displayImage(viewHolder.iv_shop_img,shopObjBean.getSellerLogo());
            viewHolder.tv_shop_name.setText(shopObjBean.getSellerName());
            viewHolder.tv_goodscount.setText(shopObjBean.getTotalProduct()+"件商品");
            viewHolder.tv_month_sale.setText("月售"+shopObjBean.getMonthSell()+"份");
            double jili=shopObjBean.getDistance();
            DecimalFormat df=new DecimalFormat("0.00");

            viewHolder.tv_distance.setText(df.format(jili/1000)+"km");
            viewHolder.tv_time.setText(shopObjBean.getTransitTime()+"分钟");
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHolder.my_recycler_view.setLayoutManager(mLayoutManager);

//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(viewHolder.my_recycler_view);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        viewHolder.my_recycler_view.setHasFixedSize(true);

        HomeAdapter_Adapter adapter_adapter=new HomeAdapter_Adapter(shopObjBean.getProductObj());
        viewHolder.my_recycler_view.setAdapter(adapter_adapter);

//            switch (shopObjBean.getProductObj().size()){
//                case 1:
//                    viewHolder.ll_01.setVisibility(View.VISIBLE);
//                    viewHolder.ll_02.setVisibility(View.GONE);
//                    viewHolder.ll_03.setVisibility(View.GONE);
//                    HomeBean.ObjBean.ShopObjBean.ProductObjBean productObjBean1=
//                            shopObjBean.getProductObj().get(0);
//                    setShop(viewHolder,productObjBean1);
//                    break;
//                case 2:
//                    viewHolder.ll_01.setVisibility(View.VISIBLE);
//                    viewHolder.ll_02.setVisibility(View.VISIBLE);
//                    viewHolder.ll_03.setVisibility(View.GONE);
//                    HomeBean.ObjBean.ShopObjBean.ProductObjBean productObj2=
//                            shopObjBean.getProductObj().get(1);
//                    HomeBean.ObjBean.ShopObjBean.ProductObjBean productObj1=
//                            shopObjBean.getProductObj().get(0);
//                    setShop(viewHolder,productObj1);
//                    setShop2(viewHolder,productObj2);
//                    break;
//                case 3:
//                    viewHolder.ll_01.setVisibility(View.VISIBLE);
//                    viewHolder.ll_02.setVisibility(View.VISIBLE);
//                    viewHolder.ll_03.setVisibility(View.VISIBLE);
//                    HomeBean.ObjBean.ShopObjBean.ProductObjBean productObj31=
//                            shopObjBean.getProductObj().get(0);
//                    HomeBean.ObjBean.ShopObjBean.ProductObjBean productObj32=
//                        shopObjBean.getProductObj().get(1);
//                    HomeBean.ObjBean.ShopObjBean.ProductObjBean productObj33=
//                            shopObjBean.getProductObj().get(2);
//                    setShop(viewHolder,productObj31);
//                    setShop2(viewHolder,productObj32);
//                    setShop3(viewHolder,productObj33);
//
//
//                    break;
//                default:
//                    viewHolder.ll_01.setVisibility(View.GONE);
//                    viewHolder.ll_02.setVisibility(View.GONE);
//                    viewHolder.ll_03.setVisibility(View.GONE);
//                    break;
//            }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SPManager.getInstance().putInt("sellerId", shopObjBean.getSellerId());
                SPManager.getInstance().putString("shopName",shopObjBean.getSellerName());
                MyUntil.mStartActivity(mContext, SupermarketActivity.class);

            }
        });

        return view;
    }
//    private void setShop(ViewHolder viewHolder,HomeBean.ObjBean.ShopObjBean.ProductObjBean productObjBean){
//        ImageLoaderManager.getInstance(mContext)
//                .displayImage(viewHolder.goods_img1,productObjBean.getLogo());
//
//        viewHolder.tv_goodsname1.setText(productObjBean.getName());
//        if (productObjBean.getIfDiscount()==0){
//            viewHolder.tv_zhehoujia1.setText(productObjBean.getPrice()+"");
//            viewHolder.tv_yuanjia1.setText("");
//        }else {
//            viewHolder.tv_zhehoujia1.setText(productObjBean.getDiscountPrice()+"");
//            viewHolder.tv_yuanjia1.setText(productObjBean.getPrice()+"");
//        }
//    }
//    private void setShop2(ViewHolder viewHolder,HomeBean.ObjBean.ShopObjBean.ProductObjBean productObjBean){
//        ImageLoaderManager.getInstance(mContext)
//                .displayImage(viewHolder.goods_img2,productObjBean.getLogo());
//
//        viewHolder.tv_goodsname2.setText(productObjBean.getName());
//        if (productObjBean.getIfDiscount()==0){
//            viewHolder.tv_zhehoujia2.setText(productObjBean.getPrice()+"");
//            viewHolder.tv_yuanjia2.setText("");
//        }else {
//            viewHolder.tv_zhehoujia2.setText(productObjBean.getDiscountPrice()+"");
//            viewHolder.tv_yuanjia2.setText(productObjBean.getPrice()+"");
//        }
//    }
//    private void setShop3(ViewHolder viewHolder,HomeBean.ObjBean.ShopObjBean.ProductObjBean productObjBean){
//        ImageLoaderManager.getInstance(mContext)
//                .displayImage(viewHolder.goods_img3,productObjBean.getLogo());
//
//        viewHolder.tv_goodsname3.setText(productObjBean.getName());
//        if (productObjBean.getIfDiscount()==0){
//            viewHolder.tv_zhehoujia3.setText(productObjBean.getPrice()+"");
//            viewHolder.tv_yuanjia3.setText("");
//        }else {
//            viewHolder.tv_zhehoujia3.setText(productObjBean.getDiscountPrice()+"");
//            viewHolder.tv_yuanjia3.setText(productObjBean.getPrice()+"");
//        }
//    }



    class ViewHolder{
        @BindView(R.id.my_recycler_view)
        RecyclerView my_recycler_view;

        @BindView(R.id.ll_01)
        LinearLayout ll_01;
        @BindView(R.id.ll_02)
        LinearLayout ll_02;
        @BindView(R.id.ll_03)
        LinearLayout ll_03;
        @BindView(R.id.iv_shop_img)
        ImageView iv_shop_img;
        @BindView(R.id.tv_shop_name)
        TextView tv_shop_name;
        @BindView(R.id.tv_goodscount)
        TextView tv_goodscount;
        @BindView(R.id.tv_month_sale)
        TextView tv_month_sale;
        @BindView(R.id.tv_distance)
        TextView tv_distance;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.goods_img1)
        ImageView goods_img1;
        @BindView(R.id.tv_sale1)
        TextView tv_sale1;
        @BindView(R.id.tv_goodsname1)
        TextView tv_goodsname1;
        @BindView(R.id.tv_zhehoujia1)
        TextView tv_zhehoujia1;
        @BindView(R.id.tv_yuanjia1)
        TextView tv_yuanjia1;


        @BindView(R.id.goods_img2)
        ImageView goods_img2;
        @BindView(R.id.tv_sale2)
        TextView tv_sale2;
        @BindView(R.id.tv_goodsname2)
        TextView tv_goodsname2;
        @BindView(R.id.tv_zhehoujia2)
        TextView tv_zhehoujia2;
        @BindView(R.id.tv_yuanjia2)
        TextView tv_yuanjia2;


        @BindView(R.id.goods_img3)
        ImageView goods_img3;
        @BindView(R.id.tv_sale3)
        TextView tv_sale3;
        @BindView(R.id.tv_goodsname3)
        TextView tv_goodsname3;
        @BindView(R.id.tv_zhehoujia3)
        TextView tv_zhehoujia3;
        @BindView(R.id.tv_yuanjia3)
        TextView tv_yuanjia3;



        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
