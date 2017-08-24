package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.BianLiDianListActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.JinRongActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.ShengHuoJiaoFeiActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.SupermarketActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYKeyAccredit;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYMenJinJiLuActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.Banner;
import com.bjxiyang.zhinengshequ.myapplication.fragment.HomeFragment;
import com.bjxiyang.zhinengshequ.myapplication.test.HomeItem;
import com.bjxiyang.zhinengshequ.myapplication.view.HorizontalListView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gll on 17-5-22.
 */

public class ViewAdapter extends LoopPagerAdapter{

    private List<Fragment> mList;
    private FragmentManager fm;
    private View view;
    private Context mContext;
    private LayoutInflater inflater;
    public ViewAdapter(RollPagerView viewPager,Context mContext) {
        super(viewPager);
        this.mContext=mContext;
    }
    @Override
    public View getView(final ViewGroup container, int position) {
        if (position==0){
            inflater=LayoutInflater.from(mContext);
            view=inflater.inflate(R.layout.fragment_home_viewpage1,container,false);
            ViewGroup viewGroup= (ViewGroup) view;
            View view1 = viewGroup.getChildAt(0);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(XYKeyAccredit.class);
//                    Toast.makeText(mContext,"点击了按键1",Toast.LENGTH_LONG).show();
                }
            });
            View view2 = viewGroup.getChildAt(1);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(XYMenJinJiLuActivity.class);
//                    Toast.makeText(mContext,"点击了按键2",Toast.LENGTH_LONG).show();
                }
            });
            View view3 = viewGroup.getChildAt(2);
            view3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext,BianLiDianListActivity.class);
                    intent.putExtra("type",0);
                    mContext.startActivity(intent);

//                    Toast.makeText(mContext,"点击了按键3",Toast.LENGTH_LONG).show();
                }
            });
            View view4 = viewGroup.getChildAt(3);
            view4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(JinRongActivity.class);
//                    Toast.makeText(mContext,"点击了按键4",Toast.LENGTH_LONG).show();
                }
            });
            View view5 = viewGroup.getChildAt(4);
            view5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(ShengHuoJiaoFeiActivity.class);
//                    Toast.makeText(mContext,"点击了按键5",Toast.LENGTH_LONG).show();
                }
            });

        }else {
            inflater=LayoutInflater.from(mContext);
            view=inflater.inflate(R.layout.fragment_home_viewpage2,container,false);
            ViewGroup viewGroup= (ViewGroup) view;
            HorizontalListView listView = (HorizontalListView) viewGroup.getChildAt(0);

            List<HomeItem> list=new ArrayList<>();
            HomeItem homeItem=new HomeItem();
            for (int i=0;i<5;i++){
                homeItem.setName("测试数据"+i);
                list.add(homeItem);
            }

            ItemAdapter itemAdapter=new ItemAdapter(mContext,list);
            listView.setAdapter(itemAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext,"点击了第"+i+"个",Toast.LENGTH_LONG).show();
                }
            });

        }
        Log.i("YYYY","测试首页分类轮播");
        return view;
    }
    @Override
    public int getRealCount() {
        return 1;
    }

    private void startActivity(Class mClass){
        Intent intent=new Intent(mContext,mClass);
        mContext.startActivity(intent);
    }
}
