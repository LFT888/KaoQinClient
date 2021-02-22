package com.lft.kaoqinclient.ui.fragment;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 23:16
 */
public class AddStudentFragment extends MyFragment<MyActivity>  {

    public static AddStudentFragment newInstance(){
        return new AddStudentFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_student;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
