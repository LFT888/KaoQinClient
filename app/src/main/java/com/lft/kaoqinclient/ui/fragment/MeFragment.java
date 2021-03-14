package com.lft.kaoqinclient.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.helper.ActivityStackManager;
import com.lft.kaoqinclient.http.glide.GlideApp;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.LoginOutApi;
import com.lft.kaoqinclient.http.request.UserInfoApi;
import com.lft.kaoqinclient.http.response.UserInfoBean;
import com.lft.kaoqinclient.ui.activity.CopyActivity;
import com.lft.kaoqinclient.ui.activity.LoginActivity;
import com.lft.kaoqinclient.ui.activity.PasswordUpdateActivity;
import com.lft.kaoqinclient.ui.activity.UserInfoActivity;
import com.lft.widget.layout.SettingBar;

import java.io.File;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends MyFragment<CopyActivity> {

    private Button mLoginView;
    private Button mLogoutView;
    private SettingBar mUserInfo;
    private SettingBar mUpdatePassword;
    private SettingBar mNameView;

    private ImageView mAvatarView;

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        mNameView = findViewById(R.id.sb_me_name);
        mAvatarView = findViewById(R.id.iv_me_avatar);

        mLoginView = findViewById(R.id.btn_me_login);
        mLogoutView = findViewById(R.id.btn_login_out);
        mUserInfo = findViewById(R.id.sb_user_info);
        mUpdatePassword = findViewById(R.id.sb_update_password);

        setOnClickListener(mLoginView, mLogoutView, mUserInfo, mUpdatePassword);
    }

    @Override
    protected void initData() {
        readImage();

        EasyHttp.get(this)
                .api(new UserInfoApi())
                .request(new HttpCallback<HttpData<UserInfoBean>>(this) {

                    @Override
                    public void onSucceed(HttpData<UserInfoBean> data) {
                        UserInfoBean user = data.getData();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                        sharedPreferences.edit()
                                .putInt("user_id", user.getId())
                                .putString("user_uid", user.getUid())
                                .putString("user_name", user.getName())
                                .putString("user_email", user.getEmail())
                                .putString("user_identity",user.getIdentity())
                                .putString("user_sex", user.getSex())
                                .putString("user_class", user.getClassName())
                                .apply();
                    }
                });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        mNameView.setLeftText(sharedPreferences.getString("user_uid", "") + '\n'+ sharedPreferences.getString("user_name", ""));

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mLogoutView){

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
                            // 进行内存优化，销毁除登录页之外的所有界面
                            ActivityStackManager.getInstance().finishAllActivities(LoginActivity.class);
                        }

                    });
        }
        else if (v == mLoginView){
            startActivity(LoginActivity.class);
        }
        else if (v == mUpdatePassword){
            startActivity(PasswordUpdateActivity.class);
        }
        else if (v == mUserInfo){
            startActivity(UserInfoActivity.class);
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
    private void readImage() {
        File filesDir = getActivity().getFilesDir();
        File file = new File(filesDir, "icon.png");
        if (file.exists()) {
            //存储--->内存
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            GlideApp.with(getActivity())
                    .load(bitmap)
                    .placeholder(R.drawable.avatar_placeholder_ic)
                    .error(R.drawable.avatar_placeholder_ic)
                    .circleCrop()
                    .into(mAvatarView);
        }
    }
}