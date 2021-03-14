package com.lft.kaoqinclient.ui.fragment;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.glide.GlideApp;
import com.lft.kaoqinclient.ui.activity.HomeActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class MessageFragment extends MyFragment<HomeActivity> {


    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}