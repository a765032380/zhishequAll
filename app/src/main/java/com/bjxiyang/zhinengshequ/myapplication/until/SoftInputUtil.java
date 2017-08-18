package com.bjxiyang.zhinengshequ.myapplication.until;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class SoftInputUtil {



    //    控制是否移动布局。比如只有密码输入框获取到焦点时才执行。
    public static  boolean flag=true;
    /**
     *  @param act      activiry用于获取底部导航栏高度。
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    public static void controlKeyboardLayout(Context act, final View root, final View scrollToView) {
        final int navigationBarHeight = getNavigationBarHeight(act);

        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > navigationBarHeight&&flag) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    if (root.getScrollY() != 0) {// 如果已经滚动，要根据上次滚动，重新计算位置。
                        srollHeight += root.getScrollY();
                    }
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }
    /**
     * 获取底部导航栏高度
     * @param act
     * @return
     */
    private static int getNavigationBarHeight(Context act) {
        Resources resources = act.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }
}