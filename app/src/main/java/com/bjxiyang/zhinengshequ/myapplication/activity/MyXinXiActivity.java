package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.LogOutBaseActivity;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.bean.ImageUrl;
import com.bjxiyang.zhinengshequ.myapplication.bean.Users1;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.dialog.SelectSexDialog;
import com.bjxiyang.zhinengshequ.myapplication.luban.LuBan;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class MyXinXiActivity extends LogOutBaseActivity implements View.OnClickListener {
    private Map map=new HashMap();
    private SelectSexDialog dialog;
    private File mFile;
    private String picturePath;
    final int RESULT_LOAD_IMAGE=2;
    private CircleImageView iv_gerenxinxi_xiugai_touxiang;

    private TextView tv_gerenxinxi_xiugai_name;
    private TextView tv_gerenxinxi_xiugai_sex;

    private LinearLayout changepassworldlativelayout;
    private TextView tv_gerenxinxi_xiugai_menjinrenzhenge;
    private LinearLayout ll_username;
    private LinearLayout ll_gerenxinxi_xiugai_touxiang;
    private LinearLayout ll_gerenxinxi_xiugai_sex;

    private RelativeLayout iv_gerenxinxi_xiugai_fanhui;
    private String mHeadPhotoUrl="";
    private String sex="0";
    private String mNickName="";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi_xiugai1);
        initUI();
        if (SPManager.getInstance().getString("c_memberId",null)!=null ||
                !SPManager.getInstance().getString("c_memberId","").equals("")) {
            Intent intent = getIntent();
            mHeadPhotoUrl = intent.getStringExtra("HeadPhotoUrl");

            sex = intent.getStringExtra("sex");
            mNickName = intent.getStringExtra("NickName");
            setTextForHTTP();
        }


    }


    private void initUI() {
        ll_username= (LinearLayout) findViewById(R.id.ll_username);
        tv_gerenxinxi_xiugai_menjinrenzhenge= (TextView) findViewById(R.id.tv_gerenxinxi_xiugai_menjinrenzhenge);
        changepassworldlativelayout= (LinearLayout) findViewById(R.id.changepassworldlativelayout);
        ll_gerenxinxi_xiugai_touxiang= (LinearLayout) findViewById(R.id.ll_gerenxinxi_xiugai_touxiang);
        ll_gerenxinxi_xiugai_sex= (LinearLayout) findViewById(R.id.ll_gerenxinxi_xiugai_sex);

        ll_username.setOnClickListener(this);
        tv_gerenxinxi_xiugai_menjinrenzhenge.setOnClickListener(this);
        changepassworldlativelayout.setOnClickListener(this);
        ll_gerenxinxi_xiugai_touxiang.setOnClickListener(this);
        ll_gerenxinxi_xiugai_sex.setOnClickListener(this);

        iv_gerenxinxi_xiugai_touxiang= (CircleImageView) findViewById(R.id.iv_gerenxinxi_xiugai_touxiang);
        tv_gerenxinxi_xiugai_name= (TextView) findViewById(R.id.tv_gerenxinxi_xiugai_name);
        tv_gerenxinxi_xiugai_sex=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_sex);
//        tv_gerenxinxi_xiugai_chushengriqi=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_chushengriqi);
//        tv_gerenxinxi_xiugai_tel=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_tel);
//        tv_gerenxinxi_xiugai_email=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_email);
//        tv_gerenxinxi_xiugai_qq=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_qq);
//        tv_gerenxinxi_xiugai_weixin=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_weixin);
//        tv_gerenxinxi_xiugai_address=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_address);
//        iv_gerenxinxi_xiugai_bt_xiugai=(ImageView) findViewById(R.id.iv_gerenxinxi_xiugai_bt_xiugai);
//        iv_gerenxinxi_xiugai_bt_xiugai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MyXinXiActivity.this,MyXinXi_XiuGaiActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        iv_gerenxinxi_xiugai_fanhui= (RelativeLayout) findViewById(R.id.iv_gerenxinxi_xiugai_fanhui);
        iv_gerenxinxi_xiugai_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData(){
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETUSERINFO+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.getUserInfo(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                Users1 users= (Users1) responseObj;
                if (users.getCode().equals("1000")){
                    setTextForHTTP();
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(MyXinXiActivity.this);

            }
        });
    }

    private void setTextForHTTP(){
        if (mNickName!=null){
            tv_gerenxinxi_xiugai_name.setText(mNickName);
        }
        if (sex!=null) {
            if (sex.equals("0")) {
                tv_gerenxinxi_xiugai_sex.setText("女");
            } else {
                tv_gerenxinxi_xiugai_sex.setText("男");
            }
        }else {
            tv_gerenxinxi_xiugai_sex.setText("女");
        }

        if (mHeadPhotoUrl!=null&&!mHeadPhotoUrl.equals("")){
            ImageLoaderManager.getInstance(this).displayImage(
                    iv_gerenxinxi_xiugai_touxiang,mHeadPhotoUrl);

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //修改用户名
            case R.id.ll_username:
                Intent intent=new Intent(MyXinXiActivity.this,XiuGaiNameActivity.class);
                intent.putExtra("name",mNickName);
                startActivityForResult(intent,1);
//                startActivity(intent);
                break;
            //点击跳转到门禁认证
            case R.id.tv_gerenxinxi_xiugai_menjinrenzhenge:
                break;
            //修改密码
            case R.id.changepassworldlativelayout:
                Intent intent2=new Intent(MyXinXiActivity.this,RegisteredActivity.class);
                intent2.putExtra("isXiuGai",true);
                startActivity(intent2);
                break;
            //修改头像
            case R.id.ll_gerenxinxi_xiugai_touxiang:
                Intent intent1 = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, RESULT_LOAD_IMAGE);
                break;
            //修改性别
            case R.id.ll_gerenxinxi_xiugai_sex:
                showSelectSexDialog();
                break;

        }
    }
    private void showSelectSexDialog(){
        dialog=new SelectSexDialog(MyXinXiActivity.this);
        dialog.setOnGetSelectTime(new SelectSexDialog.OnGetSelectTime() {
            @Override
            public void getSelectTime(String selectTime) {
                tv_gerenxinxi_xiugai_sex.setText(selectTime);
            }

            @Override
            public void getSelectTime_int(int selectTime_int) {
                String url= XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                        +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                        +"&sex="+selectTime_int;
                Log.i("lllll",selectTime_int+"");
                RequestCenter.usercenter_updateUserInfo(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        FanHui2 fanHui2= (FanHui2) responseObj;
                        if (fanHui2.getCode()==1000){
                            MyUntil.show(MyXinXiActivity.this,"修改成功");
                            dialog.dismiss();
                        }else {
                            MyUntil.show(MyXinXiActivity.this,fanHui2.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                });

            }
        });
        dialog.show();
    }

    /**
     * 用onActivityResult()接收传回的图像，当用户拍完照片，或者取消后，系统都会调用这个函数
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
//        if (requestCode == RESULT_CANCELED && resultCode == RESULT_OK) {
//
//            Bundle extras = intent.getExtras();//从Intent中获取附加值
//            Bitmap bitmap=(Bitmap) extras.get("data");
//            mFile=compressImagefile(bitmap);
//            LuBan.setOnGetImage(MyXinXiActivity.this, mFile, new LuBan.OnGetImage() {
//                @Override
//                public void getImage(File file) {
//                    map.put("iconFile", file);
//
//                }
//            });
//
////            saveBitmapToSharedPreferences((Bitmap) extras.get("data"));
////            从附加值中获取返回的图像
//            iv_gerenxinxi_xiugai_touxiang.setImageBitmap(bitmap);
//            bitmap.recycle();
//        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Uri uri1 = intent.getData();
            Bitmap bitmap=getBitmapFromUri(uri1);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri1,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath= cursor.getString(columnIndex);
            mFile=new File(picturePath);
            LuBan.setOnGetImage(MyXinXiActivity.this, mFile, new LuBan.OnGetImage() {
                @Override
                public void getImage(File file) {
                    map.put("iconFile", file);
                    String url=XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                            +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null);
                    RequestCenter.uploadPictures1(url, map, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            ImageUrl fanHui2= (ImageUrl) responseObj;
                            if (fanHui2.getCode()==1000){
                                MyUntil.show(MyXinXiActivity.this,"修改成功");
                            }else {
                                MyUntil.show(MyXinXiActivity.this,fanHui2.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {

                        }
                    });

                }
            });
            cursor.close();
            bitmap.recycle();
            ImageLoaderManager.getInstance(MyXinXiActivity.this)
                    .displayImage(iv_gerenxinxi_xiugai_touxiang,String.valueOf(uri1));

        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mNickName = intent.getStringExtra("name");
            tv_gerenxinxi_xiugai_name.setText(mNickName);
        }


    }
    private Bitmap getBitmapFromUri(Uri uri) {
        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

}
