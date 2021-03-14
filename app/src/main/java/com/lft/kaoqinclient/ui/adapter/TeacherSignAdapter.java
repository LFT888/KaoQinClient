package com.lft.kaoqinclient.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyAdapter;
import com.lft.kaoqinclient.http.response.SignInListBean;
import com.lft.kaoqinclient.http.response.StudentInfoBean;

/**
 * author LFT
 *
 * @date 2021/3/6 11:39
 */
public class TeacherSignAdapter extends MyAdapter<SignInListBean> {

    public TeacherSignAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public TeacherSignAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherSignAdapter.ViewHolder();
    }

    private final class ViewHolder extends MyAdapter.ViewHolder {

        private TextView mStudentId;
        private TextView mStudentName;
        private TextView mStudentClass;
        private TextView mStudentSignIn;

        private ViewHolder() {
            super(R.layout.item_sign_in_table);
            mStudentId = (TextView) findViewById(R.id.tv_sign_in_id);
            mStudentName = (TextView) findViewById(R.id.tv_sign_in_name);
            mStudentClass = (TextView) findViewById(R.id.tv_sign_in_class);
            mStudentSignIn = (TextView) findViewById(R.id.tv_sign_in_info);
        }

        @Override
        public void onBindView(int position) {
            mStudentId.setText(getItem(position).getUid());
            mStudentName.setText(getItem(position).getName());
            mStudentClass.setText(getItem(position).getClassName());
            mStudentSignIn.setText(getItem(position).getSignInInfo());
        }
    }

}

