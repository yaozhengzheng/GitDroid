package com.yao.feicui.gitdroid.repo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.yao.feicui.gitdroid.repo.pager.RepoListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 * <p/>
 * 此适配器用于HotRepoFragment, 每一页都是一个RepoListFragment.
 * <p/>
 * 用户浏览过的所有子页面fragment都会保存在内存中，但当它们不可见时，其上的View可能被摧毁。
 * 这可能导致占用大量的内存，因为fragment实例能保存任意量的状态值。
 * <p/>
 * 在我们的应用内，这是可以接收的，否则应该考虑使用FragmentStatePagerAdapter。
 * Created by 16245 on 2016/06/30.
 */
public class HotRepoPagerAdapter extends FragmentPagerAdapter {

    private List<String> languages = new ArrayList<>();

    public HotRepoPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        //这是测试数据
        for (int i = 0; i < 10; i++) {
            languages.add("java" + i);
        }
    }


    @Override
    public Fragment getItem(int position) {
        return RepoListFragment.getInstance(languages.get(position));
    }

    @Override
    public int getCount() {
        return languages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return languages.get(position);
    }

}
