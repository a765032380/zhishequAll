package com.bjxiyang.zhinengshequ.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYXuanZeXiaoQuActivity;
import com.bjxiyang.zhinengshequ.myapplication.adapter.MyMenJinAdapter;
import com.bjxiyang.zhinengshequ.myapplication.bean.ByCom;
import com.bjxiyang.zhinengshequ.myapplication.bean.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class RadialButtonLayout extends FrameLayout{
    private List<ByCom.Obj> mListByCom;
    public OnIsOpenListener onIsOpenListener;
    private final static long DURATION_SHORT = 400;
    private WeakReference<Context> weakContext;
    private LinearLayoutManager mLayoutManager;
    private MyMenJinAdapter mAdapter;
    private List<SelectPlot.Obj> mList;
    private int communityId=-1;
    private int nperId;
    private int floorId;
    private int unitId;
    private ByCom byCom;

    @BindView(R.id.rl_hide)
    RelativeLayout rl_hide;
    @BindView(R.id.btn_main)
    View btnMain;
//    @InjectView(R.id.btn_orange)
//    View btnOrange;
//    @InjectView(R.id.btn_yellow)
//    View btnYellow;
//    @InjectView(R.id.btn_green)
//    View btnGreen;
//    @InjectView(R.id.btn_blue)
//    View btnBlue;
//    @InjectView(R.id.btn_indigo)
//    View btnIndigo;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    private boolean isOpen = false;
    private Toast toast;
    /**
     * Default constructor
     * @param context
     */
    public RadialButtonLayout(final Context context) {
        this(context, null);
    }

    public RadialButtonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        weakContext = new WeakReference<Context>( context );
        LayoutInflater.from(context).inflate( R.layout.layout_radial_buttons, this);
        if (isInEditMode()) {
            //
        } else{
            ButterKnife.bind(this);
            //创建默认的线性LayoutManager
            mLayoutManager = new LinearLayoutManager(getContext());
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(mLayoutManager);

            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(mRecyclerView);
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            mRecyclerView.setHasFixedSize(true);
        }



    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            //
        } else {

        }
    }

    private void showToast( final int resId ) {
        if ( toast != null )
            toast.cancel();
        toast = Toast.makeText( getContext(), resId, Toast.LENGTH_SHORT );
        toast.show();
    }

    @OnClick( R.id.btn_main )
    public void onMainButtonClicked( final View btn ) {
        int resId = 0;
        if ( isOpen ) {
            hide();
            // close
//            hide(btnOrange);
//            hide(btnYellow);
//            hide(btnGreen);
//            hide(btnBlue);
//            hide(btnIndigo);

//            resId = R.string.close;
        } else {
            getData();
//            show(btnOrange, 1, 200);
//            show(btnYellow, 2, 200);
//            show(btnGreen, 3, 200);
//            show(btnBlue, 4, 200);
//            show(btnIndigo, 5, 200);

//            resId = R.string.open;
        }
//        showToast( resId);
        btn.playSoundEffect( SoundEffectConstants.CLICK);
    }

//    @OnClick({ R.id.btn_orange, R.id.btn_yellow, R.id.btn_green, R.id.btn_blue, R.id.btn_indigo})
//    public void onButtonClicked( final View btn ) {
//        int resId = 0;
//        switch ( btn.getId() ) {
//            case R.id.btn_orange:
//                resId = R.string.orange;
//                break;
//            case R.id.btn_yellow:
//                resId = R.string.yellow;
//                break;
//            case R.id.btn_green:
//                resId = R.string.green;
//                break;
//            case R.id.btn_blue:
//                resId = R.string.blue;
//                break;
//            case R.id.btn_indigo:
//                resId = R.string.indigo;
//                break;
//            default:
//                resId = R.string.undefined;
//        }
////        showToast(resId);
//        btn.playSoundEffect( SoundEffectConstants.CLICK);
//    }

    //隐藏其他弹框
    private final void hide( final View child) {
        child.animate()
                .setDuration(DURATION_SHORT)
                .translationX(0)
                .translationY(0)
                .start();
    }

    //显示其他弹框
    private final void show(final View child, final int position, final int radius) {
        float angleDeg = 180.f;
        int dist = radius;

        angleDeg+=(position)*30.f;
//        switch (position) {
//            case 1:
//                angleDeg += 0.f;
//                break;
//            case 2:
//                angleDeg += 45.f;
//                break;
//            case 3:
//                angleDeg += 90.f;
//                break;
//            case 4:
//                angleDeg += 135.f;
//                break;
//            case 5:
//                angleDeg += 180.f;
//                break;
//            case 6:
//            case 7:
//            case 8:
//            case 9:
//            case 10:
//                break;
//        }
        final float angleRad = (float) (angleDeg * Math.PI / 180.f);

        final Float x = dist * (float) Math.cos(angleRad);
        final Float y = dist * (float) Math.sin(angleRad);
        child.animate()
                .setDuration(DURATION_SHORT)
                .translationX(x)
                .translationY(y)
                .start();
    }
    private void getData(){

//        mList=new ArrayList<>();
        mListByCom = new ArrayList<>();

        DialogUntil.showLoadingDialog(getContext(),"正在加载",true);
        nperId=SPManager.getInstance().getInt("nperId_one",0);
        communityId=SPManager.getInstance().getInt("communityId_one",0);
        floorId=SPManager.getInstance().getInt("floorId_one",0);
        unitId=SPManager.getInstance().getInt("unitId_one",0);



        String url=XY_Response.URL_GETLOCKBYCOM+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone()+
                "&communityId="+communityId+
                "&nperId="+nperId+
                "&floorId="+floorId+
                "&unitId="+unitId;

        RequestCenter.getLockByCom(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                byCom= (ByCom) responseObj;
                if (byCom.getCode().equals("1000")){
                    mListByCom=byCom.getObj();
                    if (mListByCom.size()>0){
                        rl_hide.setBackgroundColor(0xcce3e3e3 );
                        isOpen = true;
                        rl_hide.setClickable(false);
                        rl_hide.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hide();
                            }
                        });
//        onIsOpenListener.isopen(isOpen);
                        mRecyclerView.setVisibility(VISIBLE);
                        mAdapter = new MyMenJinAdapter(mListByCom);
                        mRecyclerView.setAdapter(mAdapter);
//                        showText();
                    }else {
                        MyUntil.show(getContext(),"当前数据为空");
                    }
                }else {
                    MyUntil.show(getContext(),byCom.getMsg());
//                    showText_TiShi();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
//                MyDialog.showDialog(getActivity());
            }
        });


//        List<String> mList=new ArrayList<>();
//        mList.add("1");
//        mList.add("2");
//        mList.add("3");
//        mList.add("4");

        //创建并设置Adapter
//        MyMenJinAdapter adapter=new MyMenJinAdapter(mList);
//        mRecyclerView.setAdapter(adapter);
    }
    public void hide(){
        rl_hide.setBackgroundColor(0x00000000);
        isOpen = false;
        rl_hide.setClickable(false);
//        onIsOpenListener.isopen(isOpen);

        mRecyclerView.setVisibility(GONE);
    }

    public boolean isOpen(){
        return isOpen;
    }

    public void setOnisOpen(OnIsOpenListener o){
        onIsOpenListener=o;
    }
    public interface OnIsOpenListener{
        public void isopen(boolean isopen);
    }

}
