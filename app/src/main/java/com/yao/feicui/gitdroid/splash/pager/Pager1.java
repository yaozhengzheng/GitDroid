package com.yao.feicui.gitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.yao.feicui.gitdroid.R;


/**
 * 这是第二个引导界面
 * Created by Administrator on 2016/6/28 0028.
 */
public class Pager1 extends FrameLayout{
    public Pager1(Context context) {
        super(context);
        init();
    }

    public Pager1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1, this, true);
    }

}
