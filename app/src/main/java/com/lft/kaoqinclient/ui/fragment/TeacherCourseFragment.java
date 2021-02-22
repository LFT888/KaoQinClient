package com.lft.kaoqinclient.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lft.base.BaseAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.ui.activity.CourseActivity;
import com.lft.kaoqinclient.ui.activity.StudentAddCourseActivity;
import com.lft.kaoqinclient.ui.activity.TeacherAddCourseActivity;
import com.lft.kaoqinclient.ui.adapter.CoursesAdapter;
import com.lft.widget.layout.WrapRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 13:12
 */
public class TeacherCourseFragment extends MyFragment<MyActivity> implements  BaseAdapter.OnItemClickListener{

    public static TeacherCourseFragment newInstance(){
        return new TeacherCourseFragment();
    }

    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;
    private FloatingActionButton mFloatingView;

    private CoursesAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_courses;
    }

    @Override
    protected void initView() {

        mRefreshLayout = findViewById(R.id.rl_courses_refresh);
        mRecyclerView = findViewById(R.id.rv_courses_list);
        mFloatingView = findViewById(R.id.fab_add_floating);

        mAdapter = new CoursesAdapter(getAttachActivity());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        setOnClickListener(mFloatingView);

    }

    @Override
    protected void initData() {
        mAdapter.setData(coursesData());
    }

    private List<CourseBean> coursesData(){
        List<CourseBean> list;

        list = new ArrayList<>();
        CourseBean cb;
        for (int i3 = 0; i3 < 15; i3++){
            cb = new CourseBean();
            cb.setCourseName("课程名"+i3);
            cb.setSid("老师名");
            list.add(cb);
        }


        return list;
    }


    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mFloatingView) {
            startActivity(TeacherAddCourseActivity.class);
        }
    }


    /**
     * {@link BaseAdapter.OnItemClickListener}
     *
     * @param recyclerView      RecyclerView对象
     * @param itemView          被点击的条目对象
     * @param position          被点击的条目位置
     */
    @Override
    public void onItemClick(RecyclerView recyclerView, View itemView, int position) {


    }
}
