package com.lft.kaoqinclient.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.toast.ToastUtils;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.GetCodeApi;
import com.lft.kaoqinclient.http.request.VerifyCodeApi;
import com.lft.widget.view.CountdownView;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/24 16:24
 */
public final class PasswordForgetActivity extends MyActivity {

    private EditText mEmailView;
    private EditText mCodeView;
    private EditText mUsernameView;
    private CountdownView mCountdownView;
    private Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    protected void initView() {
        mEmailView = findViewById(R.id.et_password_forget_email);
        mCodeView = findViewById(R.id.et_password_forget_code);
        mUsernameView = findViewById(R.id.et_password_forget_id);
        mCountdownView = findViewById(R.id.cv_password_forget_countdown);
        mCommitView = findViewById(R.id.btn_password_forget_commit);
        setOnClickListener(mCountdownView, mCommitView);

        InputTextHelper.with(this)
                .addView(mUsernameView)
                .addView(mEmailView)
                .addView(mCodeView)
                .setMain(mCommitView)
                .build();
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mCountdownView) {

            // 获取验证码
            EasyHttp.post(this)
                    .api(new GetCodeApi()
                            .setEmail(mEmailView.getText().toString()))
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            toast(R.string.common_code_send_hint);
                            mCountdownView.start();
                        }
                    });

        } else if (v == mCommitView) {

            if (mCodeView.getText().toString().length() != getResources().getInteger(R.integer.sms_code_length)) {
                ToastUtils.show(R.string.common_code_error_hint);
                return;
            }

            // 验证码校验
            EasyHttp.post(this)
                    .api(new VerifyCodeApi()
                            .setEmail(mEmailView.getText().toString())
                            .setCode(mCodeView.getText().toString()))
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            PasswordResetActivity.start(getActivity(), mEmailView.getText().toString(), mCodeView.getText().toString(), mUsernameView.getText().toString());
                            finish();
                        }


                    });
        }
    }
}
