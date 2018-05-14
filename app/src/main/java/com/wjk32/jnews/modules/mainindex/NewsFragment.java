package com.wjk32.jnews.modules.mainindex;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.LoopRecyclerview.LoopRecyclerViewPager;
import com.chad.library.adapter.base.LoopRecyclerview.RecyclerViewPager;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.wjk32.jnews.constants.NewsC;
import com.wjk32.jnews.R;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.entity.News;
import com.wjk32.jnews.modules.Constants;
import com.wjk32.jnews.modules.mainindex.NewsDetail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v4.util.Preconditions.checkNotNull;
import static com.wjk32.jnews.constants.NewsC.APIKEY;
import static com.wjk32.jnews.constants.NewsC.SOURCES;

public class NewsFragment extends Fragment implements NewsContract.View{


    private List<Integer> imageIdList;
    private ArrayList<String> imageinfo=new ArrayList<String>();
    @BindView(R.id.tab_bar_01_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    LoopRecyclerViewPager vpTop;

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
        vpTop = (LoopRecyclerViewPager) view1.findViewById(R.id.vp_top);


        if(linearLayoutManager==null){
            linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        }
        vpTop.setLayoutManager(linearLayoutManager);


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
        if(vpTop.getParent()!=null)
            ((ViewGroup)vpTop.getParent()).removeView(vpTop); // <- fix
        vpTop.setAdapter(new NewsHeaderAdapter(R.layout.tab_bar_01_newsheader_item,articalList));
        quickNewsAdapter.addHeaderView(vpTop);
        if(mLayoutManager==null)
            mLayoutManager = new LinearLayoutManager(getContext());
        if(parcelable!=null&&!refresh)
            mLayoutManager.onRestoreInstanceState(parcelable);
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
        vpTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        isAutoPlay = false;
                        mPresenter.changeNewsheader(isAutoPlay,vpTop.getActualCurrentPosition());
                        break;
                    case MotionEvent.ACTION_UP:
                        isAutoPlay = true;
                        mPresenter.changeNewsheader(isAutoPlay,vpTop.getActualCurrentPosition());
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void showHeader(int position) {
        vpTop.smoothScrollToPosition(position);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        parcelable=mLayoutManager.onSaveInstanceState();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.undispose();
    }
}
