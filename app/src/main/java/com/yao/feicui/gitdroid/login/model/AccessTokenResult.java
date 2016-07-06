package com.yao.feicui.gitdroid.login.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 16245 on 2016/07/06.
 */
public class AccessTokenResult {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("scope")
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public String getScope() {
        return scope;
    }

    public String getTokenType() {
        return tokenType;
    }
}
