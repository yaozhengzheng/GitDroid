package com.yao.feicui.gitdroid.hot.pager;

/**
 * 下拉刷新视图抽象接口
 * Created by 16245 on 2016/07/01.
 */
public interface PtrView<T> {
    /** 显示内容视图*/
    void showContentView();
    /** 显示错识视图*/
     void showErroView(String msg);
     /** 显示空视图*/
     void showEmptyView();
     /** 刷新数据*/
     void refreshData(T t);
     /** 停止刷新*/
     void stopRefresh();

     void showMessage(String msg);

}
