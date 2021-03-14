package com.lft.kaoqinclient.ui.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lft.base.BaseAdapter;
import com.lft.base.BaseDialog;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.response.SignInListBean;
import com.lft.kaoqinclient.http.response.SignInLogBean;
import com.lft.kaoqinclient.http.response.StudentInfoBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.ui.activity.CourseActivity;
import com.lft.kaoqinclient.ui.activity.SignInLogActivity;
import com.lft.kaoqinclient.ui.adapter.TeacherSignAdapter;
import com.lft.kaoqinclient.ui.adapter.TeacherSignInLogAdapter;
import com.lft.kaoqinclient.ui.dialog.MenuDialog;
import com.lft.widget.layout.WrapRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author LFT
 *
 * @date 2021/3/6 21:06
 */
public class TeacherSignInLogFragment extends MyFragment<MyActivity> implements BaseAdapter.OnItemClickListener{


    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;

    private TeacherSignInLogAdapter mAdapter;


    private int courseId;
    private List<SignInLogBean> logList;

    public static TeacherSignInLogFragment newInstance(int courseId){
        return new TeacherSignInLogFragment(courseId);
    }

    public TeacherSignInLogFragment(int courseId){
        super();
        this.courseId = courseId;
    }

    public TeacherSignInLogFragment(){
        super();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teacher_sign;
    }

    @Override
    protected void initView() {

        mRefreshLayout = findViewById(R.id.rl_teacher_sign_refresh);
        mRecyclerView = findViewById(R.id.rv_teacher_sign_list);

        mAdapter = new TeacherSignInLogAdapter(getAttachActivity());
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addHeaderView(R.layout.item_sign_in_log_header);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

    }

    @Override
    protected void initData() {
        logList = new ArrayList<>();
        SignInLogBean bean = new SignInLogBean();
        bean.setActualNumber(35);
        bean.setShouldAttend(35);
        bean.setCourseTime("2021090909");
        bean.setLeaveNumber(0);
        bean.setLateNumber(0);
        SignInListBean l = new SignInListBean();
        l.setSignInInfo("已签到");
        l.setClassName("计科");
        l.setName("lft");
        l.setUid("17115012036");
        List<SignInListBean> ll = new ArrayList<>();
        ll.add(l);
        bean.setList(ll);

        logList.add(bean);
        mAdapter.setData(logList);
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
        SignInLogActivity.start(getActivity(), mAdapter.getItem(position).getList());
    }

}

