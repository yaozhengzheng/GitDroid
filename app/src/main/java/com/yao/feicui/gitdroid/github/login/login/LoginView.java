package com.yao.feicui.gitdroid.github.login.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by 16245 on 2016/07/05.
 */
public interface LoginView extends MvpView {

    // 显示进度条
    void showProgress();

    // 重置webView
    void resetWeb();

    void showMessage(String msg);

    // 导航至主页面
    void navigateToMain();
}
