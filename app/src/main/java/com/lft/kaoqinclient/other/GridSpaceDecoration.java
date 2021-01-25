package com.lft.kaoqinclient.other;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/24 23:14
 *
 * 图片选择列表分割线
 */
public final class GridSpaceDecoration extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public GridSpaceDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {}

    @SuppressWarnings("all")
    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int position = recyclerView.getChildAdapterPosition(view);
        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();

        // 每一行的最后一个才留出右边间隙
        if ((position + 1) % spanCount == 0) {
            rect.right = mSpace;
        }

        // 只有第一行才留出顶部间隙
        if (position < spanCount) {
            rect.top = mSpace;
        }

        rect.bottom = mSpace;
        rect.left = mSpace;
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {}
}
