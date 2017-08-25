package com.bjxiyang.zhinengshequ.myapplication.until;

import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class UnitGetGouWuChe {
    private static double zongji=0;
    private static int count=0;
    private static List<GouWuChe> mList;

    public static double getZongJia() {
        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        zongji = 0;
        if (mList != null && mList.size() > 0){
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getSellerId() == SPManager.getInstance().getInt("sellerId", 0)) {
                    if (mList.get(i).getIfDiscount() == 0) {
                        zongji = zongji + mList.get(i).getCount() * mList.get(i).getPrice();
                    } else {
                        zongji = zongji + mList.get(i).getCount() * mList.get(i).getDiscountPrice();
                    }
                }

            }
        }
        return zongji / 100;
    }

    public static int getConuntAll() {
        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        count = 0;
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getSellerId() == SPManager.getInstance().getInt("sellerId", 0)) {
                    count = count + mList.get(i).getCount();
                }
            }
        }
        return count;
    }


}
