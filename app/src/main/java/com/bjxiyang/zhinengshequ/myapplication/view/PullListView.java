package com.bjxiyang.zhinengshequ.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/8/28 0028.
 */
public class PullListView extends ListView {
    public PullListView(Context context) {
        super(context);
    }

    public PullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getChildAt(0) != null) {
            //判断是否滑动到顶部了
            if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {//到顶部了
                //返回触摸事件
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {//没有到头部
                //拦截触摸事件
                getParent().requestDisallowInterceptTouchEvent(true);
            }

        }
        return super.onInterceptTouchEvent(ev);
    }
}
