package com.wjk32.jnews.modules.mainindex;

import android.content.Context;
import androidx.annotation.Nullable;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.Artical;

import java.util.List;

public class QuickNewsAdapter extends BaseQuickAdapter<Artical,BaseViewHolder> {
    Context context;
    public QuickNewsAdapter(int layoutResId,@Nullable List<Artical> data,Context context) {
        super(layoutResId,data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Artical item) {
        helper.setText(R.id.tab_bar_01_item_textview,item.getTitle());
        Picasso.with(mContext).load(item.getUrlToImage()).into((ImageView) helper.getView(R.id.tab_bar_01_item_imageview));
    }


}
