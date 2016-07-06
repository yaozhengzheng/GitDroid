package com.yao.feicui.gitdroid.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.yao.feicui.gitdroid.R;
import com.yao.feicui.gitdroid.commons.ActivityUtils;
import com.yao.feicui.gitdroid.main.MainActivity;
import com.yao.feicui.gitdroid.network.GitHubApi;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 *  1. GitHub会有一个授权URL
 * 2. 这种授权URL在访问后，将重定向到另一个URL（授权登陆页面URL）
 * 3. 我们拿到
 * Created by 16245 on 2016/07/05.
 */
public class LoginActivity extends MvpActivity<LoginView,LoginPresenter>implements LoginView{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.webView) WebView webView;
    //显示一个GIF图片作为动画
    @Bind(R.id.gifImageView) GifImageView gifImageView;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //显示标题栏左上角的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWebView();
    }

    // WebView的初始化
    private void initWebView() {
        // 删除所有的Cookie，主要是为了清除以前的登录历史记录
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        // 授权登陆URL
        webView.loadUrl(GitHubApi.AUTH_URL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
    }

    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override public void onProgressChanged(WebView view, int newProgress) {

            if(newProgress == 100){
                gifImageView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        }
    };

    private WebViewClient webViewClient = new WebViewClient(){
        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Uri uri=Uri.parse(url);
            if (GitHubApi.CALL_BACK.equals(uri.getScheme())){
                // 获取授权码
                String code = uri.getQueryParameter("code");
                // 执行登陆的操作Presenter
                getPresenter().login(code);
                //
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @NonNull @Override public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
    @Override public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);
    }

    @Override public void resetWeb() {
        initWebView();
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void navigateToMain() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }
}
