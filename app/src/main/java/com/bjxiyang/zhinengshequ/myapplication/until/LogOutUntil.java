package com.bjxiyang.zhinengshequ.myapplication.until;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.bjxiyang.zhinengshequ.myapplication.activity.MainActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.SDLoginActivity;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class LogOutUntil {

    public static void logout(Activity activity){

        SPManager.getInstance().remove("mobilePhone");
        SPManager.getInstance().remove("c_memberId");
        SPManager.getInstance().remove("communityId_one");
        SPManager.getInstance().remove("communityName");
        UserManager.getInstance().removeUser();
        Intent intent=new Intent(activity,SDLoginActivity.class);
        activity.startActivity(intent);

        if (activity!=null){
            activity.finish();
        }
//        if (MainActivity.mainActivity!=null){
//            MainActivity.mainActivity.finish();
//        }
    }
    public static void logout1(Context activity){

        SPManager.getInstance().remove("mobilePhone");
        SPManager.getInstance().remove("c_memberId");
        SPManager.getInstance().remove("communityId_one");
        SPManager.getInstance().remove("communityName");
        UserManager.getInstance().removeUser();
        Intent intent=new Intent(activity,SDLoginActivity.class);
        activity.startActivity(intent);

//        if (activity!=null){
//            activity.finish();
//        }
//        if (MainActivity.mainActivity!=null){
//            MainActivity.mainActivity.finish();
//        }
    }
}
