package com.lft.kaoqinclient.ui.fragment;


import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageView;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.glide.GlideApp;
import com.lft.kaoqinclient.ui.activity.HomeActivity;
import com.lft.widget.view.CountdownView;
import com.lft.widget.view.SwitchButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class FindFragment extends MyFragment<HomeActivity>
        implements SwitchButton.OnCheckedChangeListener {

    private ImageView mCircleView;
    private SwitchButton mSwitchButton;
    private CountdownView mCountdownView;

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        mCircleView = findViewById(R.id.iv_find_circle);
        mSwitchButton = findViewById(R.id.sb_find_switch);
        mCountdownView = findViewById(R.id.cv_find_countdown);
        setOnClickListener(mCountdownView);

        mSwitchButton.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        GlideApp.with(this)
                .load(R.drawable.example_bg)
                .circleCrop()
                .into(mCircleView);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mCountdownView) {
            toast(R.string.common_code_send_hint);
            mCountdownView.start();
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    /**
     * {@link SwitchButton.OnCheckedChangeListener}
     */

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }
}