package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.ShangPinList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class SearchAdapter1 extends BaseAdapter {
    private Context mContext;
    private List<ShangPinList.Result.Products> mList;

    public SearchAdapter1(Context mContext, List<ShangPinList.Result.Products> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_serach,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_pic,mList.get(i).getLogo());
        viewHolder.tv_name.setText(mList.get(i).getName());
        viewHolder.tv_price.setText(mList.get(i).getPrice());
        viewHolder.tv_original_price.setText(mList.get(i).getDiscountPrice());


        return view;
    }
    class ViewHolder{
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.iv_pic)
        ImageView iv_pic;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_original_price)
        TextView tv_original_price;
        @BindView(R.id.iv_add)
        ImageView iv_add;
        @BindView(R.id.iv_remove)
        ImageView iv_remove;
        @BindView(R.id.tv_acount)
        TextView tv_acount;
    }
}
