package com.wjk32.jnews.modules.mainindex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.Artical;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wjk32 on 12/29/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{


    private ArrayList<Artical> mData;
    private List<Integer> imageIdList;
    private Context mContext;

    public NewsAdapter(ArrayList<Artical> data, Context mContext) {
        this.mContext = mContext;
        this.imageIdList=imageIdList;
        this.mData = data;
    }

    public void updateData(ArrayList<Artical> data) {
        this.mData = data;
        this.imageIdList=imageIdList;
        notifyDataSetChanged();
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_bar_01_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        ButterKnife.bind(viewHolder,v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.articaltext.setText(mData.get(position).getTitle());
        URL imageUrl=null;
        try{

            Picasso.with(mContext).load(mData.get(position).getUrlToImage()).into(holder.articaltestimageview);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    /**
     * Holder for News
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tab_bar_01_item_textview)
        TextView articaltext;

        @BindView(R.id.tab_bar_01_item_imageview)
        ImageView articaltestimageview;


        public ViewHolder(View itemView) {
            super(itemView);
        }

    }


}

