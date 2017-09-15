package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.bean.PermissionList;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.UserState;
import com.bjxiyang.zhinengshequ.myapplication.until.UserType;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.bjxiyang.zhinengshequ.myapplication.view.SwipeListLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gll on 17-5-23.
 */

public class XYKeyaccreditAdapter extends BaseAdapter{
    private ImageLoaderManager manager;
    private Context mContext;
    private List<PermissionList.Obj> mList;

    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int status;
    private int activeUser;
    private int permissionId;
    private Viewholder viewholder;
    private PermissionList.Obj obj;
    private PermissionList.Obj obj1;
    private PermissionList.Obj obj2;
    private Set<SwipeListLayout> sets = new HashSet();
    private TextView tv_delete;
    private SwipeListLayout sll_main;
    private int c_memberId = Integer.parseInt(SPManager.getInstance().getString("c_memberId",""));
    public XYKeyaccreditAdapter(Context mContext, List mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("ResourceAsColor")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        viewholder=null;
        if (view==null){
            viewholder=new Viewholder();
            view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_keyaccredit,null);
            viewholder.iv_touxiang= (CircleImageView) view.findViewById(R.id.iv_touxiang);
            viewholder.name= (TextView) view.findViewById(R.id.name);
            viewholder.iv_zukejiaren= (ImageView) view.findViewById(R.id.iv_zukejiaren);
            viewholder.item_address= (TextView) view.findViewById(R.id.item_address);
            viewholder.iv_startusing= (ImageView) view.findViewById(R.id.iv_startusing);
//            viewholder.item_date= (TextView) view.findViewById(R.id.item_date);
            viewholder.ib_jinyong_qiyong= (TextView) view.findViewById(R.id.ib_jinyong_qiyong);
            viewholder.tv_zhuangtai= (TextView) view.findViewById(R.id.tv_zhuangtai);
            sll_main= (SwipeListLayout) view.findViewById(R.id.sll_main);
            tv_delete = (TextView) view.findViewById(R.id.tv_delete);
            sll_main.setOnSwipeStatusListener(new MyOnSlipStatusListener(sll_main));
//            viewholder.item_xiugai= (TextView) view.findViewById(R.id.item_xiugai);
            view.setTag(viewholder);
        }else {
            viewholder= (Viewholder) view.getTag();
        }
        manager= ImageLoaderManager.getInstance(mContext);
        //图像处理直接用框架
        //文本处理直接拿服务器数据
        //设置头像 地址为null
        if (mList.get(position).getHeadPhotoUrl()==null||mList.get(position).getHeadPhotoUrl().equals("")){

        }else {
            manager.displayImage(viewholder.iv_touxiang,mList.get(position).getHeadPhotoUrl());

        }







        viewholder.name.setText(mList.get(position).getNickName());

            if (mList.get(position).getC_memberId()==c_memberId){
                    activeUser=mList.get(position).getRoleType();
                if (activeUser==UserType.USER_OWNER||activeUser==UserType.USER_LESSEE){
                    viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
                }
            }
            else {
                viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
            }
            if (mList.get(position).getRoleType()==(UserType.USER_FOLK)){
                viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
                //如果是家人类型，就显示家人类型的图片
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_icon_people);
            }else if (mList.get(position).getRoleType()==(UserType.USER_OWNER)) {
                if (mList.get(position).getStatus()== UserState.START_USING){
                    viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
                }else {
                    viewholder.ib_jinyong_qiyong.setVisibility(View.INVISIBLE);
                }

                //如果是业主类型，就显示业主类型的图片
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_icon_owner);
            }else if (mList.get(position).getRoleType()==(UserType.USER_LESSEE)){
                //否则就是租客类型的
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_icon_tenant);
                if (mList.get(position).getC_memberId()!= Integer.valueOf(SPManager.getInstance().getString("c_memberId",""))){

//                    viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
//                    viewholder.item_xiugai.setVisibility(View.VISIBLE);
                }else {
//                    viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
//                    viewholder.item_xiugai.setVisibility(View.GONE);
                }


            }else if(mList.get(position).getRoleType()==(UserType.USER_LESSEE_HOME)){
//                viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
//                viewholder.item_xiugai.setVisibility(View.VISIBLE);
                //否则就是租客家人
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_icon_people);
//                viewholder.iv_zukejiaren.setText("租户家人");
            }
            obj=mList.get(position);
            viewholder.item_address.setText(obj.getCommunityName()+(obj.getNperName())+"-"+obj.getFloorName()
                    +"号楼-"+obj.getUnitName()+"单元-"+obj.getDoorName()+"室");
//            viewholder.item_date.setText(obj.getAdd_time());

        if (obj.getStatus()== UserState.FORBIDDEN){
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.a_btn_startusing);
            viewholder.ib_jinyong_qiyong.setText("确认启用");
            viewholder.tv_zhuangtai.setText("已禁用");
            viewholder.tv_zhuangtai.setTextColor(R.color.yijinyong);
            viewholder.iv_startusing.setVisibility(View.VISIBLE);
            viewholder.iv_startusing.setImageResource(R.drawable.a_icon_forbidden);
        }else if (obj.getStatus()== UserState.START_USING){
            viewholder.ib_jinyong_qiyong.setText("禁用");
            viewholder.ib_jinyong_qiyong.setTextColor(R.color.yijinyong);
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.a_btn_forbidden);
            viewholder.tv_zhuangtai.setText("已启用");
            viewholder.tv_zhuangtai.setTextColor(R.color.yiqiyong);
            viewholder.iv_startusing.setVisibility(View.VISIBLE);
            viewholder.iv_startusing.setImageResource(R.drawable.a_icon_startusing);
        }
        else if (obj.getStatus()== UserState.SHENQINGZHONG){
            viewholder.tv_zhuangtai.setText("审核中");
            viewholder.tv_zhuangtai.setTextColor(R.color.shenqizhong);
            viewholder.iv_startusing.setVisibility(View.GONE);
            viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
        }else if (obj.getStatus()== UserState.JUJUE){
            viewholder.iv_startusing.setVisibility(View.GONE);
            viewholder.tv_zhuangtai.setText("已拒绝");
            viewholder.tv_zhuangtai.setTextColor(R.color.yijujue);
            viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
        }
        final int position1=position;
        viewholder.ib_jinyong_qiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj1=mList.get(position1);
                communityId=obj1.getCommunityId();
                nperId=obj1.getNperId();
                floorId=obj1.getFloorId();
                unitId=obj1.getUnitId();
                doorId=obj1.getDoorId();
                int status1 =obj1.getStatus();
                permissionId=obj1.getPermissionId();
                DialogUntil.showLoadingDialog(mContext,"正在提交",true);

                int status2=0;
                if (UserState.START_USING==status1){
                    status2= UserState.FORBIDDEN;
                }else if (UserState.FORBIDDEN==status1|| UserState.SHENQINGZHONG==status1){
                    status2= UserState.START_USING;
                }
        String url= XY_Response.URL_UPDATEPERMISSIONS
        +"mobilePhone="+ SPManager.getInstance().getString("mobilePhone","")
        +"&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+"&unitId="
        +unitId+"&doorId="+doorId+"&status="+status2+"&permissionId="+permissionId;

                RequestCenter.updatePermission(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            //这些数据需要上传给服务器的
                            if (obj1.getStatus()== UserState.FORBIDDEN|| UserState.SHENQINGZHONG==obj1.getStatus()){
                                obj1.setStatus(UserState.START_USING);
                            }else {
                                obj1.setStatus(UserState.FORBIDDEN);
                            }

                            notifyDataSetChanged();
//                            Message message=new Message();
//                            message.what=1000;
//                            message.obj=obj1;
//                            handler.sendMessage(message);
                        }else {
                            Toast.makeText(mContext,fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog((Activity) mContext,"请检查网络链接");
                    }
                });


            }
        });
        delete(position1);
//        viewholder.item_xiugai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                obj2=mList.get(position1);
//                communityId=obj2.getCommunityId();
//                nperId=obj2.getNperId();
//                floorId=obj2.getFloorId();
//                unitId=obj2.getUnitId();
//                doorId=obj2.getDoorId();
//                status=obj2.getStatus();
//                permissionId=obj2.getPermissionId();
//                DialogUntil.showLoadingDialog(mContext,"正在提交",true);
//                String url1= XY_Response.URL_DELETEPERMISSIONS+"mobilePhone="+
//                        UserManager.getInstance().getUser().getObj().getMobilePhone()+
//                        "&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+
//                        "&unitId="+unitId+"&doorId="+doorId+
//                        "&permissionId="+permissionId;
//
//                RequestCenter.deletePermission(url1, new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//                        DialogUntil.closeLoadingDialog();
//                        FanHui fanHui= (FanHui) responseObj;
//                        if (fanHui.getCode().equals("1000")){
////                            Toast.makeText(mContext,"连接成功",Toast.LENGTH_LONG).show();
//                            mList.remove(position1);
//                            notifyDataSetChanged();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//                        DialogUntil.closeLoadingDialog();
//                        MyDialog.showDialog((Activity) mContext,"请检查网络连接");
//                    }
//                });
//            }
//        });
        return view;
    }

    private class Viewholder{
        //状态前图片
        ImageView iv_startusing;
        //头像
        CircleImageView iv_touxiang;
        //姓名
        TextView name;
        //租客还是家人
        ImageView iv_zukejiaren;
        //地址
        TextView item_address;
        //时间
        TextView item_date;
        //禁用还是启用
        TextView ib_jinyong_qiyong;
        //显示禁用还是启用
        TextView tv_zhuangtai;
        //修改按钮
        TextView item_xiugai;

    }
    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

        private SwipeListLayout slipListLayout;

        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
            } else {
                if (sets.contains(slipListLayout))
                    sets.remove(slipListLayout);
            }
        }

        @Override
        public void onStartCloseAnimation() {

        }

        @Override
        public void onStartOpenAnimation() {

        }

    }
    private void delete(final int position1){
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);


        obj2=mList.get(position1);
                communityId=obj2.getCommunityId();
                nperId=obj2.getNperId();
                floorId=obj2.getFloorId();
                unitId=obj2.getUnitId();
                doorId=obj2.getDoorId();
                status=obj2.getStatus();
                permissionId=obj2.getPermissionId();
                DialogUntil.showLoadingDialog(mContext,"正在提交",true);
                String url1= XY_Response.URL_DELETEPERMISSIONS+"mobilePhone="+
                        SPManager.getInstance().getString("mobilePhone","")+
                        "&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+
                        "&unitId="+unitId+"&doorId="+doorId+
                        "&permissionId="+permissionId;

                RequestCenter.deletePermission(url1, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
//                            Toast.makeText(mContext,"连接成功",Toast.LENGTH_LONG).show();
                            mList.remove(position1);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog((Activity) mContext,"请检查网络连接");
                    }
                });
            }
        });
    }
}
