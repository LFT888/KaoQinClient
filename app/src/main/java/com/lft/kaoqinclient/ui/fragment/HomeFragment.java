package com.lft.kaoqinclient.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.lft.base.BaseFragmentAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.ui.activity.HomeActivity;
import com.lft.kaoqinclient.widget.XCollapsingToolbarLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class HomeFragment extends MyFragment<HomeActivity>
        implements XCollapsingToolbarLayout.OnScrimsListener {

    private XCollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;

    private TextView mAddressView;
    private TextView mHintView;
    private AppCompatImageView mSearchView;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mCollapsingToolbarLayout = findViewById(R.id.ctl_home_bar);
        mToolbar = findViewById(R.id.tb_home_title);

        mAddressView = findViewById(R.id.tv_home_address);
        mHintView = findViewById(R.id.tv_home_hint);
        mSearchView = findViewById(R.id.iv_home_search);

        mTabLayout = findViewById(R.id.tl_home_tab);
        mViewPager = findViewById(R.id.vp_home_pager);

        mPagerAdapter = new BaseFragmentAdapter<>(this);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String identity = sharedPreferences.getString("user_identity", null);
        if ("student".equals(identity)){
            mPagerAdapter.addFragment(CoursesFragment.newInstance(), "我的课表");
        }else if ("teacher".equals(identity)){
            mPagerAdapter.addFragment(TeacherCourseFragment.newInstance(), "我的课程");
        }
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);

        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean statusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * CollapsingToolbarLayout 渐变回调
     *
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        if (shown) {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black));
            mHintView.setBackgroundResource(R.drawable.home_search_bar_gray_bg);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black60));
            mSearchView.setSupportImageTintList(ColorStateList.valueOf(getColor(R.color.colorIcon)));
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
            mHintView.setBackgroundResource(R.drawable.home_search_bar_transparent_bg);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white60));
            mSearchView.setSupportImageTintList(ColorStateList.valueOf(getColor(R.color.white)));
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }
}