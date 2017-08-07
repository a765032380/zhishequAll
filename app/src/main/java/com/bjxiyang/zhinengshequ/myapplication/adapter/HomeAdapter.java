package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class HomeAdapter extends BaseAdapter {
    private List<HomeBean.ObjBean.SpecialObjBean> mList;
    private Context mContext;

    public HomeAdapter(Context mContext,List<HomeBean.ObjBean.SpecialObjBean> mList) {
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
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_home_super,null);
            viewHolder.iv_item_tijiaodingdan_shangpinimage=
                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_shangpinimage);
            viewHolder.tv_item_tijiaodingdan_shangpinname=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_shangpinname);
            viewHolder.tv_item_tijiaodingdan_shangpinguige=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_shangpinguige);
            viewHolder.tv_item_tijiaodingdan_money=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_money);
            viewHolder.tv_item_tijiaodingdan_count=
                    (TextView) view.findViewById(R.id.tv_item_tijiaodingdan_count);
            viewHolder.iv_item_tijiaodingdan_jian=
                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_jian);
            viewHolder.iv_item_tijiaodingdan_jia=
                    (ImageView) view.findViewById(R.id.iv_item_tijiaodingdan_jia);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_tijiaodingdan_shangpinname.setText(mList.get(position).getName());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_item_tijiaodingdan_shangpinimage,mList.get(position).getLogo());
        if (mList.get(position).getIf_discount()==0){
            viewHolder.tv_item_tijiaodingdan_shangpinguige.setText(mList.get(position).getPrice()+"");
        }else {
            viewHolder.tv_item_tijiaodingdan_shangpinguige.setText(mList.get(position).getDiscount_price()+"");
            viewHolder.tv_item_tijiaodingdan_money.setText(mList.get(position).getPrice()+"");
            viewHolder.tv_item_tijiaodingdan_money.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        }
        return view;
    }
    class ViewHolder{
        ImageView iv_item_tijiaodingdan_shangpinimage;
        TextView tv_item_tijiaodingdan_shangpinname;
        TextView tv_item_tijiaodingdan_shangpinguige;
        TextView tv_item_tijiaodingdan_money;
        TextView tv_item_tijiaodingdan_count;
        ImageView iv_item_tijiaodingdan_jian;
        ImageView iv_item_tijiaodingdan_jia;
    }
}