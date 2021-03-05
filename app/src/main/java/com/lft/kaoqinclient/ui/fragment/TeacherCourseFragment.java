package com.lft.kaoqinclient.ui.fragment;

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
import com.lft.kaoqinclient.http.request.TeacherCoursesApi;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.http.response.UserInfoBean;
import com.lft.kaoqinclient.ui.activity.CourseActivity;
import com.lft.kaoqinclient.ui.activity.StudentAddCourseActivity;
import com.lft.kaoqinclient.ui.activity.TeacherAddCourseActivity;
import com.lft.kaoqinclient.ui.adapter.CoursesAdapter;
import com.lft.kaoqinclient.ui.adapter.TeacherCourseAdapter;
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
 * @date 2021/2/21 13:12
 */
public class TeacherCourseFragment extends MyFragment<MyActivity> implements OnRefreshLoadMoreListener,BaseAdapter.OnItemClickListener{

    public static TeacherCourseFragment newInstance(){
        return new TeacherCourseFragment();
    }

    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;
    private FloatingActionButton mFloatingView;

    private TeacherCourseAdapter mAdapter;

    private List<TeacherCourseBean> list = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_courses;
    }

    @Override
    protected void initView() {

        mRefreshLayout = findViewById(R.id.rl_courses_refresh);
        mRecyclerView = findViewById(R.id.rv_courses_list);
        mFloatingView = findViewById(R.id.fab_add_floating);

        mAdapter = new TeacherCourseAdapter(getAttachActivity());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

//        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        setOnClickListener(mFloatingView);

        mRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        mAdapter.setData(coursesData());
    }

    private List<TeacherCourseBean> coursesData(){

        EasyHttp.get(getAttachActivity())
                    .api(new TeacherCoursesApi())
                    .request(new HttpCallback<HttpData<List<TeacherCourseBean>>>(getAttachActivity()){
                        @Override
                        public void onSucceed(HttpData<List<TeacherCourseBean>> data) {
                            list = data.getData();
                        }

        });

        return list == null ? new ArrayList<>() : list;
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        postDelayed(() -> {
            mAdapter.clearData();
            mAdapter.setData(coursesData());
            mRefreshLayout.finishRefresh();
            toast("刷新完成");
        }, 1000);
    }
}
