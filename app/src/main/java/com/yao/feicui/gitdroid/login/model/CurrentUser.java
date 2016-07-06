package com.yao.feicui.gitdroid.login.model;

/**
 * Created by 16245 on 2016/07/06.
 */
public class CurrentUser {
    // 此类不可实例化
    private CurrentUser(){}

    private static String accessToken;

    private static User user;

    public static void setAccessToken(String accessToken) {
        CurrentUser.accessToken = accessToken;
    }

    public static void setUser(User user){
        CurrentUser.user = user;
    }
    // 清除缓存的信息
    public static void clear(){
        accessToken = null;
        user = null;
    }


    public static String getAccessToken(){
        return accessToken;
    }

    public static User getUser() {
        return user;
    }

    // 当前是否有访问令牌
    public static boolean hasAccessToken(){
        return accessToken != null;
    }

    public static boolean isEmpty(){
        return accessToken == null || user == null;
    }
}
