package com.lft.kaoqinclient.helper;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/18 20:18
 *
 * 软键盘管理
 */
public final class KeyboardUtils {

    /**
     * 显示软键盘
     *
     * @param view      依附的View
     */
    public static void showKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view      依附的View
     */
    public static void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 切换软键盘
     *
     * @param view      依附的View
     */
    public static void toggleSoftInput(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, 0);
        }
    }
}
