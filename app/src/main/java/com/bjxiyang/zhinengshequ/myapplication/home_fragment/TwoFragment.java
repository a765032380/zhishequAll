package com.bjxiyang.zhinengshequ.myapplication.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;

/**
 * Created by gll on 2017/8/2.
 */

public class TwoFragment extends BaseFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home_viewpage2,container,false);
        return view;
    }
}
