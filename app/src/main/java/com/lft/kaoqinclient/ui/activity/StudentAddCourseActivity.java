package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.model.ResponseClass;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.helper.InputTextHelper;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.FindCourseByIdApi;
import com.lft.kaoqinclient.http.request.StudentAddCourseApi;
import com.lft.kaoqinclient.http.response.FindCourseBean;
import com.lft.kaoqinclient.ui.dialog.MessageDialog;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/10 22:34
 */
public final class StudentAddCourseActivity extends MyActivity {

    private EditText mCourseId;
    private Button mFindCourse;
    private String studentUid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_add_course;
    }

    @Override
    protected void initView() {
        mCourseId = findViewById(R.id.et_add_course_id);
        mFindCourse = findViewById(R.id.btn_find_course);

        setOnClickListener(mFindCourse);

        InputTextHelper.with(this)
                .addView(mCourseId)
                .setMain(mFindCourse)
                .build();
    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        studentUid = sharedPreferences.getString("user_uid", null);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mFindCourse){

            EasyHttp.get(this)
                    .api(new FindCourseByIdApi()
                            .setCourseId(mCourseId.getText().toString()))
                    .request(new HttpCallback<HttpData<FindCourseBean>>(this) {
                        @Override
                        public void onSucceed(HttpData<FindCourseBean> data) {
                            findCourse(data.getData());
                        }

                    });

        }

    }

    private void addCourse(){
        EasyHttp.post(this)
                .api(new StudentAddCourseApi()
                        .setCourseId(mCourseId.getText().toString())
                        .setStudentUId(studentUid))
                .request(new HttpCallback<HttpData<Void>>(this) {
                    @Override
                    public void onSucceed(HttpData<Void> data) {
                        toast("提交成功");
                    }
                });
    }

    private void findCourse(FindCourseBean course){
        if (course != null){
            new MessageDialog.Builder(this)
                    .setTitle("查找到课程")
                    .setMessage(course.getCourseName() + '\n' + course.getTeacherName() + "老师")
                    .setConfirm(getString(R.string.student_add_course_join))
                    .setCancel(getString(R.string.common_cancel))
                    .setListener(dialog -> addCourse())
                    .show();
        }
        else {
            toast("查找失败");
        }

    }
}
