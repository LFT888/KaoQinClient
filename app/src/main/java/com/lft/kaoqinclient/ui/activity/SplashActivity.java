package com.lft.kaoqinclient.ui.activity;

import androidx.annotation.NonNull;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.UserInfoApi;
import com.lft.kaoqinclient.http.response.UserInfoBean;
import com.lft.kaoqinclient.other.AppConfig;

public final class SplashActivity extends MyActivity {

    private LottieAnimationView mLottieView;
    private View mDebugView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mLottieView = findViewById(R.id.iv_splash_lottie);
        mDebugView = findViewById(R.id.iv_splash_debug);

    }

    @Override
    protected void initData() {
        if (AppConfig.isDebug()) {
            mDebugView.setVisibility(View.VISIBLE);
        } else {
            mDebugView.setVisibility(View.INVISIBLE);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("Authorization", null);
        if (token != null) {
            EasyConfig.getInstance().addHeader("Authorization", token);
            // 设置动画监听
            mLottieView.addAnimatorListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    startActivity(HomeActivity.class);
                    finish();
                }
            });
            EasyHttp.get(this)
                    .api(new UserInfoApi())
                    .request(new HttpCallback<HttpData<UserInfoBean>>(this) {
                        @Override
                        public void onSucceed(HttpData<UserInfoBean> data) {
                            UserInfoBean user = data.getData();
                            SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                            sp.edit()
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
        else {
            mLottieView.addAnimatorListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    startActivity(LoginActivity.class);
                    finish();
                }
            });
        }

    }

    @NonNull
    @Override
    protected ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig()
                // 隐藏状态栏和导航栏
                .hideBar(BarHide.FLAG_HIDE_BAR);
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
        //super.onBackPressed();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

    @Override
    protected void onDestroy() {
        mLottieView.removeAllAnimatorListeners();
        super.onDestroy();
    }
}