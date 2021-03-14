package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lft.base.BaseAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.http.response.SignInListBean;
import com.lft.kaoqinclient.other.IntentKey;
import com.lft.kaoqinclient.ui.adapter.TeacherSignAdapter;
import com.lft.widget.layout.WrapRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.Serializable;
import java.util.List;

/**
 * author LFT
 *
 * @date 2021/3/7 17:51
 */
public class SignInLogActivity extends MyActivity implements BaseAdapter.OnItemClickListener {

    private List<SignInListBean> list;

    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;

    private TeacherSignAdapter mAdapter;

    public static void start(Context context, List<SignInListBean> list) {
        Intent intent = new Intent(context, SignInLogActivity.class);
        intent.putExtra(IntentKey.SIGN_IN_LIST, (Serializable) list);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_in_log;
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.rl_sign_log_refresh);
        mRecyclerView = findViewById(R.id.rv_sign_log_list);

        mAdapter = new TeacherSignAdapter(this);
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addHeaderView(R.layout.item_sign_in_table_header);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        list = getSerializable(IntentKey.SIGN_IN_LIST);
        mAdapter.setData(list);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

    }
}
