package com.lft.kaoqinclient.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.http.glide.GlideApp;
import com.lft.kaoqinclient.ui.dialog.InputDialog;
import com.lft.widget.layout.SettingBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/8 17:56
 */
public final class UserInfoActivity extends MyActivity {

    private ViewGroup mAvatarLayout;
    private ImageView mAvatarView;
    private SettingBar mIDView;
    private SettingBar mNameView;
    private SettingBar mSexView;
    private SettingBar mEmailView;
    private SettingBar mClassView;

    /**
     * 头像地址
     */
    private String mAvatarUrl;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        mAvatarLayout = findViewById(R.id.fl_user_info_avatar);
        mAvatarView = findViewById(R.id.iv_user_info_avatar);
        mIDView = findViewById(R.id.sb_user_info_id);
        mNameView = findViewById(R.id.sb_user_info_name);
        mSexView = findViewById(R.id.sb_user_info_sex);
        mEmailView = findViewById(R.id.sb_user_info_email);
        mClassView = findViewById(R.id.sb_user_info_class);

        setOnClickListener(mAvatarLayout, mAvatarView, mEmailView);
    }

    @Override
    protected void initData() {

        readImage();

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String identity = sharedPreferences.getString("user_identity", "student");
        if (identity.equals("teacher")) {
            mClassView.setVisibility(View.INVISIBLE);
        }
        mIDView.setRightText(sharedPreferences.getString("user_uid", "null"));
        mNameView.setRightText(sharedPreferences.getString("user_name", "null"));
        mSexView.setRightText(sharedPreferences.getString("user_sex", "null"));
        mEmailView.setRightText(sharedPreferences.getString("user_email", "null"));
        mClassView.setRightText(sharedPreferences.getString("user_class", "null"));

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mAvatarLayout) {
            ImageSelectActivity.start(this, data -> {

                mAvatarUrl = data.get(0);
                saveImage(BitmapFactory.decodeFile(mAvatarUrl));

                GlideApp.with(getActivity())
                        .load(mAvatarUrl)
                        .placeholder(R.drawable.avatar_placeholder_ic)
                        .error(R.drawable.avatar_placeholder_ic)
                        .circleCrop()
                        .into(mAvatarView);

            });
        } else if (v == mAvatarView) {
            if (!TextUtils.isEmpty(mAvatarUrl)) {
                // 查看头像
                ImagePreviewActivity.start(getActivity(), mAvatarUrl);
            } else {
                // 选择头像
                onClick(mAvatarLayout);
            }
        } else if (v == mEmailView) {
            new InputDialog.Builder(this)
                    // 标题可以不用填写
                    .setTitle(getString(R.string.personal_data_email_hint))
                    .setContent(mEmailView.getRightText())
                    //.setHint(getString(R.string.personal_data_name_hint))
                    //.setConfirm("确定")
                    // 设置 null 表示不显示取消按钮
                    //.setCancel("取消")
                    // 设置点击按钮后不关闭对话框
                    //.setAutoDismiss(false)
                    .setListener((dialog, content) -> {
                        if (!mEmailView.getRightText().equals(content)) {
                            mEmailView.setRightText(content);
                        }
                    })
                    .show();
        }
    }

    private void saveImage(Bitmap bitmap) {//保存图片
        File filesDir;
        //路径：data/data/包名/files
        filesDir = this.getFilesDir();
        FileOutputStream fos = null;
        try {
            File file = new File(filesDir, "icon.png");
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 从本地的文件中以保存的图片中 获取图片的方法
    private void readImage() {
        File filesDir = getFilesDir();
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