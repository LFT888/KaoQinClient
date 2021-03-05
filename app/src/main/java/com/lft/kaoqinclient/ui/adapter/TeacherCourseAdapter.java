package com.lft.kaoqinclient.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyAdapter;
import com.lft.kaoqinclient.http.response.CourseBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 13:12
 */
public class TeacherCourseAdapter extends MyAdapter<TeacherCourseBean> {

    public TeacherCourseAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public TeacherCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherCourseAdapter.ViewHolder();
    }

    private final class ViewHolder extends MyAdapter.ViewHolder {

        private TextView mTextView;
        private TextView mTeacherView;
        private View mCardView;

        private ViewHolder() {
            super(R.layout.item_course);
            mTextView = (TextView) findViewById(R.id.tv_course_card_text);
            mTeacherView = (TextView) findViewById(R.id.tv_course_card_teacher);
            mCardView = findViewById(R.id.ca_course_card);
        }

        @Override
        public void onBindView(int position) {
            int[] background = {R.drawable.shape_background_blue, R.drawable.shape_background_golden, R.drawable.shape_background_green, R.drawable.shape_background_red};
            mCardView.setBackground(getDrawable(background[position % 4]));
            mTextView.setText(getItem(position).getCourseName());
            mTeacherView.setText(getItem(position).getCourseSid());
        }
    }

}
