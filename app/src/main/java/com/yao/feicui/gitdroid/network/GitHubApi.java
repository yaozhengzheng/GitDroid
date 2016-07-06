package com.yao.feicui.gitdroid.network;

import com.yao.feicui.gitdroid.login.model.AccessTokenResult;
import com.yao.feicui.gitdroid.login.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by 16245 on 2016/07/05.
 */
public interface GitHubApi {

    String CALL_BACK="yao";
    // GitHub开发者，申请就行
    String CLIENT_ID = "1195760bc0055bb5fe4f";
    String CLIENT_SECRET="e2be70e6f61338798a866690817c0b6295db18e7";

    // 授权时申请的可访问域
    String INITIAL_SCOPE = "user,public_repo,repo";

    // WebView来加载此URL,用来显示GitHub的登陆页面
    String AUTH_URL =
            "https://github.com/login/oauth/authorize?client_id="+
                    CLIENT_ID + "&"+"scope="+INITIAL_SCOPE;

    /**
     * 获取访问令牌API
     */
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthToken(
            @Field("client_id") String client,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);

    @GET("user")
    Call<User> getUserInfo();
}
