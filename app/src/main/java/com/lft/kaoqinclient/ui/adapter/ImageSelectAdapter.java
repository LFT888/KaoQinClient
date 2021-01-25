package com.lft.kaoqinclient.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.common.MyAdapter;
import com.lft.kaoqinclient.http.glide.GlideApp;

import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/1/20 18:29
 */
public final class ImageSelectAdapter extends MyAdapter<String> {

    private final List<String> mSelectImages;

    public ImageSelectAdapter(Context context, List<String> images) {
        super(context);
        mSelectImages = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends MyAdapter.ViewHolder {

        private ImageView mImageView;
        private CheckBox mCheckBox;

        private ViewHolder() {
            super(R.layout.item_image_select);
            mImageView = (ImageView) findViewById(R.id.iv_image_select_image);
            mCheckBox = (CheckBox) findViewById(R.id.iv_image_select_check);
        }

        @Override
        public void onBindView(int position) {
            String imagePath = getItem(position);
            GlideApp.with(getContext())
                    .load(imagePath)
                    .into(mImageView);

            mCheckBox.setChecked(mSelectImages.contains(imagePath));
        }
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        return new GridLayoutManager(context, 3);
    }
}
