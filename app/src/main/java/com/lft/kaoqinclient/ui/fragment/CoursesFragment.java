package com.lft.kaoqinclient.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hjq.http.EasyHttp;
import com.hjq.http.model.ResponseClass;

import com.lft.base.BaseAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.StudentCoursesApi;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.other.IntentKey;
import com.lft.kaoqinclient.ui.activity.CameraActivity;
import com.lft.kaoqinclient.ui.activity.CourseActivity;
import com.lft.kaoqinclient.ui.activity.ImageSelectActivity;
import com.lft.kaoqinclient.ui.adapter.CoursesAdapter;
import com.lft.widget.layout.WrapRecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/31 22:25
 */
public final class CoursesFragment extends MyFragment<MyActivity> implements  BaseAdapter.OnItemClickListener{

    public static CoursesFragment newInstance(){
        return new CoursesFragment();
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


//        try {
//            HttpData<List<CourseBean>> data = EasyHttp.post(getAttachActivity())
//                    .api(new StudentCoursesApi())
//                    .execute(new ResponseClass<HttpData<List<CourseBean>>>() {});
//
//            list =  data.getData();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }



        return list;
    }


    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mFloatingView) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            if (sharedPreferences.getString("user_identity", "null").equals("student")){

            }
            else if (sharedPreferences.getString("user_identity", "null").equals("teacher")){

            }

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


        CourseActivity.start(getActivity(), mAdapter.getItem(position));

    }
}
