package com.wjk32.jnews.modules.mainindex.NewsDetail;

import com.wjk32.jnews.base.BasePresenter;
import com.wjk32.jnews.base.BaseView;
import com.wjk32.jnews.entity.Artical;

import java.util.List;

public class NewsDetailContract {
    interface View extends BaseView<Presenter> {

        void showNews(List<Artical> articalList);
        void showHeader(int position);

    }


    interface Presenter extends BasePresenter {
    }
}
