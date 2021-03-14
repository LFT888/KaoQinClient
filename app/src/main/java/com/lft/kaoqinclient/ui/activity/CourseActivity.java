package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.toast.ToastUtils;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.DebugLog;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.CourseHaveCodeApi;
import com.lft.kaoqinclient.http.request.StudentSignApi;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.other.IntentKey;
import com.lft.widget.layout.SettingBar;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/3 22:40
 */
public final class CourseActivity extends MyActivity {

    private Button mSignView;
    private EditText mSignCode;
    private SettingBar mCourseName;
    private SettingBar mAttendanceRate;
    private SettingBar mLeaveNum;
    private SettingBar mLateNum;

    private CourseBean course;


    @DebugLog
    public static void start(Context context, CourseBean course) {
        Intent intent = new Intent(context, CourseActivity.class);
        intent.putExtra(IntentKey.COURSE, course);
        intent.putExtra(IntentKey.COURSE_ID, course.getCourseId());
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void initView() {
        mSignView = findViewById(R.id.btn_student_sign_commit);
        mSignCode = findViewById(R.id.et_student_sign_code);
        mAttendanceRate = findViewById(R.id.sb_attendance_rate);
        mCourseName = findViewById(R.id.sb_course_name);
        mLateNum = findViewById(R.id.sb_late_number);
        mLeaveNum = findViewById(R.id.sb_leave_number);

        EasyHttp.get(this).api(new CourseHaveCodeApi()
                .setCourseId(getInt(IntentKey.COURSE_ID)))
                .request(new HttpCallback<HttpData<Boolean>>(this){

                    @Override
                    public void onSucceed(HttpData<Boolean> data) {
                        if (!data.getData()){
                            mSignCode.setVisibility(View.INVISIBLE);
                            mSignView.setText(getString(R.string.sign_no_code));
                            mSignView.setEnabled(false);
                        }
                    }

                });

        setOnClickListener(mSignView);

        InputTextHelper.with(this)
                .addView(mSignCode)
                .setMain(mSignView)
                .build();
    }

    @Override
    protected void initData() {
        course = getSerializable(IntentKey.COURSE);

        mCourseName.setRightText(course.getCourseName());
        mAttendanceRate.setRightText(String.format("%.2f", course.getAttendanceRate()));
        mLateNum.setRightText("" + course.getLateNumber());
        mLeaveNum.setRightText(""+ course.getLeaveNumber());
    }

    @SingleClick
    @Override
    public void onClick(View v) {

        if (v == mSignView) {

            if (mSignCode.getText().toString().length() != getResources().getInteger(R.integer.sign_code_length)) {
                ToastUtils.show(R.string.sign_fail);
                return;
            }

            // 签到
            EasyHttp.get(this)
                    .api(new StudentSignApi()
                            .setCourseId(course.getCourseId())
                            .setCode( mSignCode.getText().toString() ) )
                    .request(new HttpCallback<HttpData<Boolean>>(this) {

                        @Override
                        public void onSucceed(HttpData<Boolean> data) {
                            if (data.getData()){
                                mSignCode.setVisibility(View.INVISIBLE);
                                mSignView.setText(getString(R.string.signed));
                                mSignView.setEnabled(false);

                                toast(getString(R.string.sign_success));
                            }
                            else {
                                toast(getString(R.string.sign_fail));
                            }
                        }

                    });
        }

    }
}
