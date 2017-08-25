package com.bjxiyang.zhinengshequ.myapplication.base;

import android.os.Bundle;
import android.view.Window;

import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.LogOutUntil;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class LogOutBaseActivity extends MySwipeBackActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SPManager.getInstance().getString("c_memberId",null)==null ||
                SPManager.getInstance().getString("c_memberId","").equals("")){
            LogOutUntil.logout(this);
        }

    }
}
