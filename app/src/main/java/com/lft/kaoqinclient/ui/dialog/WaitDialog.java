package com.lft.kaoqinclient.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.lft.base.BaseDialog;
import com.lft.kaoqinclient.R;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 19:59
 */
public final class WaitDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        private final TextView mMessageView;

        public Builder(Context context) {
            super(context);
            setContentView(R.layout.dialog_wait);
            setAnimStyle(BaseDialog.ANIM_TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);

            mMessageView = findViewById(R.id.tv_wait_message);
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }

        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }
    }
}
