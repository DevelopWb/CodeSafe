package com.juntai.upcodesafe.base;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.upcodesafe.utils.ImageJavascriptInterface;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.utils.HtmlFormat;
import com.juntai.upcodesafe.utils.StringTools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */
public  class BaseWebviewActivity<P extends BasePresenter> extends BaseAppActivity<P> {
    String urlString;
    String content;
    private WebView mAgreementWeb;
    private LinearLayout mAgreementLayout;

    public static  String  WEB_URL = "webUrl";
    public static  String  WEB_CONTENT = "webcontent";
    public static  String  TITLE_NAME = "titleName";
    private ImageJavascriptInterface imageJavascriptInterface;

    @Override
    protected P createPresenter() {
        return null;
    }


    public static void  startBaseWebviewActivity(Context mContext,Class cls,String urlString,String titleName) {
        mContext.startActivity(new Intent(mContext,cls).putExtra(WEB_URL,urlString)
        .putExtra(TITLE_NAME,titleName));
    }
    public static void  startBaseWebviewActivity(Context mContext,Class cls,String urlString,String content,String titleName) {
        mContext.startActivity(new Intent(mContext,cls)
                .putExtra(WEB_URL,urlString)
                .putExtra(WEB_CONTENT,content)
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
        content = getIntent().getStringExtra(WEB_CONTENT);
        String titleName = getIntent().getStringExtra(TITLE_NAME);
        setTitleName(titleName);
        //??????WebView?????????????????????????????????????????????????????????????????????????????????WebView??????
        WebSettings webSettings = mAgreementWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// ??????????????????????????????
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        imageJavascriptInterface = new ImageJavascriptInterface(mContext);
        mAgreementWeb.addJavascriptInterface(imageJavascriptInterface, "newsInfoActivity");

        webSettings.setDatabaseEnabled(true);
        webSettings.setDisplayZoomControls(false);//??????webview????????????
        mAgreementWeb.loadData(urlString, "text/html", "UTF-8");
        mAgreementWeb.clearCache(true);
        mAgreementWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mAgreementWeb.setWebViewClient(new WebViewClientDemo());
        if (WEB_CONTENT.equals(urlString)) {
            initNewsContent(content);
        }else {
            mAgreementWeb.loadUrl(urlString);

        }
    }

    /**
     * ?????????????????????
     * @param content
     */
    private void initNewsContent(String content){
        if (StringTools.isStringValueOk(content)) {
            content = HtmlFormat.getNewContent(content);
            mAgreementWeb.loadDataWithBaseURL("file:///android_asset/", content, "text/html",
                    "utf-8", null);
            String finalContent = content;
            mAgreementWeb.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView webView, String s) {
                    //????????????????????????
//                    EventManager.sendStringMsg(HawkProperty.CURRENT_NEWS_AUTHER_ID);
                    imageJavascriptInterface.setImageUrls(getImageUrlsFromHtml(finalContent));
                    setWebImageClick(webView,"newsInfoActivity");
                }
            });
        }
    }

    /**
     * ????????????????????????????????????   ???????????????????????? ??????JavaScript
     * @param view
     *
     */
    public  void setWebImageClick(WebView view,String method) {
        String jsCode="javascript:(function(){" +
                "var imgs=document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<imgs.length;i++){" +
                "imgs[i].pos = i;"+
                "imgs[i].onclick=function(){" +
                "window."+method+".openImage(this.src,this.pos);" +
                "}}})()";
        view.loadUrl(jsCode);
    }
    public  String [] getImageUrlsFromHtml(String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList.size() == 0) {
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }
    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }


    private class WebViewClientDemo extends WebViewClient {
        @Override
        // ???WebView???????????????????????????????????????
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        //???????????????webview??????destory???,webview????????????Activity???
        //?????????????????????webview?????????????????????Activity???context??????
        //????????????????????????????????????webview,???????????????webview
        mAgreementLayout.removeView(mAgreementWeb);
        mAgreementWeb.destroy();
        super.onDestroy();
    }
}
