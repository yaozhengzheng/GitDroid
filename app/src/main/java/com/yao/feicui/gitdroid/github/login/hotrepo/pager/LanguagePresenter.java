package com.yao.feicui.gitdroid.github.login.hotrepo.pager;



import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.yao.feicui.gitdroid.github.login.hotrepo.model.Language;
import com.yao.feicui.gitdroid.github.login.hotrepo.pager.model.Repo;
import com.yao.feicui.gitdroid.github.login.hotrepo.pager.model.RepoResult;
import com.yao.feicui.gitdroid.github.login.hotrepo.pager.view.LanguageView;
import com.yao.feicui.gitdroid.network.GitHubClient;


import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 16245 on 2016/07/04.
 */
public class LanguagePresenter extends MvpNullObjectBasePresenter<LanguageView>{
    private Call<RepoResult> reposCall;
    private int nextPage = 0;
    private Language language;

    public LanguagePresenter(Language language){
        this.language=language;
    }
    // 这是下拉刷新视图层的业务逻辑-----------------------------------------------------------
    public void refresh() {
        getView().hideLoadMore(); // 隐藏“加载更多”视图
        getView().showContentView(); // 显示内容(显示出列表)
        nextPage = 1; // 刷新永远是第1页
        reposCall = GitHubClient.getInstance().searchRepo("language:"+"java", nextPage);
        reposCall.enqueue(reposCallback);
    }

    // 这是上拉加载更多视图层的业务逻辑------------------------------------------------
    public void loadMore() {
        getView().showLoadMoreLoading();
        reposCall = GitHubClient.getInstance().searchRepo("language:"+language.getPath(), nextPage);
        reposCall.enqueue(loadMoreCallback);
    }

    private Callback<RepoResult> reposCallback = new Callback<RepoResult>() {
        @Override public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            getView().stopRefresh();// 视图停止刷新
            RepoResult repoResult = response.body();
            if(repoResult == null){
                getView().showErroView("result is null");
                return;
            }
            // 当前搜索的语言下，没有任何仓库
            if(repoResult.getTotalCount() <= 0){
                getView().refreshData(null);
                getView().showEmptyView();
                return;
            }
            // 取出当前搜索的语言下，所有仓库
            List<Repo> repoList = repoResult.getRepoList();
            getView().refreshData(repoList); // 视图数据刷新
            nextPage = 2; // 下拉刷新成功，当前为第1页,下一页则为第2页
        }

        @Override public void onFailure(Call<RepoResult> call, Throwable t) {
            getView().stopRefresh(); // 视图停止刷新
            getView().showErroView(t.getMessage());
        }
    };
    // 上拉加载的回调
    private Callback<RepoResult> loadMoreCallback = new Callback<RepoResult>() {
        @Override public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            getView().hideLoadMore();
            RepoResult result = response.body();
            if (result == null) {
                getView().showLoadMoreErro("result is null");
                return;
            }
            // 没有更多数据了
            if(result.getTotalCount() <= 0){
                getView().showLoadMoreEnd();
                return;
            }
            // 取出当前搜索的语言下，所有仓库
            List<Repo> repoList = result.getRepoList();
            getView().addMoreData(repoList);
            nextPage ++;
        }

        @Override public void onFailure(Call<RepoResult> call, Throwable t) {
            getView().hideLoadMore();
            getView().showLoadMoreErro(t.getMessage());
        }
    };
}
