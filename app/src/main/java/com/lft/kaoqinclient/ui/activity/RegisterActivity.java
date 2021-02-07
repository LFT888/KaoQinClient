package com.lft.kaoqinclient.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.RegisterApi;
import com.lft.kaoqinclient.http.response.RegisterBean;
import com.lft.kaoqinclient.other.IntentKey;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/27 23:00
 */
public final class RegisterActivity extends MyActivity {

    private EditText mEmailView;
    private EditText mUserIdView;
    private EditText mPasswordView1;
    private EditText mPasswordView2;
    private EditText mNameView;
    private EditText mClassNameView;

    private RadioGroup mSexGroup;
    private RadioGroup mIdentityGroup;
    private RadioButton mManView;
    private RadioButton mWomanView;
    private RadioButton mStudentView;
    private RadioButton mTeacherView;

    private String studentClassName;
    private String sex;
    private int identity;

    private Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mEmailView = findViewById(R.id.et_register_email);
        mUserIdView = findViewById(R.id.et_register_userId);
        mPasswordView1 = findViewById(R.id.et_register_password1);
        mPasswordView2 = findViewById(R.id.et_register_password2);
        mClassNameView = findViewById(R.id.et_register_classname);
        mNameView = findViewById(R.id.et_register_name);
        mSexGroup = findViewById(R.id.rg_register_sex);
        mIdentityGroup = findViewById(R.id.rg_register_identity);
        mManView = findViewById(R.id.rb_register_man);
        mWomanView = findViewById(R.id.rb_register_wom);
        mStudentView = findViewById(R.id.rb_register_student);
        mTeacherView = findViewById(R.id.rb_register_teacher);

        mCommitView = findViewById(R.id.btn_register_commit);
        setOnClickListener(mCommitView);

        mSexGroup.setOnCheckedChangeListener((group, checkedId) -> getSex(checkedId) );

        mIdentityGroup.setOnCheckedChangeListener( (group, checkedId) -> getIdentity(checkedId) );

        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, findViewById(R.id.tv_register_title));

        InputTextHelper.with(this)
                .addView(mEmailView)
                .addView(mUserIdView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .build();
    }

    @Override
    protected void initData() {
        studentClassName = "null";
        sex = "男";
        identity = 0;
    }

    @SingleClick
    @Override
    public void onClick(View v) {

        if (v == mCommitView) {
            if (!mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString())) {
                toast(R.string.common_password_input_unlike);
                return;
            }

            // 提交注册
            EasyHttp.post(this)
                    .api(new RegisterApi()
                            .setUserId(mUserIdView.getText().toString())
                            .setName(mNameView.getText().toString())
                            .setPassword(mPasswordView1.getText().toString())
                            .setSex(sex)
                            .setEmail(mEmailView.getText().toString())
                            .setIdentity(identity)
                            .setClassName(studentClassName))
                    .request(new HttpCallback<HttpData<RegisterBean>>(this) {

                        @Override
                        public void onSucceed(HttpData<RegisterBean> data) {
                            toast(R.string.register_succeed);
                            //数据返回给登录页
                            setResult(RESULT_OK, new Intent()
                                    .putExtra(IntentKey.USER_ID, mUserIdView.getText().toString())
                                    .putExtra(IntentKey.PASSWORD, mPasswordView1.getText().toString()));
                            finish();
                        }
                    });
        }
    }

    private void getSex(int buttonId){
        switch (buttonId){
            case R.id.rb_register_man:
                if (mManView.isChecked()) sex = "男";
                break;
            case R.id.rb_register_wom:
                if (mWomanView.isChecked()) sex = "女";
                break;
        }
    }

    private void getIdentity(int buttonId){
        switch (buttonId){
            case R.id.rb_register_student:
                if (mStudentView.isChecked()){
                    mClassNameView.setVisibility(View.VISIBLE);
                    identity = 0;
                }
                break;
            case R.id.rb_register_teacher:
                if (mTeacherView.isChecked()){
                    mClassNameView.setVisibility(View.INVISIBLE);
                    identity = 1;
                }
        }
    }


    @NonNull
    @Override
    protected ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig()
                // 不要把整个布局顶上去
                .keyboardEnable(true);
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}
