package com.yao.feicui.gitdroid.hot.pager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yao.feicui.gitdroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 16245 on 2016/06/30.
 */
public class RepoListFragment extends Fragment {

    @Bind(R.id.lvRepos)ListView listView;

    private ArrayAdapter<String>adapter;
    private List<String>datas=new ArrayList<>();
    private static int count;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //测试假数据
        for (int i = 0; i < 20; i++) {
            count++;
            datas.add("我是第"+count+"条数据");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        adapter.addAll(datas);
    }
    public static RepoListFragment getInstance(String language){
        RepoListFragment fragment=new RepoListFragment();
        Bundle args=new Bundle();
        args.putSerializable("key_language",language);
        fragment.setArguments(args);
        return fragment;
    }
}
