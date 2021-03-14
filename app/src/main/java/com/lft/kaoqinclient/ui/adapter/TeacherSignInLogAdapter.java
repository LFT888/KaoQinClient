package com.lft.kaoqinclient.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyAdapter;
import com.lft.kaoqinclient.http.response.SignInListBean;
import com.lft.kaoqinclient.http.response.SignInLogBean;

/**
 * author LFT
 *
 * @date 2021/3/6 21:09
 */
public class TeacherSignInLogAdapter extends MyAdapter<SignInLogBean> {

    public TeacherSignInLogAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public TeacherSignInLogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherSignInLogAdapter.ViewHolder();
    }

    private final class ViewHolder extends MyAdapter.ViewHolder {

        private TextView mSignInLogTime;
        private TextView mSignInLogShould;
        private TextView mSignInLogActual;
        private TextView mSignInLogLeave;
        private TextView mSignInLogLate;

        private ViewHolder() {
            super(R.layout.item_sign_in_log);
            mSignInLogTime = (TextView) findViewById(R.id.tv_sign_in_log_time);
            mSignInLogShould = (TextView) findViewById(R.id.tv_sign_in_log_should);
            mSignInLogActual = (TextView) findViewById(R.id.tv_sign_in_log_actual);
            mSignInLogLeave = (TextView) findViewById(R.id.tv_sign_in_log_leave);
            mSignInLogLate = (TextView) findViewById(R.id.tv_sign_in_log_late);
        }

        @Override
        public void onBindView(int position) {
            mSignInLogTime.setText(getItem(position).getCourseTime());
            mSignInLogShould.setText("" + getItem(position).getShouldAttend());
            mSignInLogActual.setText("" + getItem(position).getActualNumber());
            mSignInLogLeave.setText("" + getItem(position).getLeaveNumber());
            mSignInLogLate.setText("" + getItem(position).getLateNumber());

        }
    }

}

