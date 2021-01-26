package com.lft.kaoqinclient.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.fragment.app.Fragment;


import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.ui.activity.CopyActivity;
import com.lft.kaoqinclient.ui.activity.LoginActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends MyFragment<CopyActivity> {

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.btn_me_dialog, R.id.btn_me_hint, R.id.btn_me_login, R.id.btn_me_register, R.id.btn_me_forget,
                R.id.btn_me_reset, R.id.btn_me_change, R.id.btn_me_personal, R.id.btn_message_setting, R.id.btn_me_about,
                R.id.btn_me_guide, R.id.btn_me_browser, R.id.btn_me_image_select, R.id.btn_me_image_preview,
                R.id.btn_me_video_select, R.id.btn_me_video_play, R.id.btn_me_crash);
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_me_login:
                startActivity(LoginActivity.class);
                break;
            case R.id.btn_me_crash:
                // 关闭 Bugly 异常捕捉
                CrashReport.closeBugly();
                throw new IllegalStateException("are you ok?");
            default:
                break;
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}