package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.myapplication.activity.MyWebViewActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.Banner;
import com.bjxiyang.zhinengshequ.myapplication.bean.HomeBean;
import com.bjxiyang.zhinengshequ.myapplication.fragment.HomeFragment;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;


/**
 * Created by gll on 17-5-22.
 */

public class RollViewAdapter extends LoopPagerAdapter{

    private List<HomeBean.ObjBean.BannerObjBean> mList;
    private ImageLoaderManager manager;
    public RollViewAdapter(RollPagerView viewPager) {
        super(viewPager);
        HomeFragment.setOngetData(new HomeFragment.OngetData() {
            @Override
            public void OngetData(List<HomeBean.ObjBean.BannerObjBean> list) {
                mList=list;
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public View getView(final ViewGroup container, final int position) {

        manager=ImageLoaderManager.getInstance(container.getContext());
        ImageView view = new ImageView(container.getContext());

            manager.displayImage(view, mList.get(position).getAdUrl());
        final int position1=position;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mList.get(position1).getAdType()==1||mList.get(position1).getAdType()==3){
                        Intent intent=new Intent(container.getContext(), MyWebViewActivity.class);
                        intent.putExtra("url",mList.get(position1).getAdInf());
                        container.getContext().startActivity(intent);
                    }else if (mList.get(position1).getAdType()==2){
                        Intent intent=new Intent(container.getContext(), SupermarketActivity.class);
                        container.getContext().startActivity(intent);
                    }

                }
            });



        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public int getRealCount() {
        if (mList!=null){
            if (mList.size()<0){
                return 0;
            }else {
                return mList.size();
            }
        }else {
            return 0;
        }
    }
}
