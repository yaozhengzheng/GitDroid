package com.yao.feicui.gitdroid.hot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yao.feicui.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *  最热门仓库Fragment
 *
 * 本Fragment是被添加到MainActivity中。
 *
 * 它上面有一个ViewPager和一个相对应的TabLayout。
 *
 * ViewPager上，每一个页面都是一个RepoListFragment
 * Created by 16245 on 2016/06/30.
 */
public class HotRepoFragment extends Fragment {

    @Bind(R.id.viewPager)ViewPager viewPager;
    @Bind(R.id.tabLayout)TabLayout tabLayout;

    private HotRepoPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot_repo,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        //注意此处是在fragment中添加fragment，属于嵌套fragment
        viewPager.setAdapter(new HotRepoPagerAdapter(getChildFragmentManager(),getContext()));
        // 将ViewPager绑定到TabLayout上
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
