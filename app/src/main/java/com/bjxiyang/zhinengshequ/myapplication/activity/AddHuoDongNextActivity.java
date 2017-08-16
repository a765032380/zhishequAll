package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.ImageUrl;
import com.bjxiyang.zhinengshequ.myapplication.connectionsURL.XY_Response2;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.io.File;
import java.util.HashMap;
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
    private String time;
    private String money;
    private String datecount;
    private String yaoqiu;

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
        time= intent.getStringExtra("time");
        money=intent.getStringExtra("money");
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
                shangchuanImage();
                break;
        }
    }

    private void shangchuanImage() {
        if(mFile==null){
            map.put("image_one",new File(""));
        }

        String imageUrl= XY_Response2.URL_NEIGHBOR_ADDPARTYIMG+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null);
        RequestCenter.neighbor_addpartyimg(imageUrl, map, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                ImageUrl image= (ImageUrl) responseObj;


            }

            @Override
            public void onFailure(Object reasonObj) {

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
                    map.put("image_one",mFile);
                    ImageLoaderManager.getInstance(AddHuoDongNextActivity.this)
                            .displayImage(iv_addimg1, String.valueOf(selectedImage));
                    iv_addimg2.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_TWO:
                    map.put("image_twe",mFile);
                    ImageLoaderManager.getInstance(AddHuoDongNextActivity.this)
                            .displayImage(iv_addimg2,String.valueOf(selectedImage));
                    iv_addimg3.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_THREE:
                    map.put("image_three",mFile);
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
        if (map.get("image_three")!=null){
            iv_delete3.setVisibility(View.GONE);
            map.put("image_one",map.get("image_two"));
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg1,((File)map.get("image_one")).getAbsolutePath());
            map.put("image_two",map.get("image_three"));
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg2,((File)map.get("image_two")).getAbsolutePath());
            map.remove("image_three");

        }else if (map.get("image_two")!=null){
            iv_delete2.setVisibility(View.GONE);
            map.put("image_one",map.get("image_two"));
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg1,((File)map.get("image_one")).getAbsolutePath());
            map.remove("image_two");
        }else {
            iv_delete1.setVisibility(View.GONE);
            map.remove("image_one");
        }
    }
    private void delete2(){
        if (map.get("image_three")!=null){
            iv_delete3.setVisibility(View.GONE);
            map.put("image_two",map.get("image_three"));
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_addimg2,((File)map.get("image_two")).getAbsolutePath());
            map.remove("image_three");

        }else {
            iv_delete2.setVisibility(View.GONE);
            map.remove("image_two");
        }

    }
    private void delete3(){
        iv_delete3.setVisibility(View.GONE);
        map.remove("image_three");


    }



}
