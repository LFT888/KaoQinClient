package com.lft.kaoqinclient.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyAdapter;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 21:01
 */
public final class StatusAdapter extends MyAdapter<String> {

    public StatusAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends MyAdapter.ViewHolder {

        private TextView mTextView;

        private ViewHolder() {
            super(R.layout.item_status);
            mTextView = (TextView) findViewById(R.id.tv_status_text);
        }

        @Override
        public void onBindView(int position) {
            mTextView.setText(getItem(position));
        }
    }
}
