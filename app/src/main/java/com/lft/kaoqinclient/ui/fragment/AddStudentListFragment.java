package com.lft.kaoqinclient.ui.fragment;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lft.base.BaseAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.response.StudentInfoBean;
import com.lft.kaoqinclient.ui.adapter.AddStudentAdapter;
import com.lft.widget.layout.WrapRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 15:05
 */
public class AddStudentListFragment extends MyFragment<MyActivity> implements  BaseAdapter.OnItemClickListener, BaseAdapter.OnChildClickListener{


    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;

    private AddStudentAdapter mAdapter;

    private  List<StudentInfoBean> list;

    public static AddStudentListFragment newInstance(List<StudentInfoBean> list){
        return new AddStudentListFragment(list);
    }

    public AddStudentListFragment(List<StudentInfoBean> list) {
        super();
        this.list = list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_student_list;
    }

    @Override
    protected void initView() {

        mRefreshLayout = findViewById(R.id.rl_add_student_refresh);
        mRecyclerView = findViewById(R.id.rv_add_student_list);

        mAdapter = new AddStudentAdapter(getAttachActivity());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnChildClickListener(R.id.iv_add_student_delete, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addHeaderView(R.layout.item_add_student_header);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

    }

    @Override
    protected void initData() {
        mAdapter.setData(list);
    }

    public void setStudentList(){
        mAdapter.setData(list);
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
    public void onChildClick(RecyclerView recyclerView, View childView, int position) {
        list.remove(position);
        mAdapter.setData(list);
    }


}
