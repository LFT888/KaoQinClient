package com.lft.kaoqinclient.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyAdapter;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.http.response.StudentInfoBean;
import com.lft.widget.layout.SettingBar;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 14:34
 */
public class AddStudentAdapter extends MyAdapter<StudentInfoBean> {

    public AddStudentAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public AddStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddStudentAdapter.ViewHolder();
    }

    private final class ViewHolder extends MyAdapter.ViewHolder {

        private TextView mStudentId;
        private TextView mStudentName;
        private TextView mStudentClass;
        private View mDeleteStudent;

        private ViewHolder() {
            super(R.layout.item_add_student);
            mStudentId = (TextView) findViewById(R.id.tv_add_student_id);
            mStudentName = (TextView) findViewById(R.id.tv_add_student_name);
            mStudentClass = (TextView) findViewById(R.id.tv_add_student_class);
            mDeleteStudent = findViewById(R.id.iv_add_student_delete);
        }

        @Override
        public void onBindView(int position) {
            mStudentId.setText(getItem(position).getUid());
            mStudentName.setText(getItem(position).getName());
            mStudentClass.setText(getItem(position).getClassName());
        }
    }

}
