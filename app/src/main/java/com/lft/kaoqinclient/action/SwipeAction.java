package com.lft.kaoqinclient.action;

public interface SwipeAction {
    /**
     * 是否使用侧滑
     */
    default boolean isSwipeEnable() {
        // 默认开启
        return true;
    }
}
