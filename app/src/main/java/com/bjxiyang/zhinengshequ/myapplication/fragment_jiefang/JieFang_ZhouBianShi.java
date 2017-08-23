package com.bjxiyang.zhinengshequ.myapplication.fragment_jiefang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class JieFang_ZhouBianShi extends Fragment {
    private WebView web;
    private String url;
    private View view;
    private Callback callback;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getContext(), R.layout.xy_jinrongweb_activity,null);
        initUI();
        url="";
        return view;
    }
    private void initUI() {
        web= (WebView)view.findViewById(R.id.web);
        WebSettings webSettings=web.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        callback=new Callback();
        web.setWebViewClient(callback);
        web.loadUrl(url);



//        web.setWebViewClient(new WebViewClient() {
//            //覆盖shouldOverrideUrlLoading 方法
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url1) {
//                web.loadUrl("http://"+url);
//                return true;
//            }
//        });
    }
     class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            // TODO Auto-generated method stub
            if (JieFang_ZhouBianShi.this == null) {
                return false;
            }
            //调用拨号程序
            if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:") || url.startsWith("smsto:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            return false;
        }
    }

}
