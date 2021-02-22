package com.lft.kaoqinclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.lft.base.BaseFragmentAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.ui.fragment.AddStudentFragment;
import com.lft.kaoqinclient.ui.fragment.AddStudentListFragment;
import com.lft.kaoqinclient.widget.XCollapsingToolbarLayout;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/10 22:34
 */
public class TeacherAddCourseActivity extends MyActivity
        implements XCollapsingToolbarLayout.OnScrimsListener {

    private XCollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private EditText mCourseName;
    private Button mCreateCourse;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_add_course;
    }

    @Override
    protected void initView() {
        mCollapsingToolbarLayout = findViewById(R.id.ctl_tac_bar);
        mToolbar = findViewById(R.id.tb_tac_title);

        mTabLayout = findViewById(R.id.tl_tac_tab);
        mViewPager = findViewById(R.id.vp_tac_pager);

        mCourseName = findViewById(R.id.et_create_course_name);
        mCreateCourse = findViewById(R.id.btn_create_course);

        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(AddStudentFragment.newInstance(), "添加学生");
        mPagerAdapter.addFragment(AddStudentListFragment.newInstance(), "学生列表");
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(this, mToolbar);

        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);


        setOnClickListener(mCreateCourse);
        InputTextHelper.with(this)
                .addView(mCourseName)
                .setMain(mCreateCourse)
                .build();
    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mCreateCourse){

        }

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return super.isStatusBarEnabled();
    }


    /**
     * CollapsingToolbarLayout 渐变回调
     * <p>
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        if (shown) {
            mCourseName.setBackgroundResource(R.drawable.home_search_bar_gray_bg);
            mCourseName.setHintTextColor(ContextCompat.getColor(this,  R.color.black60));
            mCourseName.setTextColor(ContextCompat.getColor(this,  R.color.black60));
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mCourseName.setBackgroundResource(R.drawable.home_search_bar_transparent_bg);
            mCourseName.setHintTextColor(ContextCompat.getColor(this,  R.color.white60));
            mCourseName.setTextColor(ContextCompat.getColor(this, R.color.white60));
            getStatusBarConfig().statusBarDarkFont(false).init();
        }

    }

}
