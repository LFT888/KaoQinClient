package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.DebugLog;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.PasswordApi;
import com.lft.kaoqinclient.other.IntentKey;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/2 21:27
 */
public final class PasswordResetActivity extends MyActivity {

    @DebugLog
    public static void start(Context context, String email, String code, String username) {
        Intent intent = new Intent(context, PasswordResetActivity.class);
        intent.putExtra(IntentKey.EMAIL, email);
        intent.putExtra(IntentKey.CODE, code);
        intent.putExtra(IntentKey.USER_ID, username);
        context.startActivity(intent);
    }

    private EditText mPasswordView1;
    private EditText mPasswordView2;
    private Button mCommitView;

    /** email */
    private String mEmail;
    /** 验证码 */
    private String mVerifyCode;
    private String username;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_reset;
    }

    @Override
    protected void initView() {
        mPasswordView1 = findViewById(R.id.et_password_reset_password1);
        mPasswordView2 = findViewById(R.id.et_password_reset_password2);
        mCommitView = findViewById(R.id.btn_password_reset_commit);
        setOnClickListener(mCommitView);

        InputTextHelper.with(this)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .build();
    }

    @Override
    protected void initData() {
        mEmail = getString(IntentKey.EMAIL);
        username = getString(IntentKey.USER_ID);
        if (username == null){
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            username = sharedPreferences.getString("user_uid", null);//(key,若无数据需要赋的值)
        }
        mVerifyCode = getString(IntentKey.CODE);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mCommitView) {

            if (!mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString())) {
                toast(R.string.common_password_input_unlike);
                return;
            }


            // 重置密码
            EasyHttp.post(this)
                    .api(new PasswordApi()
                            .setUsername(username)
                            .setPassword(mPasswordView1.getText().toString()))
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            toast(R.string.password_reset_success);
                            finish();
                        }
                    });

        }
    }
}
