package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.RollViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.adapter.ViewAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.BaseFragment;
import com.bjxiyang.zhinengshequ.myapplication.bean.Banner;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyScrollView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/8/2.
 */

public class HomeFragment extends BaseFragment {
    /**
     * UI
     */
    @BindView(R.id.roll_view_pager)
    public RollPagerView mRollViewPager;
    @BindView(R.id.mTextSwitcher)
    public TextView mTextSwitcher;
    @BindView(R.id.tv_xiaoqugonggao)
    public TextView tv_xiaoqugonggao;
    @BindView(R.id.ll_xiaoqugonggaoxiangqing)
    public LinearLayout ll_xiaoqugonggaoxiangqing;
    @BindView(R.id.tv_jinrongtuijian)
    public TextView tv_jinrongtuijian;
    @BindView(R.id.ll_jinrongtuijian)
    public LinearLayout ll_jinrongtuijian;
    @BindView(R.id.ll_chaoshitejia)
    public LinearLayout ll_chaoshitejia;
    @BindView(R.id.lv_shangping)
    public ListView lv_shangping;
    @BindView(R.id.home_viewpage_btn)
    public RollPagerView home_viewpage_btn;

//    @BindView(R.id.myScrollView)
//    public MyScrollView myScrollView;
    /**
     * DATE
     */
    private List<Banner.Obj> list = new ArrayList<>();
    private Banner banner;

    /**
     * Other
     */
    private RollViewAdapter adapter;
    private View view;
    private static OngetData ongetData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home2,container,false);
        ButterKnife.bind(this,view) ;
        initUI();
        initDate();
        return view;
    }

    private void initUI() {
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);

        home_viewpage_btn.setHintView(new ColorPointHintView(getContext(),0xff4183ff,0xffc6daff));
        ViewAdapter viewAdapter=new ViewAdapter(home_viewpage_btn,getActivity());
        home_viewpage_btn.setAdapter(viewAdapter);


    }
    private  List<Banner.Obj> initDate() {

        list=new ArrayList<>();
        String url= XY_Response.URL_BANNERLIST+"mobilePhone=18813045215";
//                +SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.bannerList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                banner = (Banner) responseObj;
                if (banner.getCode().equals("1000")){
                    list= banner.getObj();
                    if (list.size()>0){
                        local();
                    }
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
            }
        });
        return list;
    }

    public void local() {
        //设置适配器
        adapter = new RollViewAdapter(mRollViewPager);
        mRollViewPager.setAdapter(adapter);
        ongetData.OngetData(list);
    }
    public static void setOngetData(OngetData ongetDat){
        ongetData=ongetDat;
    }
    public interface OngetData{
        public void OngetData(List<Banner.Obj> mList);
    }

}
