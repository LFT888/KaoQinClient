package com.lft.kaoqinclient.ui.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.base.BaseAdapter;
import com.lft.base.BaseDialog;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.ChangeSignInApi;
import com.lft.kaoqinclient.http.request.SignInTableApi;
import com.lft.kaoqinclient.http.response.SignInListBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.ui.adapter.TeacherSignAdapter;
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
 * @date 2021/3/6 11:35
 */
public class TeacherSignFragment extends MyFragment<MyActivity> implements OnRefreshLoadMoreListener,BaseAdapter.OnItemClickListener{


    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;

    private TeacherSignAdapter mAdapter;

    private List<SignInListBean> list;
    private List<String> strings;
    private TeacherCourseBean course;
    private String courseTime;

    public static TeacherSignFragment newInstance(TeacherCourseBean course, String courseTime){
        return new TeacherSignFragment(course, courseTime);
    }

    public TeacherSignFragment(TeacherCourseBean course, String courseTime){
        super();
        this.course = course;
        this.courseTime = courseTime;
    }

    public TeacherSignFragment(){
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

        mAdapter = new TeacherSignAdapter(getAttachActivity());
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addHeaderView(R.layout.item_sign_in_table_header);

//        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        mRefreshLayout.setOnRefreshLoadMoreListener(this);

    }

    @Override
    protected void initData() {
        strings = new ArrayList<>();
        strings.add("改为已签到");
        strings.add("改为未签到");
        strings.add("改为旷课");
        strings.add("改为请假");


        studentData();
        postDelayed(() -> {
            list = list == null ? new ArrayList<>() : list;
            mAdapter.setData(list);
            mRefreshLayout.finishRefresh();
        }, 600);
    }

    public void studentData(){
//
//        if (true){
//            list = new ArrayList<>();
//            SignInListBean bean = new SignInListBean();
//            bean.setClassName("计科");
//            bean.setName("lft");
//            bean.setUid("17115012036");
//            bean.setSignInInfo("未签到");
//            list.add(bean);
//            bean = new SignInListBean();
//            bean.setClassName("计科");
//            bean.setName("lft");
//            bean.setUid("17115012036");
//            bean.setSignInInfo("未签到");
//            list.add(bean);
//            return list;
//        }

        EasyHttp.get(getAttachActivity())
            .api(new SignInTableApi()
                .setCourseId(course.getCourseId())
                .setCourseTime(courseTime))
            .request(new HttpCallback<HttpData<List<SignInListBean>>>(getAttachActivity()){
                @Override
                public void onSucceed(HttpData<List<SignInListBean>> data) {
                    list = data.getData();
                }
            });


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
        new MenuDialog.Builder(getAttachActivity())
                .setList(strings)
                .setListener(new MenuDialog.OnListener<String>() {

                    @Override
                    public void onSelected(BaseDialog dialog, int index, String string) {
                        changSignIn(position, string);
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) { }
                })
                .show();

    }

    public void changSignIn(int position, String signInInfo){

        list.get(position).setSignInInfo(signInInfo.substring(2));

//        if (true){
//            mAdapter.setData(list);
//            mRefreshLayout.finishRefresh();
//            return;
//        }

        EasyHttp.post(getAttachActivity())
                .api(new ChangeSignInApi()
                        .setCourseId(course.getCourseId())
                        .setCourseTime(courseTime)
                        .setSignInInfo(list.get(position).getSignInInfo())
                        .setStudentUid(list.get(position).getUid())
                    )
                .request(new HttpCallback<HttpData<Boolean>>(getAttachActivity()){
                    @Override
                    public void onSucceed(HttpData<Boolean> data) {
                        if(data.getData()){
                            toast("修改成功");
                        }
                        else {
                            toast("修改失败");
                            list.get(position).setSignInInfo("未签到");
                        }
                    }
                });

        mAdapter.setData(list);
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        studentData();
        postDelayed(() -> {
            mAdapter.clearData();
            mAdapter.setData(list);
            mRefreshLayout.finishRefresh();
            toast("刷新完成");
        }, 1000);
    }
}

