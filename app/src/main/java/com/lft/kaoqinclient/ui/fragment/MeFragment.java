package com.lft.kaoqinclient.ui.fragment;


import androidx.fragment.app.Fragment;


import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.ui.activity.CopyActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends MyFragment<CopyActivity> {

    public static CopyFragment newInstance() {
        return new CopyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.copy_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}