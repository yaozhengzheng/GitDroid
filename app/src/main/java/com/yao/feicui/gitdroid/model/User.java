package com.yao.feicui.gitdroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 16245 on 2016/07/06.
 */
public class User implements Serializable{

    // 登录所用的账号
    private String login;
    // 用户名
    private String name;

    private int id;

    // 用户头像路径
    @SerializedName("avatar_url")
    private String avatar;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }
}
