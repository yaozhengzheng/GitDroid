package com.yao.feicui.gitdroid.github.login.login;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.yao.feicui.gitdroid.model.AccessTokenResult;
import com.yao.feicui.gitdroid.model.CurrentUser;
import com.yao.feicui.gitdroid.model.User;
import com.yao.feicui.gitdroid.network.GitHubApi;
import com.yao.feicui.gitdroid.network.GitHubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 16245 on 2016/07/05.
 */
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView>{
    private Call<AccessTokenResult> tokenCall;
    private Call<User> userCall;
    public void login(String code){
        getView().showProgress();
        if (tokenCall != null) tokenCall.cancel();
        tokenCall = GitHubClient.getInstance().getOAuthToken(GitHubApi.CLIENT_ID, GitHubApi.CLIENT_SECRET, code);
        tokenCall.enqueue(tokenCallback);
    }
    private Callback<AccessTokenResult> tokenCallback = new Callback<AccessTokenResult>() {
        @Override public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {
            // 保存token到内存里面
            String token = response.body().getAccessToken();
            CurrentUser.setAccessToken(token);
            //
            if (userCall != null) userCall.cancel();
            userCall = GitHubClient.getInstance().getUserInfo();
            userCall.enqueue(userCallback);
        }

        @Override public void onFailure(Call<AccessTokenResult> call, Throwable t) {
            getView().showMessage("Fail:" + t.getMessage());
            // 失败，重置WebView
            getView().showProgress();
            getView().resetWeb();

        }
    };

    private Callback<User> userCallback = new Callback<User>() {
        @Override public void onResponse(Call<User> call, Response<User> response) {
            // 保存user到内存里面
            User user = response.body();
            CurrentUser.setUser(user);
            // 导航至主页面
            getView().showMessage("登陆成功");
            getView().navigateToMain();
        }

        @Override public void onFailure(Call<User> call, Throwable t) {
            // 清除缓存的用户信息，
            CurrentUser.clear();
            getView().showMessage("Fail:" + t.getMessage());
            // 重置WebView
            getView().showProgress();
            getView().resetWeb();
        }
    };

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            if (tokenCall != null) tokenCall.cancel();
            if (userCall != null) userCall.cancel();
        }
    }
}
