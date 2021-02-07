package com.lft.kaoqinclient.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;

import androidx.fragment.app.Fragment;


import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.LoginOutApi;
import com.lft.kaoqinclient.http.request.PasswordApi;
import com.lft.kaoqinclient.http.request.UserInfoApi;
import com.lft.kaoqinclient.http.response.UserInfoBean;
import com.lft.kaoqinclient.ui.activity.CopyActivity;
import com.lft.kaoqinclient.ui.activity.LoginActivity;
import com.lft.kaoqinclient.ui.activity.PasswordUpdateActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.HashMap;
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

        setOnClickListener(R.id.btn_login_out, R.id.btn_me_login, R.id.btn_update_password);
    }

    @Override
    protected void initData() {

        EasyHttp.get(this)
                .api(new UserInfoApi())
                .request(new HttpCallback<HttpData<UserInfoBean>>(this) {

                    @Override
                    public void onSucceed(HttpData<UserInfoBean> data) {
                        UserInfoBean user = data.getData();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                        sharedPreferences.edit()
                                .putString("user_uid", user.getUid())
                                .putString("user_email", user.getEmail())
                                .putInt("user_id", user.getId())
                                .putString("user_identity",user.getIdentity())
                                .putString("user_sex", user.getSex())
                                .putString("user_class", user.getClassName())
                                .apply();


                    }

                });

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login_out){

            EasyHttp.get(this)
                    .api(new LoginOutApi())
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("Authorization", "");
                            EasyConfig.getInstance().setHeaders(hashMap);
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                            sharedPreferences.edit().remove("Authorization");
                            toast(R.string.login_out_success);
                            finish();
                            startActivity(LoginActivity.class);
                        }

                    });
        }
        else if (v.getId() == R.id.btn_me_login){

            startActivity(LoginActivity.class);

        }
        else if (v.getId() == R.id.btn_update_password){

            startActivity(PasswordUpdateActivity.class);

        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}