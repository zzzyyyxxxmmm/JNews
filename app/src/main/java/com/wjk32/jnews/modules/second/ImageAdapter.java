package com.wjk32.jnews.modules.second;

import android.content.Context;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.ImageEntity;

import java.util.List;

public class ImageAdapter extends BaseQuickAdapter<ImageEntity.HitsBean,BaseViewHolder> {

    Context context;
    public ImageAdapter(int layoutResId, @Nullable List<ImageEntity.HitsBean> data, Context context) {
        super(layoutResId,data);
        this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, ImageEntity.HitsBean item) {

        Picasso.with(mContext).load(item.getPreviewURL()).into((ImageView) helper.getView(R.id.tab_bar_02_item_imageview));
    }
}
