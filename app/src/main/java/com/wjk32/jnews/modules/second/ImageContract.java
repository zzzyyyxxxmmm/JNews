package com.wjk32.jnews.modules.second;

import com.wjk32.jnews.base.BasePresenter;
import com.wjk32.jnews.base.BaseView;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.entity.ImageEntity;

import java.util.List;

public class ImageContract {
    interface View extends BaseView<Presenter>{

        void showImage(List<ImageEntity.HitsBean> imageList, boolean refresh);

    }


    interface Presenter extends BasePresenter{
        void loadImage(boolean refresh);
        void undispose();
    }
}
