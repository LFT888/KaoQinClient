package com.lft.kaoqinclient.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.LoginApi;
import com.lft.kaoqinclient.http.request.PasswordUpdateApi;
import com.lft.kaoqinclient.http.response.LoginBean;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/4 23:06
 */
public final class PasswordUpdateActivity extends MyActivity {

    private EditText mOldPassword;
    private View mForgetView;
    private Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_passwoed_update;
    }

    @Override
    protected void initView() {
        mOldPassword = findViewById(R.id.et_password_update_old);
        mForgetView = findViewById(R.id.tv_password_forget);
        mCommitView = findViewById(R.id.btn_password_update_next);

        setOnClickListener(mForgetView, mCommitView);

        InputTextHelper.with(this)
                .addView(mOldPassword)
                .setMain(mCommitView)
                .build();


    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mCommitView){

            EasyHttp.post(this)
                    .api(new PasswordUpdateApi()
                            .setPassword(mOldPassword.getText().toString()))
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            startActivity(PasswordResetActivity.class);
                            finish();
                        }

                    });
        }



    }
}
