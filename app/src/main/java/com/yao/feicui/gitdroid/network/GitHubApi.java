package com.yao.feicui.gitdroid.network;

import com.yao.feicui.gitdroid.github.login.hotrepo.pager.model.RepoResult;
import com.yao.feicui.gitdroid.github.login.repo.RepoContentResult;
import com.yao.feicui.gitdroid.model.AccessTokenResult;
import com.yao.feicui.gitdroid.model.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    /**
     * @return 获取用户信息
     */
    @GET("user") Call<User> getUserInfo();

    /**
     * @param query  查询参数
     * @param pageId 查询页数，从1开始
     * @return 查询结果
     */
    @GET("/search/repositories")
    Call<RepoResult> searchRepo(
            @Query("q") String query,
            @Query("page") int pageId);

    // https://api.github.com/repos/square/okhttp/readme
    /**
     * @param owner 仓库的拥有者
     * @param repo 仓库的名称
     * @return 仓库的Readme页面的内容，{@link RepoContentResult#content}将是Markdown格式。
     */
    @GET("repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(@Path("owner") String owner, @Path("repo") String repo);

    /**
     * 获取一个Markdown内容对应的HTMl页面
     * @param body 请求体，内容来自{@link RepoContentResult#content}
     * @return Call对象
     */
    @Headers({
            "Content-Type:text/plain"
    })
    @POST("/markdown/raw")
    Call<ResponseBody> markdown(@Body RequestBody body);
}
