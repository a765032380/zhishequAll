package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.bean.FanHui2;
import com.bjxiyang.zhinengshequ.myapplication.bean.ImageUrl;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class AddHuoDongNextActivity extends MySwipeBackActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE_ONE=1;
    private static final int RESULT_LOAD_IMAGE_TWO=2;
    private static final int RESULT_LOAD_IMAGE_THREE=3;
    private static final int RESULT_LOAD_IMAGE_FOUR=4;
    private Map map=new HashMap<>();
    private List<File> imageList=new ArrayList<>();
    private File mFile;
    private String picturePath;

    /**
     * UI
     */
    @BindView(R.id.rl_fanghui)
    RelativeLayout rl_fanghui;
    @BindView(R.id.tv_wancheng)
    TextView tv_wancheng;
    @BindView(R.id.et_startActivities_jieshao)
    EditText et_startActivities_jieshao;
    @BindView(R.id.iv_addimg1)
    ImageView iv_addimg1;
    @BindView(R.id.iv_addimg2)
    ImageView iv_addimg2;
    @BindView(R.id.iv_addimg3)
    ImageView iv_addimg3;
    @BindView(R.id.iv_delete1)
    ImageView iv_delete1;
    @BindView(R.id.iv_delete2)
    ImageView iv_delete2;
    @BindView(R.id.iv_delete3)
    ImageView iv_delete3;
    /***
     * Data
     */
    private String startAddress;
    private String toAddress;
    private String startTime;
    private String endTime;
    private String bmendTime;
    private int money;
    private String datecount;
    private String yaoqiu;
    private String jieshao;
    private String responseImgList;

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String resultimageUrl1;
    private String resultimageUrl2;
    private String resultimageUrl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_faqihuodong_next);
        ButterKnife.bind(this);
        initUI();
        initData();
    }

    private void initData() {

        Intent intent=getIntent();
        startAddress=intent.getStringExtra("startAddress");
        toAddress =intent.getStringExtra("toAddress");
        startTime= intent.getStringExtra("startTime");
        endTime= intent.getStringExtra("endTime");
        bmendTime= intent.getStringExtra("bmendTime");
        money=intent.getIntExtra("money",0);
        datecount=intent.getStringExtra("datecount");
        yaoqiu=intent.getStringExtra("yaoqiu");

    }

    private void initUI() {
        rl_fanghui.setOnClickListener(this);
        iv_addimg1.setOnClickListener(this);
        iv_addimg2.setOnClickListener(this);
        iv_addimg3.setOnClickListener(this);
        iv_delete1.setOnClickListener(this);
        iv_delete2.setOnClickListener(this);
        iv_delete3.setOnClickListener(this);
        tv_wancheng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_fanghui:
                finish();
                break;
            case R.id.iv_addimg1:
                selectImage(RESULT_LOAD_IMAGE_ONE);
                break;
            case R.id.iv_addimg2:
                selectImage(RESULT_LOAD_IMAGE_TWO);
                break;
            case R.id.iv_addimg3:
                selectImage(RESULT_LOAD_IMAGE_THREE);
                break;
            case R.id.iv_delete1:
                delete1();
                break;
            case R.id.iv_delete2:
                delete2();
                break;
            case R.id.iv_delete3:
                delete3();
                break;
            case R.id.tv_wancheng:
                jieshao= String.valueOf(et_startActivities_jieshao.getText());

                if (jieshao==null){
                    MyUntil.show(AddHuoDongNextActivity.this,"请添加活动介绍");
                    break;
                }
                if(imageUrl1==null){
                    MyUntil.show(AddHuoDongNextActivity.this,"请添加图片");
                    break;
                }

                shangchuanImage();
                break;
        }
    }

    private void shangchuanImage() {
        DialogUntil.showLoadingDialog(AddHuoDongNextActivity.this,"正在提交",false);

            map=new HashMap();
            map.put("imgList",imageList);
            String imageUrl = XY_Response2.URL_NEIGHBOR_ADDPARTYIMG + "cmemberId=" +
                    SPManager.getInstance().getString("c_memberId", null);
            RequestCenter.neighbor_addpartyimg(imageUrl, map, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {

                    ImageUrl image = (ImageUrl) responseObj;
                    if (image.getCode()==1000){
                       List imgListResult= image.getResult();

                        for (int i=0;i<imgListResult.size();i++){
                            if (i==0){
                                resultimageUrl1=imgListResult.get(0).toString();
                                responseImgList=resultimageUrl1;
                            }else if (i==1){
                                resultimageUrl2=imgListResult.get(1).toString();
                                responseImgList=responseImgList+";"+resultimageUrl2;
                            }else if (i==2){
                                resultimageUrl3=imgListResult.get(2).toString();
                                responseImgList=responseImgList+";"+resultimageUrl3;
                            }
                        }

                        Log.i("LLLL","图片:"+responseImgList);
                        Log.i("LLLL","money="+money);
                        String addHuoDongUrl=XY_Response2.URL_NEIGHBOR_ADDPARTY
                                +"cmemberId="+SPManager.getInstance().getString("c_memberId",null)
                                +"&endTime="+bmendTime                  // 报名截止时间
                                +"&partyBeginTime="+startTime           // 活动开始时间
                                +"&partyEndTime="+endTime               // 活动结束时间
                                +"&partyDescribe="+jieshao              // 活动描述
                                +"&collectionPlace="+startAddress       // 集合地点
                                +"&destination=" +toAddress             //目的地
                                +"&costType="+money                     //费用类型 0：我买单 1：免费 2：线下AA
                                +"&partyRequirement="+yaoqiu            // 约伴要求
                                +"&imgList="+responseImgList            // 图片列表字符串，用”;”隔开
                                +"&peopleCount="+ datecount;            //约伴人数
                        RequestCenter.neighbor_addparty(addHuoDongUrl, new DisposeDataListener() {
                            @Override
                            public void onSuccess(Object responseObj) {
                                DialogUntil.closeLoadingDialog();
                                FanHui2 fanhui= (FanHui2) responseObj;
                                if (fanhui.getCode()==1000){
                                    AddHuoDongActivity.addHuoDongActivity.finish();
                                    finish();
                                }else {
                                    MyUntil.show(AddHuoDongNextActivity.this,fanhui.getMsg());
                                }
                            }

                            @Override
                            public void onFailure(Object reasonObj) {
                                DialogUntil.closeLoadingDialog();
                            }
                        });

                    }

                }

                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();
                }
            });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath= cursor.getString(columnIndex);
            mFile=new File(picturePath);

            cursor.close();
            switch (requestCode){
                case RESULT_LOAD_IMAGE_ONE:
                    imageUrl1=String.valueOf(selectedImage);
                    iv_delete1.setVisibility(View.VISIBLE);
                    imageList.add(mFile);
                    ImageLoaderManager.getInstance(AddHuoDongNextActivity.this)
                            .displayImage(iv_addimg1, String.valueOf(selectedImage));
                    iv_addimg2.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_TWO:
                    imageUrl2=String.valueOf(selectedImage);
                    iv_delete2.setVisibility(View.VISIBLE);
                    imageList.add(mFile);
                    ImageLoaderManager.getInstance(AddHuoDongNextActivity.this)
                            .displayImage(iv_addimg2,String.valueOf(selectedImage));
                    iv_addimg3.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_THREE:
                    imageUrl3=String.valueOf(selectedImage);
                    iv_delete3.setVisibility(View.VISIBLE);
                    imageList.add(mFile);
                    ImageLoaderManager.getInstance(AddHuoDongNextActivity.this)
                            .displayImage(iv_addimg3,String.valueOf(selectedImage));
                    break;

            }
        }
    }




    private void selectImage(int code){
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, code);
    }
    private void delete1(){
        imageList.remove(0);
        if (imageUrl3!=null){

            imageUrl1=imageUrl2;
            imageUrl2=imageUrl3;
            imageUrl3=null;
            iv_delete3.setVisibility(View.GONE);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg1,imageUrl1);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg2,imageUrl2);

            iv_addimg3.setImageResource(R.drawable.c_a_a_btn_add);

        }else if (imageUrl2!=null){
            iv_delete2.setVisibility(View.GONE);
            imageUrl1=imageUrl2;
            imageUrl2=null;
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg1,imageUrl1);

            iv_addimg2.setImageResource(R.drawable.c_a_a_btn_add);
        }else {
            imageUrl1=null;
            iv_delete1.setVisibility(View.GONE);
            iv_addimg1.setImageResource(R.drawable.c_a_a_btn_add);
        }
    }
    private void delete2(){
        imageList.remove(1);
        if (imageUrl3!=null){
            imageUrl2=imageUrl3;
            imageUrl3=null;
            iv_delete3.setVisibility(View.GONE);

            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg2,imageUrl2);

            iv_addimg3.setImageResource(R.drawable.c_a_a_btn_add);
        }else {
            imageUrl2=null;
            iv_delete2.setVisibility(View.GONE);
            iv_addimg2.setImageResource(R.drawable.c_a_a_btn_add);
        }

    }
    private void delete3(){
        imageList.remove(2);
        imageUrl3=null;
        iv_delete3.setVisibility(View.GONE);
        iv_addimg3.setImageResource(R.drawable.c_a_a_btn_add);

    }



}
