package com.sunfb.onekeygray;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.sunfb.onekeygray.base.BaseActivity;
import com.sunfb.onekeygray.impl.GrayController;
import com.sunfb.onekeygray.impl.GrayObservable;
import com.sunfb.onekeygray.impl.GrayObserver;

public class WebviewActivity extends BaseActivity {
    public static final Boolean JS_BUG_ALLVERSION_HANDLEFLAG = false;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviw);
        initWebview();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initWebview() {
        mWebView = findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;// 返回false
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);

        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheMaxSize(8 * 1024 * 1024);

        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //TODO 一键置灰必须设置 ，否则灰灰界面错误
        if(GrayController.getInStance().isState() ){
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        removeSystemJavaScriptInterface();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.loadUrl("http://www.baidu.com");
        ///4、设置响应超链接，在安卓5.0系统，不使用下面语句超链接也是正常的，但在MIUI中安卓4.4.4中需要使用下面这条语句，才能响应超链接
        // mWebView.setWebViewClient(new HelloWebViewClient());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mWebView.canGoBack()) {
                mWebView.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                finish();
                return true;
            }

        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void removeSystemJavaScriptInterface() {
        if (isLargeHoneycomb() && !isLargeJellyBean()) {
            mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            mWebView.removeJavascriptInterface("accessibility");
            mWebView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    private boolean isLargeHoneycomb() {
        if (JS_BUG_ALLVERSION_HANDLEFLAG) {
            return true;
        } else {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
        }
    }

    private boolean isLargeJellyBean() {
        if (JS_BUG_ALLVERSION_HANDLEFLAG) {
            return false;
        } else {
            return Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN;
        }
    }


}