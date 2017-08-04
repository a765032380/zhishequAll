package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.test.HomeItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class HomeJinRongAdapter extends BaseAdapter {
    private static final int ERSHOUFANG = 1;
    private static final int DIANZI = 2;
    private static final int XINYONG = 3;
    private static final int DIYA = 4;
    boolean isHiddenone=false;
    boolean isHiddentwo=false;

    private List<HomeBean.ObjBean.FinanceObjBean> mList;
    private Context mContext;

    public HomeJinRongAdapter(Context mContext, List<HomeBean.ObjBean.FinanceObjBean> mList) {
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
    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder=null;
        if (view == null) {
            view= LayoutInflater.from(mContext).inflate(R.layout.item_home_ershoufang,null);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_ershoufananjie_title.setText(mList.get(position).getName());
        List<HomeBean.ObjBean.FinanceObjBean.ValueObjBean> valueObj=mList.get(position).getValueObj();

        holder.tv_01.setText(valueObj.get(0).getTitleName()+":");
        holder.tv_02.setText(valueObj.get(0).getTitaleValue());

        holder.tv_03.setText(valueObj.get(1).getTitleName()+":");
        holder.tv_04.setText(valueObj.get(1).getTitaleValue());

        holder.tv_05.setText(valueObj.get(2).getTitleName()+":");
        holder.tv_06.setText(valueObj.get(2).getTitaleValue());
        switch (mList.get(position).getType()) {
            case ERSHOUFANG:
                holder.ll_gone_01.setVisibility(View.VISIBLE);
                holder.ll_gone_02.setVisibility(View.VISIBLE);
                holder.tv_07.setText(valueObj.get(3).getTitleName()+":");
                holder.tv_08.setText(valueObj.get(3).getTitaleValue());
                holder.tv_09.setText(valueObj.get(4).getTitleName()+":");
                holder.tv_10.setText(valueObj.get(4).getTitaleValue());
                holder.tv_11.setText(valueObj.get(5).getTitleName()+":");
                holder.tv_12.setText(valueObj.get(5).getTitaleValue());
                holder.ll_edu_gone.setVisibility(View.GONE);
                break;
            case DIANZI:
                holder.tv_07.setText(valueObj.get(3).getTitleName()+":");
                holder.tv_08.setText(valueObj.get(3).getTitaleValue());
                holder.ll_edu_gone.setVisibility(View.VISIBLE);
                holder.tv_daikuanedu.setText("额度");
                holder.ll_gone_01.setVisibility(View.GONE);
                holder.ll_gone_02.setVisibility(View.GONE);
                break;
            case XINYONG:
            case DIYA:
                holder.ll_edu_gone.setVisibility(View.VISIBLE);
                holder.tv_daikuanedu.setText("额度");
                holder.ll_gone_01.setVisibility(View.GONE);
                holder.ll_gone_02.setVisibility(View.GONE);
                holder.tv_07.setVisibility(View.GONE);
                holder.tv_08.setVisibility(View.GONE);
                break;
        }
        final int position1=position;
        final ViewHolder finalHolder = holder;
        holder.ll_shousuofangkai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position1==0){
                    if (!isHiddenone) {
                        finalHolder.iv_on.setBackgroundResource(R.drawable.a_icon_underthe);
                        finalHolder.invisible.setVisibility(View.GONE);
                        isHiddenone = true;
                    }else {
                        finalHolder.iv_on.setBackgroundResource(R.drawable.a_icon_on);
                        finalHolder.invisible.setVisibility(View.VISIBLE);
                        isHiddenone = false;
                    }
                }else {
                    if (!isHiddentwo) {
                        finalHolder.iv_on.setBackgroundResource(R.drawable.a_icon_underthe);
                        finalHolder.invisible.setVisibility(View.GONE);
                        isHiddentwo = true;
                    }else {
                        finalHolder.iv_on.setBackgroundResource(R.drawable.a_icon_on);
                        finalHolder.invisible.setVisibility(View.VISIBLE);
                        isHiddentwo = false;
                    }
                }

            }
        });
        return view;
    }

}
class ViewHolder {
    @BindView(R.id.tv_ershoufananjie_title)
    public TextView tv_ershoufananjie_title;
    @BindView(R.id.tv_daikuanedu)
    public TextView tv_daikuanedu;
    @BindView(R.id.tv_01)
    public TextView tv_01;
    @BindView(R.id.tv_02)
    public TextView tv_02;
    @BindView(R.id.tv_03)
    public TextView tv_03;
    @BindView(R.id.tv_04)
    public TextView tv_04;
    @BindView(R.id.tv_05)
    public TextView tv_05;
    @BindView(R.id.tv_06)
    public TextView tv_06;
    @BindView(R.id.tv_07)
    public TextView tv_07;
    @BindView(R.id.tv_08)
    public TextView tv_08;
    @BindView(R.id.tv_09)
    public TextView tv_09;
    @BindView(R.id.tv_10)
    public TextView tv_10;
    @BindView(R.id.tv_11)
    public TextView tv_11;
    @BindView(R.id.tv_12)
    public TextView tv_12;
    @BindView(R.id.ll_gone_01)
    public LinearLayout ll_gone_01;
    @BindView(R.id.ll_gone_02)
    public LinearLayout ll_gone_02;
    @BindView(R.id.ll_edu_gone)
    public LinearLayout ll_edu_gone;
    @BindView(R.id.invisible)
    public LinearLayout invisible;
    @BindView(R.id.ll_shousuofangkai1)
    public LinearLayout ll_shousuofangkai1;
    @BindView(R.id.iv_on)
    public ImageView iv_on;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
