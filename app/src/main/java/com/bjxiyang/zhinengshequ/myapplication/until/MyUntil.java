package com.bjxiyang.zhinengshequ.myapplication.until;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public class MyUntil {

    public static final String WEIXIN_APPID="";


    public static void show(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    public static void setListViewHeightBasedOnChildren(ListView listView, ListAdapter adapter) {
        if (listView == null) return;
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);//计算每项   Item的高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        //循环完之后,要计算出getDividerHeight(空白处占得height)   才是Listview的高度
        listView.setLayoutParams(params);

    }

    public static void mStartActivity(Context mContext,Class mClass){
        Intent intent=new Intent(mContext,mClass);
        mContext.startActivity(intent);

    }


}
