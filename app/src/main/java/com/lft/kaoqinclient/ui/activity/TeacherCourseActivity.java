package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.base.BaseFragmentAdapter;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.CourseSignedApi;
import com.lft.kaoqinclient.http.request.SignInStartApi;
import com.lft.kaoqinclient.http.response.SignInStartBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.other.IntentKey;
import com.lft.kaoqinclient.ui.fragment.TeacherSignFragment;
import com.lft.kaoqinclient.ui.fragment.TeacherSignInLogFragment;

/**
 * author LFT
 *
 * @date 2021/3/5 20:05
 */
public class TeacherCourseActivity extends MyActivity implements AdapterView.OnItemSelectedListener {

    private TeacherCourseBean course = null;
    private String courseTime;
    private String courseCode;
    private int time;

    private Button mSignInView;
    private Spinner mTimeSpinner;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    public static void start(Context context, TeacherCourseBean course) {
        Intent intent = new Intent(context, TeacherCourseActivity.class);
        intent.putExtra(IntentKey.COURSE_ID, course.getCourseId());
        intent.putExtra(IntentKey.COURSE_SID, course.getCourseSid());
        intent.putExtra(IntentKey.COURSE_NAME, course.getCourseName());
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_course;
    }

    @Override
    protected void initView() {
        mSignInView = findViewById(R.id.btn_sign_commit);
        mTimeSpinner = findViewById(R.id.sp_time);
        mTabLayout = findViewById(R.id.tl_teacher_course_tab);
        mViewPager = findViewById(R.id.vp_teacher_course_pager);

        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(TeacherSignInLogFragment.newInstance(getInt(IntentKey.COURSE_ID)), "签到日志");
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTimeSpinner.setOnItemSelectedListener(this);
        setOnClickListener(mSignInView);



        EasyHttp.get(this).api(new CourseSignedApi()
                .setCourseId(getInt(IntentKey.COURSE_ID)))
                .request(new HttpCallback<HttpData<SignInStartBean>>(this){

                    @Override
                    public void onSucceed(HttpData<SignInStartBean> data) {
                        if (data.getData() != null && data.getData().getCourseCode() != null && !data.getData().getCourseCode().equals("")){
                            mSignInView.setText("签到码："+ data.getData().getCourseCode());
                            mSignInView.setEnabled(false);
                            mPagerAdapter.addFragment(TeacherSignFragment.newInstance(course, data.getData().getCourseTime()), "签到表");
                        }
                    }
                });

    }

    @Override
    protected void initData() {
        course = new TeacherCourseBean();
        course.setCourseId(getInt(IntentKey.COURSE_ID));
        course.setCourseSid(getString(IntentKey.COURSE_SID));
        course.setCourseSid(getString(IntentKey.COURSE_NAME));
        time = 5;

    }

    @Override
    public void onClick(View v){
        if (v == mSignInView){

//            if (true){
//                mSignInView.setText("正在签到");
//                mSignInView.setEnabled(false);
//                mPagerAdapter.addFragment(TeacherSignFragment.newInstance(course, "2021030710"), "签到表");
//                return;
//            }

            EasyHttp.get(this)
                .api(new SignInStartApi()
                    .setCourseId(course.getCourseId())
                    .setTime(time))
                .request(new HttpCallback<HttpData<SignInStartBean>>(this){
                    @Override
                    public void onSucceed(HttpData<SignInStartBean> data) {
                        courseTime = data.getData().getCourseTime();
                        courseCode = data.getData().getCourseCode();
                        if (courseCode == null || courseCode.equals("")){
                            toast("服务器异常");
                        }
                        else {
                            mSignInView.setText("签到码："+ courseCode);
                            mSignInView.setEnabled(false);
                            mPagerAdapter.addFragment(TeacherSignFragment.newInstance(course, courseTime), "签到表");
                        }
                    }
                });

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] languages = getResources().getStringArray(R.array.expiration);
        try {
            time = Integer.parseInt(languages[position].substring(0, languages[position].length()-2));
        }catch (Exception e){
            time = 5;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
