package com.yao.feicui.gitdroid.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.yao.feicui.gitdroid.login.LoginActivity;
import com.yao.feicui.gitdroid.main.MainActivity;
import com.yao.feicui.gitdroid.R;
import com.yao.feicui.gitdroid.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页面,第一次启动时进入的页面
 */
public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.btnLogin) Button btnLogin;
    @Bind(R.id.btnEnter) Button btnEnter;

    private ActivityUtils mActivityUtils;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mActivityUtils=new ActivityUtils(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        mActivityUtils.startActivity(LoginActivity.class);
    }

    @OnClick(R.id.btnEnter)
    public void enter() {
        mActivityUtils.startActivity(MainActivity.class);
    }
}
