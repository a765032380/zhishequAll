package com.bjxiyang.zhinengshequ.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.DingDanXiangQingItemAdapter;
import com.bjxiyang.zhinengshequ.myapplication.base.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.DingDan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class DingDanXiangQingActivity extends MySwipeBackActivity {

    private RelativeLayout iv_dingdanxiangqing_fanhui;
    private ImageView tv_dingdanxiangqing_tel;
    private TextView tv_dingdanxiangqing_paystatus;
    private TextView tv_dingdanxiangqing_paytime;
    private TextView tv_genxie_dianming;
    private TextView tv_item_dingdanxiangqing_shopname;
    private TextView tv_item_dingdanxiangqing_count;
    private ListView lv_dingdanxiangqing;
    private TextView tv_dingdanxiangqing_money;
    private TextView tv_dingdanxiangqing_youhui;
    private TextView tv_dingdanxiangqing_zongji;
    private TextView tv_item_dingdanxiangqing_peisongshijian;
    private TextView tv_item_dingdanxiangqing_dingdanhao;
    private TextView tv_item_dingdanxiangqing_xiadanshijian;
    private TextView tv_item_dingdanxiangqing_zhifufangshi;
    private TextView tv_item_dingdanxiangqing_peisongdizhi;
    private DingDan.ResultBean resultBean;

    private List<DingDan.ResultBean.OrderInfoProductsBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdanxiangqing);
        Intent intent=getIntent();
        resultBean= (DingDan.ResultBean) intent.getSerializableExtra("xiangqing");
        initUI();
        setData();
    }

    private void setData() {

        tv_dingdanxiangqing_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+resultBean.getOrderInfo().getReceivePhone()+""));
                startActivity(i);
            }
        });

        tv_genxie_dianming.setText(resultBean.getSeller().getDes());
        tv_dingdanxiangqing_paytime.setText(resultBean.getOrderInfo().getCreateTime()+"");
        tv_item_dingdanxiangqing_shopname.setText(resultBean.getSeller().getShopName());
        tv_dingdanxiangqing_money.setText(resultBean.getOrderInfo().getTotalAmount()/100+"");
        tv_dingdanxiangqing_youhui.setText(resultBean.getOrderInfo().getTotalAmount()/100-
                resultBean.getOrderInfo().getAfterDiscountAmount()/100+"");
        tv_dingdanxiangqing_zongji.setText(resultBean.getOrderInfo().getAfterDiscountAmount()/100+"");
        tv_item_dingdanxiangqing_count.setText(resultBean.getOrderInfoProducts().size()+"");
        tv_item_dingdanxiangqing_dingdanhao.setText(resultBean.getOrderInfo().getOrderNo()+"");
        tv_item_dingdanxiangqing_xiadanshijian.setText(resultBean.getOrderInfo().getCreateTime()+"");
//        tv_item_dingdanxiangqing_zhifufangshi.setText();
        tv_item_dingdanxiangqing_peisongdizhi.setText(resultBean.getOrderInfo().getReceiver()
        +" "+resultBean.getOrderInfo().getReceivePhone()+" "+resultBean.getOrderInfo().getReceiveAddress());

        mList=resultBean.getOrderInfoProducts();
        DingDanXiangQingItemAdapter adapter=new DingDanXiangQingItemAdapter(DingDanXiangQingActivity.this,mList);
        lv_dingdanxiangqing.setAdapter(adapter);



    }

    private void initUI() {
        iv_dingdanxiangqing_fanhui= (RelativeLayout) findViewById(R.id.iv_dingdanxiangqing_fanhui);
        tv_dingdanxiangqing_tel= (ImageView) findViewById(R.id.tv_dingdanxiangqing_tel);
        tv_dingdanxiangqing_paystatus= (TextView) findViewById(R.id.tv_dingdanxiangqing_paystatus);
        tv_dingdanxiangqing_paytime= (TextView) findViewById(R.id.tv_dingdanxiangqing_paytime);
        tv_genxie_dianming= (TextView) findViewById(R.id.tv_genxie_dianming);
        tv_item_dingdanxiangqing_shopname= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_shopname);
        tv_item_dingdanxiangqing_count= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_count);
        lv_dingdanxiangqing= (ListView) findViewById(R.id.lv_dingdanxiangqing);
        tv_dingdanxiangqing_money= (TextView) findViewById(R.id.tv_dingdanxiangqing_money);
        tv_dingdanxiangqing_youhui= (TextView) findViewById(R.id.tv_dingdanxiangqing_youhui);
        tv_dingdanxiangqing_zongji= (TextView) findViewById(R.id.tv_dingdanxiangqing_zongji);
        tv_item_dingdanxiangqing_peisongshijian= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_peisongshijian);
        tv_item_dingdanxiangqing_dingdanhao= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_dingdanhao);
        tv_item_dingdanxiangqing_xiadanshijian= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_xiadanshijian);
        tv_item_dingdanxiangqing_zhifufangshi= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_zhifufangshi);
        tv_item_dingdanxiangqing_peisongdizhi= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_peisongdizhi);
        iv_dingdanxiangqing_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
