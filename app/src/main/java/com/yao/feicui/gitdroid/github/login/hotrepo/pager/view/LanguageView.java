package com.yao.feicui.gitdroid.github.login.hotrepo.pager.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.yao.feicui.gitdroid.github.login.hotrepo.pager.model.Repo;

import java.util.List;

/**
 * Created by 16245 on 2016/07/04.
 */
public interface LanguageView extends MvpView, PtrView<List<Repo>>,LoadMoreView<List<Repo>> {
}
