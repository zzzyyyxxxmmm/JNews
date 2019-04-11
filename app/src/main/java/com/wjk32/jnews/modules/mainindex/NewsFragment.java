package com.wjk32.jnews.modules.mainindex;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.modules.mainindex.NewsDetail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsFragment extends Fragment implements NewsContract.View{


    private List<Integer> imageIdList;
    private ArrayList<String> imageinfo=new ArrayList<String>();
    @BindView(R.id.tab_bar_01_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    
    Boolean isAutoPlay;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager mLayoutManager;
    Parcelable parcelable;
    private NewsContract.Presenter mPresenter;

    public NewsFragment() {
        // Requires empty public constructor
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("OnAttach");
    }


    @Override
    public void onStart(){
        super.onStart();
        isAutoPlay=true;
        mPresenter.start(false);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_bar_01,null);
        ButterKnife.bind(this,view);


        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.tab_bar_01_newsheader, (ViewGroup) recyclerView.getParent(), false);



        if(linearLayoutManager==null){
            linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        }


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start(true);
        }
        });
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void showNews(List<Artical> articalList,boolean refresh) {
        QuickNewsAdapter  quickNewsAdapter=new QuickNewsAdapter(R.layout.tab_bar_01_item,articalList,getContext());
        quickNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetailActivity.start(getContext(),view.findViewById(R.id.tab_bar_01_item_imageview),articalList.get(position));

            }
        });
        
        if(mLayoutManager==null){

            mLayoutManager = new LinearLayoutManager(getContext());
        }
        if(parcelable!=null&&!refresh){

            mLayoutManager.onRestoreInstanceState(parcelable);
        }
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(quickNewsAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showHeader(int position) {;
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mLayoutManager!=null)    {
            parcelable=mLayoutManager.onSaveInstanceState();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.undispose();
    }
}
