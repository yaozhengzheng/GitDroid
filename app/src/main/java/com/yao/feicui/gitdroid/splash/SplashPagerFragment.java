package com.yao.feicui.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yao.feicui.gitdroid.R;
import com.yao.feicui.gitdroid.splash.pager.Pager2;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by 16245 on 2016/06/28.
 */
public class SplashPagerFragment extends Fragment {

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.indicator)
    CircleIndicator mIndicator;//指示器（下方的小圆点）

    private SplashPagerAdapter mAdapter;

    //    viewpager页面对相应的背景颜色
    @BindColor(R.color.colorGreen)
    int colorGreen;
    @BindColor(R.color.colorRed)
    int colorRed;
    @BindColor(R.color.colorBlue)
    int colorBlue;

    @Bind(R.id.content)
    FrameLayout mFrameLayout;//当前界面的布局，用于显示当前颜色变化的渐变

    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mAdapter = new SplashPagerAdapter(getContext());
//        添加mViewPager监听
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(pageColorChangeListener);
        mViewPager.addOnPageChangeListener(pagePhoneChangeListener);
        mIndicator.setViewPager(mViewPager);
    }

    //此监听器主要负责viewpager在scroll过程中,当前布局上layoutPhone布局的平移、缩放、渐变的处理
    private final ViewPager.OnPageChangeListener pagePhoneChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //viewpager在第一个和第二个页面之间
            if (position == 0) {
                float scale = 0.3f + positionOffset * 0.5f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                // 在移动过程中，font实时的变化
                ivPhoneFont.setAlpha(positionOffset);
                // 在移动过程中，有一个平移的动画
                int scroll = (int) ((positionOffset - 1) * 400);
                layoutPhone.setTranslationX(scroll);
                return;
            }
            // 当ViewPager在第二个页面和第三个页面之间时(总是为1),手机要和ViewPager一起平移
            if (position == 1) {
                layoutPhone.setTranslationX(-positionOffsetPixels);
                return;
            }

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private final ViewPager.OnPageChangeListener pageColorChangeListener = new ViewPager.OnPageChangeListener() {
        //Argb取值器
        final ArgbEvaluator mEvaluator = new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //滑动页面的时候颜色变化
            //第一个页面滑动到第二个页面的颜色变化
            if (position == 0) {
                int color = (int) mEvaluator.evaluate(positionOffset, colorGreen, colorRed);
                mFrameLayout.setBackgroundColor(color);
                return;
            }

            //第二个页面滑动到第三个页面的颜色变化
            if (position == 1) {
                int color = (int) mEvaluator.evaluate(positionOffset, colorRed, colorBlue);
                mFrameLayout.setBackgroundColor(color);
                return;
            }
        }

        // 滑到第二个页面的时候显示动画
        @Override
        public void onPageSelected(int position) {
            if (position == 2) {
                Pager2 pager2 = (Pager2) mAdapter.getView(2);
                pager2.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
