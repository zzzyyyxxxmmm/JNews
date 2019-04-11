package com.wjk32.jnews.modules.mainindex;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.Artical;

import java.util.List;

public class NewsHeaderAdapter extends BaseQuickAdapter<Artical,BaseViewHolder> {
    public NewsHeaderAdapter(int layoutResId, @Nullable List<Artical> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Artical item) {
        helper.setText(R.id.tv_top_title,item.getTitle());
        Picasso.with(mContext).load(item.getUrlToImage()).into((ImageView) helper.getView(R.id.iv_top_image));
    }
}
