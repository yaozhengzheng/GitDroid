package com.yao.feicui.gitdroid.network;

import com.yao.feicui.gitdroid.github.login.hotrepo.pager.model.RepoResult;
import com.yao.feicui.gitdroid.model.AccessTokenResult;
import com.yao.feicui.gitdroid.model.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.Query;

/**
 * 需要添加并且导包
 * Created by 16245 on 2016/07/05.
 */
public class GitHubClient implements GitHubApi{
    private static GitHubClient sClient;
    private final GitHubApi gitHubApi;
    public static GitHubClient getInstance(){
        if (sClient == null) {
            sClient = new GitHubClient();
        }
        return sClient;
    }

    private GitHubClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor()) // 添加拦截器, 处理AccessToken
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        gitHubApi = retrofit.create(GitHubApi.class);
    }
    @Override public Call<AccessTokenResult> getOAuthToken(@Field("client_id") String client, @Field("client_secret") String clientSecret, @Field("code") String code) {
        return gitHubApi.getOAuthToken(client, clientSecret, code);
    }
    @Override public Call<User> getUserInfo() {
        return gitHubApi.getUserInfo();
    }

    @Override
    public Call<RepoResult> searchRepo(@Query("q") String query, @Query("page") int pageId) {
        return gitHubApi.searchRepo(query,pageId);
    }
}
