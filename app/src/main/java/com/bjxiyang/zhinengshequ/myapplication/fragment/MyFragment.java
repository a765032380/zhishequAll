package com.bjxiyang.zhinengshequ.myapplication.fragment;

import com.bjxiyang.zhinengshequ.myapplication.activity.DaiFuKuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.GouWuCheActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.HuiFuActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.MyXinXiActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.activity.SheZhiActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.ShouHuoDiZhiActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.WoFaQiDeYueYouActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.XYKeyAccredit;
import com.bjxiyang.zhinengshequ.myapplication.activity.YiJianFanKuiActivity;
import com.bjxiyang.zhinengshequ.myapplication.activity.ZheKouQuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.GeRenZhongXin;
import com.bjxiyang.zhinengshequ.myapplication.bean.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.CommonDialog;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.service.UpdateService;
import com.bjxiyang.zhinengshequ.myapplication.update.util.Util;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

/**
 * Created by gll on 17-5-20.
 * 56214450
 */

public class MyFragment extends Fragment implements View.OnClickListener{
    //个人中心
    private LinearLayout gerenxinxi;
//    private LinearLayout changepassworldlativelayout;
//    private LinearLayout jianchagengxin;
    private LinearLayout yijianfankui;
    private LinearLayout lianxikefu;
    private LinearLayout jiamenghezuo;
    private LinearLayout ll_gerenxinxi_xiugai_menjinrenzhenge;
    private TextView tv_gerenxinxi_xiugai_menjinrenzhenge;

    /**
     * UI
     */
    private TextView tv_youhuiquancount;
    private TextView tv_fatiecount;
    private LinearLayout fatie;
    private TextView tv_gouwuche;
    private TextView wodecaogao;
    private TextView mydate;
    private RelativeLayout rl_tixing;
    private RelativeLayout rl_shezhi;


    //商超的内容
    private LinearLayout daifukuan;
    private LinearLayout daifahuo;
    private LinearLayout quanbudingdan;
    private LinearLayout youhuiquan;
    private LinearLayout diyongjuan;
    private LinearLayout daishouhuo;
    private LinearLayout tuikuantuihuo;
    private LinearLayout wodedizhi;

    //退出按钮
//    private Button siginoutbutton;

    //显示用户名
    private TextView account_people;
    //显示用户头像
    private CircleImageView gerenxinxi_touxiang;
    private TextView my_daifukuan_tishi;
    private TextView my_daifahuo_tishi;
    private TextView my_daishouhuo_tishi;
    private TextView my_tuikuantuihuo_tishi;
    private TextView my_alldingdan_tishi;

    private ImageView iv_tongzhi_tishi;
    private ImageView iv_shezhi_tishi;

    private boolean isShow=false;
    private GeRenZhongXin geRenZhongXin;

    private Context mContext;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=getContext();
        view= inflater.inflate(R.layout.fragment_my1,container,false);
        initView();

//        getUserInfo();
        return view;
    }



    private void getData(){
        String url= XY_Response2.URL_UESRCENTER_GETUSERINFO
                +"cmemberId="+SPManager.getInstance().getString("c_memberId",null);
        RequestCenter.usercenter_getUserInfo(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                geRenZhongXin= (GeRenZhongXin) responseObj;
                if (geRenZhongXin.getCode()==1000){
                    setData(geRenZhongXin.getObj());
                    setTishi(geRenZhongXin.getObj().getOrderInfo());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });



    }

    @Override
    public void onResume() {
        if (SPManager.getInstance().getString("c_memberId",null)!=null ||
                !SPManager.getInstance().getString("c_memberId","").equals("")){
            getData();
        }



//        if (!UserManager.getInstance().getUser().getObj().getHeadPhotoUrl().equals("")&&
//                UserManager.getInstance().getUser().getObj().getHeadPhotoUrl()!=null ){
//            ImageLoaderManager.getInstance(getContext()).displayImage(gerenxinxi_touxiang,
//                    UserManager.getInstance().getUser().getObj().getHeadPhotoUrl());
//        }
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            if (SPManager.getInstance().getString("c_memberId",null)!=null ||
                    !SPManager.getInstance().getString("c_memberId","").equals("")){
                getData();
            }
//            if (!UserManager.getInstance().getUser().getObj().getHeadPhotoUrl().equals("")&&
//                    UserManager.getInstance().getUser().getObj().getHeadPhotoUrl()!=null ){
//                ImageLoaderManager.getInstance(getContext()).displayImage(gerenxinxi_touxiang,
//                        UserManager.getInstance().getUser().getObj().getHeadPhotoUrl());
//            }
        }
        super.onHiddenChanged(hidden);
    }

    public void initView(){
        tv_gerenxinxi_xiugai_menjinrenzhenge=view.findViewById(R.id.tv_gerenxinxi_xiugai_menjinrenzhenge);
        ll_gerenxinxi_xiugai_menjinrenzhenge=view.findViewById(R.id.ll_gerenxinxi_xiugai_menjinrenzhenge);
        ll_gerenxinxi_xiugai_menjinrenzhenge.setOnClickListener(this);

        iv_tongzhi_tishi=view.findViewById(R.id.iv_tongzhi_tishi);
        my_alldingdan_tishi = view.findViewById(R.id.my_alldingdan_tishi);
        my_tuikuantuihuo_tishi = view.findViewById(R.id.my_tuikuantuihuo_tishi);
        my_daishouhuo_tishi = view.findViewById(R.id.my_daishouhuo_tishi);
        my_daifukuan_tishi = view.findViewById(R.id.my_daifukuan_tishi);
        my_daifahuo_tishi =  view.findViewById(R.id.my_daifahuo_tishi);

        rl_tixing=view.findViewById(R.id.rl_tixing);
        rl_shezhi=view.findViewById(R.id.rl_shezhi);
        rl_tixing.setOnClickListener(this);
        rl_shezhi.setOnClickListener(this);

        tv_gouwuche=view.findViewById(R.id.tv_gouwuche);
        wodecaogao=view.findViewById(R.id.wodecaogao);
        mydate=view.findViewById(R.id.mydate);
        tv_gouwuche.setOnClickListener(this);
        wodecaogao.setOnClickListener(this);
        mydate.setOnClickListener(this);

        fatie=view.findViewById(R.id.fatie);
        fatie.setOnClickListener(this);
        tv_fatiecount=view.findViewById(R.id.tv_fatiecount);
        tv_youhuiquancount=view.findViewById(R.id.tv_youhuiquancount);
//        jiamenghezuo= (LinearLayout) view.findViewById(R.id.jiamenghezuo);
//        jiamenghezuo.setOnClickListener(this);
        daishouhuo= (LinearLayout) view.findViewById(R.id.daishouhuo);
        tuikuantuihuo= (LinearLayout) view.findViewById(R.id.tuikuantuihuo);
        wodedizhi= (LinearLayout) view.findViewById(R.id.wodedizhi);
        daishouhuo.setOnClickListener(this);
        tuikuantuihuo.setOnClickListener(this);
        wodedizhi.setOnClickListener(this);
        lianxikefu= (LinearLayout) view.findViewById(R.id.lianxikefu);
        lianxikefu.setOnClickListener(this);
//        diyongjuan= (LinearLayout) view.findViewById(R.id.diyongjuan);
//        diyongjuan.setOnClickListener(this);
        //个人中心
        gerenxinxi= (LinearLayout) view.findViewById(R.id.gerenxinxi);
//        changepassworldlativelayout= (LinearLayout) view.findViewById(R.id.changepassworldlativelayout);
//        jianchagengxin= (LinearLayout) view.findViewById(R.id.jianchagengxin);
        yijianfankui= (LinearLayout) view.findViewById(R.id.yijianfankui);
        //商超的内容
        daifukuan= (LinearLayout) view.findViewById(R.id.daifukuan);
        daifahuo= (LinearLayout) view.findViewById(R.id.daifahuo);
        quanbudingdan= (LinearLayout) view.findViewById(R.id.quanbudingdan);
        youhuiquan= (LinearLayout) view.findViewById(R.id.youhuiquan);
//        siginoutbutton= (Button) view.findViewById(R.id.siginoutbutton);
        account_people= (TextView) view.findViewById(R.id.account_people);
        gerenxinxi_touxiang= (CircleImageView) view.findViewById(R.id.gerenxinxi_touxiang);
//        if (!UserManager.getInstance().getUser().getObj().getHeadPhotoUrl().equals("")&&
//                UserManager.getInstance().getUser().getObj().getHeadPhotoUrl()!=null ){
//            ImageLoaderManager.getInstance(getContext()).displayImage(gerenxinxi_touxiang,
//                    UserManager.getInstance().getUser().getObj().getHeadPhotoUrl());
//        }

        gerenxinxi.setOnClickListener(this);
        yijianfankui.setOnClickListener(this);
        daifukuan.setOnClickListener(this);
        daifahuo.setOnClickListener(this);
        quanbudingdan.setOnClickListener(this);
        youhuiquan.setOnClickListener(this);
        gerenxinxi_touxiang.setOnClickListener(this);

    }

    //检查更新代码
    private void checkVersion() {
        DialogUntil.showLoadingDialog(mContext,"正在检查更新",true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj=updateModel.getObj();
                    if (Double.valueOf(Util.getVersionCode(getActivity()))<Double.valueOf(obj.getVerNo())) {
                        //说明有新版本,开始下载
                        CommonDialog dialog = new CommonDialog(getActivity(),
                                getString(R.string.update_new_version),
                                obj.getVerDescript(),
                                getString(R.string.cancel),
                                getString(R.string.update_install),
                                new CommonDialog.DialogClickListener() {
                                    @Override
                                    public void onDialogClick() {
                                        Intent intent = new Intent(getActivity(), UpdateService.class);
                                        intent.putExtra("APPURL", updateModel.getObj().getVerUrl());
                                        getActivity().startService(intent);
                                    }
                                });
                        dialog.setCancelable(false);
                        dialog.show();
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("提示");
                        dialog.setCancelable(false);
                        dialog.setMessage("该版本已是最新版本");
                        dialog.setIcon(R.mipmap.ic_launcher);
                        dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        });
                        dialog.show();
                        //弹出一个toast提示当前已经是最新版本等处理
                    }
                }else {
                    MyUntil.show(getContext(),updateModel.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                AlertDialog dialog=builder
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("检查更新")
                        .setMessage("网络连接失败")
                        .setPositiveButton("确定", null)
                        .show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            //跳转到加盟合作
//            case R.id.jiamenghezuo:
//                Intent j = new Intent();
//                j.setAction(Intent.ACTION_DIAL);
//                j.setData(Uri.parse("tel:4000080828"));
//                startActivity(j);
//                break;
            //跳转到个人信息页面
            case R.id.gerenxinxi:
                Intent intent1=new Intent(getContext(),MyXinXiActivity.class);
                if (geRenZhongXin!=null) {
                    intent1.putExtra("HeadPhotoUrl", geRenZhongXin.getObj().getHeadPhotoUrl());
                    intent1.putExtra("sex", geRenZhongXin.getObj().getSex());
                    intent1.putExtra("NickName", geRenZhongXin.getObj().getNickName());
                }
                startActivity(intent1);
//                startIntent(MyXinXiActivity.class);
                break;
            //跳转到意见反馈页面
            case R.id.yijianfankui:
                startIntent(YiJianFanKuiActivity.class);
                break;
            //待付款页面
            case R.id.daifukuan:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            //待发货页面
            case R.id.daifahuo:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            //待收货
            case R.id.daishouhuo:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            //退款退货
            case R.id.tuikuantuihuo:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            //我的地址
            case R.id.wodedizhi:
                startIntent(ShouHuoDiZhiActivity.class);
                break;

            //全部订单页面
            case R.id.quanbudingdan:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
                break;
            //优惠券页面
            case R.id.youhuiquan:
                intent=new Intent(getContext(), ZheKouQuanActivity.class);
                startActivity(intent);
                break;
            //抵用券页面

            case R.id.lianxikefu:
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:4000080828"));
                startActivity(i);
                break;
            //提示页面
            case R.id.rl_tixing:
                Intent intent3=new Intent(getContext(), HuiFuActivity.class);
                startActivity(intent3);
                break;
            //跳转到设置
            case R.id.rl_shezhi:
                MyUntil.mStartActivity(getContext(), SheZhiActivity.class);

                break;
//            case R.id.diyongjuan:
////                MyUntil.mStartActivity(getContext(),DiYongJuanActivity);
//                break;
            //退出登录
//            case R.id.siginoutbutton:
//                logOutHuanXin();
//                SPManager.getInstance().remove("mobilePhone");
//                SPManager.getInstance().remove("communityId_one");
//                UserManager.getInstance().removeUser();
//                startIntent(SDLoginActivity.class);
//                getActivity().finish();
//                break;
            //发帖数
            case R.id.fatie:

                break;
            //购物车
            case R.id.tv_gouwuche:
                Intent intent2=new Intent(getContext(), GouWuCheActivity.class);
                startActivity(intent2);
                break;
            //我的草稿
            case R.id.wodecaogao:
                break;
            //我发起的约游
            case R.id.mydate:
                MyUntil.mStartActivity(getContext(), WoFaQiDeYueYouActivity.class);
                break;
            //门禁认证
            case R.id.ll_gerenxinxi_xiugai_menjinrenzhenge:
                MyUntil.mStartActivity(getContext(), XYKeyAccredit.class);
                break;

        }
    }

    private void startIntent(Class c){
        Intent intent = new Intent(mContext, c);
        startActivity(intent);
    }
    private void setTishi(GeRenZhongXin.ObjBean.OrderInfoBean orderInfoBean){
        panduan(my_daifukuan_tishi,orderInfoBean.getObligationCount());
        panduan(my_daifahuo_tishi,orderInfoBean.getWaitings());
        panduan(my_daishouhuo_tishi,orderInfoBean.getMailing());
        panduan(my_tuikuantuihuo_tishi,orderInfoBean.getRefundment());
        panduan(my_alldingdan_tishi,orderInfoBean.getAllcount());
//        my_daifukuan_tishi.setText(orderInfoBean.getObligationCount());
//        my_daifahuo_tishi.setText(orderInfoBean.getWaitings());
//        my_daishouhuo_tishi.setText(orderInfoBean.getMailing());
//        my_tuikuantuihuo_tishi.setText(orderInfoBean.getRefundment());
//        my_alldingdan_tishi.setText(orderInfoBean.getAllcount());
    }
    private void setData(GeRenZhongXin.ObjBean objBean){
        ImageLoaderManager.getInstance(getContext())
                .displayImage(gerenxinxi_touxiang,objBean.getHeadPhotoUrl());
        if (objBean.getNickName()!=null&&!objBean.getNickName().equals("")){
            account_people.setText(objBean.getNickName());
        }else {
            account_people.setText(SPManager.getInstance().getString("mobilePhone","未登陆"));
        }

        tv_gerenxinxi_xiugai_menjinrenzhenge.setText(objBean.getEntranceCount()+"");
        tv_youhuiquancount.setText(objBean.getCoupon()+"");
        if (SPManager.getInstance().getInt("messageInfo",0)<objBean.getMessageInfo()){
            SPManager.getInstance().putInt("messageInfo",objBean.getMessageInfo());
            iv_tongzhi_tishi.setVisibility(View.VISIBLE);
        }else {
            iv_tongzhi_tishi.setVisibility(View.GONE);
        }





    }
    private void panduan(TextView tv,int count) {
        if (count!=0){
            tv.setVisibility(View.VISIBLE);
            tv.setText(count+"");
        }else {
            tv.setVisibility(View.GONE);
        }
    }


}
