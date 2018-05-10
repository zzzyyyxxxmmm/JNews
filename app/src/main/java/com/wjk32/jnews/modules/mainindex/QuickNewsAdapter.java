package com.wjk32.jnews.modules.mainindex;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.Artical;

import java.util.List;

public class QuickNewsAdapter extends BaseQuickAdapter<Artical,BaseViewHolder> {
    public QuickNewsAdapter(int layoutResId,@Nullable List<Artical> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Artical item) {
        helper.setText(R.id.tab_bar_01_item_textview,item.getTitle());
        Picasso.with(mContext).load(item.getUrlToImage()).into((ImageView) helper.getView(R.id.tab_bar_01_item_imageview));
    }
}
