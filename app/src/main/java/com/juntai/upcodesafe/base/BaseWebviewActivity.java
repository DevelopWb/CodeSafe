package com.juntai.upcodesafe.base;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.upcodesafe.R;


/**
 *
 */
public  class BaseWebviewActivity<P extends BasePresenter> extends BaseAppActivity<P> {
    String urlString;
    private WebView mAgreementWeb;
    private LinearLayout mAgreementLayout;

    public static  String  WEB_URL = "webUrl";
    public static  String  TITLE_NAME = "titleName";
    @Override
    protected P createPresenter() {
        return null;
    }


    public static void  startBaseWebviewActivity(Context mContext,Class cls,String urlString,String titleName) {
        mContext.startActivity(new Intent(mContext,cls).putExtra(WEB_URL,urlString)
        .putExtra(TITLE_NAME,titleName));
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_user_agreement;
    }

    @Override
    public void initView() {

        mAgreementWeb = (WebView) findViewById(R.id.agreement_web);
        mAgreementLayout = (LinearLayout) findViewById(R.id.agreement_layout);

        urlString = getIntent().getStringExtra(WEB_URL);
        String titleName = getIntent().getStringExtra(TITLE_NAME);
        setTitleName(titleName);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings webSettings = mAgreementWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        mAgreementWeb.loadData(urlString, "text/html", "UTF-8");
        mAgreementWeb.clearCache(true);
        mAgreementWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mAgreementWeb.setWebViewClient(new WebViewClientDemo());
        mAgreementWeb.loadUrl(urlString);
    }


    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }


    private class WebViewClientDemo extends WebViewClient {
        @Override
        // 在WebView中不在默认浏览器下显示页面
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        //但是注意：webview调用destory时,webview仍绑定在Activity上
        //这是由于自定义webview构建时传入了该Activity的context对象
        //因此需要先从父容器中移除webview,然后再销毁webview
        mAgreementLayout.removeView(mAgreementWeb);
        mAgreementWeb.destroy();
        super.onDestroy();
    }
}
