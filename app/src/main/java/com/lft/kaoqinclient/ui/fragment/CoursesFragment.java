package com.lft.kaoqinclient.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.model.ResponseClass;

import com.lft.base.BaseAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.StudentCoursesApi;
import com.lft.kaoqinclient.http.request.TeacherCoursesApi;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.other.IntentKey;
import com.lft.kaoqinclient.ui.activity.CameraActivity;
import com.lft.kaoqinclient.ui.activity.CourseActivity;
import com.lft.kaoqinclient.ui.activity.ImageSelectActivity;
import com.lft.kaoqinclient.ui.activity.PasswordForgetActivity;
import com.lft.kaoqinclient.ui.activity.StudentAddCourseActivity;
import com.lft.kaoqinclient.ui.adapter.CoursesAdapter;
import com.lft.widget.layout.WrapRecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/31 22:25
 */
public final class CoursesFragment extends MyFragment<MyActivity> implements OnRefreshLoadMoreListener,BaseAdapter.OnItemClickListener{

    public static CoursesFragment newInstance(){
        return new CoursesFragment();
    }

    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;

    private CoursesAdapter mAdapter;
    private List<CourseBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_courses;
    }

    @Override
    protected void initView() {

        mRefreshLayout = findViewById(R.id.rl_courses_refresh);
        mRecyclerView = findViewById(R.id.rv_courses_list);

        mAdapter = new CoursesAdapter(getAttachActivity());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

//        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

    }

    @Override
    protected void initData() {

        coursesData();
        postDelayed(() -> {
            list = list == null ? new ArrayList<>() : list;
            mAdapter.setData(list);
            mRefreshLayout.finishRefresh();
        }, 800);

    }

    private void coursesData(){

        EasyHttp.get(getAttachActivity())
                .api(new StudentCoursesApi())
                .request(new HttpCallback<HttpData<List<CourseBean>>>(getAttachActivity()){
                    @Override
                    public void onSucceed(HttpData<List<CourseBean>> data) {
                        list = data.getData();
                    }

                });

    }


    @SingleClick
    @Override
    public void onClick(View v) {

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

        CourseActivity.start(getActivity(), mAdapter.getItem(position));

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {


        coursesData();
        postDelayed(() -> {
            mAdapter.clearData();
            mAdapter.setData(list);
            mRefreshLayout.finishRefresh();
            toast("刷新完成");
        }, 1000);
    }
}
