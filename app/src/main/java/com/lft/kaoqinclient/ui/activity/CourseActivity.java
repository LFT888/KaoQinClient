package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.toast.ToastUtils;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.DebugLog;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.StudentSignApi;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.other.IntentKey;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/3 22:40
 */
public final class CourseActivity extends MyActivity {

    private Button signView;
    private EditText codeView;
    private TextView attendanceRateView;

    private int courseId;
    private String attendanceRate;


    @DebugLog
    public static void start(Context context, CourseBean course) {
        Intent intent = new Intent(context, PasswordResetActivity.class);
        intent.putExtra(IntentKey.CS_ID, course.getId());
        intent.putExtra(IntentKey.COURSE_ID, course.getCourseId());
        intent.putExtra(IntentKey.STUDENT_ID, course.getStudentId());
        intent.putExtra(IntentKey.COURSE_NAME, course.getCourseName());
        intent.putExtra(IntentKey.LEAVE_NUMBER, course.getLeaveNumber());
        intent.putExtra(IntentKey.LATE_NUMBER, course.getLateNumber());
        intent.putExtra(IntentKey.SIGN_NUMBER, course.getSignNumber());
        intent.putExtra(IntentKey.ATTENDANCE_RATE, course.getAttendanceRate());
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void initView() {
        signView = findViewById(R.id.btn_student_sign_commit);
        codeView = findViewById(R.id.et_student_sign_code);
        attendanceRateView = findViewById(R.id.tv_attendance_rate);

        setOnClickListener(signView);
    }

    @Override
    protected void initData() {
        courseId = getInt(IntentKey.COURSE_ID);
        attendanceRate = String.valueOf(getDouble(IntentKey.ATTENDANCE_RATE));
        attendanceRateView.setText(attendanceRate);
    }

    @SingleClick
    @Override
    public void onClick(View v) {

        if (v == signView) {


            if (codeView.getText().toString().length() != getResources().getInteger(R.integer.sign_code_length)) {
                ToastUtils.show(R.string.sign_fail);
                return;
            }

            // 签到
            EasyHttp.get(this)
                    .api(new StudentSignApi()
                            .setCourseId(courseId)
                            .setCode( codeView.getText().toString() ) )
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            toast(R.string.sign_success);
                            finish();
                        }

                        @Override
                        public void onFail(Exception e) {
                            ToastUtils.show(R.string.sign_fail + '\n' + e.getMessage());
                        }

                    });
        }
    }
}
