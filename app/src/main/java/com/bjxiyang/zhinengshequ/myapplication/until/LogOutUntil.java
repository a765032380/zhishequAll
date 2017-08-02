package com.bjxiyang.zhinengshequ.myapplication.until;


import android.content.Intent;

import com.bjxiyang.zhinengshequ.myapplication.activity.MainActivity;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class LogOutUntil {

    public static void logout(){

        SPManager.getInstance().remove("mobilePhone");
        SPManager.getInstance().remove("communityId_one");
        UserManager.getInstance().removeUser();
//        Intent intent=new Intent(MainActivity.mainActivity,SDLoginActivity.class);
//        MainActivity.mainActivity.startActivity(intent);
//        MainActivity.mainActivity.finish();
    }
}
