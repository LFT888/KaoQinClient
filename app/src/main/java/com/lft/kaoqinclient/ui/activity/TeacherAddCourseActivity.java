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
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.base.BaseFragmentAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.FindCourseByIdApi;
import com.lft.kaoqinclient.http.request.TeacherAddCourseApi;
import com.lft.kaoqinclient.http.response.FindCourseBean;
import com.lft.kaoqinclient.http.response.StudentInfoBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.ui.fragment.AddStudentFragment;
import com.lft.kaoqinclient.ui.fragment.AddStudentListFragment;
import com.lft.kaoqinclient.widget.XCollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/10 22:34
 */
public class TeacherAddCourseActivity extends MyActivity
        implements AddStudentFragment.AddStudentCallBack {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private EditText mCourseName;
    private Button mCreateCourse;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    private AddStudentFragment addStudentFragment;
    private AddStudentListFragment addStudentListFragment;
    private List<StudentInfoBean> studentList = new ArrayList<>();
    private String teacherId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_add_course;
    }

    @Override
    protected void initView() {

        mTabLayout = findViewById(R.id.tl_tac_tab);
        mViewPager = findViewById(R.id.vp_tac_pager);

        mCourseName = findViewById(R.id.et_create_course_name);
        mCreateCourse = findViewById(R.id.btn_create_course);

        mPagerAdapter = new BaseFragmentAdapter<>(this);
        addStudentFragment = AddStudentFragment.newInstance(studentList);
        addStudentListFragment = AddStudentListFragment.newInstance(studentList);
        mPagerAdapter.addFragment(addStudentListFragment, "学生列表");
        mPagerAdapter.addFragment(addStudentFragment, "添加学生");
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);



        setOnClickListener(mCreateCourse);
        InputTextHelper.with(this)
                .addView(mCourseName)
                .setMain(mCreateCourse)
                .build();
    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        teacherId = sharedPreferences.getString("user_uid", "null");
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mCreateCourse) {
            EasyHttp.post(this)
                    .api(new TeacherAddCourseApi()
                            .setCourseName(mCourseName.getText().toString())
                            .setTeacherUid(teacherId)
                            .setStudentList(studentList))
                    .request(new HttpCallback<HttpData<TeacherCourseBean>>(this) {
                        @Override
                        public void onSucceed(HttpData<TeacherCourseBean> data) {
                            toast("创建成功");
                        }
                    });
        }

    }

    @Override
    public void getStudentList() {
        addStudentListFragment.setStudentList();
    }


    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return super.isStatusBarEnabled();
    }



}
