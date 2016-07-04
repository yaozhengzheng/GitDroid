package com.yao.feicui.gitdroid.hot.pager;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by 16245 on 2016/07/04.
 */
public interface PtrPageView extends MvpView, PtrView<List<String>>,LoadMoreView<List<String>>{
}
